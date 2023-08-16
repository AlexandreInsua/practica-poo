
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
     * @param rendimiento rendimiento (en tm.) del produto por hect�reas.
     * @param precio precio (en Euros) de referencia del producto.
     */
    public Perecedero(String nombre, float rendimiento, float precio)
    {
        super(nombre, rendimiento, precio);
    }

    /**
     * Devuelve una representaci�n en forma de cadena (String) de la informaci�n relevante del producto.
     * @return Una cadena que representa la informaci�n del producto
     */
    public String toString(){
        return getNombre() + ". Perecedero. Precio de ref.: " + priceFormatter.format(getPrecio()) + "�. Producci�n anual: " 
        + priceFormatter.format(getProduccion()) + "kg. Disponible: " + priceFormatter.format(getDisponible()) + "kg";
    }
}
