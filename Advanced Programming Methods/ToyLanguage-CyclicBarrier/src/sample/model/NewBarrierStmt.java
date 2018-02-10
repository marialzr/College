package sample.model;

import sample.utils.IdGenerator;
import sample.utils.MyPair;
import sample.utils.exceptions.DivideByZeroException;
import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;
import sample.utils.exceptions.OperatorException;

import java.util.ArrayList;

public class NewBarrierStmt implements IStmt{
    private String var;
    private Exp exp;
    public NewBarrierStmt(String var, Exp e){
        this.exp=e;
        this.var=var;
    }

    @Override
    public PrgState execute(PrgState state){
        try {
            int nr=exp.eval(state.getSymTable(), state.getHeap());
            MyPair pair=new MyPair(new ArrayList<>(), nr);
            int id= IdGenerator.generateId();
            synchronized (state.getBarrier()){
                state.getBarrier().add(id, pair);
            }
            if (state.getSymTable().contains(var)){
                int val=state.getSymTable().get(var);
                state.getSymTable().update(var, id);
            }else {
                state.getSymTable().add(var, id);
            }

        } catch (NotExistingException e) {
            e.printStackTrace();
        } catch (DivideByZeroException e) {
            e.printStackTrace();
        } catch (OperatorException e) {
            e.printStackTrace();
        } catch (EmptyException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String toString(){
        return "NewBarrierStmt("+var+", "+exp+")";
    }

}
