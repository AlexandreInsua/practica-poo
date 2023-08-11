
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

    /**
     * Devuelve una representación en forma de cadena (String) de la información relevante del producto.
     * @return Una cadena que representa la información del producto
     */
    public String toString(){
        return getNombre() + ". No perecedero. Precio de ref.: " + getPrecio() + "€. Producción anual: " + getProduccion()+ "kg. Disponible: " + getDisponible() + "kg";
    }
}
