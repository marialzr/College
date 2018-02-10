package sample.model;

import sample.utils.MyIDictionary;
import sample.utils.MyIHeap;
import sample.utils.MyILatchTable;
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
    public int eval(MyIDictionary<String, Integer> symbolTable, MyIHeap<Integer, Integer> hp, MyILatchTable<Integer, Integer> latchTable) throws NotExistingException, DivideByZeroException, OperatorException, EmptyException {
        int l=left.eval(symbolTable, hp, latchTable);
        int r=right.eval(symbolTable, hp, latchTable);
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
