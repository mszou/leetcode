// CircularBuffer.java (including thread-safe)
// Implement a circular buffer using an array.

import java.io.*;
import java.util.*;

// For shifting turn to circular array list
// http://www.museful.net/2012/software-development/circulararraylist-for-java

//class Solution {
//  class CircularBuffer {
//    int head; // head ==== tail
//    int tail;
//    int count;
//    int size;
//    int[] array;
//    CircularBuffer(int size) {
//      this.head = 0;
//      this.tail = 0;
//      this.count = 0;
//      this.size = size;
//      array = new int[size];
//    }
//    
//    public void add(int data) {
//      if(isFull()) {
//        throw new RuntimeException("Overflow occured");
//      }
//      else {
//        array[tail] = data;
//        tail = (tail + 1) % size;
//        count++;
//      }
//    }
//    
//    public int remove() {
//      if(isEmpty()) {
//        throw new RuntimeException("NoElement Exception");
//      }
//      else {
//        count--;
//        int res = array[head];
//        array[head] = 0; // remember to set the deleted to zero
//        head = (head + 1) % size;
//        return res;
//      }
//    }
//    
//    public boolean isEmpty() {
//      if(count <= 0) {
//        return true;
//      }
//      else {
//        return false;
//      }
//    }
//    
//    public boolean isFull() {
//      if(count >= size) return true;
//      else return false;
//    }
//  }
//  public static void main(String[] args) {
//       Solution s = new Solution();
//       CircularBuffer c = s.new CircularBuffer(6);
//       c.add(2);
//       c.add(2);
//       c.add(2);
//       System.out.println(c.remove());
//       c.add(5);
//       c.add(6);
//       System.out.println(c.remove());
//       c.add(6);
//       c.add(6);
//       System.out.println(c.remove());
//       System.out.println(c.remove());
//       System.out.println(c.remove());
//       System.out.println(c.remove());
//       System.out.println(c.remove());
//  }
//}


// Thread Safe

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

// For shifting turn to circular array list
// http://www.museful.net/2012/software-development/circulararraylist-for-java

class CircularBuffer {
    class CircularBuffer1 {
        int head; // head ==== tail
        int tail;
        int size;
        int[] array;
        Semaphore mutex;
        Semaphore full;
        Semaphore empty;
        int produces = 0;
        CircularBuffer1(int size) {
            this.head = -1;
            this.tail = -1;
            this.size = size;
            array = new int[size];
            this.mutex = new Semaphore(1);
            this.full = new Semaphore(0);
            this.empty = new Semaphore(size);
        }

        class Producer extends Thread {
            public void run() {
                try {
                    while(true) {
                        empty.acquire();
                        mutex.acquire();
                        if(tail == -1 && head == -1) {
                            tail = 0;
                            head = 0;
                            array[tail] = produces;
                        }
                        else {
                            tail = (tail + 1) % size;
                            array[tail] = produces;
                        }
                        System.out.println("Produces: " + produces);
                        produces++;
                        mutex.release();
                        full.release();
                        Thread.sleep(500);

                    }
                }
                catch(Exception x) {
                    x.printStackTrace();
                }
            }
        }

        class Consumer extends Thread {
            public void run() {
                try {
                    while(true) {
                        full.acquire();
                        mutex.acquire();
                        int out = array[head];
                        System.out.println("Consumes: "+ out);
                        array[head] = 0;
                        head = (head + 1) % size;
                        mutex.release();
                        empty.release();
                        Thread.sleep(3000);

                    }
                }
                catch(Exception x) {
                    x.printStackTrace();
                }
            }
        }

    }
    public static void main(String[] args) {
    	CircularBuffer s = new CircularBuffer();
        CircularBuffer1 c = s.new CircularBuffer1(5);
        c.new Producer().start();
        c.new Consumer().start();
        c.new Consumer().start();
        c.new Producer().start();
    }
}
