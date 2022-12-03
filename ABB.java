//package colecciones.arbol;

import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.LinkedList;

/**
 * Arbol binario de busqueda (ABB), es una implementación de {@code Diccionario} mediante nodos encadenados que preserva las propiedades de Diccionario.
 * @param <T> Tipo del valor asociado a los nodos del árbol, debe ser posible definir un orden total para los mismos.
 * @see NodoBinario
 */
public class ABB<T> implements Diccionario<T> {

    private NodoBinario<T> raiz;

    /**
     * Comparador usado para mantener el orden en un ABB, o null.
     */
    private final Comparator<? super T> comparador;

    /**
     * Construye un nuevo árbol vacío ordenado acorde al comparador dado.
     * @param comparador define una forma de comparar los valores insertados en el arbol.
     */
    public ABB(Comparator<? super T> comparador) {
        this.comparador = comparador;
        this.raiz = null;
    }

    /**
     * Construye un nuevo árbol con un elemento en la raiz, ordenado acorde al comparador dado.
     * @param comparador define una forma de comparar los valores insertados en el arbol.
     * @param valor de la raiz del nuevo arbol si no es null.
     */
    public ABB(Comparator<? super T> comparador, T valor) {
        this.comparador = comparador;
        this.raiz = new NodoBinario<T>(valor);
    }

