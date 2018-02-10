package sample.utils;

import sample.utils.exceptions.EmptyException;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyStack<T> implements MyIStack<T> {
    private Deque<T> stack;
    public MyStack(){
        this.stack=new ArrayDeque<>();
    }
    @Override
    public void push(T elem){
        this.stack.push(elem);
    }

    @Override
    public T pop() throws EmptyException {
        if(this.stack.isEmpty())
            throw new EmptyException("The stack is empty!");
        return this.stack.pop();
    }

    @Override
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    @Override
    public String toString(){
        String s="";
        if (!this.stack.isEmpty())
        {
            for (T o:stack){
                s+=o;
            }
        }
        return s;
    }

    @Override
    public Iterable<T> getAll(){
        return stack;
    }
}
