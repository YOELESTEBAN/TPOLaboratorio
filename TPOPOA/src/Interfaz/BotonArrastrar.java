/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Jonathan
 */
public class BotonArrastrar extends JButton {

    private JFrame pantalla;
    private int x;
    private int y;

    public BotonArrastrar(JFrame p) {
        pantalla = p;
    }

    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    public void mouseDragged(MouseEvent e) {
        Point point = MouseInfo.getPointerInfo().getLocation();
        pantalla.setLocation(point.x - x, point.y - y);
    }
}
