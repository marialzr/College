package sample.model;

import sample.utils.MyIDictionary;
import sample.utils.MyIHeap;
import sample.utils.exceptions.*;

public class BooleanExp extends Exp{
    private Exp left, right;
    String op;
    public BooleanExp(Exp l, Exp r, String op){
        this.left=l;
        this.right=r;
        this.op=op;
    }

    @Override
    public int eval(MyIDictionary<String, Integer> symbolTable, MyIHeap<Integer, Integer> hp) throws NotExistingException, DivideByZeroException, OperatorException, EmptyException {
        int l=left.eval(symbolTable, hp);
        int r=right.eval(symbolTable, hp);
        switch(op){
            case ">":
                return (l>r)?(1):(0);
            case ">=":
                return (l>=r)?(1):(0);
            case "<":
                return (l<r)?(1):(0);
            case "<=":
                return (l<=r)?(1):(0);
            case "==":
                return (l==r)?(1):(0);
            case "!=":
                return (l!=r)?(1):(0);
        }
        throw new InterpretorException("Invalid operator");
    }

    @Override
    public String toString() {
        return this.left+" "+op+" "+this.right;
    }
}
