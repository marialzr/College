package sample.model;

import sample.utils.FileData;
import sample.utils.MyIDictionary;
import sample.utils.MyIHeap;
import sample.utils.exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStmt {
    private Exp exp_file_id;
    public CloseRFile(Exp exp_file_id){
        this.exp_file_id=exp_file_id;
    }
    @Override
    public PrgState execute(PrgState state) throws EmptyException, NotExistingException, DivideByZeroException, OperatorException {
        MyIDictionary<String, Integer> st=state.getSymTable();
        MyIHeap<Integer, Integer> h=state.getHeap();
        int id=exp_file_id.eval(st, h);
        FileData fd=state.getFileTable().get(id);
        BufferedReader bf=fd.getFileDescriptor();
        try {
            bf.close();
        } catch (IOException e) {
            throw new InterpretorException("Cannot close the file");
        }
        //st.remove(exp_file_id);
        state.getFileTable().remove(id);
        return null;
    }

    @Override
    public String toString(){
        return "Close File "+exp_file_id;
    }
}
