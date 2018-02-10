package sample.utils.exceptions;

public class MyStmtExecException extends Exception {
    public MyStmtExecException(){};
    public MyStmtExecException(String msg){
        super(msg);
    }
}

