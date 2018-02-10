package sample.utils;

public interface MyIList<T>{
    void add(T elem);
    String toString();
    Iterable<T> getAll();
}

