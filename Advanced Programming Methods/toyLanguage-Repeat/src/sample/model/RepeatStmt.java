package sample.model;

public class RepeatStmt implements IStmt {
    private IStmt stmt;
    private Exp expr;
    public RepeatStmt(IStmt stmt, Exp exp){
        this.expr=exp;
        this.stmt=stmt;
    }
    @Override
    public PrgState execute(PrgState state){
        IStmt news=new CompStmt(
                stmt,
                new WhileStmt(new NegExpr(expr),stmt)
        );
        state.getExeStack().push(news);
        return null;
    }

    @Override
    public String toString(){
        return "Repeat \n    "+stmt+"\nuntil "+expr;
    }
}
