
/**
 * Clase NoPerecedero. Representa un producto no perecedero.
 * 
 * @author Alexandre Insua Moreira.
 * @version
 */
public class NoPerecedero extends Producto
{

    /**
     * Constructor para objetos de la clase NoPerecedero.
     * @param nombre nombre del producto.
     * @param rendimiento rendimiento (en tm.) del produto por hectáreas.
     * @param precio precio (en Euros) de referencia del producto.
     */
    public NoPerecedero(String nombre, float rendimiento, float precio)
    {
        super(nombre, rendimiento, precio);
    }
    
      public String toString(){
        return getNombre() + ". No perecedero. Precio de ref.: " + getPrecio() + "€";
    }
}
