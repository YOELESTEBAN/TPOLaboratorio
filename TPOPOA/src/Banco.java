
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

    public Banco() {
        clientes = new ArrayList();
        cuentas = new ArrayList();
    }
    
    public static void main(String[]args){
        Movimiento mov = new Movimiento("1",100);
        System.out.println(mov.getFecha().toString());
        
    }
}
