package sample.model;

import sample.utils.MyIDictionary;
import sample.utils.MyIHeap;
import sample.utils.exceptions.DivideByZeroException;
import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;
import sample.utils.exceptions.OperatorException;

public class ReadH extends Exp {
    private String var;
    public ReadH(String var){
        this.var=var;
    }

    @Override
    public int eval(MyIDictionary<String, Integer> symbolTable, MyIHeap<Integer, Integer> hp) throws NotExistingException, DivideByZeroException, OperatorException, EmptyException {
        int heapKey=symbolTable.get(var); //get in MyIDictionary throws an exception if the key does not exist
        int value=hp.get(heapKey);  //same here
        return value;
    }

    @Override
    public String toString(){
        return "readH("+var+")";
    }
}
