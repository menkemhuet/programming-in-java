package agh.ii.prinjava.proj1;

import agh.ii.prinjava.proj1.impl.DLinkList;
import agh.ii.prinjava.proj1.impl.MyQueueDLLBImpl;

public interface MyQueue<E> {
    /**
     *
     * @param x, add the given element x to the end of the queue
     */
    void enqueue(E x);

    /**
     *
     * @return E type, the element at the head of the queue
     */

    E dequeue();

    /**
     *
     * @return True if we don't have element in our queue
     */

    default boolean isEmpty() {
        return numOfElems() == 0;
    }

    /**
     *
     * @return int, the number of elements in the queue
     */

    int numOfElems();

    /**
     *
     * @return E type, it returns our last elements
     */

    E peek();

    /**
     *
     * @return E type, first element
     */

    E returnFirstQueue();

    /**
     *
     * @return E type, last element (it will be use in our function)
     */

    E  returnLastQueue();



    /** Consider pros and cons of having a factory method in the interface */
    static <T> MyQueue<T> create() {
        return new MyQueueDLLBImpl<T>();
    }
}
