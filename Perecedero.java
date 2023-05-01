
/**
 * Clase Perecedero. Representa un producto perecedero.
 * 
 * @author Alexandre Insua Moreira.
 * @version 
 */
public class Perecedero extends Producto
{

    /**
     * Constructor para objetos de la clase Perecedero
     * @param nombre nombre del producto.
     * @param rendimiento rendimiento (en tm.) del produto por hectáreas.
     * @param precio precio (en Euros) de referencia del producto.
     */
    public Perecedero(String nombre, float rendimiento, float precio)
    {
        super(nombre, rendimiento, precio);
    }

    public String toString(){
        return getNombre() + ". Perecedero. Precio de ref.: " + getPrecio() + "€";
    }
}
