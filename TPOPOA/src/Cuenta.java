
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
public class Cuenta {
    private Cliente cliente;
    private String numeroCuenta;
    private double saldo;
    private ArrayList movimientos;

    public Cuenta(Cliente cliente, String numeroCuenta) {
        this.cliente = cliente;
        this.numeroCuenta = numeroCuenta;
        saldo = 0;
        movimientos = new ArrayList();
    }
}
