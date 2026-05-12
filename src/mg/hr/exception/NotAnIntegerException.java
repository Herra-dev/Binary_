package mg.hr.exception;

public class NotAnIntegerException extends java.lang.Exception {
    public NotAnIntegerException(double _nbr) {
        System.out.println(_nbr + " is not an integer");
    }    
}