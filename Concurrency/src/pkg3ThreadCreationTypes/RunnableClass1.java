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
public class RunnableClass1 implements Runnable {
    
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {

            System.out.println( i);
        }
    }

}
