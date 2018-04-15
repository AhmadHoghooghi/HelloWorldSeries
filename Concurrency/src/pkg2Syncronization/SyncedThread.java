/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2Syncronization;


/**
 *
 * @author Ahmad
 */
public class SyncedThread extends BeautifulThread{
    private SyncedPrinter sp;

    public SyncedThread(String name, SyncedPrinter sp) {
        super(name);
        this.sp=sp;
    }

    @Override
    public void run() {
        sp.printNumbers(getIden());
    }
    
    
}
