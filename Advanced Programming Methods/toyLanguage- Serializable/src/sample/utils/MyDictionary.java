package sample.utils;

import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {
    private Map<K, V> elems;

    public MyDictionary() {
        this.elems = new HashMap<K, V>();
    }

    @Override
    public void add(K key, V value) {
        this.elems.put(key, value);
    }

    @Override
    public void remove(K key) {
        this.elems.remove(key);
    }

    @Override
    public boolean contains(K k) {
        if (this.elems.containsKey(k))
            return true;
        return false;
    }

    @Override
    public void update(K key, V value) {
        this.elems.put(key, value);
    }

    @Override
    public V get(K key) throws EmptyException, NotExistingException {
        if (this.elems.isEmpty())
            throw new EmptyException("The container is empty!");
        if (this.elems.containsKey(key))
            return this.elems.get(key);
        else
            throw new NotExistingException("the value does not exists in the file table");
    }

    @Override
    public String toString() {
        String s = "";
        for (HashMap.Entry<K, V> entry : elems.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            s += key + " " + value + "; ";
        }
        return s;
    }

    @Override
    public void setContent(Map<K, V> symTbl) {
        elems = symTbl;
    }

    @Override
    public Iterable<K> getAllKeys() {
        return elems.keySet();
    }

    @Override
    public Iterable<V> getAllValues() {
        return this.elems.values();
    }

    @Override
    public Map<K, V> getContent() {
        return this.elems;
    }

    @Override
    public void copy(MyIDictionary<K, V> d){
        for(HashMap.Entry<K,V> entry : d.getContent().entrySet()){
            this.elems.put(entry.getKey(),entry.getValue());
        }
    }
}
