package sample.model;


import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;

public class CountDownStmt implements IStmt {
    private String var;
    public CountDownStmt(String var){
        this.var=var;
    }
    @Override
    public  PrgState execute(PrgState state){
        try {
            if (!state.getSymTable().contains(var))
                throw new NotExistingException("The value does not exist in the symbol table!\n");
            int foundIndex=state.getSymTable().get(var);
            synchronized (state.getLatchTable()){
                if (state.getLatchTable().contains(foundIndex)) {
                    int valIndex = state.getLatchTable().get(foundIndex);

                    if (valIndex > 0) {
                        state.getLatchTable().update(foundIndex, valIndex - 1);
                        state.getOut().add(state.getId());
                    }
                }
            }

        } catch (EmptyException e) {
            e.printStackTrace();
        } catch (NotExistingException e) {
            e.printStackTrace();
        }

        return  null;
    }
    @Override
    public String toString(){
        return "CountDown("+this.var+")";
    }
}
