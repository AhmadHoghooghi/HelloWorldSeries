/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4InterThreadCommunication;

/**
 *
 * @author Ahmad
 */
public class Q {

    private boolean fresh = false;
    private int n;

    synchronized int get() throws InterruptedException {
        while (fresh == false) {
            this.wait();
        }
        System.out.println("\tGot n:" + n);
        fresh = false;
        this.notifyAll();
        return n;
    }

    synchronized void put(int n) throws InterruptedException {
        while (fresh == true) {
            this.wait();
        }
        this.n = n;
        fresh = true;
        System.out.println("Put n:" + n);
        this.notify();
        while (fresh == true) {
            this.wait();
        }
    }
}
