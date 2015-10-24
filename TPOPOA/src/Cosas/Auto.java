/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cosas;

import puenteMonitor.*;

/**
 *
 * @author kozur
 */
public class Auto extends Thread {

    private boolean posicion; //True = Norte - False = Sur.
    private GestionaTrafico mon;
    private int lugar;

    public Auto(boolean pos, GestionaTrafico m) {
        posicion = pos;
        mon = m;
    }

    @Override
    public void run() {
        while (true) {
            this.espera();
            if (posicion) {
                //Auto que viene direccion Norte-Sur
                lugar = mon.entrarCocheDelNorte();
                this.transicion();
                mon.salirCocheDelNorte();
                posicion = false; //Auto que entro del Norte y salio en el Sur debe cambiar su posicion
            } else {
                //Auto que viene direccion Sur-Norte
                lugar = mon.entrarCocheDelSur();
                this.transicion();
                mon.salirCocheDelSur();
                posicion = true; //Auto que entro del Sur y salio en el Norte debe cambiar su posicion
            }
        }
    }

    public void transicion() {
        try {
            Thread.sleep(100 * lugar); //De esta forma se logra que si multiples autos ingresan al puente, el que ingresa primero tenga un tiempo de espera menor que los que ingresan despues
        } catch (InterruptedException ex) {
        }
    }

    public void espera() {
        try {
            Thread.sleep((int) (Math.random() * 4000)); //Espera aleatoria para que no lleguen todos los autos juntos
        } catch (InterruptedException ex) {
        }
    }

    public static void main(String[] args) {
        int i;
        GestionaTrafico mon = new GestionaTrafico();
        for (i = 0; i < 13; i++) {
            Auto a1 = new Auto(true, mon);
            Auto a2 = new Auto(false, mon);
            a1.start();
            a2.start();
        }
    }

}
