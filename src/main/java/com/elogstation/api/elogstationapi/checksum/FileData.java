package com.elogstation.api.elogstationapi.checksum;

public class FileData {
    public static String mainCheck(long value) {

        //Extract lower byte
        byte lowByte = (byte)(value & 0xFF);
        byte secondLowByte = (byte)((value >> 8) & 0xFF);
        String s1 = String.format("%8s", Integer.toBinaryString(lowByte & 0xFF)).replace(' ', '0');
        String s1b = String.format("%8s", Integer.toBinaryString(secondLowByte & 0xFF)).replace(' ', '0');
//        System.out.println(s1b);
//        System.out.println(s1);

        //Circular shift thrice left
        int lowerInt = ((lowByte & 0xFF) << 3) | ((lowByte & 0xFF) >>> 5);
        int secondLowerInt = ((secondLowByte & 0xFF) << 3) | ((secondLowByte & 0xFF) >>> 5);
        String s2 = String.format("%8s", Integer.toBinaryString(lowerInt & 0xFF)).replace(' ', '0');
        String s2b = String.format("%8s", Integer.toBinaryString(secondLowerInt & 0xFF)).replace(' ', '0');
//        System.out.println(s2b);
//        System.out.println(s2);

        //merge binary to int
        int mergedInt = Integer.parseInt(s2b.concat(s2), 2);

        //XOR with 38556
        int xorredValue = mergedInt ^ 38556;
        String s3 = String.format("%8s", Integer.toBinaryString(xorredValue)).replace(' ', '0');
//        System.out.println(s3);

        //Get Hex value
        String hexStr = Integer.toHexString(xorredValue);
        return hexStr.toUpperCase();

    }
}
