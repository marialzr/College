package sample.utils;

import sample.utils.exceptions.NotExistingException;

import java.util.HashMap;
import java.util.Map;

public class MyLatchTable<K,V> implements MyILatchTable<K,V> {
    private Map<K, V> elems;

    public MyLatchTable() {
        this.elems = new HashMap<>();
    }

    @Override
    public V get(K key) throws NotExistingException {
        if (this.elems.containsKey(key))
            return this.elems.get(key);
        else
            throw new NotExistingException("the value does not exists in the table");
    }

    @Override
    public boolean contains(K key) {
        return this.elems.containsKey(key);
    }

    @Override
    public void remove(K key) {
        this.elems.remove(key);
    }

    @Override
    public void add(K key, V val) {
        this.elems.put(key, val);
    }

    @Override
    public Iterable<K> getAllKeys() {
        return this.elems.keySet();
    }

    @Override
    public Iterable<V> getAllValues() {
        return this.elems.values();
    }

    @Override
    public Map<K,V> getContent(){
        return elems;
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
    public void update(K key, V val){
        this.elems.put(key, val);
    }
}
