
/**
 * Clase PeqProductor que representa un productor pequeño.
 *  @author Alexandre Insua Moreira
 * @version 
 */
public class PeqProductor extends Productor
{
    
     private ProductoProductor[] productos;
     
    /**
     * Constructor for objects of class Pequeno
     */
    public PeqProductor(String nombre)
    {
        super(nombre);
        productos = new ProductoProductor[5];
    
    }

    
}
