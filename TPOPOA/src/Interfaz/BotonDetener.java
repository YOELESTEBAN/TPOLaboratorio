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
 * @author Yoni
 */
public class BotonDetener extends JButton{

    private JFrame pantalla;

    public BotonDetener(JFrame p) {
        pantalla = p;
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("***************BOTON DETENER***************");
    }

}
