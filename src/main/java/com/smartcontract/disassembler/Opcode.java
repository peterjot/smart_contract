package com.smartcontract.disassembler;

import static lombok.Lombok.checkNotNull;


enum Opcode {
    STOP(0, "Halts execution."),
    ADD(0, "Addition operation."),
    MUL(0, "Multiplication operation."),
    SUB(0, "Subtraction operation."),
    DIV(0, "Integer division operation."),
    SDIV(0, "Signed integer division operation (truncated)."),
    MOD(0, "Modulo remainder operation."),
    SMOD(0, "Signed modulo remainder operation."),
    ADDMOD(0, "Modulo addition operation."),
    MULMOD(0, "Modulo multiplication operation."),
    EXP(0, "Exponential operation."),
    SIGNEXTEND(0, "Extend length of two's complement signed integer."),
    LT(0, "Less-than comparision."),
    GT(0, "Greater-than comparision."),
    SLT(0, "Signed less-than comparision."),
    SGT(0, "Signed greater-than comparision."),
    EQ(0, "Equality comparision."),
    ISZERO(0, "Simple not operator."),
    AND(0, "Bitwise AND operation."),
    OR(0, "Bitwise OR operation."),
    XOR(0, "Bitwise XOR operation."),
    NOT(0, "Bitwise NOT operation."),
    BYTE(0, "Retrieve single byte from word."),
    SHA3(0, "Compute Keccak-256 selector."),
    ADDRESS(0, "Get address of currently executing account     ."),
    BALANCE(0, "Get balance of the given account."),
    ORIGIN(0, "Get execution origination address."),
    CALLER(0, "Get caller address."),
    CALLVALUE(0, "Get deposited value by the instruction/transaction responsible for this execution."),
    CALLDATALOAD(0, "Get input data of current environment."),
    CALLDATASIZE(0, "Get size of input data in current environment."),
    CALLDATACOPY(0, "Copy input data in current environment to memory."),
    CODESIZE(0, "Get size of code running in current environment."),
    CODECOPY(0, "Copy code running in current environment to memory."),
    GASPRICE(0, "Get price of gas in current environment."),
    EXTCODESIZE(0, "Get size of an account's code."),
    EXTCODECOPY(0, "Copy an account's code to memory."),
    RETURNDATASIZE(0, "Get size of output data from the previous call from the current environment"),
    RETURNDATACOPY(0, "Copy output data from the previous call to memory"),
    BLOCKHASH(0, "Get the selector of one of the 256 most recent complete blocks."),
    COINBASE(0, "Get the block's beneficiary address."),
    TIMESTAMP(0, "Get the block's timestamp."),
    NUMBER(0, "Get the block's number."),
    DIFFICULTY(0, "Get the block's difficulty."),
    GASLIMIT(0, "Get the block's gas limit."),
    POP(0, "Remove item from stack."),
    MLOAD(0, "Load word from memory."),
    MSTORE(0, "Save word to memory."),
    MSTORE8(0, "Save byte to memory."),
    SLOAD(0, "Load word from storage."),
    SSTORE(0, "Save word to storage."),
    JUMP(0, "Alter the program counter."),
    JUMPI(0, "Conditionally alter the program counter."),
    GETPC(0, "Get the value of the program counter prior to the increment."),
    MSIZE(0, "Get the size of active memory in bytes."),
    GAS(0, "Get the amount of available gas, including the corresponding reduction the amount of available gas."),
    JUMPDEST(0, "Mark a valid destination for jumps."),

