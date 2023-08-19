/**
 * La clase ProductoFederado representa un producto federado, 
 * que es producido por un peque�o productor y es usado por un Productor Federado.
 * Esta clase permite acceder a la informaci�n del productor y del producto asociado.
 * 
 * @author Alexandre Insua Moreira.
 * @version 1.0
 */
public class ProductoFederado
{
    // Peque�o productor original que federa un producto.
    private Productor productor;
    // Producto federado.
    private ProductoProductor producto;

    /**
     * Constructor para objetos de la clase ProductoFederado.
     * 
     * @param productor El productor asociado al producto federado.
     * @param producto El producto de productor asociado al producto federado.
     */
    public ProductoFederado(Productor productor, ProductoProductor producto)
    {
        this.productor = productor;
        this.producto = producto;
    }

    /**
     * Obtiene el productor asociado al producto federado.
     * 
     * @return El productor asociado.
     */
    public Productor getProdutor(){
        return productor; 
    }

    /**
     * Obtiene el producto de productor asociado al producto federado.
     * 
     * @return El producto de productor asociado.
     */
    public ProductoProductor getProductoProductor(){
        return producto;
    }

    /**
     * Retorna una representaci�n en forma de cadena de caracteres del producto federado.
     * 
     * @return Una cadena que contiene informaci�n sobre el productor, la extensi�n y la disponibilidad del producto.
     */
    public String toString(){
        return "Productor: " + productor.getNombre() + " " +   producto.getExtension() +  " Ha. Disponible: " + producto.getDisponible() + "kg"; 
    }
}
