package sample.utils;

import sample.utils.exceptions.NotExistingException;

import java.util.Map;

public interface MyIFileTable<K,V> {
    V get(K key) throws NotExistingException;
    boolean contains(K key);
    void remove(K key);
    void add(K key, V val);
    Iterable<K> getAllKeys();
    Iterable<V> getAllValues();
    Map<K,V> getContent();
}
