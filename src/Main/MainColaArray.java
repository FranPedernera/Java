package Main;
import colecciones.ColaArregloFijo;
public class MainColaArray {
    public static void main(String[] args) {
        ColaArregloFijo <Integer> cola = new ColaArregloFijo<>(null);
        cola.encolar(5);
        cola.encolar(20);
        cola.encolar(1);
        cola.toString();
    }
    
}
j