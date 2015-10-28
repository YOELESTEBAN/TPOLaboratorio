/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cosas;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kozur
 */
public class GestionaTrafico {

    private int cantAutos;
    private int direccion; //(0 = liberado)(1 = Norte-Sur)(2 = Sur-Norte)
    private int norte;
    private int sur;

    public GestionaTrafico() {
        cantAutos = 0;
        direccion = 0;
        norte = 0;
        sur = 0;
    }

    public synchronized int entrarCocheDelNorte() {
        while ((this.condicion(true)) || (norte == 10)) {
            this.esperar();
        }
        direccion = 1; //De esta forma bloquea la entrada de autos del otro lado
        cantAutos++;
        norte++; //acumulador de autos, si llega a 10 no pueden entrar mas autos del Norte, si entra uno del Sur, se reinicia.
        sur = 0; //Aca reinicia el acumulador del Sur
        System.out.println(Thread.currentThread().getName() + ": entra por el norte");
        this.notifyAll();
        return cantAutos;
    }

    public synchronized void salirCocheDelNorte() {
        cantAutos--;
        if (cantAutos == 0) {
            direccion = 0; //El ultimo en salir libera el bloqueo de entrada de autos del otro lado
        }
        System.out.println(Thread.currentThread().getName() + ": sale por el sur");
        this.notifyAll();
    }

    public synchronized int entrarCocheDelSur() {
        while ((this.condicion(false)) || (sur == 10)) {
            this.esperar();
        }
        direccion = 2; //De esta forma bloquea la entrada de autos del otro lado
        cantAutos++;
        sur++; //acumulador de autos, si llega a 10 no pueden entrar mas autos del Sur, si entra uno del Norte, se reinicia.
        norte = 0; //Aca reinicia el acumulador del Norte
        System.out.println(Thread.currentThread().getName() + ": entra por el sur");
        this.notifyAll();
        return cantAutos;
    }

    public synchronized void salirCocheDelSur() {
        cantAutos--;
        if (cantAutos == 0) {
            direccion = 0; //El ultimo en salir libera el bloqueo de entrada de autos del otro lado
        }
        System.out.println(Thread.currentThread().getName() + ": sale por el norte");
        this.notifyAll();
    }

    private boolean condicion(boolean norte) {
        boolean con;
        if (norte) {
            con = direccion == 2;
        } else {
            con = direccion == 1;
        }
        return con;
    }

    private void esperar() {
        try {
            this.wait();
        } catch (InterruptedException ex) {
        }
    }
}
