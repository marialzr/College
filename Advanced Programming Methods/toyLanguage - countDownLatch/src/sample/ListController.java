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
                new CompStmt(
                    new CompStmt(
                        new CompStmt(
                                new CompStmt(new NewH("v1", new ConstExp(2)), new NewH("v2", new ConstExp(3))),
                                new CompStmt(new NewH("v3", new ConstExp(4)), new NewLatchStmt("cnt", new ReadH("v2")))
                        ),

                        new ForkStmt(
                                new CompStmt(
                                    new CompStmt(
                                        new WriteH("v1", new ArithExp('*',new ReadH("v1"), new ConstExp(10))),
                                        new PrintStmt(new ReadH("v1"))
                                    ),
                                    new CountDownStmt("cnt")
                                )
                        )
                    ),

                    new CompStmt(
                            new ForkStmt(
                                    new CompStmt(
                                        new CompStmt(
                                            new WriteH("v2", new ArithExp('*',new ReadH("v2"), new ConstExp(10))),
                                            new PrintStmt(new ReadH("v2"))
                                        ),
                                        new CountDownStmt("cnt")
                                    )
                            ),
                            new ForkStmt(
                                    new CompStmt(
                                            new CompStmt(
                                                    new WriteH("v3", new ArithExp('*',new ReadH("v3"), new ConstExp(10))),
                                                    new PrintStmt(new ReadH("v3"))
                                            ),
                                            new CountDownStmt("cnt")
                                    )
                            ))
                ),
                new CompStmt(
                        new CompStmt(
                                new AwaitStmt("cnt"),
                                new PrintStmt(new ConstExp(100))
                        ),
                        new CompStmt(
                                new CountDownStmt("cnt"),
                                new PrintStmt(new ConstExp(100))
                        )
                ));


        /*new(v1,2);new(v2,3);new(v3,4);newLatch(cnt,rH(v2));
        fork(wh(v1,rh(v1)*10));print(rh(v1));countDown(cnt);
        fork(wh(v2,rh(v2)*10));print(rh(v2));countDown(cnt);
        fork(wh(v3,rh(v3)*10));print(rh(v3));countDown(cnt))));
        await(cnt);
        print(100);
        countDown(cnt);
        print(100)*/

        IStmt stmt4=new CompStmt(
                        new CompStmt(
                                new CompStmt(
                                        new CompStmt(new NewH("v1", new ConstExp(2)), new NewH("v2", new ConstExp(3))),
                                        new CompStmt(new NewH("v3", new ConstExp(4)), new NewLatchStmt("cnt", new ReadH("v2")))
                                ),

                                new ForkStmt(
                                        new CompStmt(
                                                new CompStmt(
                                                        new WriteH("v1", new ArithExp('*',new ReadH("v1"), new ConstExp(10))),
                                                        new PrintStmt(new ReadH("v1"))
                                                ),
                                                new CompStmt(
                                                    new CountDownStmt("cnt"),
                                                        new ForkStmt(
                                                                new CompStmt(
                                                                        new CompStmt(
                                                                                new WriteH("v2", new ArithExp('*',new ReadH("v2"), new ConstExp(10))),
                                                                                new PrintStmt(new ReadH("v2"))
                                                                        ),
                                                                        new CompStmt(
                                                                                new CountDownStmt("cnt"),
                                                                                new ForkStmt(
                                                                                        new CompStmt(
                                                                                                new CompStmt(
                                                                                                        new WriteH("v3", new ArithExp('*',new ReadH("v3"), new ConstExp(10))),
                                                                                                        new PrintStmt(new ReadH("v3"))
                                                                                                ),
                                                                                                new CountDownStmt("cnt")
                                                                                        )
                                                                                ))
                                                                        )


                                                                )
                                                        )
                                                )
                                        )
                                ),
                new CompStmt(
                        new CompStmt(
                                new AwaitStmt("cnt"),
                                new PrintStmt(new ConstExp(100))
                        ),
                        new CompStmt(
                                new CountDownStmt("cnt"),
                                new PrintStmt(new ConstExp(100))
                        )
                ));



        ObservableList<IStmt> ps= FXCollections.observableArrayList(stmt1, stmt2, stmt3, stmt4);
        return ps;
    }
}

