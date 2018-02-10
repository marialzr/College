package sample.model;

import sample.utils.*;
import sample.utils.exceptions.DivideByZeroException;
import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;
import sample.utils.exceptions.OperatorException;

public class AssignStmt implements IStmt {
    private String varName;
    private Exp expr;
    public AssignStmt(String varName, Exp e){
        this.expr=e;
        this.varName=varName;
    }
    @Override
    public PrgState execute(PrgState prgState) throws DivideByZeroException, NotExistingException, OperatorException, EmptyException {
        MyIDictionary<String, Integer> symb=prgState.getSymTable();
        MyIHeap<Integer, Integer> h=prgState.getHeap();
        int result=expr.eval(symb, h, prgState.getLatchTable());
        if (symb.contains(varName))
            symb.update(varName, result);
        else
            symb.add(varName, result);
        return null;
    }
    @Override
    public String toString(){
        return varName+"="+expr;
    }
}
