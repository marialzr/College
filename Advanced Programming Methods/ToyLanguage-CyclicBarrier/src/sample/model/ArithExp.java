package sample.model;

import sample.utils.*;
import sample.utils.exceptions.DivideByZeroException;
import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;
import sample.utils.exceptions.OperatorException;

public class ArithExp extends Exp {  //leftexpr+rightexpr
    private Exp left;
    private Exp right;
    private char op;
    public ArithExp(char op, Exp left, Exp right){
        this.left=left;
        this.right=right;
        this.op=op;
    }
    @Override
    public int eval(MyIDictionary<String,Integer> symbolTable, MyIHeap<Integer, Integer> hp) throws DivideByZeroException, NotExistingException, OperatorException, EmptyException {
        int left1 = left.eval(symbolTable, hp);
        int right1 = right.eval(symbolTable, hp);
        if (op == '+')
            return left1 + right1;
        else if (op == '-')
            return left1 - right1;
        else if (op == '*')
            return left1 * right1;
        else if (op == '/') {
            if (right1 == 0)
                throw new DivideByZeroException("Error: Cannot divide by zero!\n");
            return left1 / right1;
        }
        throw new OperatorException("Give a valid operator! \n");
    }


    @Override
    public String toString(){
        return ""+this.left.toString()+"  "+op+" "+this.right.toString();
    }
}
