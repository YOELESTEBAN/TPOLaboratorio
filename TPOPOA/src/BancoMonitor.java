
import java.util.ArrayList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jonathan
 */
public class BancoMonitor {

    private Banco elBanco;
    private Cajero cajero1;
    private Cajero cajero2;
    private ArrayList clientes;
    private Queue cola;

    public synchronized void hacerCola(HiloCliente c) {
        if (!cola.isEmpty()) {
            cola.add(c);
            c.encolar();
            this.esperar();

        }

    }

    public synchronized void solicitarCajero(HiloCliente c) {
        while (c.estaEnCola()) {  //El cliente se despierta y se pregunta si esta en cola
            this.esperar();     //Si esta en cola se duerme
        }                       //Sino procede a solicitar un cajero
        if (!cajero1.estaOcupado()) {
            cajero1.ocupar();
            c.asignarCajero(cajero1);
        } else {
            cajero2.ocupar();
            c.asignarCajero(cajero2);
        }
    }

    public synchronized void liberarCajero(HiloCliente c) {
        if (!cola.isEmpty()) {
            HiloCliente proximoCliente;
            proximoCliente = (HiloCliente) cola.remove();
            proximoCliente.desencolar();
            c.getCajero().desocupar();
            this.notifyAll();
        }
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

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                System.out.print("no deberia suceder ");

        }
    }

    private void generarDeposito(HiloCliente c) {
    double montoNuevo = Math.floor(Math.random() * (1000) + 1); 
    elBanco.depositar(c.getNumeroCuenta(), montoNuevo);
        /*debe devolver un monto generado de forma aleatoria*/
    
    }

    private double extraer() {
        /*debe devolver un monto generado de forma aleatoria*/
        return 1;
    }

    public void esperar() {
        try {
            this.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(BancoMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
