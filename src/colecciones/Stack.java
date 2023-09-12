package colecciones;
public interface Stack<T> {

    // Agrega un elemento en la parte superior de la pila
    boolean push(T elemento);

    // Elimina y devuelve el elemento en la parte superior de la pila
    T pop();

    // Devuelve el elemento en la parte superior de la pila sin eliminarlo
    T peek();

    // Verifica si la pila está vacía
    boolean isEmpty();

    // Devuelve el número de elementos en la pila
    int size();

    //Vacia la pila 
    boolean empty();
}
