package sample.model;

import sample.utils.MyIStack;

public class CompStmt implements IStmt {
    IStmt first, second;
    public CompStmt(IStmt first, IStmt second){
        this.first=first;
        this.second=second;
    }
    @Override
    public PrgState execute(PrgState state){
        MyIStack stack=state.getExeStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public String toString(){
        return "("+first+" ; "+second+")";
    }
}
