package agh.ii.prinjava.proj1.impl;

import agh.ii.prinjava.proj1.MyStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStackDLLBImplTest {
    MyStack<Integer> stackOfInts = MyStack.create();

    /**
     * same, we set up our stack
     */

    @BeforeEach
    void setUp() {
        System.out.println("let's test");
        stackOfInts.push(1);
        stackOfInts.push(2);
        stackOfInts.push(3);
        stackOfInts.push(4);
        stackOfInts.push(5);


    }

    @AfterEach
    void tearDown() {
        System.out.println("--------------");
    }

    /**
     * we push value 10 in our stack
     */
    @Test
    void testPush(){
        stackOfInts.push(10);
        Assertions.assertEquals(10,stackOfInts.returnFirstStack());
    }

    /**
     * we pop the first value of our stack so 5 is gone
     */
    @Test
    void testPop(){
        stackOfInts.pop();
        Assertions.assertEquals(4,stackOfInts.returnFirstStack());
    }

    /**
     * we count the number of elements
     */
    @Test
    void testNumelem(){
        stackOfInts.numOfElems();
        Assertions.assertEquals(5,stackOfInts.numOfElems());
    }

    @Test
    void testPeek(){
        stackOfInts.peek();
        Assertions.assertEquals(5,stackOfInts.returnFirstStack());
    }
}