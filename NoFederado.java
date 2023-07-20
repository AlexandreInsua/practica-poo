import java.util.List;

/**
 * Clase abstracta NoFederado representa una abstracción que modela 
 * el comportamiento común a las clases GranProductor y PeqProductor.
 * 
 * @author Alexandre Insua Moreira
 * @version 
 */
public abstract class NoFederado extends Productor
{
    /**
     * Constructor de la clase NoFederado.
     * @param nombre El nombre del productor no federado.
     */
    public NoFederado(String nombre)
    {
        super(nombre);
    }

    /**
     * Método abstracto para asignar un producto al productor no federado.
     * @param producto El producto a ser asignado al productor no federado.
     * @return true si el producto se asignó correctamente, false en caso contrario.
     */
    public abstract boolean asignarProducto(ProductoProductor producto); 

    /**
     * Método abstracto para obtener la lista de productos del productor no federado.
     *
     * @return Una lista de objetos ProductoProductor que representa los productos asignados al productor no federado.
     */
    public abstract List<ProductoProductor> getProductos();
}
