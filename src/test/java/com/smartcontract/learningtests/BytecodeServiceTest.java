package com.smartcontract.learningtests;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class BytecodeServiceTest {


    @Test
    @Ignore
    public void CountDuplicates() {
        String function1 = "123";
        String function3 = "123";

        List<String> functions = Arrays.asList(function1, function1, function3);

        Map<String, Long> collect = functions.stream().collect(groupingBy(identity(), counting()));
        System.out.println(collect);
    }

}