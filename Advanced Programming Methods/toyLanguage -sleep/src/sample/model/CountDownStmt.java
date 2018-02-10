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
            int foundIndex=state.getSymTable().get(var);
            synchronized (state.getLatchTable()){
                int valIndex=state.getLatchTable().get(foundIndex);

                if (valIndex>0){
                    state.getLatchTable().update(foundIndex,valIndex-1);
                    state.getOut().add(state.getId());
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
