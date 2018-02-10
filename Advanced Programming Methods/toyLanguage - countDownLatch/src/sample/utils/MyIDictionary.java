package sample.utils;

import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;

import java.util.Map;

public interface MyIDictionary<K, V> {
    void add(K key, V value);
    boolean contains(K k);
    void update(K key, V value);
    V get(K key) throws EmptyException, NotExistingException;
    String toString();
    Iterable<K> getAllKeys();
    void remove(K key);
    Iterable<V>getAllValues();
    Map<K,V> getContent();
    void setContent(Map<K, V> symTbl);
    void copy(MyIDictionary<K, V> d);
}

