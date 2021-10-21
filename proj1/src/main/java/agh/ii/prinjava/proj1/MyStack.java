package agh.ii.prinjava.proj1;

import agh.ii.prinjava.proj1.impl.MyStackDLLBImpl;

public interface MyStack<E> {
    /**
     *
     * @return E type, delele the last element of our stack
     */
    E pop();

    /**
     *
     * @param x, add the given element type E to our stack
     */

    void push(E x);

    /**
     *
     * @return True if we don't have any element in our stack ( stack empty )
     */

    default boolean isEmpty() {
        return numOfElems() == 0;
    }

    /**
     *
     * @return int, the number of elements of our stack
     */

    int numOfElems();

    /**
     *
     * @return E type, it returns our first element
     */

    E peek();

    /**
     * same as the function in MyQueue
     * @return E type
     */
    E returnFirstStack();

    /** Consider pros and cons of having a factory method in the interface */
    static <T> MyStack<T> create() { return new MyStackDLLBImpl<T>(); }
}
