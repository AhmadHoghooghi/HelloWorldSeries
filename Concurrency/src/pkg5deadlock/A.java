/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg5deadlock;

import java.util.Arrays;

/**
 *
 * @author Ahmad
 */
public class A  {

    B b;

    public A() {
    }

    public void setB(B b) {
        this.b = b;
    }

    public synchronized void aFirstMethod() {
        System.out.println("a first");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
        b.bSecondMethod();
    }

    public synchronized void aSecondMethod() {
        System.out.println("a Sec");
    }

}
