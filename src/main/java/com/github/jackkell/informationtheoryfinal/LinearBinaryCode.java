package com.github.jackkell.informationtheoryfinal;

import util.BinaryMatrix;

public class LinearBinaryCode {
    private int n;
    private int k;
    private BinaryMatrix g;
    private BinaryMatrix h;

    public LinearBinaryCode(int n, int k) {
        this.n = n;
        this.k = k;
        int r = n - k;
        BinaryMatrix a = BinaryMatrix.randomMatrix(k, r);
        System.out.println("A matrix");
        a.print();
        this.g = BinaryMatrix.identity(k).concatenate(a);
        this.h = a.transpose().concatenate(BinaryMatrix.identity(r));

        System.out.println("G matrix");
        g.print();
        System.out.println("H matrix");
        h.print();
    }

    public String encode(String rawMessage) {
        BinaryMatrix messageVector = new BinaryMatrix(rawMessage);
        System.out.println("Raw Message");
        messageVector.print();
        System.out.println("G matrix");
        g.print();
        System.out.println("Encoded Message");
        messageVector.multiply(g).print();
        return "I am an encoded message";
    }

    public String decode(String encodedMessage) {
        return "I am the decoded message";
    }
}
