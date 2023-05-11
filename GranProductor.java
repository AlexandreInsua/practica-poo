import java.util.ArrayList;

/**
 * Clase GranProductor que representa un gran productor.
 * 
 * @author Alexandre Insua Moreira
 * @version 
 */
public class GranProductor extends Productor
{
    // instance variables - replace the example below with your own
    ArrayList<ProductoProductor> productos;

    /**
     * Constructor for objects of class Grande
     */
    public GranProductor(String nombre)
    {
        super(nombre);
        productos = new ArrayList<ProductoProductor>();
    
    }

}
