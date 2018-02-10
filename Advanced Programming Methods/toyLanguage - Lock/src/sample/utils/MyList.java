package sample.utils;

import java.util.ArrayList;

public class MyList<T> implements MyIList<T> {
    private ArrayList<T> list;
    public MyList(){
        this.list=new ArrayList<T>();
    }
    @Override
    public void add(T elem) {
        this.list.add(elem);
    }
    @Override
    public String toString(){
        String s="";
        int i=0;
        if (!this.list.isEmpty())
            while (i<this.list.size()){
                s+=this.list.get(i++)+" ";
            }
        return s;
    }
    @Override
    public Iterable<T> getAll(){
        return list;
    }
}
