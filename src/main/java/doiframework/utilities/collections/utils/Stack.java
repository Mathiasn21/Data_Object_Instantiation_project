package doiframework.utilities.collections.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T> T
 */
public final class Stack<T> {
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

    public final void push(T t){
        if(isFull()){
            tArr = Arrays.copyOf(tArr, size*2);
        }
        tArr[++top] = t;
    }

    private final boolean isFull() {
        return size() >= size;
    }

    @Nullable
    public final T pop(){
        if(isEmpty()){return null;}
        return tArr[top--];
    }

    @Nullable
    @Contract(pure = true)
    public final T peek(){
        if(isEmpty()){return null;}
        return tArr[top];}

    public final boolean isEmpty(){return top == -1;}
    public final int size(){return top+1;}
}
