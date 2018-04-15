/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1ColumnByThreadName;

import pkg2Syncronization.BeautifulThread;

/**
 *
 * @author Ahmad
 */
public class SimpleNumPrinter extends BeautifulThread{

    public SimpleNumPrinter(String name) {
        super(name);
    }
    
    

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.getIden()+i);
        }
    }
    
}
