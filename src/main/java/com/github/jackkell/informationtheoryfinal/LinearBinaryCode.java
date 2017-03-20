package com.github.jackkell.informationtheoryfinal;

import util.BinaryMatrix;

import java.util.*;

public class LinearBinaryCode {
    private int n;
    private int k;
    private BinaryMatrix g;
    private BinaryMatrix h;
    private Map<String, String> syndromeDecoder;

    // WARNING: DO NOT SET n to be anything larger than 20 you will be here a while
    public LinearBinaryCode(int n, int k) {
        this.syndromeDecoder = new HashMap<>();
        this.n = n;
        this.k = k;
        int r = n - k;
        BinaryMatrix a = BinaryMatrix.randomMatrix(k, r);
        this.g = BinaryMatrix.identity(k).concatenate(a);
        this.h = a.transpose().concatenate(BinaryMatrix.identity(r));

        System.out.println("A matrix");
        a.print();
        System.out.println("G matrix");
        g.print();
        System.out.println("H matrix");
        h.print();

        populateSyndromeDecoder();
    }

    private void populateSyndromeDecoder() {
        for (int i = 0; i < Math.pow(2, n); i++) {
            String currentOutputVector = String.format("%" + n + "s", Integer.toBinaryString(i)).replace(" ", "0");
            String currentSyndrome = BinaryMatrix.multiplyVector(h, currentOutputVector);
            if (syndromeDecoder.containsKey(currentSyndrome)) {
                if (weight(syndromeDecoder.get(currentSyndrome)) > weight(currentOutputVector)) {
                    syndromeDecoder.put(currentSyndrome, currentOutputVector);
                }
            } else {
                syndromeDecoder.put(currentSyndrome, currentOutputVector);
            }
        }
        System.out.println(syndromeDecoder);
        System.out.println(syndromeDecoder.size());
    }

    private int weight(String binaryString) {
        int weight = 0;
        for (char character: binaryString.toCharArray()) {
            if (character != '0') {
                weight++;
            }
        }
        return weight;
    }

    public String encode(String rawMessage) {
        BinaryMatrix messageVector = new BinaryMatrix(rawMessage);
        System.out.println("Raw Message");
        messageVector.print();
        System.out.println("G matrix");
        g.print();
        System.out.println("Encoded Message");
        return BinaryMatrix.multiplyVector(rawMessage, g);
    }

    public String decode(String encodedMessage) {
        int encodedValue = Integer.parseInt(encodedMessage, 2);
        String syndrome = BinaryMatrix.multiplyVector(h, encodedMessage);
        int syndromValue = Integer.parseInt(syndrome, 2);
        if (syndromValue == 0) {
            return encodedMessage.substring(0, k);
        } else {
            String v = BinaryMatrix.multiplyVector(encodedMessage, h.transpose());
            int correctedValue = encodedValue - Integer.parseInt(syndromeDecoder.get(v), 2);
            String correctedMessage = String.format("%" + n + "s", Integer.toBinaryString(correctedValue)).replace(' ', '0');
            return correctedMessage.substring(0, k);
        }
    }
}
