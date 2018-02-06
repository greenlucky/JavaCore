package com.lamdevops.string;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Given n 'n-bit' strings where n can be 10000, generate a new n-bit string which is different from all the others in the input.
 * For ex - if n is 2 and the input strings are 01 and 11, the output can be 00 or 10.
 * If n is 3 and the input strings are 110, 010 and 101, the output can be any one of 000, 001, 011, 100, 111.
 */
public class ConvertIntToBinary {

    public String intToBinary(int n, int numOfBits) {
        String binary = "";
        for (int i = 0; i < numOfBits; i++, n /= 2) {
            switch (n % 2) {
                case 0: binary = "0" + binary; break;
                case 1: binary = "1" + binary; break;
            }
        }
        return binary;
    }

    private void sequenceBinary(int numOfBi) {
        IntStream.range(1, numOfBi + 1).forEach(i -> {
            String seqBi = intToBinary(i, numOfBi);
            System.out.println(seqBi);
        });
    }


    @Test
    public void testIntToBinary2And3() throws Exception {
        String result = intToBinary(2, 3);
        assertEquals("010", result);
    }

    @Test
    public void testIntToBinary4And4() throws Exception {
        String result = intToBinary(4, 4);
        assertEquals("0100", result);
    }

    @Test
    public void testPrintSequenceBinaryVal2() {
       sequenceBinary(2);
    }

    @Test
    public void testPrintSequenceBinaryVal3() {
       sequenceBinary(3);
    }

    @Test
    public void testPrintSeqBinaryExcludeInputString3() {
        sequenceBinary(3, "001, 010");
    }

    @Test
    public void testPrintSeqBinaryExcludeInputString4() {
        sequenceBinary(4, "0010, 0100");
    }

    @Test
    public void testPrintSeqBinaryExcludeInputString5() {
        sequenceBinary(5, "00101, 01000");
    }

    @Test
    public void testPrintSeqBinaryExcludeInputString8() {
        sequenceBinary(8, "00101000, 00001000");
    }

    @Test
    public void testPrintSeqBinaryExcludeInputString10() {
        sequenceBinary(10, "0000101000, 1000001000");
    }

    private void sequenceBinary(int numOfStr, String exStr) {
        IntStream.range(1, (int) Math.pow(2, numOfStr)).forEach(i -> {
            String bina = intToBinary(i, numOfStr);
            //System.out.println(bina);
            if(!exStr.contains(bina)){
                System.out.println(bina);
            }
        });
    }


}
