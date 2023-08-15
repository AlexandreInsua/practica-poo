import java.text.DecimalFormat;
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
    // variable auxiliar para formatear precio
    private DecimalFormat priceFormatter;

    /**
     * Constructor de la clase NoFederado.
     * 
     * @param nombre El nombre del productor no federado.
     */
    public NoFederado(String nombre)
    {
        super(nombre);
        ventas = new ArrayList();
        priceFormatter = new DecimalFormat("#.##");        
    }

    /**
     * Agrega una venta a la lista de ventas.
     * 
     * @param venta La instancia de la clase Venta que se va a agregar a la lista.
     */
    public void agregarVenta(Venta venta){
        ventas.add(venta);    
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
        if (ventas.size() > 0){
            System.out.println("Ventas de " + getNombre() + ":");
            float total = 0;
            for(Venta venta: ventas){
                System.out.println(" " + venta.toString());
                total += venta.getBeneficio();
            }
            System.out.println("\tTotal : " + priceFormatter.format(total) + "€");    
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
