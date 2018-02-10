package sample.utils;

import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;

import java.util.Map;

public interface MyIHeap<K, V> {
    void add(K k, V v);
    void update(K k, V v);
    boolean contains(K k);
    V get(K k) throws EmptyException, NotExistingException;
    Iterable<K> getAll();
    int allocate();
    void setContent(Map<K, V> m);
    Map<K,V> getContent();
    String toString();
}
