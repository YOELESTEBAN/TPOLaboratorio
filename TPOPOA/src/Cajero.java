

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jonathan
 */
public class Cajero{
    private boolean ocupado;

        /**
         * Los cajeros se construyen vacios. Pocho
         */
    public Cajero(){
        ocupado=false;
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
}
