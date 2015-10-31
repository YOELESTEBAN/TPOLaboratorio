/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Jonathan
 */
public class BotonCerrar extends JButton{
    private JFrame pantalla;
    
    public BotonCerrar(JFrame p){
        pantalla = p;
    }
    
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
