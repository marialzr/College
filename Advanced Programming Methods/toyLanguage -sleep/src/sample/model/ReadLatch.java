package sample.model;

import sample.utils.MyIDictionary;
import sample.utils.MyIHeap;
import sample.utils.MyILatchTable;
import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;

public class ReadLatch extends Exp {
    private String var;
    public ReadLatch(String var){
        this.var=var;
    }
    @Override
    public int eval(MyIDictionary<String, Integer> symbolTable, MyIHeap<Integer, Integer> hp, MyILatchTable<Integer, Integer> latchTable) throws NotExistingException, EmptyException {
            int k=symbolTable.get(var);

            if (!latchTable.contains(k)){
                throw new NotExistingException("the key does not exist in the latch table!\n");
            }
            int v=latchTable.get(k);
        return v;
    }


    @Override
    public String toString(){
        return this.var;
    }
}
