package com.smartcontract.api;

import com.smartcontract.bytecode.BytecodeService;
import com.smartcontract.solidity.IdentifiedSolidityFileDto;
import com.smartcontract.solidity.SolidityFile;
import com.smartcontract.solidity.SolidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;


@RestController
public class SolidityApiController {

    private final BytecodeService bytecodeService;
    private final SolidityService solidityService;


    @Autowired
    public SolidityApiController(BytecodeService bytecodeService, SolidityService solidityService) {
        requireNonNull(bytecodeService, "Expected not-null bytecodeService");
        requireNonNull(solidityService, "Expected not-null solidityService");
        this.bytecodeService = bytecodeService;
        this.solidityService = solidityService;
    }

    @PostMapping("/api/bytecode")
    public ResponseEntity<List<IdentifiedSolidityFileDto>> findTop10FileHashesByBytecode(
            @RequestBody String bytecode) {
        requireNonNull(bytecode, "Expected not-null bytecode");

        List<IdentifiedSolidityFileDto> implementations = bytecodeService.findTop10FileHashesWithValueOfMatch(bytecode);

        if (implementations.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(implementations);
    }

    @PostMapping("/api/solidityFiles")
    public ResponseEntity<SolidityFile> uploadFile(@RequestBody String sourceCode) throws IOException {
        requireNonNull(sourceCode, "Expected not-null sourceCode");
        return ResponseEntity.ok(solidityService.save(sourceCode));
    }

    @GetMapping(value = "/api/sourceCode/{fileHash}.sol", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getSourceCodeByHash(@PathVariable("fileHash") String fileHash) {
        requireNonNull(fileHash, "Expected not-null fileHash");

        Optional<String> sourceCodeByHash = solidityService.findSourceCodeByHash(fileHash);
        return sourceCodeByHash
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
