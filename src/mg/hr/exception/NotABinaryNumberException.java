package mg.hr.exception;

public class NotABinaryNumberException extends java.lang.Exception {
    public NotABinaryNumberException(int binaryNumber) {
        System.out.println("Impossible, " + binaryNumber + " is not a binary number!");
    }
}