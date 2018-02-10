package sample.model;

import sample.utils.FileData;
import sample.utils.MyIDictionary;
import sample.utils.exceptions.DivideByZeroException;
import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;
import sample.utils.exceptions.OperatorException;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStmt {
    private Exp exp_file_id;  //with this we get the id from symtable
    private String var_name;

    public ReadFile(Exp exp_file_id, String var_name){
        this.exp_file_id=exp_file_id;
        this.var_name=var_name;
    }

    @Override
    public PrgState execute(PrgState state) throws DivideByZeroException, NotExistingException, OperatorException, EmptyException, IOException {
        MyIDictionary<String, Integer> st=state.getSymTable();
        int id=exp_file_id.eval(state.getSymTable(), state.getHeap());
        FileData fd=state.getFileTable().get(id);
        BufferedReader bf=fd.getFileDescriptor();
        String s=bf.readLine();
        int number;
        if(s.equals(""))
            number=0;
        else
            number=Integer.parseInt(s);

        if(st.contains(var_name))
            st.update(var_name, number);
        else
            st.add(var_name, number);
        return state;
    }

    @Override
    public String toString(){
        return "ReadFile "+exp_file_id+" "+var_name;
    }
}
