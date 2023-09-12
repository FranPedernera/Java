package colecciones;

import java.util.Collection;

/**
* Implementación basada en arreglos de tamaño fijo de la interfaz {@code Cola}.
* @see colecciones.cola.Cola
*/
public class ColaArregloFijo<T> implements Cola<T> {

	/**
	* Capacidad máxima por defecto ({@value #CAPACIDAD_POR_DEFECTO})
	*/
	public static final int CAPACIDAD_POR_DEFECTO = 20;
	private Object[] arreglo;
	private int elementos = 0;

	/**
	* Construye una nueva cola vacía con una capacidad máxima de {@value #CAPACIDAD_POR_DEFECTO}.
	*/
	public ColaArregloFijo() {
		this(CAPACIDAD_POR_DEFECTO);
	}

	/**
	* Construye una nueva cola vacía con una capacidad determinada mayor a 0.
	* @param capacidad la capacidad de la cola
	* @throws IllegalArgumentException si {@code capacidad} es menor o igual a 0 
	*/
	public ColaArregloFijo(int capacidad) {
		if (capacidad <= 0)
			throw new IllegalArgumentException("la capacidad debe ser un numero positivo (" + capacidad + ")");
		arreglo = new Object[capacidad];
	}

	/**
	* Construye una cola a partir de elementos en una colección.
	* Los elementos en la colección se encolan de izquierda a derecha.
	* La capacidad de la cola va a ser suficiente para por lo menos contener todos los elementos de la colección.
	* @param elems los elementos a agregar a la cola
	*/
	public ColaArregloFijo(Collection<T> elems) {
		if (elems == null)
			throw new IllegalArgumentException("elems es null");
		arreglo = new Object[Math.max(elems.size(), CAPACIDAD_POR_DEFECTO)];
		for (T e : elems) {
			encolar(e);	
		}
	}

	@Override
	public boolean esVacia() {
		return elementos == 0;
	}

	@Override
	public int elementos() {
		return elementos;
	}

	@Override
	public boolean encolar(T elem) {
		int j = 0;
		if (elementos +1 > elementos){
			return false ;
		}else {
			for ( j = elementos; j>=0; j--){
				arreglo[j+1] = arreglo[j];
			}
			if (j == 0){
				arreglo[j] = elem;
				elementos++;
			}
			return true;
		}
	}

	@Override
	public T desencolar() {
		T elem;
		if(elementos == 0){ 
			throw new IllegalStateException("La cola se encuentra vacia ");
		}else {
			//Casteo mi arreblo de Objet a un tipo T
			elem = (T) arreglo[elementos];
			elementos--;
			return elem;
		}
	}

	@Override
	public T primero() {
		T elem;
		if (elementos == 0){
			throw new IllegalStateException("La cola esta vacia .");
		}else 
		elem = (T) arreglo[0];
		return elem;
	}

	@Override
	public void vaciar() {
		elementos=0;
	}

	@Override
	public boolean repOK() {
		if(elementos<0){
			return false ;
		}else 
		return true;
	}

	@Override
	public String toString() {
		String cola = "[";
		for (int i = 0; i <= elementos; i++){
			cola += arreglo[i] + "";
		}
		cola += "]";
		return cola;
	}

	@Override
	public boolean equals(Object other) {
		return true;
		}

	/**
	* Permite obtener un elemento del arreglo en un indice determinado realizando el casteo necesario.
	* @param index el indice del elemento a obtener
	*/
	@SuppressWarnings("unchecked")
   	private T elemento(int index) {
        	return (T) arreglo[index];
    	}

}
