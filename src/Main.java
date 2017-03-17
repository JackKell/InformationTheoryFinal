public class Main {

    public static void main(String[] args) {
        BinaryMatrix a = new BinaryMatrix(3, 3);
        a.setElement(1,1, 1);
        BinaryMatrix b = new BinaryMatrix(3, 3);
        b.setElement(1,1, 1);
        System.out.println("BinaryMatrix A");
        System.out.println(a);
        System.out.println("BinaryMatrix B");
        System.out.println(b);
        System.out.println("BinaryMatrix A + B");
        System.out.println(a.subtract(b));
        System.out.println("Identity k=5");
        System.out.println(BinaryMatrix.Identity(1));
    }
}
