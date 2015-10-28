
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

    public String getNumeroCuenta() {
        return this.numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void ingresarMonto(double monto) {

        this.setSaldo(this.getSaldo() + monto);
    }

    public void extraerMonto(double monto) {
        this.setSaldo(this.getSaldo() - monto);

    }

    public void agregarMovimiento(Movimiento nuevo) {
        this.movimientos.add(nuevo);
    }

    /**
     * devuelve todos los movimientos de la cuenta
     */
    public ArrayList getMovimientos() {
        return movimientos;
    }

    public String movimientosAcadena() {
        String salida = "";
        for (int i = 0; i < this.movimientos.size(); i++) {
            Movimiento aux = (Movimiento) movimientos.get(i);
            salida = salida + aux.aCadena() + "\n";
        }
        return salida;
    }

}
