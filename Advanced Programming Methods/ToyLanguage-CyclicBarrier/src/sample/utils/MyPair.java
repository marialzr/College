package sample.utils;

import java.util.ArrayList;

public class MyPair {
    private ArrayList<Integer> threads;
    private int value;
    public MyPair(ArrayList<Integer> arr, int value){
        this.threads=arr;
        this.value=value;
    }

    public ArrayList<Integer> getThreads() {
        return threads;
    }

    public void setThreads(ArrayList<Integer> threads) {
        this.threads = threads;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString(){
        return threads.toString()+"\n"+value;
    }
}
