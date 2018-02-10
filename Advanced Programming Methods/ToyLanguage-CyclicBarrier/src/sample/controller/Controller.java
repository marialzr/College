package sample.controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import sample.model.PrgState;
import sample.repository.IRepository;
import sample.utils.*;
import sample.utils.exceptions.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repo;

    public ExecutorService getExecutor() {
        return executor;
    }

    public void setExecutor(ExecutorService executor) {
        this.executor = executor;
    }

    private ExecutorService executor;

    public Controller(IRepository r) {
        this.repo = r;
    }

    public IRepository getRepo() {
        return repo;
    }

    public void setRepo(IRepository repo) {
        this.repo = repo;
    }

    public void oneStepForAll(List<PrgState> list) throws InterruptedException {

        List<Callable<PrgState>> callList=list.stream()
                .map((PrgState p)->(Callable<PrgState>)(p::oneStep))
                .collect(Collectors.toList());

        List<PrgState> newPrgList=executor.invokeAll(callList).stream()
                .map(future-> {
                    try {
                        return future.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        list.addAll(newPrgList);
        list.forEach(p ->repo.logPrgStateExec(p));
        repo.setPrgStates(list);

    }

    public void allStep() throws EmptyException, DivideByZeroException, MyStmtExecException, NotExistingException, OperatorException, IOException, InterruptedException {
        executor = Executors.newFixedThreadPool(3);
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgStates());
        while (prgList.size() > 0) {
            oneStepForAll(prgList);
            prgList = removeCompletedPrg(repo.getPrgStates());
        }

        executor.shutdown();
        repo.setPrgStates(prgList);
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream().filter(e->e.isNotCompleted()).collect(Collectors.toList());
    }
}

