package com.github.jackkell.informationtheoryfinal;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        int n = 20;
        int k = 10;
        int numberOfErrors = 3;

        LinearBinaryCode lbc = new LinearBinaryCode(n, k);
//        String message = "0101000000";
        String message = String.join("", Collections.nCopies(k, "0"));
        String encodedMessage = lbc.encode(message);

        char[] tempEncodedMessage = encodedMessage.toCharArray();
        for (int i = 0; i < numberOfErrors && i < n; i++) {
            tempEncodedMessage[i] = tempEncodedMessage[i] == '0' ? '1' : '0';
        }

        String corruptedEncodeMessage = String.valueOf(tempEncodedMessage);
        String decodedEncodedMessage = lbc.decode(encodedMessage);
        String decodedCorruptedMessage = lbc.decode(corruptedEncodeMessage);

        System.out.println("Message        : " + message);
        System.out.println("Encoded        : " + encodedMessage);
        System.out.println("Corrupt        : " + corruptedEncodeMessage);
        System.out.println("DecodedEncoded : " + decodedEncodedMessage);
        System.out.println("DecodedCorrupt : " + decodedCorruptedMessage);
    }
}
