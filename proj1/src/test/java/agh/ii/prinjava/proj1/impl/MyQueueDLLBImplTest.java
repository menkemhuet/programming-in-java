package agh.ii.prinjava.proj1.impl;

import agh.ii.prinjava.proj1.MyQueue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueDLLBImplTest {
    MyQueue<Integer> queueOfInts = MyQueue.create();

    /**
     * let set up a queue
     */

    @BeforeEach
    void setUp() {
        System.out.println("let's test !");
        queueOfInts.enqueue(1);
        queueOfInts.enqueue(2);
        queueOfInts.enqueue(3);
        queueOfInts.enqueue(4);



    }

    @AfterEach
    void tearDown() {
        System.out.println("------------end");}

    /**
     * now we try our test
     */
    @Test
    void testEnqueue(){
        queueOfInts.enqueue(8);
        Assertions.assertEquals(8,queueOfInts.returnFirstQueue());
    }

    @Test
    void testDequeue(){
        queueOfInts.dequeue();
        Assertions.assertEquals(2,queueOfInts.returnLastQueue());

    }

    @Test
    void testNumelem(){
        queueOfInts.numOfElems();
        Assertions.assertEquals(4,queueOfInts.numOfElems());
    }

    @Test
    void testPeak(){
        queueOfInts.peek();
        Assertions.assertEquals(2,queueOfInts.returnLastQueue());

    }
    
}