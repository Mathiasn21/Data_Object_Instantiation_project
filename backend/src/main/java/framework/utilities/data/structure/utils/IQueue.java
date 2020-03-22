package framework.utilities.data.structure.utils;

public interface IQueue<T> {
    void grow();
    void enqueue(T t);

    T dequeue() throws EmptyQueueError;
    T first() throws EmptyQueueError;

    int size();

    boolean isEmpty();
    boolean isFull();
}
