/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4InterThreadCommunication;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ahmad
 */
public class ConsumerRunnable implements Runnable {

    private Q q;

    public ConsumerRunnable(Q q) {
        this.q = q;
    }

    @Override
    public void run() {
        while (true) {
            try {
                q.get();
            } catch (InterruptedException ex) {
                System.out.println(Arrays.toString(ex.getStackTrace()));
            }
        }
    }

}
