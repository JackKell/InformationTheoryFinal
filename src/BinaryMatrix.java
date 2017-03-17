public class BinaryMatrix {
    private int rowSize;
    private int columnSize;
    private int[][] elements;

    public BinaryMatrix(int columnSize, int rowSize, int[][] elements) {
        this.columnSize = columnSize;
        this.rowSize = rowSize;
        this.elements = elements;
    }

    public BinaryMatrix(int columnSize, int rowSize) {
        this(columnSize, rowSize, new int[columnSize][rowSize]);
    }

    public void print() {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                System.out.print(elements[j][i] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        String matrixString = "";
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                matrixString += elements[j][i] + " ";
            }
            matrixString += "\n";
        }
        return matrixString;
    }

    public void setElement(int column, int row, int value) {
        elements[column][row] = value > 0 ? 1 : 0;
    }

    public BinaryMatrix add(BinaryMatrix otherBinaryMatrix) {
        if (otherBinaryMatrix.columnSize == this.columnSize && otherBinaryMatrix.rowSize == this.rowSize) {
            int[][] newElements = elements.clone();
            for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
                for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
                    newElements[columnIndex][rowIndex] += otherBinaryMatrix.elements[rowIndex][columnIndex];
                    newElements[columnIndex][rowIndex] %= 2;
                }
            }
            return new BinaryMatrix(columnSize, rowSize, newElements);
        } else {
            throw new Error("The given matrix is not equal to the size of the current matrix!");
        }
    }

    public BinaryMatrix subtract(BinaryMatrix otherBinaryMatrix) {
        if (otherBinaryMatrix.columnSize == this.columnSize && otherBinaryMatrix.rowSize == this.rowSize) {
            int[][] newElements = elements.clone();
            for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
                for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
                    newElements[columnIndex][rowIndex] -= otherBinaryMatrix.elements[rowIndex][columnIndex];
                    newElements[columnIndex][rowIndex] %= 2;
                }
            }
            return new BinaryMatrix(columnSize, rowSize, newElements);
        } else {
            throw new Error("The given matrix is not equal to the size of the current matrix!");
        }
    }

    public BinaryMatrix multiply(BinaryMatrix otherBinaryMatrix) {
        if (this.columnSize == otherBinaryMatrix.rowSize) {
            int[][] newElements = new int[otherBinaryMatrix.columnSize][rowSize];
            for (int i = 0; i < rowSize; i++) {
                for (int j = 0; j < columnSize; j++) {
                    return;
                }
            }
        } else {
            throw new Error("The given matrix is not equal to the size of the current matrix!");
        }
    }

    static public BinaryMatrix Identity(int size) {
        BinaryMatrix identityMatrix = new BinaryMatrix(size, size);
        for (int i = 0; i < size; i++) {
            identityMatrix.setElement(i, i, 1);
        }
        return identityMatrix;
    }
}
