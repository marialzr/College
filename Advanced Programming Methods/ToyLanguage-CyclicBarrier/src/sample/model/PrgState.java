package sample.model;

import sample.utils.*;
import sample.utils.exceptions.*;

import java.io.IOException;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Integer> symTable;
    private MyIList<Integer> out;
    private MyIFileTable<Integer, FileData> fileTable;
    private MyIHeap<Integer, Integer> heap;
    private MyIBarrierTable<Integer, MyPair> barrier;
    private IStmt stmt;
    private int id;

    public PrgState(MyIStack<IStmt> stack, MyIDictionary<String, Integer> symTable, MyIList<Integer> out, IStmt stmt, MyIFileTable<Integer, FileData> tab, MyIHeap<Integer, Integer> hp, MyIBarrierTable<Integer, MyPair> barrier){
        this.exeStack=stack;
        this.symTable=symTable;
        this.out=out;
        this.stmt=stmt;
        this.fileTable=tab;
        this.heap=hp;
        this.barrier=barrier;
        this.id=IdGenerator.generateId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "Id"+this.id+"\nExec stack "+exeStack+"\nHeap "+heap+"\nMessages list "+out+"\nSymbol table "+symTable+"\nFile Table "+fileTable+"\nStatement "+stmt+"\n\n";
    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public MyIDictionary<String, Integer> getSymTable() {
        return symTable;
    }

    public MyIList<Integer> getOut() {
        return out;
    }

    public MyIFileTable<Integer, FileData> getFileTable() {
        return fileTable;
    }

    public IStmt getStmt() {
        return stmt;
    }

    public MyIHeap<Integer, Integer> getHeap() {
        return heap;
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymTable(MyIDictionary<String, Integer> symTable) {
        this.symTable = symTable;
    }

    public void setOut(MyIList<Integer> out) {
        this.out = out;
    }

    public void setStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    public MyIBarrierTable<Integer, MyPair> getBarrier() {
        return barrier;
    }

    public void setBarrier(MyIBarrierTable<Integer, MyPair> barrier) {
        this.barrier = barrier;
    }

    public boolean isNotCompleted(){
        if (!this.exeStack.isEmpty())
            return true;
        return false;
    }

    public PrgState oneStep() throws DivideByZeroException, NotExistingException, OperatorException, MyStmtExecException, EmptyException, IOException {
            if (this.exeStack.isEmpty())
                throw new EmptyException("The stack is empty");
            IStmt crtStmt = (IStmt) this.exeStack.pop();
            return crtStmt.execute(this);
    }
}
