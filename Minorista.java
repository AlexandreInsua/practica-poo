/**
 * Esta clase representa un minorista, que es un tipo de cliente de una cooperativa.
 * Hereda de la clase Cliente y proporciona una implementación concreta de sus métodos.
 * 
 * @author Alexandre Insua Moreira
 * @version 
 */
public class Minorista extends Cliente {

    /**
     * Constructor de la clase Minorista.
     * 
     * @param nombre El nombre del minorista.
     * @param distancia La distancia del minorista hasta la cooperativa.
     */
    public Minorista(String nombre, int distancia) {
        super(nombre, distancia);
    }   
}