package sample.repository;

import sample.model.PrgState;

import java.io.Serializable;
import java.util.List;

public interface IRepository {
    void addPrgState(PrgState p);
    void logPrgStateExec(PrgState p);
    void closeFile();
    List<PrgState> getPrgStates();
    void setPrgStates(List<PrgState> prgStates);
}
