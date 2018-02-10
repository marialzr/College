package sample.model;

import sample.utils.IdGenerator;
import sample.utils.exceptions.DivideByZeroException;
import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;
import sample.utils.exceptions.OperatorException;

public class NewLatchStmt implements IStmt {
    private String var;
    private Exp exp;
    public NewLatchStmt(String var, Exp exp){
        this.var=var;
        this.exp=exp;
    }

    public PrgState execute(PrgState state){
        try {
            int number=exp.eval(state.getSymTable(), state.getHeap(), state.getLatchTable());
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(number);
            int id= IdGenerator.generateId();
            synchronized (state.getLatchTable()){
                state.getLatchTable().add(id, number);
            }
            if(state.getSymTable().contains(var)){
                state.getSymTable().update(var, id);
            }
            else{
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
        return "NewLatchStmt("+var+", "+exp+")";
    }
}
