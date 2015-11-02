package todo;

import java.util.ArrayList;
import java.util.Observable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jonathan
 */
public class Cajero extends Observable {

    private Banco banco;
    private boolean ocupado;

    /**
     * Los cajeros se construyen vacios. Pocho
     */
    public Cajero() {
        ocupado = false;
    }

    public void setBanco(Banco b) {
        banco = b;
    }

    public boolean estaOcupado() {
        return ocupado;
    }

    public void ocupar() {
        ocupado = true;
    }

    public void desocupar() {
        ocupado = false;
    }

    public void depositar(String numeroCuenta, double montoNuevo) {
        banco.depositar(numeroCuenta, montoNuevo);
        this.setChanged();
        this.notifyObservers("El cliente " + numeroCuenta + "- hizo un deposito: "+montoNuevo);
    }

    public void guardarNuevoMovimiento(String numeroCuenta, Movimiento mov) {
        this.setChanged();
        this.notifyObservers("El cliente "+ numeroCuenta+" guardo movimientos");
        banco.guardarNuevoMovimiento(numeroCuenta, mov);
    }

    public void extraer(String numeroCuenta, double montoNuevo) {
        banco.extraer(numeroCuenta, montoNuevo);
        this.setChanged();
        this.notifyObservers("El cliente " + numeroCuenta + "- hizo una extraccion: "+montoNuevo);
    }

    public double verSaldoUnaCuenta(String numeroCuenta) {
        Double saldo = banco.verSaldoUnaCuenta(numeroCuenta);
        this.setChanged();
        this.notifyObservers("El cliente " + numeroCuenta + "operar - verSaldo: " + saldo);
        return saldo;
        
    }

    public ArrayList verMovimientos(String numeroCuenta) {
        this.setChanged();
        this.notifyObservers("El cliente " + numeroCuenta + " consulto los movimientos");
        return banco.verMovimientos(numeroCuenta);
    }
}
