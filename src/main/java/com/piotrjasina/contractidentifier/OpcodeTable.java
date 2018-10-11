package com.piotrjasina.contractidentifier;

import java.util.HashMap;
import java.util.Map;

public class OpcodeTable {

    public static Map<Integer, Opcode> opcodes = new HashMap<Integer, Opcode>() {{
        put(0x00, Opcode.STOP);
        put(0x01, Opcode.ADD);
        put(0x02, Opcode.MUL);
        put(0x03, Opcode.SUB);
        put(0x04, Opcode.DIV);
        put(0x05, Opcode.SDIV);
        put(0x06, Opcode.MOD);
        put(0x07, Opcode.SMOD);
        put(0x08, Opcode.ADDMOD);
        put(0x09, Opcode.MULMOD);
        put(0x0a, Opcode.EXP);
        put(0x0b, Opcode.SIGNEXTEND);
        put(0x10, Opcode.LT);
        put(0x11, Opcode.GT);
        put(0x12, Opcode.SLT);
        put(0x13, Opcode.SGT);
        put(0x14, Opcode.EQ);
        put(0x15, Opcode.ISZERO);
        put(0x16, Opcode.AND);
        put(0x17, Opcode.OR);
        put(0x18, Opcode.XOR);
        put(0x19, Opcode.NOT);
        put(0x1a, Opcode.BYTE);
        put(0x20, Opcode.SHA3);
        put(0x30, Opcode.ADDRESS);
        put(0x31, Opcode.BALANCE);
        put(0x32, Opcode.ORIGIN);
        put(0x33, Opcode.CALLER);
        put(0x34, Opcode.CALLVALUE);
        put(0x35, Opcode.CALLDATALOAD);
        put(0x36, Opcode.CALLDATASIZE);
        put(0x37, Opcode.CALLDATACOPY);
        put(0x38, Opcode.CODESIZE);
        put(0x39, Opcode.CODECOPY);
        put(0x3a, Opcode.GASPRICE);
        put(0x3b, Opcode.EXTCODESIZE);
        put(0x3c, Opcode.EXTCODECOPY);
        put(0x3d, Opcode.RETURNDATASIZE);
        put(0x3e, Opcode.RETURNDATACOPY);
        put(0x40, Opcode.BLOCKHASH);
        put(0x41, Opcode.COINBASE);
        put(0x42, Opcode.TIMESTAMP);
        put(0x43, Opcode.NUMBER);
        put(0x44, Opcode.DIFFICULTY);
        put(0x45, Opcode.GASLIMIT);
        put(0x50, Opcode.POP);
        put(0x51, Opcode.MLOAD);
        put(0x52, Opcode.MSTORE);
        put(0x53, Opcode.MSTORE8);
        put(0x54, Opcode.SLOAD);
        put(0x55, Opcode.SSTORE);
        put(0x56, Opcode.JUMP);
        put(0x57, Opcode.JUMPI);
        put(0x58, Opcode.GETPC);
        put(0x59, Opcode.MSIZE);
        put(0x5a, Opcode.GAS);
        put(0x5b, Opcode.JUMPDEST);
        put(0x60, Opcode.PUSH1);
        put(0x61, Opcode.PUSH2);
        put(0x62, Opcode.PUSH3);
        put(0x63, Opcode.PUSH4);
        put(0x64, Opcode.PUSH5);
        put(0x65, Opcode.PUSH6);
        put(0x66, Opcode.PUSH7);
        put(0x67, Opcode.PUSH8);
        put(0x68, Opcode.PUSH9);
        put(0x69, Opcode.PUSH10);
        put(0x6a, Opcode.PUSH11);
        put(0x6b, Opcode.PUSH12);
        put(0x6c, Opcode.PUSH13);
        put(0x6d, Opcode.PUSH14);
        put(0x6e, Opcode.PUSH15);
        put(0x6f, Opcode.PUSH16);
        put(0x70, Opcode.PUSH17);
        put(0x71, Opcode.PUSH18);
        put(0x72, Opcode.PUSH19);
        put(0x73, Opcode.PUSH20);
        put(0x74, Opcode.PUSH21);
        put(0x75, Opcode.PUSH22);
        put(0x76, Opcode.PUSH23);
        put(0x77, Opcode.PUSH24);
        put(0x78, Opcode.PUSH25);
        put(0x79, Opcode.PUSH26);
        put(0x7a, Opcode.PUSH27);
        put(0x7b, Opcode.PUSH28);
        put(0x7c, Opcode.PUSH29);
        put(0x7d, Opcode.PUSH30);
        put(0x7e, Opcode.PUSH31);
        put(0x7f, Opcode.PUSH32);
        put(0x80, Opcode.DUP1);
        put(0x81, Opcode.DUP2);
        put(0x82, Opcode.DUP3);
        put(0x83, Opcode.DUP4);
        put(0x84, Opcode.DUP5);
        put(0x85, Opcode.DUP6);
        put(0x86, Opcode.DUP7);
        put(0x87, Opcode.DUP8);
        put(0x88, Opcode.DUP9);
        put(0x89, Opcode.DUP10);
        put(0x8a, Opcode.DUP11);
        put(0x8b, Opcode.DUP12);
        put(0x8c, Opcode.DUP13);
        put(0x8d, Opcode.DUP14);
        put(0x8e, Opcode.DUP15);
        put(0x8f, Opcode.DUP16);
        put(0x90, Opcode.SWAP1);
        put(0x91, Opcode.SWAP2);
        put(0x92, Opcode.SWAP3);
        put(0x93, Opcode.SWAP4);
        put(0x94, Opcode.SWAP5);
        put(0x95, Opcode.SWAP6);
        put(0x96, Opcode.SWAP7);
        put(0x97, Opcode.SWAP8);
        put(0x98, Opcode.SWAP9);
        put(0x99, Opcode.SWAP10);
        put(0x9a, Opcode.SWAP11);
        put(0x9b, Opcode.SWAP12);
        put(0x9c, Opcode.SWAP13);
        put(0x9d, Opcode.SWAP14);
        put(0x9e, Opcode.SWAP15);
        put(0x9f, Opcode.SWAP16);
        put(0xa0, Opcode.LOG0);
        put(0xa1, Opcode.LOG1);
        put(0xa2, Opcode.LOG2);
        put(0xa3, Opcode.LOG3);
        put(0xa4, Opcode.LOG4);
        put(0xf0, Opcode.CREATE);
        put(0xf1, Opcode.CALL);
        put(0xf2, Opcode.CALLCODE);
        put(0xf3, Opcode.RETURN);
        put(0xf4, Opcode.DELEGATECALL);
        put(0xfa, Opcode.STATICCALL);
        put(0xfd, Opcode.REVERT);
        put(0xfe, Opcode.INVALID);
        put(0xff, Opcode.SELFDESTRUCT);
    }};

    public static Opcode getByByte(int hexValue){
        Opcode opcode = opcodes.get(getMaskedValue(hexValue));

        if(opcode == null){
            return Opcode.UNKNOWNCODE;
        }
        return opcode;
    }

    private static int getMaskedValue(int hexValue) {
        return hexValue & 0xFF;
    }
}
