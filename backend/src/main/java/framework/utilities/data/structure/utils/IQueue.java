package framework.utilities.data.structure.utils;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T>
 */
public interface IQueue<T> {
    void grow();
    void enqueue(T t);

    T dequeue() throws EmptyQueueError;
    T first() throws EmptyQueueError;

    int size();

    boolean isEmpty();
    boolean isFull();
}
