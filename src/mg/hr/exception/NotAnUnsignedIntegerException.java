package mg.hr.exception;

public class NotAnUnsignedIntegerException extends java.lang.Exception {
    public NotAnUnsignedIntegerException(double _nbr) {
        System.out.println(_nbr + " is not an unsigned integer");
    }    
}