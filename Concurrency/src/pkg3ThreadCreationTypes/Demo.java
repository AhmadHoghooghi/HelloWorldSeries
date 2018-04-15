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
public class Demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //method 1: create thread with runnable object
        RunnableClass1 rc1 = new RunnableClass1();
        Thread thread = new Thread(rc1, "1");
        thread.start();
        
        //delegate start to raunnable class, embed a Thread in Runnable class.
        RunnableClass2 rc2 = new RunnableClass2();
        rc2.start();
    }
    
}
