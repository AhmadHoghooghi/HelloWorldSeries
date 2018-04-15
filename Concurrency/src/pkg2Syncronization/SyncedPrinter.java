/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2Syncronization;

import java.util.Arrays;

/**
 *
 * @author Ahmad
 */
public class SyncedPrinter {

    public synchronized void printNumbers(String ident) {
        for (int i = 0; i < 10; i++) {
            System.out.println(ident + i);
//            if (i==5) {
//                try {
//                    this.wait(1000);
//                } catch (InterruptedException ex) {
//                    System.out.println(Arrays.toString(ex.getStackTrace()));
//                }
//            }
        }
    }

}
