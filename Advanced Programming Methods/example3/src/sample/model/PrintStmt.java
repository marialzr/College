package sample.model;

import sample.utils.*;
import sample.utils.exceptions.DivideByZeroException;
import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;
import sample.utils.exceptions.OperatorException;

public class PrintStmt implements IStmt {
    private Exp expr;
    public PrintStmt(Exp e){
        expr=e;
    }
    @Override
    public PrgState execute(PrgState p) throws DivideByZeroException, NotExistingException, OperatorException, EmptyException {
        MyIList<Integer> list=p.getOut();
        MyIDictionary<String, Integer> dict=p.getSymTable();
        MyIHeap<Integer, Integer> h=p.getHeap();
        int res=expr.eval(dict, h);
        list.add(res);
        return null;
    }
    @Override
    public String toString(){
        return "print("+expr+")";
    }
}
