package sample.model;

import sample.utils.exceptions.DivideByZeroException;
import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;
import sample.utils.exceptions.OperatorException;

import java.io.IOException;

public interface IStmt {
    String toString();
    PrgState execute(PrgState state) throws DivideByZeroException, NotExistingException, OperatorException, EmptyException, IOException;
}
