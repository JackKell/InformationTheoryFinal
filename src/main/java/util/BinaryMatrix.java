package util;

import java.util.concurrent.ThreadLocalRandom;

public class BinaryMatrix {
    private int rowSize;
    private int columnSize;
    private int[][] elements;

    public BinaryMatrix(String binaryString) {
        this(1, binaryString.length(), binaryString);
    }

    public BinaryMatrix(int rowSize, int columnSize, String binaryString) {
        if (binaryString.length() != rowSize * columnSize) {
            throw new Error("The length of the given binary String must be of length columnSize * rowSize!");
        }
        this.columnSize = columnSize;
        this.rowSize = rowSize;
        this.elements = new int[rowSize][columnSize];
        int rowIndex = 0;
        for (int bitIndex = 0; bitIndex < rowSize * columnSize; bitIndex++) {
            int bit = Integer.parseInt(String.valueOf(binaryString.charAt(bitIndex)));
            int columnIndex = bitIndex % columnSize;
            this.elements[rowIndex][columnIndex] = bit;
            if (columnIndex == columnSize - 1) rowIndex++;
        }
    }

    public BinaryMatrix(int rowSize, int columnSize, int[][] elements) {
        this.columnSize = columnSize;
        this.rowSize = rowSize;
        this.elements = elements;
    }

    public BinaryMatrix(int rowSize, int columnSize) {
        this(rowSize, columnSize, new int[rowSize][columnSize]);
    }

    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        String matrixString = "";
        for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
                matrixString += elements[rowIndex][columnIndex] + " ";
            }
            matrixString += "\n";
        }
        return matrixString;
    }

    public void setElement(int rowIndex, int columnIndex, int value) {
        elements[rowIndex][columnIndex] = value > 0 ? 1 : 0;
    }

    public int getElement(int rowIndex, int columnIndex) {
        return elements[rowIndex][columnIndex];
    }

    public BinaryMatrix add(BinaryMatrix otherBinaryMatrix) {
        if (otherBinaryMatrix.columnSize != columnSize || otherBinaryMatrix.rowSize != rowSize) {
            throw new Error("The given matrix is not equal to the size of the current matrix!");
        }
        int[][] newElements = elements.clone();
        for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
                newElements[rowIndex][columnIndex] += otherBinaryMatrix.elements[rowIndex][columnIndex];
                newElements[rowIndex][columnIndex] %= 2;
            }
        }
        return new BinaryMatrix(rowSize, columnSize, newElements);
    }

    public BinaryMatrix subtract(BinaryMatrix otherBinaryMatrix) {
        if (otherBinaryMatrix.columnSize != columnSize || otherBinaryMatrix.rowSize != rowSize) {
            throw new Error("The given matrix is not equal to the size of the current matrix!");
        }
        int[][] newElements = elements.clone();
        for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
                newElements[rowIndex][columnIndex] -= otherBinaryMatrix.elements[rowIndex][columnIndex];
                newElements[rowIndex][columnIndex] %= 2;
            }
        }
        return new BinaryMatrix(rowSize, columnSize, newElements);
    }

    public BinaryMatrix multiply(BinaryMatrix otherBinaryMatrix) {
        if (columnSize != otherBinaryMatrix.rowSize) {
            throw new Error("The given matrix is not equal to the size of the current matrix!");
        }
        System.out.println(rowSize + "X" + columnSize + " * " + otherBinaryMatrix.rowSize + "X" + otherBinaryMatrix.columnSize);
        int[][] newElements = new int[rowSize][otherBinaryMatrix.columnSize];
        for (int m1RowIndex = 0; m1RowIndex < rowSize; m1RowIndex++) {
            for (int m2ColumnIndex = 0; m2ColumnIndex < otherBinaryMatrix.columnSize; m2ColumnIndex++) {
                for (int m1ColumnIndex = 0; m1ColumnIndex < columnSize; m1ColumnIndex++) {
                    int a = elements[m1RowIndex][m1ColumnIndex];
                    int b = otherBinaryMatrix.elements[m1ColumnIndex][m2ColumnIndex];
                    newElements[m1RowIndex][m2ColumnIndex] += a * b;
                }
                newElements[m1RowIndex][m2ColumnIndex] %= 2;
            }
        }
        return new BinaryMatrix(rowSize, otherBinaryMatrix.columnSize, newElements);
    }

    public BinaryMatrix concatenate(BinaryMatrix otherBinaryMatrix) {
        if (rowSize != otherBinaryMatrix.rowSize) {
            throw new Error("The given matrix is not equal to the size of the current matrix!");
        }
        int[][] newElements = new int[rowSize][otherBinaryMatrix.columnSize + columnSize];
        for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columnSize + otherBinaryMatrix.columnSize; columnIndex++) {
                if (columnIndex < columnSize) {
                    newElements[rowIndex][columnIndex] = elements[rowIndex][columnIndex];
                } else {
                    newElements[rowIndex][columnIndex] = otherBinaryMatrix.elements[rowIndex][columnIndex - columnSize];
                }
            }
        }
        return new BinaryMatrix(rowSize, otherBinaryMatrix.columnSize + columnSize, newElements);
    }

    public BinaryMatrix transpose() {
        int[][] newElements = new int[columnSize][rowSize];
        for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
                newElements[columnIndex][rowIndex] = elements[rowIndex][columnIndex];
            }
        }
        return new BinaryMatrix(columnSize, rowSize, newElements);
    }

    static public BinaryMatrix identity(int size) {
        BinaryMatrix identityMatrix = new BinaryMatrix(size, size);
        for (int i = 0; i < size; i++) {
            identityMatrix.setElement(i, i, 1);
        }
        return identityMatrix;
    }

    static public BinaryMatrix randomMatrix(int rowSize, int columnSize) {
        int[][] newElements = new int[rowSize][columnSize];
        for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
                // noinspection Since15
                newElements[rowIndex][columnIndex] = ThreadLocalRandom.current().nextInt(0, 2);
            }
        }
        return new BinaryMatrix(rowSize, columnSize, newElements);
    }
}
