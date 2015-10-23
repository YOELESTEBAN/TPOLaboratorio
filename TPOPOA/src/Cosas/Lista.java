package Cosas;



import java.io.*;
import Cosas.ErrorTDA;

public class Lista implements Serializable {

    private Nodo cab;

//*********************************************CONSTRUCTORES********************************************
    public Lista() {
        cab = null;
    }

    /**
     * Constructor de clonacion
     */
    public Lista clonar() {
        Lista l = new Lista();
        Nodo aux = this.cab;
        int i = 1;
        while (aux != null) {
            try {
                l.insertarElemento(aux.getElemento(), i);
                i++;
                aux = aux.getEnlace();
            } catch (ErrorTDA e) {
                System.out.println(e.getMensaje());
            }
        }
        return l;
    }
//*********************************************MODIFICADORES********************************************

    public void insertarPrimerPosicion(Object o) {
        Nodo insertar = new Nodo(o);
        insertar.setEnlace(cab);
        this.cab = insertar;
    }

    /**
     * Inserta los elementos en una posicion dada por el usuario.
     *
     * @throws Error de posicion no existente.
     */
    public void insertarElemento(Object o, int posicion) throws ErrorTDA {
        // caso 1: la lista esta vacia, entonces inserta en el primer lugar
        // caso 2: la lista no esta vacia, inserta despues del primero,

        if (posicion < 1 || posicion > longitud() + 1) {
            throw new ErrorTDA(4);
        } else {
            Nodo insertar = new Nodo(o);
            if (posicion == 1) {
                insertar.setEnlace(cab);
                this.cab = insertar;
            } else {
                Nodo aux = this.cab;
                int i = 1;
                while (i < (posicion - 1)) {
                    i++;
                    aux = aux.getEnlace();

                }
                insertar.setEnlace(aux.getEnlace());
                aux.setEnlace(insertar);
            }
        }
    }

    /**
     * Elimina un elemento dada una posicion. Larga una exception cuando la
     * posicion no es valida
     */
    public void eliminarElemento(int posicion) throws ErrorTDA {

        if (posicion <= 0 || posicion >= longitud() + 1) {
            throw new ErrorTDA(4);
        } else {
            //Caso 1: Eliminar en la primera posicion..
            if (posicion == 1) {
                cab = cab.getEnlace();
            } else {
                //caso 2: posicion distinta a 1
                Nodo aux = this.cab;
                int i = 1;
                while (i < (posicion - 1)) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
        }
    }

    public void eliminarPrimerElemento() {
        if (!this.esListaVacia()) {
            cab = cab.getEnlace();
        }
    }

    /**
     * Vacia toda la lista
     */
    public void vaciar() {
        Nodo aux = this.cab;
        int i = 1;

        while (aux != null) {
            try {
                eliminarElemento(i);
                i++;
                aux = aux.getEnlace();
            } catch (ErrorTDA e) {
            }
        }
    }

    public void mostrar() {
        Nodo aux = cab;
        while (aux != null) {
            System.out.println(aux.getElemento());
            aux = aux.getEnlace();
        }
    }

    //*********************************************OBSERVADORES*********************************************
    /**
     * Cuenta la cantidad de nodos que tiene la lista
     */
    public int longitud() {
        Nodo aux = this.cab;
        int t = 0;
        while (aux != null) {
            t++;
            aux = aux.getEnlace();
        }
        return t;
    }

    /**
     * Dice si la lista esta vacia o no
     */
    public boolean esListaVacia() {
        return this.cab == null;
    }

    /**
     * Devuelve el objecto dada una posicion
     */
    public Object recuperarElemento(int posicion) {
        Nodo aux = this.cab;
        if (posicion <= 0 || posicion > longitud()) {
            return null;
        } else {
            int i = 1;
            while (aux != null) {
                if (i == posicion) {
                    return aux.getElemento();
                } else {
                    i++;
                    aux = aux.getEnlace();
                }
            }
        }
        return null;
    }

    /**
     * Devuelve la posición del objecto ingresado.
     */
    public int localizarElemento(Object elem) {
        int pos = -1, i = 1;
        Nodo aux = this.cab;
        boolean encontrado = false;
        while (i <= longitud() && !encontrado) {
            if (elem.equals(aux.getElemento())) {
                pos = i;
                encontrado = true;
            } else {
                aux = aux.getEnlace();
                i++;
            }
        }
        return pos;
    }

    public Object recuperarElementoClave(Object elem) {
        int pos = -1, i = 1;
        Nodo aux = this.cab;
        Object elemAux = null;
        boolean encontrado = false;
        while (i <= longitud() && !encontrado) {
            if (elem.equals(aux.getElemento())) {
                elemAux = aux.getElemento();
                encontrado = true;
            } else {
                aux = aux.getEnlace();
                i++;
            }
        }
        return elemAux;
    }
}
