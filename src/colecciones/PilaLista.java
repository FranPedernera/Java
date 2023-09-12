package colecciones;

public class PilaLista<T> implements Stack<T>{
    
    Nodo<T> cabeza ;
    int cantElem;

    public PilaLista(){
        cabeza = null;
        cantElem =  0;
    }
    @Override
    public boolean push (T elem){
        Nodo<T> nuevoNodo = new Nodo<T>(elem);

        if(cabeza == null){
            cabeza = nuevoNodo;
            nuevoNodo.setNext(null);
            cantElem++;
            return true;
        }else{
            nuevoNodo.setNext(cabeza);
            cabeza = nuevoNodo;
            cantElem++;
            return true;
            
        }
    }

    public T pop (){

        if (cabeza == null ){
            throw new IllegalStateException("La pila ya esta vacia");

        }else {
            T valor ;
            valor = cabeza.getValor();
            cabeza.setNext(cabeza.getNext());
            cantElem --;
            return valor;
        }
    }

        // Devuelve el elemento en la parte superior de la pila sin eliminarlo
        public T peek(){
            T valor = cabeza.getValor();
            return valor;
        }

        public boolean isEmpty(){
            return cabeza == null;
        }

        public int size (){
            return cantElem;
        }

        public boolean empty(){
            cabeza = null;
            return true; 
        }
}
