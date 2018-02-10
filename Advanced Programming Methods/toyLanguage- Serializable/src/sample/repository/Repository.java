package sample.repository;

import sample.model.IStmt;
import sample.model.PrgState;
import sample.utils.FileData;
import sample.utils.MyIDictionary;
import sample.utils.MyIFileTable;
import sample.utils.MyIHeap;
import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.InterpretorException;
import sample.utils.exceptions.NotExistingException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<PrgState> prgStates;
    private String logFilePath;
    private PrintWriter logFile;
    public Repository(String logFilePath){
        prgStates=new ArrayList<>();
        this.logFilePath=logFilePath;
    }
    @Override
    public void addPrgState(PrgState p){
        this.prgStates.add(p);
    }

    @Override
    public void logPrgStateExec(PrgState state) {
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            int idC = state.getId();

            logFile.println("Current id: [" + idC + "]");
            System.out.println("Current id: [" + idC + "]");

            logFile.println("ExeStack:");
            System.out.println("ExeStack:");
            for (IStmt st : state.getExeStack().getAll()){
                logFile.println("\t" + st);
                System.out.println(st);
            }

            System.out.println("SymTable:");
            MyIDictionary<String, Integer> dict = state.getSymTable();
            logFile.println("SymTable:");
            for (String key : dict.getAllKeys()){
                logFile.println("\t" + key + "-->" + dict.get(key));
                System.out.println(key + "-->" + dict.get(key));
            }

            System.out.println("Out");
            logFile.println("Out:");
            for (int v : state.getOut().getAll()){
                logFile.println("\t" + v);
                System.out.println(v);
            }

            System.out.println("FileTable:");
            logFile.println("FileTable:");
            MyIFileTable<Integer, FileData> fileTable = state.getFileTable();
            for (int id : fileTable.getAllKeys()){
                logFile.println("\t" + id + "-->" + fileTable.get(id).getFileName());
                System.out.println(id + "-->" + fileTable.get(id).getFileName());
            }

            System.out.println("Heap:");
            logFile.println("Heap:");
            MyIHeap<Integer, Integer> heap = state.getHeap();
            for (int address : heap.getAll()) {
                logFile.println("\t" + address + "-->" + heap.get(address));
                System.out.println(address + "-->" + heap.get(address));
            }

            System.out.println();
            logFile.println("__________________________________________________________________________________________________");
        }catch (IOException e){
            throw new InterpretorException(e.getMessage());
        } catch (NotExistingException e) {
            e.printStackTrace();
        } catch (EmptyException e) {
            e.printStackTrace();
        } finally {
            logFile.close();
        }
    }

    @Override
    public void closeFile(){
        this.logFile.close();
    }

    public List<PrgState> getPrgStates() {
        return prgStates;
    }

    public void setPrgStates(List<PrgState> prgStates) {
        this.prgStates = prgStates;
    }
}