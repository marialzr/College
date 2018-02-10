package sample.utils;

import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;

import java.util.Map;

public interface MyILockTable<K,V> {
    void add(K k, V v);
    void update(K k, V v);
    boolean contains(K k);
    V get(K k) throws EmptyException, NotExistingException;
    Iterable<K> getAll();
    void setContent(Map<K, V> m);
    Map<K,V> getContent();
    void setValue(K key, V val);
    String toString();
}
