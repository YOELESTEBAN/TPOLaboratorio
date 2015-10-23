package Cosas;

import java.io.Serializable;


class Nodo implements Serializable {

    private Nodo enlace;
    private Object elem;
//*********************************************CONSTRUCTORES********************************************

    public Nodo(Object elem) {
        this.elem = elem;
        this.enlace = null;
    }

    public Nodo(Object elem, Nodo enlace) {
        this.enlace = enlace;
        this.elem = elem;
    }
//*********************************************MODIFICADORES********************************************

    public Nodo getEnlace() {
        return enlace;
    }

    public Object getElemento() {
        return elem;
    }//*********************************************OBSERVADORES*********************************************

    public void setEnlace(Nodo enlace) {
        this.enlace = enlace;
    }

    public void setElemento(Object elem) {
        this.elem = elem;
    }
}
