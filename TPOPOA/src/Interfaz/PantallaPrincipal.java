/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author Jonathan
 */
public class PantallaPrincipal extends JFrame {

    private BotonCerrar cerrar;
    private BotonArrastrar arrastrar;

    public PantallaPrincipal() {
        super();
        inicializarVentana();
        inicializarComponentes();
    }

    private void inicializarVentana() {
        this.setUndecorated(true);
        this.setLocation(400, 100);
        this.setSize(600, 600);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ((JPanel) getContentPane()).setOpaque(false);
        ImageIcon uno = new ImageIcon(this.getClass().getResource("/Iconos/fondoVentana.jpg"));
        JLabel fondo = new JLabel();
        fondo.setIcon(uno);
        getLayeredPane().add(fondo, JLayeredPane.FRAME_CONTENT_LAYER);
        fondo.setBounds(0, 0, uno.getIconWidth(), uno.getIconHeight());
    }

    private void inicializarComponentes() {
        inicializarCerrar();
        inicializarArrastrar();

    }

    private void inicializarCerrar() {
        cerrar = new BotonCerrar(this);
        cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iconoCerrar.jpg")));
        cerrar.setBounds(570, 0, 30, 30);
        cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrar.actionPerformed(evt);
            }
        });

        this.add(cerrar);

    }

    private void inicializarArrastrar() {
        arrastrar = new BotonArrastrar(this);
        arrastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iconoArrastrar.jpg")));
        arrastrar.setBounds(0, 0, 570, 30);
        arrastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                arrastrar.mousePressed(evt);
            }
        });
        arrastrar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                arrastrar.mouseDragged(evt);
            }
        });

        this.add(arrastrar);
    }
}
