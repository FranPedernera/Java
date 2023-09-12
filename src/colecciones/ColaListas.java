package colecciones;
import colecciones.Nodo;

public class ColaListas<T> implements Lista<T> {
    Nodo <T> cabeza;
    int elemento;
    
    public ColaListas(){
        cabeza = null;
        elemento = 0;
    }

    public boolean esVacia(){
        return cabeza == null;
    }

    public boolean encolar(T elem){
        Nodo<T> nuevo = new Nodo<T>(elem);

        nuevo.setNext(cabeza);
        cabeza = nuevo;
        elemento++;
        return true ;
        
    }

    public T desencolar(){
        T valor ;
        Nodo<T> aux = cabeza;
        Nodo<T> aneterior = null;

        if (cabeza==null){
            throw new IllegalStateException("La cola ya esta vacia ");
        }else {
            while(aux.getNext()!=null){
                aneterior=aux;
                aux.setNext(aux.getNext());
            }
            valor = aux.getValor();
            aneterior.setNext(aux.getNext());
            return valor;
            

        }

    }

    public T primero(){
        Nodo<T> aux = cabeza;
        T Valor;
        
        while(aux.getNext()!=null){
            aux.setNext(aux.getNext());
        }
        Valor= aux.getValor();
        return Valor;
    }




    
}
