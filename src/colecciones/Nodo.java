package colecciones;

public class Nodo <T> {
    
    T valor;
    Nodo<T> next ;

    //Constructores para los nodos 
    public Nodo (){}

    public Nodo (T valor){
        this.valor = valor;
    }

    //Geters y Seters de Valores 

    public T getValor() {
        return valor;
    }

    public void setValor(T valor ){
        this.valor = valor;
    }

    //Geters y Seters de los nodos 

    public Nodo<T> getNext (){
        return next;
    }

    public void setNext (Nodo <T> next ) {
        this.next=next;
    }


}
