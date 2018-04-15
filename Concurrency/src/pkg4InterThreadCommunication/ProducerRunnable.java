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
public class ProducerRunnable implements Runnable {

    private int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
        11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    private Q q;

    public ProducerRunnable(Q q) {
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
