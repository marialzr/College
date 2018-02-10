package sample.model;

public class SleepStmt implements IStmt {
    private int nr;
    public SleepStmt(int nr){
        this.nr=nr;
    }
    @Override
    public PrgState execute(PrgState state){
        if (nr>0){
            IStmt news=new SleepStmt(nr-1);
            state.getExeStack().push(news);
        }
        return null;
    }
    @Override
    public String toString(){
        return "Sleep("+nr+")";
    }
}
