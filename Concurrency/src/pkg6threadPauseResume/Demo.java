/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg6threadPauseResume;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ahmad
 */
public class Demo {

    public static void main(String[] args) {
        try {
            PausableThread thread = new PausableThread("pausableThread");
            thread.start();
            Thread.sleep(1000);
            System.out.println("Pause Pausable Thread for 2 sec");
            thread.suspend();
            Thread.sleep(2000);
            thread.resume();
            
        } catch (InterruptedException ex) {
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

}
