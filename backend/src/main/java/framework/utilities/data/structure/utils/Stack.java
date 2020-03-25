package framework.utilities.data.structure.utils;

import java.util.Arrays;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T>
 */
public class Stack<T> {
    private int size;
    private static final int DEFAULT_SIZE = 25;
    private T[] tArr;
    private int top = -1;

    public Stack(){ this(DEFAULT_SIZE); }

    @SuppressWarnings("unchecked")
    public Stack(int size) {
        this.size = size;
        this.tArr = (T[])new Object[this.size];
    }

    public void push(T t){
        if(isFull()){
            tArr = Arrays.copyOf(tArr, size*2);
        }
        tArr[++top] = t;
    }

    private boolean isFull() {
        return size() >= size;
    }

    public T pop(){
        if(isEmpty()){return null;}
        return tArr[top--];
    }

    public T peek(){
        if(isEmpty()){return null;}
        return tArr[top];}

    public boolean isEmpty(){return top == -1;}
    public int size(){return top+1;}
}
