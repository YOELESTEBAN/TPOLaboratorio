/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextArea;

/**
 *
 * @author Yoni
 */
public class AreaTexto extends JTextArea implements Observer {

    /* public AreaTexto(){
     super();
     }*/
    @Override
    public void update(Observable o, Object o1) {
        this.setText(this.getText()+"\n"+(String)o1);
    }

}
