/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3ThreadCreationTypes;

/**
 *
 * @author Ahmad
 */
public class RunnableClass2 implements Runnable{
    
    Thread t;

    public RunnableClass2() {
        t = new Thread(this, "ThreadImplementingRunnable");
        
    }
    
    public void start(){
        t.start();
    }
    

    @Override
    public void run() {
        System.out.println("from: " + t.getName());
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
    
}
