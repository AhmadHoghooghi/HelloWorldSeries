/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1ColumnByThreadName;


/**
 *
 * @author Ahmad
 */
public class ThreadResultColomnChooser {

    private final Thread thread;
    private boolean cached = false;
    private String ident;

    public ThreadResultColomnChooser(Thread thread) {
        this.thread = thread;
    }
    
    public String getIdentation(){
        if (cached==false) {
            ident = calcIdentation();
        }
        return ident;
    }
    
    private String calcIdentation() {
        int threadNum;
        try {
            threadNum = Integer.parseInt(thread.getName());
        } catch (NumberFormatException e) {
            threadNum=0;
        }
        String ident ="";
        for (int i = 0; i < threadNum; i++) {
            ident = ident+"\t";
        }
        return ident;
    }

}
