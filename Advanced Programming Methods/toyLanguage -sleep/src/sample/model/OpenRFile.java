package sample.model;

import sample.utils.FileData;
import sample.utils.IdGenerator;
import sample.utils.MyIDictionary;
import sample.utils.exceptions.InterpretorException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFile implements IStmt {
    private String var_file_id;  //variable name
    private String fileName;

    public OpenRFile(String var_file_id, String fileName){
        this.fileName=fileName;
        this.var_file_id=var_file_id;
    }

    private void containsp(PrgState p){
        for (FileData f:p.getFileTable().getAllValues()) {
            if (f.getFileName().equals(fileName))
                throw new InterpretorException("The file is already open");
        }

    }

    @Override
    public PrgState execute(PrgState p){
        containsp(p);
        try{
            BufferedReader bf=new BufferedReader(new FileReader(fileName));
            FileData filed=new FileData(fileName, bf);
            int id= IdGenerator.generateId();
            p.getFileTable().add(id, filed);
            MyIDictionary<String, Integer> st=p.getSymTable();
            if(st.contains(var_file_id))
                st.update(var_file_id, id);
            else
                st.add(var_file_id, id);

        }catch (IOException e){
            throw new InterpretorException("The file cannot be opened. It does not exist or another error occured.");
        }
        return null;
    }

    @Override
    public String toString(){
        return var_file_id+" "+fileName;
    }
}
