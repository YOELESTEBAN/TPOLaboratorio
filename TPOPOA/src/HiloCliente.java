
import java.util.Random;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yoni
 */
public class HiloCliente implements Runnable {

    private Cliente cliente;
    private BancoMonitor monitor;
    private boolean enCola;
    private Cajero cajero;

    public HiloCliente(Cliente c, BancoMonitor m) {
        this.cliente = c;
        this.monitor = m;
        this.enCola = false;
    }

    public Cliente verCliente() {
        return cliente;
    }

    
    public void encolar() {
        this.enCola = true;
    }

    public void desencolar() {
        this.enCola = false;
    }

    public boolean estaEnCola() {
        return this.enCola;
    }

    public void asignarCajero(Cajero c) {
        this.cajero = c;
    }

    public Cajero getCajero() {
        return this.cajero;
    }
    public String getNumeroCuenta (){
        return cliente.getNumeroCuenta();
    }

    @Override
    public void run() {
        /*
         Entra al banco
         Hace cola
         Obtiene el cajero
         Se loguea
         realiza la operacion deseada
         Libera el cajeros
         Muere 
         */

        int n = (int) Math.floor(Math.random() * (4) + 1);
        monitor.hacerCola(this);
        monitor.solicitarCajero(this);
        monitor.operar(n, this);
        monitor.liberarCajero(this);
    }

}
