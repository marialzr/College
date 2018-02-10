package sample.model;

import sample.utils.MyIDictionary;
import sample.utils.MyIHeap;

public class ConstExp extends Exp {  //2
    private int number;
    public ConstExp(int number){
        this.number=number;
    }
    @Override
    public int eval(MyIDictionary<String, Integer> symbolTable, MyIHeap<Integer, Integer> hp){
        return number;
    }

    @Override
    public String toString(){
        return ""+this.number;
    }
}
