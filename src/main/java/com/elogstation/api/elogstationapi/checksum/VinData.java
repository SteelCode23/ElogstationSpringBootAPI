package com.elogstation.api.elogstationapi.checksum;

import static com.elogstation.api.elogstationapi.checksum.Common.isDigitOrChar;
import static com.elogstation.api.elogstationapi.checksum.Common.validVinChar;

public class VinData {
    public static char calVinCheckDigit(String vin) {

        vin = vin.toUpperCase();

        //check if vin has 17 characters
        if(vin.length() != 17){
            return '\0';
        }

        //check for alphanumeric values
        for(int i=0;i <vin.length(); i++){
            if(!isDigitOrChar(vin.charAt(i))){
                return '\0';
            }
            if(!validVinChar(vin.charAt(i))){
                return '\0';
            }
        }

        int sum = 0;

        for(int i=0;i <vin.length(); i++){
            if(Character.isDigit(vin.charAt(i))){
                sum += Character.getNumericValue(vin.charAt(i)) * numericMultiplier(i+1);
            } else {
                sum += vinCheckMapping(vin.charAt(i)) * numericMultiplier(i+1);
            }
        }

//        System.out.println("Sum:" + sum);
        sum %= 11;
        System.out.println("Sum:" + sum);
        if(sum < 10){
            return Character.forDigit(sum, 10);
        } else {
            return 'X';
        }

    }

    public static int numericMultiplier(int n) {
        switch (n) {
            case 1: return 8;
            case 2: return 7;
            case 3: return 6;
            case 4: return 5;
            case 5: return 4;
            case 6: return 3;
            case 7: return 2;
            case 8: return 10;
            case 9: return 0;
            case 10: return 9;
            case 11: return 8;
            case 12: return 7;
            case 13: return 6;
            case 14: return 5;
            case 15: return 4;
            case 16: return 3;
            case 17: return 2;

        }
        return 0;

    }

    public static int vinCheckMapping(char c){
        switch (c){
            case 'A': return 1;
            case 'B': return 2;
            case 'C': return 3;
            case 'D': return 4;
            case 'E': return 5;
            case 'F': return 6;
            case 'G': return 7;
            case 'H': return 8;

            case 'J': return 1;
            case 'K': return 2;
            case 'L': return 3;
            case 'M': return 4;
            case 'N': return 5;

            case 'P': return 7;

            case 'R': return 9;

            case 'S': return 2;
            case 'T': return 3;
            case 'U': return 4;
            case 'V': return 5;
            case 'W': return 6;
            case 'X': return 7;
            case 'Y': return 8;
            case 'Z': return 9;
        }

        return 0;
    }
}
