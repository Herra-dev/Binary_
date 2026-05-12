package mg.hr.exception;

public class BinaryException extends java.lang.Exception{
    public BinaryException(int _bitNumber){
        System.out.println("Impossible, _bitNumber can't be a negative value: " + _bitNumber);
    }
}