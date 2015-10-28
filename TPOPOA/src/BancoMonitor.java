
import java.util.ArrayList;
import java.util.LinkedList;
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
public class BancoMonitor extends Thread {

    private Banco elBanco;
    private Cajero cajero1;
    private Cajero cajero2;
    private ArrayList clientes;
    private LinkedList cola;

    public BancoMonitor(Banco nuevoBanco, ArrayList nuevosCli) {
        elBanco = nuevoBanco;
        clientes = nuevosCli;
        this.cajero1 = new Cajero();
        this.cajero2 = new Cajero();
        this.cola = new LinkedList();

    }

    /*hacer cola estaba sincronizado*/
    public void hacerCola(HiloCliente c) {
        if (!cola.isEmpty()) {
            System.out.println("El cliente" + c.getNumeroCuenta() + " esta esperando en la cola por una caja");
            cola.add(c);
            c.encolar();
            this.esperar();
        } else {
            if (cajero1.estaOcupado() && cajero2.estaOcupado()) {
                System.out.println(" los dos cajeros estan ocupados, el cliente "+c.getNumeroCuenta()+" debe entrar en la cola");
                cola.add(c);
                c.encolar();
                this.esperar();
            }
        }
    }

    public synchronized void solicitarCajero(HiloCliente c) {
        System.out.println("Cliente " + c.getNumeroCuenta() + " esta solicitando cajero");
        System.out.println("tamaño de la cola " + cola.size());
        while (c.estaEnCola()) {  //El cliente se despierta y se pregunta si esta en cola
            this.esperar();     //Si esta en cola se duerme
        }
        System.out.println("El cliente " + c.getNumeroCuenta() + " salio de la cola, va al cajero");//Sino procede a solicitar un cajero
        if (!cajero1.estaOcupado()) {
            System.out.println("El cliente " + c.getNumeroCuenta() + " cliente ocupo cajero 1");

            cajero1.ocupar();
            c.asignarCajero(cajero1);
        } else {
            System.out.println("El cliente " + c.getNumeroCuenta() + " cliente ocupo cajero 2");
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
        System.out.println("El cliente " + c.getNumeroCuenta() + " El cliente libero el cajero ");
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
        elBanco.depositar(c.getNumeroCuenta(), montoNuevo);
        elBanco.guardarNuevoMovimiento(c.getNumeroCuenta(), mov);
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
        elBanco.extraer(c.getNumeroCuenta(), montoNuevo);
        elBanco.guardarNuevoMovimiento(c.getNumeroCuenta(), mov);
    }

    private double verSaldo(HiloCliente c) {
        return (elBanco.verSaldoUnaCuenta(c.getNumeroCuenta()));
    }

    /**
     * devuelve una lista con los ultimos movimientos de una cuenta ingresada
     */
    private ArrayList consultarMovimientos(HiloCliente c) {
        return (elBanco.verMovimientos(c.getNumeroCuenta()));
    }

    public synchronized void esperar() {
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
