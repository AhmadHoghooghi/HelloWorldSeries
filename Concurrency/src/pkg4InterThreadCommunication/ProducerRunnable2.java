/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4InterThreadCommunication;

import java.util.Arrays;

/**
 *
 * @author Ahmad
 */
public class ProducerRunnable2 implements Runnable {

    private int[] numbers = {101, 102, 103, 104, 105, 106, 107, 108, 109, 110,
        111, 112, 113, 114, 115, 116, 117, 118, 19, 120};
    private Q q;

    public ProducerRunnable2(Q q) {
        this.q = q;
    }

    @Override
    public void run() {
        int i = 0;
        
        for (int number : numbers) {
            try {
                q.put(number);
            } catch (InterruptedException ex) {
                System.out.println(Arrays.toString(ex.getStackTrace()));
            }
        }
    }

}