    private ABB(Comparator<? super T> comparador, NodoBinario<T> raiz){
        this.comparador = comparador;
        this.raiz = raiz; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertar(T elem) {
        raiz = insertarNodo(raiz, elem);
    }

    private NodoBinario<T> insertarNodo(NodoBinario<T> raiz, T elem){
        if (raiz == null || raiz.getValor() == null){
            raiz = new NodoBinario<>(elem);
            return raiz; 
        }
        
        int cmp = comparador.compare(elem, raiz.getValor());
        if (cmp < 0 ){
            raiz.setIzquierdo(insertarNodo(raiz.getIzquierdo(), elem));
        } else if (cmp > 0){
            raiz.setDerecho(insertarNodo(raiz.getDerecho(), elem));
        }
        return raiz;
    }

    /**
     * {@inheritDoc}
     */
    public boolean pertenece(T elem) {
        return pertenece(elem, raiz); 
    }
    private boolean pertenece(T elem, NodoBinario<T> raiz){
        if(raiz == null) return false; 
        int cmp = comparador.compare(elem, raiz.getValor());
        if(cmp > 0) return pertenece(elem, raiz.getDerecho());
        if(cmp <0) return pertenece(elem, raiz.getIzquierdo());
        return true; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void borrar(T elem) {
        borrar(elem, raiz);
    }
    private NodoBinario<T> borrar(T elem, NodoBinario<T> raiz){
        if (raiz == null) return null; //elem no encontrado
        int cmp = comparador.compare(elem, raiz.getValor());
        if(cmp < 0) raiz.setIzquierdo(borrar(elem, raiz.getIzquierdo()));
        else if (cmp > 0) raiz.setDerecho(borrar(elem, raiz.getDerecho()));

        //elemento encontrado 
        else if (raiz.getIzquierdo() == null && raiz.getDerecho() == null)
            raiz = null; //borrar elemento 
        else if(raiz.getDerecho() == null){
            raiz = raiz.getIzquierdo();  
        }
        else if(raiz.getIzquierdo() == null){
            raiz = raiz.getDerecho();
        }
        else if (raiz.getIzquierdo() != null && raiz.getDerecho() != null){
            // el nodo tiene ambas ramas
            T sucesorAux = subArbolDerecho().menorValor();
            borrar(sucesorAux, raiz);
            raiz.setValor(sucesorAux);
        }
        return raiz; 
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void vaciar() {
        raiz = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T raiz() {
        return raiz.getValor();
    }
    

    /**
     * {@inheritDoc}
     */
    @Override
    public Diccionario<T> subArbolIzquierdo() {
        return new ABB<>(comparador, raiz.getIzquierdo());
    }

    private Diccionario<T> subArbolIzquierdo(NodoBinario<T> raiz) {
        return new ABB<>(comparador, raiz.getIzquierdo());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Diccionario<T> subArbolDerecho() {
        return new ABB<>(comparador, raiz.getDerecho());
    }
    private Diccionario<T> subArbolDerecho(NodoBinario<T> raiz) {
        return new ABB<>(comparador, raiz.getDerecho());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int elementos() {
        return elementos(raiz); 
    }
    private int elementos(NodoBinario<T> raiz){
        if (raiz == null) return 0; 
        return 1 + elementos(raiz.getIzquierdo()) + elementos(raiz.getDerecho());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int altura() {
        if(esVacio()) return 0; 
        return 1 + Math.max(subArbolIzquierdo().altura(), subArbolDerecho().altura());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean esVacio() {
        return raiz == null;
    }

    /**
     * {@inheritDoc}
     */
    public T mayorValor(){
        if(esVacio())
            throw new NullPointerException("Arbol Vacio");
        if (raiz.getDerecho() == null) return raiz.getValor(); 
        return subArbolDerecho().mayorValor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T menorValor() {
        if(esVacio())
            throw new NullPointerException("Arbol Vacio");
        if (raiz.getIzquierdo() == null) return raiz.getValor(); 
        return subArbolIzquierdo().menorValor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T sucesor(T elem) {
        Stack<NodoBinario<T>> ancestros = new Stack<>();
        return sucesor(elem, raiz, ancestros); 
    }

    private T sucesor(T elem, NodoBinario<T> raiz, Stack<NodoBinario<T>> ancestros){
        if(raiz == null) return null;
        int cmp = comparador.compare(elem, raiz.getValor());
        if(cmp > 0){
            ancestros.add(raiz);
            return sucesor(elem, raiz.getDerecho(), ancestros);
        }else if(cmp < 0) {
            ancestros.add(raiz);
            return sucesor(elem, raiz.getIzquierdo(), ancestros);
        } else return buscarSucesor(raiz, ancestros);
    }

    private T buscarSucesor(NodoBinario<T> raiz, Stack<NodoBinario<T>> ancestros){
        if(raiz.getDerecho() == null && ancestros.isEmpty()) return null; 
        if(raiz.getDerecho() == null && !ancestros.isEmpty()){
            while(!ancestros.isEmpty()){
                NodoBinario<T> ancestro = ancestros.pop();
                if(comparador.compare(raiz.getValor(), ancestro.getValor()) < 0) return ancestro.getValor();
            }
            return null; 
        }
        else{   //(raiz.getDerecho() != null)
            Diccionario<T> subarbol = subArbolDerecho(raiz);
            return subarbol.menorValor();

        }

    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    
    public T predecesor(T elem) {
        Stack<NodoBinario<T>> ancestros = new Stack<>();
        return predecesor(elem, raiz, ancestros); 
    }

    private T predecesor(T elem, NodoBinario<T> raiz, Stack<NodoBinario<T>> ancestros){
        if(raiz == null) return null;
        int cmp = comparador.compare(elem, raiz.getValor());
        if(cmp > 0){
            ancestros.add(raiz);
            return predecesor(elem, raiz.getDerecho(), ancestros);
        }else if(cmp < 0) {
            ancestros.add(raiz);
            return predecesor(elem, raiz.getIzquierdo(), ancestros);
        } else return buscarPredecesor(raiz, ancestros);
    }

    private T buscarPredecesor(NodoBinario<T> raiz, Stack<NodoBinario<T>> ancestros){
        if(raiz.getIzquierdo() == null && ancestros.isEmpty()) return null; 
        if(raiz.getIzquierdo() == null && !ancestros.isEmpty()){
            while(!ancestros.isEmpty()){
                NodoBinario<T> ancestro = ancestros.pop();
                if(comparador.compare(raiz.getValor(), ancestro.getValor()) >0) return ancestro.getValor();
                System.out.println(raiz.getValor() + " < " + ancestro.getValor() );
            }
            return null; 
        }
        if(raiz.getIzquierdo() != null) {
            Diccionario<T> subarbol = subArbolIzquierdo(raiz);
            return subarbol.mayorValor();

        }
        return null; 

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean repOK() {
        throw new UnsupportedOperationException("TODO: implementar");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return toString(raiz);  
    }
    private String toString(NodoBinario<T> raiz){
        String str = ""; 
        if(raiz == null) return ""; 
        str += toString(raiz.getIzquierdo());
        str += raiz.getValor().toString() + " ";
        str += toString(raiz.getDerecho());
        return str; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object other) {
        if (other == null)
			return false; 
		if (other == this)
			return true;
		if (!(other instanceof Diccionario))	
			return false; 
		if (!(getClass().equals(other.getClass())))
			return false; 
		ABB<T> otroArbol = (ABB<T>) other; 
        if (!comparador.equals(otroArbol.comparador)) return false; 
		if(elementos() != otroArbol.elementos()) return false; 
        if(!arbolesIguales(otroArbol)) return false; 
		return true; 
    }

    private boolean arbolesIguales(ABB<T> arbol){
        if (esVacio() && arbol.esVacio()) return true; 
        if(!esVacio() && !arbol.esVacio()){
            if(!raiz.getValor().equals(arbol.raiz.getValor())) return false;
            return ((ABB <T>) subArbolIzquierdo()).arbolesIguales((ABB<T>) arbol.subArbolIzquierdo()) && ((ABB <T>) subArbolDerecho()).arbolesIguales((ABB<T>) arbol.subArbolDerecho());
        }
        return false; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> aLista() {
        return aLista(Orden.INORDER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> aLista(Orden orden) {
        List<T> elementos = new LinkedList<>();
        switch (orden) {
            case INORDER:
                return aListaInOrder(raiz, elementos);
            case PREORDER:
                return aListaPreOrder(raiz, elementos);
            case POSTORDER:
                return aListaPostOrder(raiz, elementos);
        }
        return elementos;
    }

    /* (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido in order.
     * Si bien el prefil está pensando para una implementación recursiva, puede probar con una implementación iterativa.
     */
    private List<T> aListaInOrder(NodoBinario<T> raiz, List<T> elementos) {
                 // Recorrido InOrden
                NodoBinario<T> nodoAux = raiz;
                
                if(!(nodoAux==null)){
                   //recorre Izquierda      
                if (nodoAux.getIzquierdo() != null){
                        nodoAux = nodoAux.getIzquierdo();
                        aListaInOrder(nodoAux,elementos);
                        }
                       // se agraga a la lista el valor del nodo
                        
                        elementos.add(nodoAux.getValor());
                        //recorre Derecha
                        if (nodoAux.getDerecho() != null){
                        nodoAux = nodoAux.getDerecho();
                        aListaInOrder(nodoAux,elementos);
                    }
                }   
            return elementos; 
        //TODO: IMPLEMENTAR 
    }

    /* (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido pre order.
     * Si bien el prefil está pensando para una implementación recursiva, puede probar con una implementación iterativa.
     */
    private List<T> aListaPreOrder(NodoBinario<T> raiz, List<T> elementos) {
        NodoBinario<T> raizAux = this.raiz;  //Mi raizAux seria como un nodo aux para poder recorrer el arbol 

        if (raizAux!=null) {
            
            elementos.add(raizAux.getValor());

            if (raizAux.getIzquierdo()!=null){
                elementos.add(raizAux.getValor());
                aListaInOrder(raizAux,elementos);
            }else {
                if (raizAux.getDerecho()!=null){
                    elementos.add(raizAux.getValor());
                    aListaInOrder(raizAux,elementos);
                }
            }
        }
    
        return null; 
        //TODO: IMPLEMENTAR 
    }

    /* (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido post order.
     * Si bien el prefil está pensando para una implementación recursiva, puede probar con una implementación iterativa.
     */
    private List<T> aListaPostOrder(NodoBinario<T> raiz, List<T> elementos) {
         // Recorrido postOrden
		    NodoBinario<T> nodoAux = raiz;
        
            if(!(nodoAux==null)){      

                  //recorre izquierda
                if (nodoAux.getIzquierdo() != null){
                    nodoAux = nodoAux.getIzquierdo();
                    aListaInOrder(nodoAux,elementos);
                    }
                   //recorre derecha
                    if (nodoAux.getDerecho() != null){
                        nodoAux = nodoAux.getDerecho();
                        aListaInOrder(nodoAux,elementos);
                }
                  // se agraga a la lista el valor del nodo 
                    elementos.add(nodoAux.getValor());

            }	
        return elementos; 
    }

}
