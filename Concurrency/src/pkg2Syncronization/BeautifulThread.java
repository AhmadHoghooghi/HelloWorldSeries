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
public abstract class BeautifulThread extends Thread {

    private ThreadResultColomnChooser colChooser;
    public BeautifulThread(String name) {
        super(name);
        this.colChooser = new ThreadResultColomnChooser(this);
    }


    public abstract void run();
    public String getIden(){
        return colChooser.getIdentation();
    }

}
