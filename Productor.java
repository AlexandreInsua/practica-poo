/**
 * Clase abstracta Productor. Representa un Productor genérico no instanciable.
 * Implementa el nombre del productor que proporciona a la cooperativa.
 * 
 * Esta clase abstracta sirve como base para representar a los productores que suministran
 * productos a una cooperativa. Cada productor tiene un nombre asociado.
 *  
 * @author Alexandre Insua Moreira.
 * @version 1.0
 */
public abstract class Productor
{
    // Nombre del productor
    private String nombre;

    
    /**
     * Constructor de la clase Productor.
     * 
     * @param nombre El nombre del productor.
     */
    Productor(String nombre){
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre del productor.
     * 
     * @return El nombre del productor.
     */
    public String getNombre(){
        return nombre;
    }
}
