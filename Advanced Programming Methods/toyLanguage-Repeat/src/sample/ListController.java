package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.MainWindowController;
import sample.model.*;
import sample.utils.exceptions.*;

import java.io.IOException;

public class ListController {
    @FXML
    private ListView<IStmt> listView;
    @FXML
    private Button executeButton;

    @FXML
    void changeSceneToMainView(ActionEvent event) {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("Main window.fxml"));
        Parent listControllerParent= null;
        try {
            listControllerParent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene listScene=new Scene(listControllerParent);

        MainWindowController controller=loader.getController();
        ObservableList<IStmt> obl=listView.getSelectionModel().getSelectedItems();
        System.out.println(obl.size());
        //send the selected statement
        controller.initialize(obl.get(0));

        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(listScene);
        window.show();
    }

    @FXML
    public void initialize() throws DivideByZeroException, EmptyException, IOException, MyStmtExecException, NotExistingException, InterruptedException, OperatorException {
        listView.setItems(getStatements());
    }

    public ObservableList<IStmt>  getStatements() throws DivideByZeroException, EmptyException, IOException, MyStmtExecException, InterruptedException, NotExistingException, OperatorException {
        IStmt stmt1 = new CompStmt(
                new AssignStmt("v", new ConstExp(10)),
                new CompStmt(
                        new ForkStmt(new NewH("a", new ConstExp(22))),
                        new CompStmt(
                                new ForkStmt(new CompStmt(
                                        new WriteH("a", new ConstExp(30)),
                                        new CompStmt(
                                                new AssignStmt("v", new ConstExp(32)),
                                                new CompStmt(
                                                        new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new ReadH("a"))
                                                )
                                        )
                                )),
                                new CompStmt(
                                        new PrintStmt(new VarExp("v")),
                                        new PrintStmt(new ReadH("a"))
                                ))
                )
        );

        IStmt stmt2 = new CompStmt(
                new AssignStmt("v", new ConstExp(10)),
                new CompStmt(
                        new NewH("a", new ConstExp(22)),
                        new CompStmt(
                                new ForkStmt(new CompStmt(
                                        new WriteH("a", new ConstExp(30)),
                                        new CompStmt(
                                                new AssignStmt("v", new ConstExp(32)),
                                                new CompStmt(
                                                        new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new ReadH("a"))
                                                )
                                        )
                                )),
                                new CompStmt(
                                        new PrintStmt(new VarExp("v")),
                                        new PrintStmt(new ReadH("a"))
                                ))
                )
        );

        IStmt stmt3=new CompStmt(
                new AssignStmt("v", new ConstExp(3)),
                new RepeatStmt(new CompStmt(
                        new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ConstExp(1))),
                        new PrintStmt(new VarExp("v"))),
                        new BooleanExp(new VarExp("v"), new ConstExp(1), "<=")
                ));

        /*v=0;
        (repeat (fork(print(v);v=v-1);v=v+1) until v==3);
        x=1;y=2;z=3;w=4;
        print(v*10)*/

        IStmt stmtbun=new CompStmt(
                new CompStmt(
                        new AssignStmt("v", new ConstExp(0)),
                        new RepeatStmt(
                                new CompStmt(
                                        new ForkStmt(
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                             new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ConstExp(1))))
                                        ),
                                        new AssignStmt("v", new ArithExp('+', new VarExp("v"), new ConstExp(1)))
                                ),
                                new BooleanExp(new VarExp("v"), new ConstExp(3), "==")
                                )
                ),
                new CompStmt(
                        new CompStmt(
                                new CompStmt(
                                    new AssignStmt("x", new ConstExp(1)),
                                    new AssignStmt("y", new ConstExp(2))
                                ),
                                new CompStmt(
                                        new AssignStmt("z", new ConstExp(3)),
                                        new AssignStmt("w", new ConstExp(4))
                                )
                        ),
                        new PrintStmt(new ArithExp('*', new VarExp("v"), new ConstExp(10)))
                )
        );

        ObservableList<IStmt> ps= FXCollections.observableArrayList(stmt1, stmt2, stmt3, stmtbun);
        return ps;
    }
}

