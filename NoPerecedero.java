
/**
 * Clase NoPerecedero. Representa un producto no perecedero.
 * 
 * @author Alexandre Insua Moreira.
 * @version 1.0
 */
public class NoPerecedero extends Producto
{

    /**
     * Constructor para objetos de la clase NoPerecedero.
     * 
     * @param nombre El nombre del producto.
     * @param rendimiento El rendimiento (en tm.) del produto por hectáreas.
     * @param precio El precio (en Euros) de referencia del producto.
     */
    public NoPerecedero(String nombre, float rendimiento, float precio)
    {
        super(nombre, rendimiento, precio);
    }

    /**
     * Devuelve una representación en forma de cadena de caracteres de la información relevante del producto.
     * 
     * @return Una cadena que representa la información del producto
     */
    public String toString(){
        return getNombre() + ". No perecedero. Precio de ref.: " + priceFormatter.format(getPrecio()) + "€. Producción anual: " 
        + priceFormatter.format(getProduccion()) + "kg. Disponible: " + priceFormatter.format(getDisponible()) + "kg";
    }
}
