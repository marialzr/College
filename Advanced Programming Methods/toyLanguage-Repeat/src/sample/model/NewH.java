package sample.model;

import sample.utils.MyIDictionary;
import sample.utils.MyIHeap;
import sample.utils.exceptions.DivideByZeroException;
import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;
import sample.utils.exceptions.OperatorException;

import java.io.IOException;

public class NewH implements IStmt {
    private String variable;
    private Exp expr;

    public NewH(String var, Exp e) {
        variable = var;
        expr = e;
    }

    @Override
    public PrgState execute(PrgState state) throws DivideByZeroException, NotExistingException, OperatorException, EmptyException, IOException {
        MyIHeap<Integer, Integer> h = state.getHeap();
        MyIDictionary<String, Integer> symt = state.getSymTable();
        int key = h.allocate();
        System.out.println(">>>>>>>>>>> WTF < KEY" + key);
        int value = expr.eval(symt, h);
        h.add(key, value);
        if (symt.contains(variable)) {
            symt.add(variable, key);
        } else {
            symt.add(variable, key);
        }
        return null;
    }

    @Override
    public String toString(){
        return "newH( "+variable+", "+expr + ")";
    }
}
