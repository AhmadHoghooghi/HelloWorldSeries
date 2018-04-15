/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4InterThreadCommunication;

/**
 *
 * @author Ahmad
 */
public class Demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                Q q = new Q();
        Thread producer = new Thread(new ProducerRunnable(q), "producer");
        Thread producer2 = new Thread(new ProducerRunnable2(q), "producer2");
        Thread consumer = new Thread(new ConsumerRunnable(q), "consumer");
        producer.start();
        System.out.println("producer thread started");
        producer2.start();
        System.out.println("producer2 thread started");
        
        consumer.start();
        System.out.println("consumer thread started");
    }
    
}
