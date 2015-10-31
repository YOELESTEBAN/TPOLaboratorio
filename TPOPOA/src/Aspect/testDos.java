package Aspect;

import Interfaz.PantallaPrincipal;
import java.awt.EventQueue;
import todo.Banco;
import todo.Cliente;
import todo.Cuenta;
import java.util.ArrayList;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import todo.Cajero;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yoni
 */
public class testDos {

    /**
     * yoni:- mejorar constructores Banco, cliente y Cuenta
     */
    public static void main(String[] args) {

        /*NO TOCAR GUACHOOOO!*/
        Cliente a = new Cliente("Yoni", "1", "aca cerca");
        Cliente b = new Cliente("sadg", "2", "aca cerca");
        Cliente c = new Cliente("asfdi", "3", "aca cerca");
        Cliente d = new Cliente("asfdi", "4", "aca cerca");
        Cliente e = new Cliente("asfdi", "5", "aca cerca");
        Cuenta un = new Cuenta(a, "1");
        Cuenta dos = new Cuenta(b, "2");
        Cuenta tres = new Cuenta(c, "3");
        Cuenta cua = new Cuenta(d, "4");
        Cuenta cin = new Cuenta(e, "5");
        a.setCuenta(un);
        b.setCuenta(dos);
        c.setCuenta(tres);
        d.setCuenta(cua);
        e.setCuenta(cin);

        ArrayList lisClientes = new ArrayList();
        lisClientes.add(a);
        lisClientes.add(b);
        lisClientes.add(c);
        lisClientes.add(d);
        lisClientes.add(e);
        ArrayList lisCtas = new ArrayList();
        lisCtas.add(un);
        lisCtas.add(dos);
        lisCtas.add(tres);
        lisCtas.add(cua);
        lisCtas.add(cin);

        Banco elBanco = new Banco(lisClientes, lisCtas);
        System.out.println(elBanco.aCadena());
        System.out.println("");
        Cajero cajero1 = elBanco.getCajero1();
        Cajero cajero2 = elBanco.getCajero2();
        cajero1.setBanco(elBanco);
        cajero2.setBanco(elBanco);
        BancoMonitor moni = new BancoMonitor(lisClientes, cajero1, cajero2);
        PantallaPrincipal pant = new PantallaPrincipal(moni);
        pant.setVisible(true);
        HiloCliente h1n1 = new HiloCliente(a, moni);
        HiloCliente h1n2 = new HiloCliente(b, moni);
        HiloCliente h1n3 = new HiloCliente(c, moni);
        HiloCliente h1n4 = new HiloCliente(d, moni);
        HiloCliente h1n5 = new HiloCliente(e, moni);
        Thread hm = new Thread(moni);
        hm.start();
        h1n1.start();
        h1n2.start();
        h1n3.start();
        h1n4.start();
        h1n5.start();

        try {
            hm.join();
            h1n1.join();
            h1n2.join();
            h1n3.join();
            h1n4.join();
            h1n5.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(testDos.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Ahora vemos los movimientos ");
        System.out.println(un.movimientosAcadena());

    }
}
