package com.elogstation.api.elogstationapi.checksum;

public class EventData {
    public static String mainCheck(long value) {

        //Extract lower byte
        byte lowByte = (byte)(value & 0xFF);
        String s1 = String.format("%8s", Integer.toBinaryString(lowByte & 0xFF)).replace(' ', '0');
//        System.out.println(s1);

        //Circular shift thrice left
        int lowerInt = (lowByte << 3) | (lowByte >>> 5);
        String s2 = String.format("%8s", Integer.toBinaryString(lowerInt & 0xFF)).replace(' ', '0');
//        System.out.println(s2);

        //XOR with 195
        int xorredValue = lowerInt ^ 195;
        String s3 = String.format("%8s", Integer.toBinaryString(xorredValue & 0xFF)).replace(' ', '0');
//        System.out.println(s3);

        //Get Hex value
        String hexStr = Integer.toHexString(xorredValue & 0xFF);
        return hexStr.toUpperCase();

    }
}
