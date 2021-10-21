package agh.ii.prinjava.proj1.impl;

public class DLinkList<E> {
    Node<E> first;

    private static class Node<T> {
        T elem;
        Node<T> next;
        Node<T> prev;

        public Node(T elem) {
            this.elem = elem;
            this.next = null;
            this.prev = null;
        }
    }


    /**
     * we add a new elem in our list
     *
     * if the list is empty, el will be the first node
     * @param el, element we want to insert into our list
     */
    void addFirst(E el){
        Node<E> new_node = new Node<>(el);
        if(first != null) {
            new_node.next = first;
            first.prev = new_node;
            first = new_node;
        }
        else{

            first = new_node;
        }
    }

    /**
     * we add a new element at the end
     *
     * same as before, if the list is empty, el will be the first node
     * @param el
     */
    void addLast(E el){
        Node<E> new_node = new Node<E>(el);
        if(first != null) {
            Node<E> temp = first;
            while(temp.next != null){
                temp = temp.next;
            }
            new_node.prev = temp;
            temp.next=new_node;
        }
        else{
            first = new_node;
        }
    }

    /**
     * we will need this fonction to get the last node of the list
     * @return the last element of the dblist
     *
     */
    public Node<E> getLast(){
        Node<E> temp = first;
        while(temp.next != null){
            temp = temp.next;
        }
        return temp;
    }
    public E getLast_Elem(){
        Node<E> temp = first;
        while(temp.next != null){
            temp = temp.next;
        }
        return temp.elem;
    }

    /**
     * this function will be useful in the test to get the last element
     * @return E type, the value of the last element
     */
    public E getLastValue(){
        Node<E> temp = first;
        while(temp.next != null){
            temp = temp.next;
        }
        return temp.elem;
    }

    /**
     * same as getLast
     * @return E type, the first element
     */
    public E getFirst(){
        return first.elem;
    }

    /**
     *function that will remove the first node of the list
     * @return E type, the first element of the dblist
     */
    public E removeFirst(){
        Node<E> temp = first;
        first.next.prev = null;
        first = first.next;
        temp.next = null;
        return temp.elem;
    }

    /**
     *same as the other function but it will remove the last node of the list
     * @return the last element of the doubly linked list
     */
    public E removeLast(){
        Node<E> last = getLast();
        last.prev.next = null;
        last.prev = null;
        return last.elem;
    }


    /**
     *
     * @return toString
     */
    @Override
    public String toString() {
        Node<E> temp = first;
        String tostring = "DLinkList{ ";
        while(temp.next != null){
            tostring += temp.elem + " / ";
            temp = temp.next;
        }
        return tostring + " }";
    }

    /**
     *
     * @return int, number of elements contains in our list
     */
    public int numOfElems(){
        if(first.elem == null){
            return 0;
        }
        else{
            int counter = 1;
            Node<E> temp = first;
            while(temp.next != null) {
                counter++;
                temp = temp.next;
            }
            return counter;
        }
    }


}