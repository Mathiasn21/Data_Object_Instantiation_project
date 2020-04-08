package DOIFramework.utilities.collections.utils;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T> T
 */
public final class CircularQueue<T> implements IQueue<T> {
    private T[] object;
    private int size, head, tail;

    public CircularQueue() {this(10);}

    @SuppressWarnings("unchecked")
    public CircularQueue(int i) {
        this.object = (T[]) new Object[i];
        head = tail = size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final void grow() {
        T[] newObject = (T[]) (new Object[object.length * 2]);
        for (int i = 0; i < size; i++) {
            newObject[i] = object[head];
            head = (head + 1) % object.length;
        }
        head = 0;
        tail = size;
        object = newObject;
    }

    @Override
    public final void enqueue(T t) {
        if(size() == object.length){grow();}
        object[tail] = t;
        size++;
        tail = (tail + 1) % object.length;
    }

    @Override
    public final T dequeue() throws EmptyQueueError {
        if(isEmpty()) { throw new EmptyQueueError(); }
        T res = object[head];
        object[head] = null;
        head = (head + 1) % object.length;

        size--;
        return res;
    }

    @Override
    public final int size() {return size;}

    @Override
    public final T first() throws EmptyQueueError {
        if(isEmpty()) { throw new EmptyQueueError(); }
        return object[head];
    }

    @Override
    public final boolean isEmpty() {
        return size == 0;
    }

    @Override
    public final boolean isFull() {
        return size == object.length;
    }
}