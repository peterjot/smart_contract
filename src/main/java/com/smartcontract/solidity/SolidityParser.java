package com.smartcontract.solidity;

import org.slf4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.lang.String.join;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableMap;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.slf4j.LoggerFactory.getLogger;
import static org.web3j.crypto.Hash.sha3String;

class SolidityParser {

    private static final Logger LOGGER = getLogger(SolidityParser.class);

    private static final String FUNCTION_REGEX = "^\\s*function\\s*([a-zA-Z_][a-zA-Z0-9_]*)\\s*\\(\\s*([^(){}]*)\\s*\\)\\s*(?!.*(internal|private)).*$";
    private static final Pattern FUNCTION_PATTERN = Pattern.compile(FUNCTION_REGEX);
    private static final int FUNCTION_NAME_GROUP_ID = 1;
    private static final int FUNCTION_ARGUMENTS_GROUP_ID = 2;

    private static final String MAPPING_PUBLIC_VARIABLE_REGEX =
            "^\\s*mapping\\s*\\(\\s*([a-zA-Z][a-zA-Z]*)\\s*=>\\s*(.*)\\s*\\)\\s*public\\s*([a-zA-Z_$][a-zA-Z_$0-9]*)\\s*(=.*)?\\s*;+\\s*(//.*)?$";
    private static final Pattern MAPPING_PUBLIC_VARIABLE_PATTERN = Pattern.compile(MAPPING_PUBLIC_VARIABLE_REGEX);
    private static final int MAPPING_VARIABLE_KEY_GROUP_ID = 1;
    private static final int MAPPING_VARIABLE_VALUE_GROUP_ID = 2;
    private static final int MAPPING_VARIABLE_NAME_GROUP_ID = 3;

    private static final String MAPPING_REGEX = "^\\s*mapping\\s*\\(\\s*([a-zA-Z0-9][a-zA-Z0-9]*)\\s*=>\\s*(.*)\\s*\\)\\s*";
    private static final Pattern MAPPING_PATTERN = Pattern.compile(MAPPING_REGEX);
    private static final int MAPPING_KEY_GROUP_ID = 1;
    private static final int MAPPING_VALUE_GROUP_ID = 2;

    private static final String ARRAY_PUBLIC_VARIABLE_REGEX =
            "^\\s*[a-zA-Z0-9][a-zA-Z0-9]*((\\s*\\[\\s*[a-zA-Z0-9]*\\s*]\\s*)+)\\s*public\\s*([a-zA-Z_$][a-zA-Z_$0-9]*)\\s*(=.*)?\\s*;+\\s*(//.*)?$";
    private static final Pattern ARRAY_PUBLIC_VARIABLE_PATTERN = Pattern.compile(ARRAY_PUBLIC_VARIABLE_REGEX);
    private static final int ARRAY_PUBLIC_VALUE_GROUP_ID = 1;
    private static final int ARRAY_PUBLIC_NAME_GROUP_ID = 3;

    private static final String ARRAY_REGEX = "^\\s*[a-zA-Z0-9][a-zA-Z0-9]*((\\s*\\[\\s*[a-zA-Z0-9]*\\s*]\\s*)+)\\s*";
    private static final Pattern ARRAY_PATTERN = Pattern.compile(ARRAY_REGEX);
    private static final int ARRAY_VALUE_GROUP_ID = 1;

    private static final String VARIABLE_TYPES_REGEX = "\\bpublic|\\binternal|\\bprivate|\\bconstant";

    private static final String NORMAL_VARIABLE_REGEX =
            "^\\s*[a-zA-Z0-9][a-zA-Z0-9]*\\s*(" + VARIABLE_TYPES_REGEX + ")*\\s*public\\s*(" + VARIABLE_TYPES_REGEX + ")*\\s*([a-zA-Z_$][a-zA-Z_$0-9]*)\\s*(=.*)?\\s*;+\\s*(//.*)?$";
    private static final Pattern NORMAL_VARIABLE_PATTERN = Pattern.compile(NORMAL_VARIABLE_REGEX);
    private static final int NORMAL_VARIABLE_NAME_GROUP_ID = 3;


    private static final String CANONICAL_ARRAY_KEY_TYPE = "uint256";
    private static final String DELIMITER = ",";

    private static final Map<String, String> CANONICAL_TYPES = unmodifiableMap(new HashMap<String, String>() {{
        put("uint", "uint256");
        put("int", "int256");
        put("byte", "bytes1");
    }});


    Optional<SolidityFunction> findFunctionInLine(String line) {
        List<Optional<SolidityFunction>> functions =
                Stream.of(
                        findFunctionSignature(line),
                        findMappingGetter(line),
                        findArrayGetter(line),
                        findNormalVariableGetter(line)
                ).filter(Optional::isPresent).collect(toList());

        if (functions.size() > 1) {
            throw new IllegalStateException("Expected only one function, but found :" + functions.size());
        } else if (functions.size() == 1) {
            return functions.listIterator().next();
        }
        return Optional.empty();
    }

    private Optional<SolidityFunction> findFunctionSignature(String line) {
        Matcher matcher = FUNCTION_PATTERN.matcher(line);
        if (matcher.find()) {
            LOGGER.info("Found function: {}", line);
            String functionName = matcher.group(FUNCTION_NAME_GROUP_ID);
            String functionArguments = matcher.group(FUNCTION_ARGUMENTS_GROUP_ID);

            String functionSignature = normalizeFunctionSignature(functionName, functionArguments);
            String functionSelector = getFunctionSelector(functionSignature);

            return Optional.of(new SolidityFunction(functionSelector, functionSignature));
        }
        return Optional.empty();
    }

