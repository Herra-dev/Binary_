package herra.exception;

public class UnacceptableParameterException extends Exception {
    public UnacceptableParameterException(String error) {
        System.out.println("The parameter: " + error + " is invalid");
    }
}