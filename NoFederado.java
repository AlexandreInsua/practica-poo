import java.util.ArrayList;
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
    // Relación de ventas de un Productor no federado;
    ArrayList<Venta> ventas;

    /**
     * Constructor de la clase NoFederado.
     * 
     * @param nombre El nombre del productor no federado.
     */
    public NoFederado(String nombre)
    {
        super(nombre);
        ventas = new ArrayList();
    }

    /**
     * Calcula el beneficio total sumando los beneficios de todas las ventas del Producto.
     *
     * @return El beneficio total de todas las ventas del Productor.
     */
    public float getBeneficioTotal(){
        return ventas.stream().map(Venta::getBeneficio).reduce(0f, (subtotal, venta)-> subtotal + venta);
    }

    /**
     * Muestra los detalles de todas las ventas en la lista en la consola.
     */
    public void listarVentas(){
        for(Venta venta: ventas){
            System.out.println(venta.toString());
        }
    }

    /**
     * Método abstracto para asignar un producto al productor no federado.
     * 
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

    /**
     * Método abstracto para obtener un producto
     * 
     * @param nombreProducto El nombre del producto que se va a buscar.
     * @return Un objeto ProductoProductor que representa un asignado al productor.
     */
    public abstract ProductoProductor buscarProducto(String nombreProducto);
}
