package sample.utils;

import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.EmptyException;

public interface MyIStack<T> {
    void push(T elem);
    T pop() throws EmptyException, EmptyException;
    boolean isEmpty();
    String toString();
    Iterable<T> getAll();
}
