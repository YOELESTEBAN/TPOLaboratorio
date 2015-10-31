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
public class Cajero extends Observable{
    private Banco banco;
    private boolean ocupado;

        /**
         * Los cajeros se construyen vacios. Pocho
         */
    public Cajero(){
        ocupado=false;
    }
    public void setBanco(Banco b){
        banco = b;
    }
   
    public boolean estaOcupado(){
        return ocupado;
    }

    public void ocupar() {
        ocupado = true;
    }
    public void desocupar(){
        ocupado = false;
    }

    public void depositar(String numeroCuenta, double montoNuevo) {
        banco.depositar(numeroCuenta, montoNuevo);
    }

    public void guardarNuevoMovimiento(String numeroCuenta, Movimiento mov) {
        banco.guardarNuevoMovimiento(numeroCuenta, mov);
    }

    public void extraer(String numeroCuenta, double montoNuevo) {
        banco.extraer(numeroCuenta, montoNuevo);
    }

    public double verSaldoUnaCuenta(String numeroCuenta) {
        return banco.verSaldoUnaCuenta(numeroCuenta);
    }

    public ArrayList verMovimientos(String numeroCuenta) {
        return banco.verMovimientos(numeroCuenta);
    }
}
