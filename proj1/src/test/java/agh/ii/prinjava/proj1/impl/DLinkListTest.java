package agh.ii.prinjava.proj1.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DLinkListTest {
    DLinkList<Integer> dLinkList = new DLinkList<>();

    @BeforeEach
    void setUp() {
        /**
         * We initialize a list. However, it doesn't print the last element of our list and i cannot manage to fix that.
         * same problem for queue and stack
         */
        System.out.println("let's run the test");
        dLinkList.addLast(1);
        dLinkList.addLast(2);
        dLinkList.addLast(3);
        dLinkList.addLast(5);
        dLinkList.addLast(6);
        System.out.println(dLinkList);


    }

    @AfterEach
    void tearDown() {
        System.out.println("-------end--------");
    }

    @Test

    void testAddFirst(){
        dLinkList.addFirst(1);
        Assertions.assertEquals(1,dLinkList.getFirst());
    }
    /**
     * We test our function addLast : i put 10 at the end of the list
     */
    @Test

    void testAddlast(){
        dLinkList.addLast(10);
        Assertions.assertEquals(10,dLinkList.getLastValue());
    }

    /**
     * we remove the first value
     */
    @Test
    void testRemoveFirst(){
        dLinkList.removeFirst();
        Assertions.assertEquals(2,dLinkList.getFirst());
    }

    /**
     * now, the last
     */
    @Test
    void testRemoveLast(){
        dLinkList.removeLast();
        Assertions.assertEquals(5,dLinkList.getLastValue());
    }



}