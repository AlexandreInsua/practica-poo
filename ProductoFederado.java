
/**
 * ProductoFederado .
 * 
 * @author Alexandre Insua Moreira. 
 * @version 
 */
public class ProductoFederado
{
    // Pequeño productor original que federa un producto.
    private Productor productor;
    // Producto federado.
    private ProductoProductor producto;

    /**
     * Constructor for objects of class ProductoFederado
     */
    public ProductoFederado(Productor pro, ProductoProductor p)
    {
        productor = pro;
        producto = p;
    }

    public Productor getProdutor(){
        return productor; 
    }

    public ProductoProductor getProductoProductor(){
        return producto;
    }
}
