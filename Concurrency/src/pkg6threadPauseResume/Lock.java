/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg6threadPauseResume;

/**
 *
 * @author Ahmad
 */
public class Lock {
    private boolean suspend;

    public Lock() {
        suspend=false;
    }
    
    public void suspend(){
        suspend = true;
    }

    public boolean isSuspended() {
        return suspend;
    }
    
    
   
    public synchronized void resume(){
        suspend = false;
        this.notify();
    }
}
