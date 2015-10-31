/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Aspect.BancoMonitor;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Jonathan
 */
public class PantallaPrincipal extends JFrame implements Observer {

    private BancoMonitor banco;
    
    private BotonCerrar cerrar;
    private BotonArrastrar arrastrar;
    private BotonComenzar comenzar;
    private BotonDetener detener;
    private JTextArea log;
    private JScrollPane barraLog;

    public PantallaPrincipal(BancoMonitor b) {
        super();
        banco = b;
        banco.addObserver(this);
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
        inicializarComenzar();
        inicializarDetener();
        inicializarLog();

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

    private void inicializarComenzar() {
        comenzar = new BotonComenzar(this);
        comenzar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iconoComenzar.jpg")));
        comenzar.setBounds(30, 40, 100, 30);
        comenzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comenzar.actionPerformed(evt);
            }
        });

        this.add(comenzar);
    }

    private void inicializarDetener() {
        detener = new BotonDetener(this);
        detener.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iconoDetener.jpg")));
        detener.setBounds(160, 40, 100, 30);
        detener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detener.actionPerformed(evt);
            }
        });

        this.add(detener);
    }
    
    private void inicializarLog(){
        log = new JTextArea();
        log.setEditable(false);
        barraLog = new JScrollPane(log);
        barraLog.setBounds(160, 80, 430, 150);
        this.add(barraLog);
        
    }

    @Override
    public void update(Observable o, Object o1) {
        log.setText(log.getText()+"\n"+(String)o1);
    }
}
