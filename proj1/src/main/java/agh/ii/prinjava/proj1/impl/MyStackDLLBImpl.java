package agh.ii.prinjava.proj1.impl;

import agh.ii.prinjava.proj1.MyStack;

public class MyStackDLLBImpl<E> implements MyStack<E> {
    private DLinkList<E> elems;
    public MyStackDLLBImpl() { this.elems = new DLinkList<>(); }

    /**
     *
     * @return E type, first element
     */

    @Override
    public E pop() {
        return elems.removeFirst();

    }

    /**
     *
     * @param x, add the given element type E to our stack
     */
    @Override
    public void push(E x) {
        elems.addFirst(x);
    }

    /**
     *
     * @return int, number of element
     */
    @Override
    public int numOfElems() {
        return elems.numOfElems();
    }

    @Override
    public E peek() {
        return elems.getFirst();
    }

    @Override
    public E returnFirstStack() {return elems.getFirst();}
}
