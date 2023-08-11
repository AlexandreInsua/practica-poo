/**
 * Esta clase representa un distribuidor, que es un tipo de cliente de una cooperativa.
 * Hereda de la clase Cliente y proporciona una implementaci�n concreta de sus m�todos.
 * @author Alexandre Insua Moreira
 * @version 1.0
 */
public class Distribuidor extends Cliente {

    /**
     * Constructor de la clase Distribuidor.
     * @param nombre El nombre del distribuidor.
     * @param distancia La distancia del distribuidor hasta la cooperativa.
     */
    public Distribuidor(String nombre, int distancia) {
        super(nombre, distancia);
    }
    
    /**
     * Devuelve una representaci�n en forma de cadena de caracteres del objeto Distribuidor.
     * @return representaci�n en forma de cadena de caracteres del objeto Distribuidor.
     */
    public String toString(){
        return getNombre() + " DISTRIBUIDOR. Distancia " + getDistancia()+" km.";
    }
}