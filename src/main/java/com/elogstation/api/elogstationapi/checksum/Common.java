package com.elogstation.api.elogstationapi.checksum;

public class Common {
    public static long getCharacterMapping(String str){
        long value = 0;
        for (int i = 0; i < str.length(); i++) {
            value += getSingleCharacterMapping(str.charAt(i));
//            System.out.print("Char: " + str.charAt(i));
//            System.out.println(",value: " + value);
        }
//        System.out.println("Value: " + value);
        return value;
    }

    public static long getSingleCharacterMapping(char c){
        if(isDigitOrChar(c)){
            return (int) c - 48;
        } else {
            return 0;
        }
    }

    public static boolean isDigitOrChar(char c){
        if((c >= 65 && c <= 90)||
                (c >= 97 && c <= 122) ||
                (c >= 48 && c <= 57)){
            return true;
        } else {
            return false;
        }
    }

    public static boolean validVinChar(char c){
        if(c == 'I' || c == 'O' || c == 'Q' ){
            return false;
        } else {
            return true;
        }
    }

}
