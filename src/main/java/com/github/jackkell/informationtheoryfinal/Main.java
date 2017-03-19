package com.github.jackkell.informationtheoryfinal;

import util.BinaryMatrix;

public class Main {
    public static void main(String[] args) {
//        BinaryMatrix g = BinaryMatrix.randomMatrix(10, 30).concatenate(BinaryMatrix.identity(30));
//        System.out.print(g);
//        BinaryMatrix b = BinaryMatrix.randomMatrix(4,2);
//        b.print();
//        b.transpose().print();
//        b.transpose().transpose().print();
        LinearBinaryCode linearBinaryCode = new LinearBinaryCode(7, 4);
        linearBinaryCode.encode("0101");
//        BinaryMatrix g = new BinaryMatrix(4, 7, "1000101010010100100100001011");
//        BinaryMatrix inputM = new BinaryMatrix("0101");
//        inputM.multiply(g).print();
//        BinaryMatrix a = new BinaryMatrix("0101");
//        a.print();
    }
}
