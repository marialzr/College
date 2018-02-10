package sample.model;

public class ForStmt implements IStmt {
    private IStmt init, step, body;
    private Exp cond;
    public ForStmt(IStmt init, Exp cond, IStmt step, IStmt body){
        this.init=init;
        this.body=body;
        this.cond=cond;
        this.step=step;
    }
    @Override
    public PrgState execute(PrgState state){
        IStmt newStmt=new CompStmt(
                            init,
                            new WhileStmt(cond, new CompStmt(body, step))
        );
        state.getExeStack().push(newStmt);
        return null;
    }

    @Override
    public String toString(){
        return "for ("+init+";"+cond+";"+step+"){\n"+body+"\n}\n";
    }
}
