package com.piotrjasina.bytecode;

import java.util.HashMap;
import java.util.Map;

class OpcodeTable {

    private static Map<String, Opcode> opcodes = new HashMap<String, Opcode>() {{
        put("00", Opcode.STOP);
        put("01", Opcode.ADD);
        put("02", Opcode.MUL);
        put("03", Opcode.SUB);
        put("04", Opcode.DIV);
        put("05", Opcode.SDIV);
        put("06", Opcode.MOD);
        put("07", Opcode.SMOD);
        put("08", Opcode.ADDMOD);
        put("09", Opcode.MULMOD);
        put("0a", Opcode.EXP);
        put("0b", Opcode.SIGNEXTEND);
        put("10", Opcode.LT);
        put("11", Opcode.GT);
        put("12", Opcode.SLT);
        put("13", Opcode.SGT);
        put("14", Opcode.EQ);
        put("15", Opcode.ISZERO);
        put("16", Opcode.AND);
        put("17", Opcode.OR);
        put("18", Opcode.XOR);
        put("19", Opcode.NOT);
        put("1a", Opcode.BYTE);
        put("20", Opcode.SHA3);
        put("30", Opcode.ADDRESS);
        put("31", Opcode.BALANCE);
        put("32", Opcode.ORIGIN);
        put("33", Opcode.CALLER);
        put("34", Opcode.CALLVALUE);
        put("35", Opcode.CALLDATALOAD);
        put("36", Opcode.CALLDATASIZE);
        put("37", Opcode.CALLDATACOPY);
        put("38", Opcode.CODESIZE);
        put("39", Opcode.CODECOPY);
        put("3a", Opcode.GASPRICE);
        put("3b", Opcode.EXTCODESIZE);
        put("3c", Opcode.EXTCODECOPY);
        put("3d", Opcode.RETURNDATASIZE);
        put("3e", Opcode.RETURNDATACOPY);
        put("40", Opcode.BLOCKHASH);
        put("41", Opcode.COINBASE);
        put("42", Opcode.TIMESTAMP);
        put("43", Opcode.NUMBER);
        put("44", Opcode.DIFFICULTY);
        put("45", Opcode.GASLIMIT);
        put("50", Opcode.POP);
        put("51", Opcode.MLOAD);
        put("52", Opcode.MSTORE);
        put("53", Opcode.MSTORE8);
        put("54", Opcode.SLOAD);
        put("55", Opcode.SSTORE);
        put("56", Opcode.JUMP);
        put("57", Opcode.JUMPI);
        put("58", Opcode.GETPC);
        put("59", Opcode.MSIZE);
        put("5a", Opcode.GAS);
        put("5b", Opcode.JUMPDEST);
        put("60", Opcode.PUSH1);
        put("61", Opcode.PUSH2);
        put("62", Opcode.PUSH3);
        put("63", Opcode.PUSH4);
        put("64", Opcode.PUSH5);
        put("65", Opcode.PUSH6);
        put("66", Opcode.PUSH7);
        put("67", Opcode.PUSH8);
        put("68", Opcode.PUSH9);
        put("69", Opcode.PUSH10);
        put("6a", Opcode.PUSH11);
        put("6b", Opcode.PUSH12);
        put("6c", Opcode.PUSH13);
        put("6d", Opcode.PUSH14);
        put("6e", Opcode.PUSH15);
        put("6f", Opcode.PUSH16);
        put("70", Opcode.PUSH17);
        put("71", Opcode.PUSH18);
        put("72", Opcode.PUSH19);
        put("73", Opcode.PUSH20);
        put("74", Opcode.PUSH21);
        put("75", Opcode.PUSH22);
        put("76", Opcode.PUSH23);
        put("77", Opcode.PUSH24);
        put("78", Opcode.PUSH25);
        put("79", Opcode.PUSH26);
        put("7a", Opcode.PUSH27);
        put("7b", Opcode.PUSH28);
        put("7c", Opcode.PUSH29);
        put("7d", Opcode.PUSH30);
        put("7e", Opcode.PUSH31);
        put("7f", Opcode.PUSH32);
        put("80", Opcode.DUP1);
        put("81", Opcode.DUP2);
        put("82", Opcode.DUP3);
        put("83", Opcode.DUP4);
        put("84", Opcode.DUP5);
        put("85", Opcode.DUP6);
        put("86", Opcode.DUP7);
        put("87", Opcode.DUP8);
        put("88", Opcode.DUP9);
        put("89", Opcode.DUP10);
        put("8a", Opcode.DUP11);
        put("8b", Opcode.DUP12);
        put("8c", Opcode.DUP13);
        put("8d", Opcode.DUP14);
        put("8e", Opcode.DUP15);
        put("8f", Opcode.DUP16);
        put("90", Opcode.SWAP1);
        put("91", Opcode.SWAP2);
        put("92", Opcode.SWAP3);
        put("93", Opcode.SWAP4);
        put("94", Opcode.SWAP5);
        put("95", Opcode.SWAP6);
        put("96", Opcode.SWAP7);
        put("97", Opcode.SWAP8);
        put("98", Opcode.SWAP9);
        put("99", Opcode.SWAP10);
        put("9a", Opcode.SWAP11);
        put("9b", Opcode.SWAP12);
        put("9c", Opcode.SWAP13);
        put("9d", Opcode.SWAP14);
        put("9e", Opcode.SWAP15);
        put("9f", Opcode.SWAP16);
        put("a0", Opcode.LOG0);
        put("a1", Opcode.LOG1);
        put("a2", Opcode.LOG2);
        put("a3", Opcode.LOG3);
        put("a4", Opcode.LOG4);
        put("f0", Opcode.CREATE);
        put("f1", Opcode.CALL);
        put("f2", Opcode.CALLCODE);
        put("f3", Opcode.RETURN);
        put("f4", Opcode.DELEGATECALL);
        put("fa", Opcode.STATICCALL);
        put("fd", Opcode.REVERT);
        put("fe", Opcode.INVALID);
        put("ff", Opcode.SELFDESTRUCT);
    }};

    static Opcode getOpcodeByHex(String hex) {
        Opcode opcode = opcodes.get(hex);
        if (opcode == null) {
            return Opcode.UNKNOWNCODE;
        }
        return opcode;
    }

}
