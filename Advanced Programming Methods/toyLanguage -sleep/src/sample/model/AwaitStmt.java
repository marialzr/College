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
            int foundIndex=state.getSymTable().get(var);
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
