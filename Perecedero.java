
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

    /**
     * Devuelve una representación en forma de cadena (String) de la información relevante del producto.
     * @return Una cadena que representa la información del producto
     */
    public String toString(){
        return getNombre() + ". Perecedero. Precio de ref.: " + priceFormatter.format(getPrecio()) + "€. Producción anual: " 
        + priceFormatter.format(getProduccion()) + "kg. Disponible: " + priceFormatter.format(getDisponible()) + "kg";
    }
}
