package sample.model;

import sample.utils.MyIDictionary;
import sample.utils.MyIHeap;
import sample.utils.MyILatchTable;
import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;

public class VarExp extends Exp {
    private String id;

    public VarExp(String id){
        this.id=id;
    }

    @Override
    public int eval(MyIDictionary<String, Integer> symbolTable, MyIHeap<Integer, Integer> hp, MyILatchTable<Integer, Integer> latchTable) throws NotExistingException, EmptyException {
        if(symbolTable.contains(id)){
            return symbolTable.get(id);
        }
        throw new NotExistingException("Not found");
    }

    @Override
    public String toString(){
        return ""+id;
    }
}
