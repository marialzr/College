package sample.model;

import sample.utils.MyIStack;
import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;

public class LockStmt implements IStmt {
    private String var;
    public LockStmt(String var){
        this.var=var;
    }
    @Override
    public PrgState execute(PrgState state){
        int keyt=0;
        try {
            keyt=state.getSymTable().get(var);
        } catch (EmptyException e) {
            e.printStackTrace();
        } catch (NotExistingException e) {
            e.printStackTrace();
        }
        synchronized (state.getLockTable()){
            try {
                int val=state.getLockTable().get(keyt);
                if(val==-1){
                    state.getLockTable().setValue(keyt, val);
                }
                else{
                    MyIStack<IStmt> stk=state.getExeStack();
                    stk.push(this);
                }
            } catch (EmptyException e) {
                e.printStackTrace();
            } catch (NotExistingException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    @Override
    public String toString(){
        return "LockStmt("+var+")";
    }
}
