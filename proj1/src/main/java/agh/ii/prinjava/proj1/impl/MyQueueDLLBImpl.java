package agh.ii.prinjava.proj1.impl;

import agh.ii.prinjava.proj1.MyQueue;

public class MyQueueDLLBImpl<E> implements MyQueue<E> {
    private DLinkList<E> elems;
    public MyQueueDLLBImpl() { this.elems = new DLinkList<>(); }

    /**
     *
     * @param x, elem we have to add at the end of the file
     *
     */

    @Override
    public void enqueue(E x) {
        elems.addFirst(x);
    }

    /**
     *
     * @return E type, the first element of our queue
     */

    @Override
    public E returnFirstQueue() {return elems.getFirst();}

    /**
     *
     * @return E type, the last element of our queue
     */

    @Override
    public E returnLastQueue() {return elems.getLast_Elem();}

    /**
     *
     * @return E type, we take of the last elem
     */

    @Override
    public E dequeue() {return elems.removeLast();}

    /**
     *
     * @return int, number of element in our queue
     */
    @Override
    public int numOfElems() {
       return elems.numOfElems();
    }

    @Override
    public E peek() {
        return elems.removeLast();
    }
}
