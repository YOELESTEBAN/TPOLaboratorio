package todo;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yoni
 */
public class Banco {

    private ArrayList clientes;
    private ArrayList cuentas;
    private Cajero cajero1,cajero2;

    public Banco() {
        clientes = new ArrayList();
        cuentas = new ArrayList();
    }

    public Banco(ArrayList cli, ArrayList ctas) {
        this.clientes = cli;
        this.cuentas = ctas;
        cajero1 = new Cajero();
        cajero2 = new Cajero();
    }

    public Cajero getCajero1() {
        return cajero1;
    }

    public Cajero getCajero2() {
        return cajero2;
    }


    /**
     * Depositar recibe un numero de cuenta, y un monto aleatorio generado en el
     * BancoMonitor, no deberia dar errores de ningùn tipos
     */
    public void depositar(String numeroCuenta, double montoNuevo) {
        Cuenta aux = buscarCuenta(numeroCuenta);
        aux.ingresarMonto(montoNuevo);
    }

    /**
     * Extraer recibe como parametro un numero de cuenta y un monto aleatorio
     * generado en el BancoMonitor. Podria lanzar un error cuando el saldo es
     * insuficiente para realizar la operacion. Tratarlo maa adelantes
     */
    public void extraer(String numeroCuenta, double montoNuevo) {
        Cuenta aux = buscarCuenta(numeroCuenta);
        if (montoNuevo <= aux.getSaldo()) {
            aux.ingresarMonto(montoNuevo);
        } else {
            System.out.println("Error - Saldo insuficiente para realizar la extracción");
        }
    }

    private Cuenta buscarCuenta(String numero) {
        int index = 0;
        Cuenta aux = null;
        boolean band = false;
        while ((index < cuentas.size()) && (!band)) {

            aux = (Cuenta) cuentas.get(index);
            band = aux.getNumeroCuenta().equals(numero);
            index++;
        }
        if (!band) //salio en false, o sea no encontro la cuenta...
        {
            System.out.println("no deberia pasar");
            aux = null;
        }
        return aux;

    }

    /**
     * Dado un numero de cuenta, devuelve el saldo de esa cuenta.
     */
    public double verSaldoUnaCuenta(String numero) {
        double saldo;
        Cuenta aux = buscarCuenta(numero);
        if (aux != null) //supongamos que la cuenta exista.
        {
            saldo = aux.getSaldo();
        } //AQUI DEBERIA COMUNICAR A LA INTERFAZ GRAFICA LO SUCEDIDO
        //COMUNICAR A SUS OBSERVADORES
        else {
            System.out.println("Numero de cuenta inexistente");
            saldo = -99999;
        }
        //esto no deberia ocurrir porque el cliente se loggeo y la cuenta por lo tanto, existe
        return saldo;
    }

    public void guardarNuevoMovimiento(String numero, Movimiento mov) {
        Cuenta aux = buscarCuenta(numero);
        aux.agregarMovimiento(mov);

    }

    public ArrayList verMovimientos(String numero) {
        Cuenta aux = buscarCuenta(numero);
        return (aux.getMovimientos());
    }

    public String aCadena() {
        String salida = "";
        for (Object cliente : clientes) {
            Cliente aux = (Cliente) cliente;
            salida = salida + " " + aux.getNombre() + "- numero cuenta" + aux.getNumeroCuenta() + "\n";
        }
        return salida;
    }
}