    private Optional<SolidityFunction> findMappingGetter(String line) {
        Matcher mappingVariableMatcher = MAPPING_PUBLIC_VARIABLE_PATTERN.matcher(line);
        if (mappingVariableMatcher.find()) {
            LOGGER.info("Found public mapping variable: {}", line);

            List<String> canonicalMappingKeys = new ArrayList<>();

            String mappingName = mappingVariableMatcher.group(MAPPING_VARIABLE_NAME_GROUP_ID);
            String mappingKey = mappingVariableMatcher.group(MAPPING_VARIABLE_KEY_GROUP_ID);
            String mappingValue = mappingVariableMatcher.group(MAPPING_VARIABLE_VALUE_GROUP_ID);
            String canonicalMappingKey = toCanonicalType(mappingKey);
            canonicalMappingKeys.add(canonicalMappingKey);

            while (true) {
                Matcher mappingMatcher = MAPPING_PATTERN.matcher(mappingValue);
                Matcher arrayMatcher = ARRAY_PATTERN.matcher(mappingValue);

                if (mappingMatcher.find()) {
                    String nextMappingKey = mappingMatcher.group(MAPPING_KEY_GROUP_ID);
                    String canonicalArgument = toCanonicalType(nextMappingKey);
                    canonicalMappingKeys.add(canonicalArgument);
                    mappingValue = mappingMatcher.group(MAPPING_VALUE_GROUP_ID);
                } else if (arrayMatcher.find()) {
                    String arrayValue = arrayMatcher.group(ARRAY_VALUE_GROUP_ID);

                    int dimensionCount = getArrayDimensionCount(arrayValue);
                    if (dimensionCount <= 0) {
                        throw new IllegalStateException("Expected greater than one dimensionCount, but got: " + dimensionCount);
                    }

                    for (int i = 0; i < dimensionCount; i++) {
                        canonicalMappingKeys.add(CANONICAL_ARRAY_KEY_TYPE);
                    }
                    break;
                } else {
                    break;
                }
            }
            LOGGER.info("MappingArgs: {}", canonicalMappingKeys);

            String functionSignature = mappingName + "(" + join(DELIMITER, canonicalMappingKeys) + ")";
            String functionSelector = getFunctionSelector(functionSignature);

            return Optional.of(new SolidityFunction(functionSelector, functionSignature));
        }
        return Optional.empty();
    }

    private Optional<SolidityFunction> findArrayGetter(String line) {
        Matcher matcher = ARRAY_PUBLIC_VARIABLE_PATTERN.matcher(line);
        if (matcher.find()) {
            LOGGER.info("Found public array variable: {}", line);

            String arrayName = matcher.group(ARRAY_PUBLIC_NAME_GROUP_ID);
            String arrayValue = matcher.group(ARRAY_PUBLIC_VALUE_GROUP_ID);

            int dimensionCount = getArrayDimensionCount(arrayValue);
            if (dimensionCount <= 0) {
                throw new IllegalStateException("Expected greater than one dimensionCount, but got: " + dimensionCount);
            }

            List<String> arrayArguments = new ArrayList<>();
            for (int i = 0; i < dimensionCount; i++) {
                arrayArguments.add(CANONICAL_ARRAY_KEY_TYPE);
            }

            String functionSignature = arrayName + "(" + join(DELIMITER, arrayArguments) + ")";
            String functionSelector = getFunctionSelector(functionSignature);

            return Optional.of(new SolidityFunction(functionSelector, functionSignature));
        }
        return Optional.empty();
    }

    private int getArrayDimensionCount(String arrayValue) {
        int dimensionCount = 0;
        for (int i = 0; i < arrayValue.length(); i++) {
            if (arrayValue.charAt(i) == '[') {
                dimensionCount++;
            }
        }
        return dimensionCount;
    }

    private Optional<SolidityFunction> findNormalVariableGetter(String line) {
        Matcher matcher = NORMAL_VARIABLE_PATTERN.matcher(line);
        if (matcher.find()) {
            LOGGER.info("Found public normal variable: {}", line);
            String variableName = matcher.group(NORMAL_VARIABLE_NAME_GROUP_ID);

            String functionSignature = variableName + "()";
            String functionSelector = getFunctionSelector(functionSignature);

            return Optional.of(new SolidityFunction(functionSelector, functionSignature));
        }
        return Optional.empty();
    }

    private String getFunctionSelector(String normalizedFunctionSignature) {
        return sha3String(normalizedFunctionSignature).substring(2, 10);
    }

    private String normalizeFunctionSignature(String functionName, String functionArgumentsString) {
        String[] functionArguments = functionArgumentsString.trim().split("\\s*,\\s*");

        List<String> functionArgumentsList = asList(functionArguments);

        String normalizedArguments =
                functionArgumentsList
                        .stream()
                        .map(s -> toCanonicalType(getFirstWord(s)))
                        .collect(joining(DELIMITER));

        String normalizedFunctionSignature = functionName + "(" + normalizedArguments + ")";
        LOGGER.info(("SolidityFunction signature(normalized): [{}]"), normalizedFunctionSignature);

        return normalizedFunctionSignature;
    }

    private String toCanonicalType(String baseType) {
        String canonicalType = CANONICAL_TYPES.get(baseType);
        if (nonNull(canonicalType)) {
            return canonicalType;
        }
        return baseType;
    }

    private String getFirstWord(String s) {
        return s.replaceAll(" .*", "");
    }
}
