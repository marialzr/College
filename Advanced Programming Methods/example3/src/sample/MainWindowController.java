package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.controller.Controller;
import sample.model.*;
import sample.model.PrgState;
import sample.repository.IRepository;
import sample.repository.Repository;
import sample.utils.*;
import sample.utils.exceptions.*;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class MainWindowController {

    @FXML
    private TableView<HeapData> heapTableView;

    @FXML
    private TableColumn<HeapData, Integer> addressColumn;

    @FXML
    private TableColumn<HeapData, Integer> valueColumn;

    @FXML
    private TableView<FileTableData> fileTableView;

    @FXML
    private TableColumn<FileTableData, Integer> identifierColumn;

    @FXML
    private TableColumn<FileTableData, String> FileNameColumn;


    @FXML
    private ListView<String> outListView;

    @FXML
    private ListView<PrgState> statesListView;

    @FXML
    private TableView<SymTableData> symTableView;

    @FXML
    private TableColumn<SymTableData, String> nameColumn;

    @FXML
    private TableColumn<SymTableData, Integer> valueColumnSymTbl;

    @FXML
    private ListView<IStmt> exeStackView;

    @FXML
    private Button runButton;

    @FXML
    private TextField number;
    private Controller ctrl;
    private ExecutorService executor;

    public void initialize(IStmt st){
        MyStack exec1 = new MyStack();
        exec1.push(st);
        MyIDictionary<String, Integer> symbolT = new MyDictionary<>();
        MyIList<Integer> msg = new MyList<>();
        MyIFileTable<Integer, FileData> table=new MyFileTable<>();
        MyIHeap<Integer, Integer> h=new MyHeap<>();
        PrgState prg1 = new sample.model.PrgState(exec1, symbolT, msg, st, table, h);
        IRepository repo1 = new Repository("saveGui.txt");
        repo1.addPrgState(prg1);
        Controller ctr1 = new Controller(repo1);
        this.ctrl=ctr1;

        //setting up the columns
        identifierColumn.setCellValueFactory(new PropertyValueFactory<FileTableData, Integer>("identifier"));
        FileNameColumn.setCellValueFactory(new PropertyValueFactory<FileTableData, String>("fileName"));

        addressColumn.setCellValueFactory(new PropertyValueFactory<HeapData, Integer>("address"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<HeapData, Integer>("value"));

        nameColumn.setCellValueFactory(new PropertyValueFactory<SymTableData, String>("name"));
        valueColumnSymTbl.setCellValueFactory(new PropertyValueFactory<SymTableData, Integer>("value"));

        statesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<sample.model.PrgState>() {
            @Override
            public void changed(ObservableValue<? extends PrgState> observable, PrgState oldValue, PrgState newValue) {
                ObservableList<IStmt> elems= getExeStackData(newValue);
                exeStackView.setItems(elems);
                try {
                    ObservableList<SymTableData> stdata=getSymTableData(newValue);
                    symTableView.setItems(stdata);
                } catch (EmptyException e) {
                    e.printStackTrace();
                } catch (NotExistingException e) {
                    e.printStackTrace();
                }
            }
        });

        initializeExecutor();
    }

    @FXML
    void runOneStep(ActionEvent event) throws InterruptedException, NotExistingException, EmptyException {

        List<PrgState> prgList = removeCompletedPrg(this.ctrl.getRepo().getPrgStates());

        if (prgList.size()>0){
            ctrl.oneStepForAll(prgList);

            outListView.setItems(getMessages());
            fileTableView.setItems(getFileTableData());
            heapTableView.setItems(getHeapData());
            ObservableList<PrgState> sts=getPrgStates();
            statesListView.setItems(sts);
            int nr=sts.size();
            String s=Integer.toString(nr);
            number.setText(s);
            prgList = removeCompletedPrg(ctrl.getRepo().getPrgStates());
        }

        if (prgList.size()==0){
            executor.shutdown();
            this.ctrl.getRepo().setPrgStates(prgList);
        }
    }

    public List<sample.model.PrgState> removeCompletedPrg(List<sample.model.PrgState> inPrgList){
        return inPrgList.stream().filter(e->e.isNotCompleted()).collect(Collectors.toList());
    }

    public ObservableList<String> getMessages(){
        MyIList<Integer> mess=this.ctrl.getRepo().getPrgStates().get(0).getOut();
        ObservableList<String> result=FXCollections.observableArrayList();
        for (Integer i:mess.getAll()){
            result.add(i.toString());
        }
        return result;
    }

    public ObservableList<FileTableData> getFileTableData() throws NotExistingException {
        MyIFileTable<Integer, FileData> ft=this.ctrl.getRepo().getPrgStates().get(0).getFileTable();
        ObservableList<FileTableData> result= FXCollections.observableArrayList();
        for (Integer id:ft.getAllKeys()){
            FileTableData data=new FileTableData(id, ft.get(id).getFileName());
            result.add(data);
        }
        return result;
    }

    public ObservableList<HeapData> getHeapData() throws NotExistingException, EmptyException {
        MyIHeap<Integer, Integer>h= this.ctrl.getRepo().getPrgStates().get(0).getHeap();
        ObservableList<HeapData> result= FXCollections.observableArrayList();
        for (Integer id:h.getContent().keySet()){
            HeapData data=new HeapData(id, h.get(id));
            result.add(data);
        }
        return result;
    }

    public ObservableList<PrgState> getPrgStates(){
        List<PrgState> states=this.ctrl.getRepo().getPrgStates();

        ObservableList<PrgState> result=FXCollections.observableArrayList();
        for (PrgState p:states){
            result.add(p);
        }
        return result;
    }

    public ObservableList<IStmt> getExeStackData(PrgState p){
        MyIStack<IStmt> stk=p.getExeStack();
        ObservableList<IStmt> result=FXCollections.observableArrayList();
        for (IStmt i:stk.getAll()){
            result.add(i);
        }
        return result;
    }

    public ObservableList<SymTableData> getSymTableData(PrgState p) throws EmptyException, NotExistingException {
        MyIDictionary<String, Integer> symTbl=p.getSymTable();
        ObservableList<SymTableData> result= FXCollections.observableArrayList();
        for (String n:symTbl.getAllKeys()){
            SymTableData data=new SymTableData(n,symTbl.get(n));
            result.add(data);
        }
        return result;
    }
    public void initializeExecutor(){
        executor = Executors.newFixedThreadPool(3);
        this.ctrl.setExecutor(executor);
    }
}
