/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import com.sun.awt.AWTUtilities;
import java.awt.Button;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Jonathan
 */
public class PantallaPrincipal extends JFrame {
    /*ESTA PARTE ESTA SIENDO REMODELADA*/
    
    
/*
    private Button comenzar;
    private Button detener;
    private Button reiniciar;
    private Button cerrar;
    private JButton arrastrar;
    private JPanel cajero1;
    private JPanel cajero2;
    private JPanel cola;

    public PantallaPrincipal() {
        super();
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        this.setUndecorated(true); //Quita todo el decorado, barras, botones
        this.setSize(300, 300);//Le indica el tamaño de la ventana
        this.setLocationRelativeTo(null);//Posiciona la ventana en el centro de la pantalla

    }

    private void inicializarComponentes() {
        comenzar = new Button();

        detener = new Button();

        reiniciar = new Button();

        cerrar = new Button();

        arrastrar = new JButton();
        ShapedButtonUI polygonUI = new ShapedButtonUI();
        polygonUI.setShape(ButtonShape.POLYGON, polygonButton);
        polygonButton.setUI(polygonUI);
        polygonButton.setPreferredSize(new Dimension(100, 100));
        
        int[] puntosX = {0, this.getWidth() / 10, 0, 0};
        int[] puntosY = {0, 0, this.getHeight() / 10, 0};
        Shape triangulo = new Polygon(puntosX, puntosY, 4);
        AWTUtilities.setWindowShape(arrastrar, triangulo);
        arrastrar.setVisible(true);

        cajero1 = new JPanel();

        cajero2 = new JPanel();

        cola = new JPanel();

        this.add(arrastrar);

    }
    */
}
