package Aspect;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import todo.Banco;
import todo.Cajero;
import todo.Movimiento;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jonathan
 */
public class BancoMonitor extends Observable implements Runnable {

    private Cajero cajero1;
    private Cajero cajero2;
    private ArrayList clientes;
    private LinkedList cola;

    public BancoMonitor(ArrayList nuevosCli, Cajero c1, Cajero c2) {
        clientes = nuevosCli;
        cajero1 = c1;
        cajero2 = c2;
        cola = new LinkedList();

    }

    /*hacer cola estaba sincronizado*/
    public synchronized void hacerCola(HiloCliente c) {
        if (!cola.isEmpty()) {

            this.setChanged();
            this.notifyObservers("El cliente" + c.getNumeroCuenta() + " esta esperando en la cola por una caja");

            cola.add(c);
            c.encolar();
            this.esperar();
        } else {
            if (cajero1.estaOcupado() && cajero2.estaOcupado()) {

                this.setChanged();
                this.notifyObservers(" los dos cajeros estan ocupados, el cliente " + c.getNumeroCuenta() + " debe entrar en la cola");

                cola.add(c);
                c.encolar();
                this.esperar();
            }
        }
    }

    public synchronized void solicitarCajero(HiloCliente c) {

        /*this.setChanged();
        this.notifyObservers("Cliente " + c.getNumeroCuenta() + " esta solicitando cajero");

        this.setChanged();
        this.notifyObservers("tamaño de la cola " + cola.size());*/

        while (c.estaEnCola()) {  //El cliente se despierta y se pregunta si esta en cola
            this.esperar();     //Si esta en cola se duerme
        }

        this.setChanged();
        this.notifyObservers("El cliente " + c.getNumeroCuenta() + " salio de la cola, va al cajero");

        if (!cajero1.estaOcupado()) {

            this.setChanged();
            this.notifyObservers("El cliente " + c.getNumeroCuenta() + " ocupo cajero 1");

            cajero1.ocupar();
            c.asignarCajero(cajero1);
        } else {

            this.setChanged();
            this.notifyObservers("El cliente " + c.getNumeroCuenta() + " ocupo cajero 2");

            cajero2.ocupar();
            c.asignarCajero(cajero2);
        }
    }

    public synchronized void liberarCajero(HiloCliente c) {
        c.getCajero().desocupar();
        if (!cola.isEmpty()) {
            HiloCliente proximoCliente;
            proximoCliente = (HiloCliente) cola.remove();
            proximoCliente.desencolar();
        }

        this.setChanged();
        this.notifyObservers("El cliente " + c.getNumeroCuenta() + " libero el cajero ");
        
        this.notifyAll();
    }

    public void operar(int n, HiloCliente c) {
        /*
         Se loguea previamente utilizando ASPECTOS
         1 - depositar
         2 - extraer
         3 - consultar saldo
         4 - consultar movimientos
         */
        switch (n) {
            case 1:
                generarDeposito(c);
                System.out.println("El cliente " + c.getNumeroCuenta() + "- hizo un deposito");
                break;
            case 2:
                generarExtraccion(c);
                System.out.println("El cliente " + c.getNumeroCuenta() + "- hizo una extraccion");
                break;
            case 3:
                System.out.println("El cliente " + c.getNumeroCuenta() + "operar - verSaldo: " + verSaldo(c));
                break;
            case 4:
                consultarMovimientos(c);
                System.out.println("El cliente " + c.getNumeroCuenta() + "el cliente consulto los movimientos");
                break;
            default:
                System.out.print("no deberia suceder ");
                break;

        }
        try {
            Thread.sleep(5000);
            //dormimos el hilo para que los demas hilos esperen un rato. simulamos que demora en operar.
        } catch (InterruptedException ex) {
            Logger.getLogger(BancoMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generarDeposito(HiloCliente c) {
        double montoNuevo = Math.floor(Math.random() * (1000) + 1);
        Movimiento mov = new Movimiento("DEPOSITO", montoNuevo);
        c.getCajero().depositar(c.getNumeroCuenta(), montoNuevo);
        c.getCajero().guardarNuevoMovimiento(c.getNumeroCuenta(), mov);
        /*debe devolver un monto generado de forma aleatoria*/

    }

    /**
     * Sabiendo el saldo de su cuenta, el cliente genera un monto aleatorio, que
     * puede ser menor, igual o mayor que el saldo de su cuenta, con fines de
     * realizar una buena simulacion.
     */
    private void generarExtraccion(HiloCliente c) {
        double saldoCuenta = c.verCliente().getSaldoCuenta();
        double montoNuevo = Math.floor(Math.random() * (3) - 1); //Genera un valor entero entre -1 y 1
        montoNuevo = montoNuevo + saldoCuenta;
        Movimiento mov = new Movimiento("EXTRACCION", montoNuevo);
        c.getCajero().extraer(c.getNumeroCuenta(), montoNuevo);
        c.getCajero().guardarNuevoMovimiento(c.getNumeroCuenta(), mov);
    }

    private double verSaldo(HiloCliente c) {
        return (c.getCajero().verSaldoUnaCuenta(c.getNumeroCuenta()));
    }

    /**
     * devuelve una lista con los ultimos movimientos de una cuenta ingresada
     */
    private ArrayList consultarMovimientos(HiloCliente c) {
        return (c.getCajero().verMovimientos(c.getNumeroCuenta()));
    }

    public void esperar() {
        try {
            this.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(BancoMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        //el monitor solamente lo mantenemos vivo
        while (true);
    }
}
