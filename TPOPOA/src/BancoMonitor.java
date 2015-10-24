
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
        while(c.estaEnCola()){  //El cliente se despierta y se pregunta si esta en cola
            this.esperar();     //Si esta en cola se duerme
        }                       //Sino procede a solicitar un cajero
        if(!cajero1.estaOcupado()){
            cajero1.ocupar();
            c.asignarCajero(cajero1);           
        }
        else{
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

    public void operar() {
        /*
        Se loguea previamente utilizando ASPECTOS
        1 - depositar
        2 - extraer
        3 - consultar saldo
        4 - consultar movimientos
        */
    }
    public void esperar(){
        try {
            this.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(BancoMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
