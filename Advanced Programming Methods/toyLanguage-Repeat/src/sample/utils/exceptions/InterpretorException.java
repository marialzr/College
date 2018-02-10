package sample.utils.exceptions;

public class InterpretorException extends RuntimeException {
    public InterpretorException(){}
    public InterpretorException(String msg){
        super(msg);
    }
}
