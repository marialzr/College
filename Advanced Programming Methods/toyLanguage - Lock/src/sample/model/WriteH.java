package sample.model;

import sample.utils.MyIDictionary;
import sample.utils.MyIHeap;
import sample.utils.exceptions.DivideByZeroException;
import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;
import sample.utils.exceptions.OperatorException;

import java.io.IOException;

public class WriteH implements IStmt {
    private String var;
    private Exp e;

    public WriteH(String var, Exp e){
        this.var=var;
        this.e=e;
    }

    @Override
    public PrgState execute(PrgState state) throws DivideByZeroException, NotExistingException, OperatorException, EmptyException, IOException {
        MyIDictionary<String, Integer> symt=state.getSymTable();
        MyIHeap<Integer, Integer> h=state.getHeap();
        int ad=symt.get(var);
        if (!(h.contains(ad))){
            throw new NotExistingException("The address is not in the heap");
        }
        int value=e.eval(symt, h);
        h.update(ad, value);
        return null;
    }

    @Override
    public String toString() {
        return "WriteH( "+this.var+","+this.e+")";
    }
}
