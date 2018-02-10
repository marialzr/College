package sample.utils;

import sample.utils.exceptions.EmptyException;
import sample.utils.exceptions.NotExistingException;

import java.util.HashMap;
import java.util.Map;

public class MyLockTable<K,V> implements MyILockTable<K,V> {
    private HashMap<K,V> elems;

    public MyLockTable(){
        elems=new HashMap<>();
    }

    @Override
    public void add(K k, V v) {
        elems.put(k,v);
    }

    @Override
    public void update(K k, V v) {
        elems.put(k,v);
    }

    @Override
    public boolean contains(K k) {
        return this.elems.containsKey(k);
    }

    @Override
    public V get(K k) throws EmptyException, NotExistingException {
        if (this.elems.isEmpty())
            throw new EmptyException("The heap is empty");
        if (this.elems.containsKey(k)){
            return this.elems.get(k);}
        else{
            throw new NotExistingException("The key does not exist");
        }
    }

    @Override
    public Iterable<K> getAll() {
        return this.elems.keySet();
    }


    @Override
    public void setContent(Map<K,V> m){
        this.elems.clear();
        this.elems.putAll(m);
    }

    @Override
    public HashMap<K,V> getContent(){
        return this.elems;
    }

    @Override
    public void setValue(K key, V val){
        this.elems.put(key, val);
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
}
