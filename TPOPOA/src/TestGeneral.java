
import java.util.ArrayList;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

/**
 *
 * @author ndrpochin
 */
public class TestGeneral {
    public static void main (String[] args){
        System.out.println("");
        
        
        Cliente uno = new Cliente ("Juan Perez", "1", "Aca a la vuelta");
        Cliente dos = new Cliente ("Pedro Perez", "2", "Aca ");
        Cliente tres = new Cliente ("Luis Perez", "3", "en la esquina");
        Cuenta cuenUno = new Cuenta (uno, "1");
        Cuenta cuenDos = new Cuenta (dos, "2");
        Cuenta cuenTres = new Cuenta (tres, "3");
        uno.setCuenta(cuenUno);
        dos.setCuenta(cuenDos);
        tres.setCuenta(cuenTres);
       
        ArrayList cuentas = new ArrayList();
        cuentas.add(cuenUno);cuentas.add(cuenDos);cuentas.add(cuenTres);
        
        ArrayList clientes = new ArrayList();
        clientes.add(uno);clientes.add(dos);clientes.add(tres);
        
        Banco bancoMain = new Banco(clientes, cuentas);
        System.out.println(bancoMain.aCadena());
        
        //simulacion de algunos movimientos de una cuenta
        System.out.println(" ");
        System.out.println("");
        cuenUno.ingresarMonto(1000);
        cuenUno.ingresarMonto(500);
        System.out.println("saldo actual "+cuenUno.getSaldo());            
        cuenUno.extraerMonto(1100);
        System.out.println("saldo actual "+cuenUno.getSaldo());            
        cuenUno.extraerMonto(1000); //aca deberia dar error
        System.out.println("movimientos \n"+cuenUno.movimientosAcadena());
        
    }
}
