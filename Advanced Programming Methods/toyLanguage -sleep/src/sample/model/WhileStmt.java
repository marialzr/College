package sample.model;

import sample.utils.MyIStack;
import sample.utils.exceptions.DivideByZeroException;
import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;
import sample.utils.exceptions.OperatorException;

import java.io.IOException;

public class WhileStmt implements IStmt {
    private Exp expr;
    private IStmt stmt;

    public WhileStmt(Exp e, IStmt stmt){
        this.expr=e;
        this.stmt=stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws DivideByZeroException, NotExistingException, OperatorException, EmptyException, IOException {
        MyIStack<IStmt> exeStack=state.getExeStack();
        if (expr.eval(state.getSymTable(), state.getHeap(), state.getLatchTable())==1){
            exeStack.push(this);
            exeStack.push(stmt);
        }
        return null;
    }

    @Override
    public String toString(){
        return "While ("+expr+"){"+this.stmt+" }";
    }
}
