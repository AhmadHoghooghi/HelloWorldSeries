/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2Syncronization;

/**
 *
 * @author Ahmad
 */
public class Demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SyncedPrinter sp = new SyncedPrinter();
        BeautifulThread thread1 = new SyncedThread("1", sp);
//        thread1.setPriority(9);
        BeautifulThread thread2 = new SyncedThread("2", sp);
//        thread2.setPriority(8);
        BeautifulThread thread3 = new SyncedThread("3", sp);

        thread1.start();
        thread2.start();
        thread3.start();
    }

}
