/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg5deadlock;

/**
 *
 * @author Ahmad
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.setB(b);
        b.setA(a);
        
        Thread ta = new Thread(()->a.aFirstMethod());
        Thread tb = new Thread(()->b.bFirstMethod());
        
        ta.start();
        tb.start();
    }

}
