package sample.model;

import sample.utils.*;
import sample.utils.exceptions.DivideByZeroException;
import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;
import sample.utils.exceptions.OperatorException;

public abstract class Exp {
    public abstract int eval(MyIDictionary<String, Integer> symbolTable, MyIHeap<Integer, Integer> hp,MyILatchTable<Integer, Integer> latchTable) throws NotExistingException, DivideByZeroException, OperatorException, EmptyException;
    public abstract String toString();
}
