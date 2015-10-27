/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jonathan
 */
public class Cliente {

    private String nombre;
    private String DNI;
    private String domicilio;
    private Cuenta cuenta;
    private String contraseña;

    public Cliente(String nombre, String DNI, String domicilio) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.domicilio = domicilio;
    }

    public String getNombre() {
        return nombre;
    }

    

    public String getDNI() {
        return DNI;
    }

    

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNumeroCuenta() {
        return (cuenta.getNumeroCuenta());
        
    }
    /**Este metodo es solo para poder hacer una buena simulacion.
      NO DEBERIA DE EXISTIR
    */
    public double getSaldoCuenta(){
        return(cuenta.getSaldo());
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
}
