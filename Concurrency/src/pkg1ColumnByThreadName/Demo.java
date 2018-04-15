/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1ColumnByThreadName;

import pkg2Syncronization.BeautifulThread;

/**
 *
 * @author Ahmad
 */
public class Demo {
    public static void main(String[] args) {
        
            BeautifulThread thread1 = new SimpleNumPrinter("1");
        //thread1.setPriority(9);
        BeautifulThread thread2 = new SimpleNumPrinter("2");
        BeautifulThread thread3 = new SimpleNumPrinter("3");

        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println("All Threads are active");
    }
}
