package colecciones.lista;
import colecciones.*;

public class ListaEnlazada<T>  {

    Nodo<T> cabeza ;

    public ListaEnlazada(){
        cabeza = null;
    }

	/**
	* Agrega un elemento al final de la lista.
	* @param elem el elemento a agregar
	* @return {@code true} sii el elemento pudo ser agregado
	*/
	public boolean agregar(T elem){

        Nodo<T> nuevNodo = new Nodo <T> (elem);
        
        if(cabeza.getValor()==null){
            cabeza = nuevNodo;
            return true ;
        }else {
            Nodo<T> aux = cabeza;
            while (aux.getNext()!=null){
                aux = aux.getNext();
            }
            //Duda sobre que hace estoo
            aux.setNext(nuevNodo);
            return true;
            
        }
        
    }


    /**
	* Agrega todos los elementos de otra lista, al final de esta lista.
	* @param otraLista lista conteniendo todos los elementos a agregar al final de esta lista
	* @return {@code true} sii todos los elementos en {@code otraLista} fueron agregados
	*/
	public boolean agregarTodos(Lista<T> otraLista){
        return true;
    }

    /**
	* Agrega un elemento en una posicion particular de la lista.
	* @param elem el elemento a agregar
	* @param indice el indice donde se agrega el elemento
	* @return {@code true} sii el elemento pudo ser agregado
	* @throws IndexOutOfBoundsException si {@code indice} &lt; {@code 0}
	*/
	public boolean insertar(T elem, int indice){
        
        Nodo <T> nuevo = new Nodo<T>(elem);

        try  {
            if (indice < 0){
                throw new IndexOutOfBoundsException ("Indice invalido");
            }
        } catch (Exception e) {
            System.out.println("Error producido" + e);
            return false ;
        }
        
        if (indice == 0){
            nuevo.setNext(cabeza);
            cabeza = nuevo;
            return true;
        }else{
            Nodo<T> aux = cabeza;
            Nodo<T> anterior = null;
                int i = 0;
                while(aux.getNext()!=null && i < indice){
                    anterior = aux;
                    aux = aux.getNext();
                    i++;
                }
                if (indice == i){
                    anterior.setNext(nuevo);
                    nuevo.setNext(aux);
                    
                }

            
        }
        return true;
    }



}
