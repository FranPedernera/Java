package colecciones;


public class ListaEnlazada<T> implements Lista<T> {

    Nodo<T> cabeza ;
    int cantElem;

    public ListaEnlazada(){
        cabeza = null;
        cantElem = 0;
    }

	/**
	* Agrega un elemento al final de la lista.
	* @param elem el elemento a agregar
	* @return {@code true} sii el elemento pudo ser agregado
	*/
    @Override
	public boolean agregar(T elem){

        Nodo<T> nuevNodo = new Nodo <T> (elem);
        
        if(cabeza.getValor()==null){
            cabeza = nuevNodo;
            cantElem++;
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

        int i = 0;

        if (cabeza ==  null && otraLista.elementos()==0){
            return false;
        }else {

            while(i <= otraLista.elementos()){
                agregar(otraLista.obtener(i));
                i++;
            }
            return i == otraLista.elementos();
        }
        
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
                if (indice ==cantElem ){
                    anterior.setNext(nuevo);
                    nuevo.setNext(aux);
                    
                }

            
        }
        return true;
    }





	public T eliminar(int indice){
        Nodo<T> aux = cabeza;
        Nodo <T> anterior = null;
        T elem;

        if (indice < 0 || indice > cantElem){
            throw new IndexOutOfBoundsException ("Indice ingresado invalido");
        }
        int i = 0;
        while ( aux.getNext()!=null &&  i<indice){
            anterior = aux;
            aux.setNext(aux.getNext());
        }

        if(anterior == null){
            elem= aux.getValor();
            cabeza = aux.getNext();
            cantElem--;
            return elem;
        }else{
            elem = aux.getValor();
            anterior.setNext(aux.getNext());
            cantElem--;
            return elem;
        }

    }

	/**
	* Retorna la porción de esta lista entre los índice especificados {@code desdeInd}, inclusivo, y {@code hastaInd}, exclusivo, en una nueva lista.
	* Si {@code fromInd} es igual a {@code hastaInd} se retorna un a lista vacía.
	* @param desdeInd el índice inferior, inclusivo
	* @param hastaInd el índice superior, exclusivo
	* @return una nueva lista formada con los elementos entre {@code desdeInd} hasta {@code hastaInd - 1} de esta lista
	* @throws IndexOutOfBoundsException si ({@code fromInd} &lt; {@code 0} || {@code hastaInd} &gt; {@code #elementos()} || {@code desdeInd} &gt; {@code hastaInd})
	* @see #elementos() 
	*/
	public Lista<T> subLista(int desdeInd, int hastaInd){
        Nodo <T> aux = cabeza;
        int i = 0;
        ListaEnlazada<T> nuevaLista = new ListaEnlazada<>();

        if (desdeInd < 0 || hastaInd > cantElem || desdeInd > hastaInd){
            throw new  IndexOutOfBoundsException("Indices ingresados invalidos");
        }else {
            while(aux != null){
                if (i >= desdeInd && i<= hastaInd){
                    nuevaLista.agregar(aux.getValor()); 
                    aux.setNext(aux.getNext());
                }else{ 
                aux.setNext(aux.getNext());
                }
            } 
        }
        return nuevaLista;
    }


   
	public boolean contiene(T elem){
        Nodo<T> aux = cabeza;
        if (aux == null){
            return false;
        }{
            while(aux != null){
                if(aux.getValor().equals(elem)){
                    return true;
                }else{
                    aux.setNext(aux.getNext());
                }
            }
            return false;
        }
    }

    public void vaciar(){
        cabeza = null;
    }



	public int elementos(){
        return cantElem;
    }
	
    public boolean esVacia(){
        if (cantElem == 0){
            return true ;
        }
        return false;
    }

    public boolean repOK(){
        int contador = 0;

        Nodo <T> aux = cabeza;
        while (aux !=null){
            contador ++;
            aux.setNext(aux.getNext());
        }

        return contador==cantElem;
    }


    public T obtener(int indice){
        Nodo <T> aux = cabeza;
        T val ;
        int i = 0;

        if(indice < 0 || indice >= cantElem){
            throw new IndexOutOfBoundsException ("Indice invalido:"); 
        }{
            while (aux != null && i != indice){
                aux.setNext(aux.getNext());
                i++;
            }
            val = aux.getValor();
            return val;
        }
    
    }


}
