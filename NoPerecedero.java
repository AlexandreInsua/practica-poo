
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
     * @param rendimiento El rendimiento (en tm.) del produto por hect�reas.
     * @param precio El precio (en Euros) de referencia del producto.
     */
    public NoPerecedero(String nombre, float rendimiento, float precio)
    {
        super(nombre, rendimiento, precio);
    }

    /**
     * Devuelve una representaci�n en forma de cadena de caracteres de la informaci�n relevante del producto.
     * 
     * @return Una cadena que representa la informaci�n del producto
     */
    public String toString(){
        return getNombre() + ". No perecedero. Precio de ref.: " + priceFormatter.format(getPrecio()) + "�. Producci�n anual: " 
        + priceFormatter.format(getProduccion()) + "kg. Disponible: " + priceFormatter.format(getDisponible()) + "kg";
    }
}
