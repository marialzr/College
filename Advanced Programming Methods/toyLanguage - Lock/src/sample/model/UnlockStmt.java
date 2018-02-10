package sample.model;

import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;

public class UnlockStmt implements IStmt {
    private String var;
    public UnlockStmt(String var){
        this.var=var;
    }

    @Override
    public PrgState execute(PrgState state){
        try {
            int val=state.getSymTable().get(var);
            synchronized (state.getLockTable()){
                int idd=state.getLockTable().get(val);
                if (idd==state.getId()){
                    state.getLockTable().setValue(val, -1);
                }
            }
        } catch (EmptyException e) {
            e.printStackTrace();
        } catch (NotExistingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String toString(){
        return "Unlock("+var+")";
    }
}