    PUSH1(1, "Place 1 byte item on stack."),
    PUSH2(2, "Place 2-byte item on stack."),
    PUSH3(3, "Place 3-byte item on stack."),
    PUSH4(4, "Place 4-byte item on stack."),
    PUSH5(5, "Place 5-byte item on stack."),
    PUSH6(6, "Place 6-byte item on stack."),
    PUSH7(7, "Place 7-byte item on stack."),
    PUSH8(8, "Place 8-byte item on stack."),
    PUSH9(9, "Place 9-byte item on stack."),
    PUSH10(10, "Place 10-byte item on stack."),
    PUSH11(11, "Place 11-byte item on stack."),
    PUSH12(12, "Place 12-byte item on stack."),
    PUSH13(13, "Place 13-byte item on stack."),
    PUSH14(14, "Place 14-byte item on stack."),
    PUSH15(15, "Place 15-byte item on stack."),
    PUSH16(16, "Place 16-byte item on stack."),
    PUSH17(17, "Place 17-byte item on stack."),
    PUSH18(18, "Place 18-byte item on stack."),
    PUSH19(19, "Place 19-byte item on stack."),
    PUSH20(20, "Place 20-byte item on stack."),
    PUSH21(21, "Place 21-byte item on stack."),
    PUSH22(22, "Place 22-byte item on stack."),
    PUSH23(23, "Place 23-byte item on stack."),
    PUSH24(24, "Place 24-byte item on stack."),
    PUSH25(25, "Place 25-byte item on stack."),
    PUSH26(26, "Place 26-byte item on stack."),
    PUSH27(27, "Place 27-byte item on stack."),
    PUSH28(28, "Place 28-byte item on stack."),
    PUSH29(29, "Place 29-byte item on stack."),
    PUSH30(30, "Place 30-byte item on stack."),
    PUSH31(31, "Place 31-byte item on stack."),
    PUSH32(32, "Place 32-byte (full word) item on stack."),

    DUP1(0, "Duplicate 1st stack item."),
    DUP2(0, "Duplicate 2nd stack item."),
    DUP3(0, "Duplicate 3rd stack item."),
    DUP4(0, "Duplicate 4th stack item."),
    DUP5(0, "Duplicate 5th stack item."),
    DUP6(0, "Duplicate 6th stack item."),
    DUP7(0, "Duplicate 7th stack item."),
    DUP8(0, "Duplicate 8th stack item."),
    DUP9(0, "Duplicate 9th stack item."),
    DUP10(0, "Duplicate 10th stack item."),
    DUP11(0, "Duplicate 11th stack item."),
    DUP12(0, "Duplicate 12th stack item."),
    DUP13(0, "Duplicate 13th stack item."),
    DUP14(0, "Duplicate 14th stack item."),
    DUP15(0, "Duplicate 15th stack item."),
    DUP16(0, "Duplicate 16th stack item."),

    SWAP1(0, "Exchange 1st and 2nd stack items."),
    SWAP2(0, "Exchange 1st and 3rd stack items."),
    SWAP3(0, "Exchange 1st and 4th stack items."),
    SWAP4(0, "Exchange 1st and 5th stack items."),
    SWAP5(0, "Exchange 1st and 6th stack items."),
    SWAP6(0, "Exchange 1st and 7th stack items."),
    SWAP7(0, "Exchange 1st and 8th stack items."),
    SWAP8(0, "Exchange 1st and 9th stack items."),
    SWAP9(0, "Exchange 1st and 10th stack items."),
    SWAP10(0, "Exchange 1st and 11th stack items."),
    SWAP11(0, "Exchange 1st and 12th stack items."),
    SWAP12(0, "Exchange 1st and 13th stack items."),
    SWAP13(0, "Exchange 1st and 14th stack items."),
    SWAP14(0, "Exchange 1st and 15th stack items."),
    SWAP15(0, "Exchange 1st and 16th stack items."),
    SWAP16(0, "Exchange 1st and 17th stack items."),

    LOG0(0, "Append log record with no topics."),
    LOG1(0, "Append log record with one topic."),
    LOG2(0, "Append log record with two topics."),
    LOG3(0, "Append log record with three topics."),
    LOG4(0, "Append log record with four topics."),

    CREATE(0, "Create a new account with associated code."),
    CALL(0, "Message-call into an account."),
    CALLCODE(0, "Message-call into this account with alternative account's code."),
    RETURN(0, "Halt execution returning output data."),
    DELEGATECALL(0, "Message-call into this account with an alternative account's code, but persisting into this account with an alternative account's code."),
    STATICCALL(0, "Static message-call into an account."),
    REVERT(0, "Stop execution and revert state changes, without consuming all provided gas and providing a reason."),
    INVALID(0, "Designated invalid instruction"),
    SELFDESTRUCT(0, "Halt execution and register account for later deletion."),
    UNKNOWNCODE(0, "Unknown code");

    Opcode(int operandSize, String description) {
        checkNotNull(description, "Expected not-null description");
        this.operandSize = operandSize;
        this.description = description;
    }

    private final int operandSize;
    private final String description;

    public boolean hasMnemonic(String mnemonic) {
        checkNotNull(mnemonic, "Expected not-null mnemonic");
        return name().equals(mnemonic);
    }

    public int getOperandSize() {
        return operandSize;
    }

    public String getDescription() {
        return description;
    }
}
