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
}
