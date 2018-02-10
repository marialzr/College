package sample.model;

import sample.utils.*;
import sample.utils.exceptions.DivideByZeroException;
import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;
import sample.utils.exceptions.OperatorException;

public class IfStmt implements IStmt {
    private Exp condition;
    private IStmt trueS, falseS;

    public IfStmt(Exp e, IStmt trueS, IStmt falseS) {
        this.condition = e;
        this.falseS = falseS;
        this.trueS = trueS;
    }

    @Override
    public PrgState execute(PrgState prgState) throws DivideByZeroException, NotExistingException, OperatorException, EmptyException {
        MyIStack<IStmt> s = prgState.getExeStack();
        MyIDictionary<String, Integer> dict = prgState.getSymTable();
        MyIHeap<Integer, Integer> h=prgState.getHeap();
        if (this.condition.eval(dict, h, prgState.getLatchTable()) == 0)
            s.push(this.falseS);
        else
            s.push(this.trueS);
        return null;
    }

    @Override
    public String toString() {
        return "If " + condition + " then" + this.trueS + " else " + this.falseS;
    }
}
