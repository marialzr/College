package sample.model;

import sample.utils.IdGenerator;
import sample.utils.MyIDictionary;
import sample.utils.MyIStack;

public class NewLockStmt implements IStmt {
    public String var;
    public NewLockStmt(String var){
        this.var=var;
    }

    @Override
    public PrgState execute(PrgState state){
        int key;
        synchronized (state.getLockTable()){
            key= IdGenerator.generateId();
            state.getLockTable().add(key, -1);
        }

        MyIDictionary<String, Integer> st=state.getSymTable();
        if (st.contains(var))
            st.update(var,key);
        else
            st.add(var, key);
        return null;
    }

    @Override
    public String toString(){
        return "NewLockStmt("+var+")";
    }
}
