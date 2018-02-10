package sample.model;

import sample.utils.MyIDictionary;
import sample.utils.MyIHeap;
import sample.utils.exceptions.DivideByZeroException;
import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;
import sample.utils.exceptions.OperatorException;

public class NegExpr extends Exp {
    private Exp expr;
    public NegExpr(Exp e){
        this.expr=e;
    }
    @Override
    public int eval(MyIDictionary<String, Integer> symbolTable, MyIHeap<Integer, Integer> hp) throws EmptyException, DivideByZeroException, NotExistingException, OperatorException {
        return 1-expr.eval(symbolTable, hp);
    }

    @Override
    public String toString(){
        return "Not("+expr+")";
    }
}
