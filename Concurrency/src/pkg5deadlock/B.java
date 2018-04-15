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
public class B {

    A a;

    public B() {
    }

    public void setA(A a) {
        this.a = a;
    }
    
    

    public synchronized void bFirstMethod() {
        System.out.println("b first");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
        a.aSecondMethod();
    }

    public synchronized void bSecondMethod() {
        System.out.println("b Sec");
    }

}
