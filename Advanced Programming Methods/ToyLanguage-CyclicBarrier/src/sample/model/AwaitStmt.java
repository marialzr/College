package sample.model;

import sample.utils.MyPair;
import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;

public class AwaitStmt implements IStmt {
    private String var;
    public AwaitStmt(String var){
        this.var=var;
    }

    @Override
    public PrgState execute(PrgState state) throws NotExistingException, EmptyException {
        if (!state.getSymTable().contains(var))
            throw new NotExistingException("The element does not exist in symbolTable");
        int foundIndex=state.getSymTable().get(var);
        synchronized (state.getBarrier()){
            if (!state.getBarrier().contains(foundIndex)){
                throw new NotExistingException("The value does not exist in the table");
            }
            MyPair val=state.getBarrier().get(foundIndex);
            if (val.getThreads().size()!=val.getValue()){
                if (val.getThreads().contains(state.getId())){
                    state.getExeStack().push(this);
                }else{
                    state.getExeStack().push(this);
                    state.getBarrier().get(foundIndex).getThreads().add(state.getId());
                }
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return "Await("+this.var+")";
    }
}
