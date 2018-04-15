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
public class PausableThread implements Runnable {

    private Lock lock;
    Thread thread;

    public PausableThread(String name) {
        thread = new Thread(this, name);
        lock = new Lock();
    }

    public void suspend() {
        this.lock.suspend();
    }

    public void resume() {
        this.lock.resume();
    }
    
    public void start(){
        thread.start();
    }

    @Override
    public void run() {
        try {

            for (int i = 0; i < 20; i++) {
                synchronized (lock) {
                    while (lock.isSuspended()) {
                        lock.wait();
                    }

                }
                System.out.println("i= " + i);
                Thread.sleep(250);
            }
        } catch (InterruptedException ex) {
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

}
