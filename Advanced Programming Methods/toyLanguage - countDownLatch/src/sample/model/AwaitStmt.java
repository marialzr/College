package sample.model;


import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;

public class AwaitStmt implements IStmt {
    private String var;
    public AwaitStmt(String var){
        this.var=var;
    }

    @Override
    public PrgState execute(PrgState state){
        try {
            if (!state.getSymTable().contains(var))
                throw new NotExistingException("The value does not exist in the symbol table");
            int foundIndex=state.getSymTable().get(var);

            if(!state.getLatchTable().contains(foundIndex)){
                throw new NotExistingException("The value does not exist in the latch table");
            }

            int valTbl=state.getLatchTable().get(foundIndex);
            if (valTbl!=0){
                state.getExeStack().push(this);
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
        return "Await("+var+")";
    }

}
