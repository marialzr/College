package sample.model;

import sample.utils.*;
import sample.utils.exceptions.DivideByZeroException;
import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;
import sample.utils.exceptions.OperatorException;

import java.io.IOException;

public class ForkStmt implements IStmt {
    private IStmt stmt;
    public ForkStmt(IStmt st){
        stmt=st;
    }
    @Override
    public PrgState execute(PrgState state) throws DivideByZeroException, NotExistingException, OperatorException, EmptyException, IOException{
        MyIStack<IStmt> newStack=new MyStack<>();
        newStack.push(stmt);
        MyIDictionary<String, Integer> newSymTable=new MyDictionary<>();
        newSymTable.copy(state.getSymTable());
        MyIHeap<Integer, Integer> h=state.getHeap();
        MyIFileTable<Integer, FileData> ft=state.getFileTable();
        MyIList<Integer> out=state.getOut();
        PrgState p=new PrgState(newStack, newSymTable,out,stmt,ft,h);
        return p;
    }
    @Override
    public String toString(){
        return "Fork( "+this.stmt+" )";
    }
}
