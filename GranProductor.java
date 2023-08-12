import java.util.ArrayList;
import java.util.List;

/**
 * Clase GranProductor que representa un gran productor. Esta clase extiende la clase Productor
 * y permite gestionar la información relacionada
 * con un gran productor, incluyendo los productos que cultiva y asigna.
 * 
 * Autor: Alexandre Insua Moreira
 * Versión: 1.0
 */
public class GranProductor extends NoFederado {

    // Lista de productos asignados al gran productor
    ArrayList<ProductoProductor> productos;

    /**
     * Constructor para objetos de la clase GranProductor.
     * 
     * @param nombre El nombre del gran productor.
     */
    public GranProductor(String nombre) {
        super(nombre);
        productos = new ArrayList<ProductoProductor>();
    }

    /**
     * Devuelve una representación en forma de cadena de caracteres del objeto GranProductor.
     * 
     * @return Una representación en forma de cadena de caracteres del objeto GranProductor.
     */
    public String toString() {
        return getNombre() + " Gran productor" + listProducts();
    }

    /**
     * Asigna un producto al gran productor.
     * 
     * @param p El producto a asignar.
     * @return true si el producto se asigna correctamente, false de lo contrario.
     */
    public boolean asignarProducto(ProductoProductor producto) {
        boolean result = false;

        try {
            checkIsProduct(producto);
            productos.add(producto);
            result = true;
        } catch (Exception e) {
            System.err.println(e);
        }
        return result;
    }

    /**
     * Busca y devuelve un objeto de tipo ProductoProductor que coincida con el nombre especificado.
     * 
     * @param nombreProducto El nombre del producto que se desea buscar.
     * @return El objeto ProductoProductor que coincide con el nombre especificado, o null si no se encuentra ninguna coincidencia.
     */
    public ProductoProductor buscarProducto(String nombreProducto){
        for (ProductoProductor producto : productos) {
            if (producto.getProducto().getNombre().equals(nombreProducto)) {
                return producto;
            }
        }
        return null;    
    }

    /**
     * Devuelve la lista de productos asignados al productor.
     * @return Una lista de objetos ProductoProductor que representa los productos asignados
     *         al productor.
     */
    public List<ProductoProductor> getProductos(){
        return productos;
    }

    /**
     * Verifica si un producto ya ha sido asignado al gran productor.
     * 
     * @param p El producto a verificar.
     * @throws Exception si el producto ya ha sido asignado previamente.
     */
    private void checkIsProduct(ProductoProductor p) throws Exception {
        for (ProductoProductor producto : productos) {
            if (producto != null && (producto == p || producto.getProducto().getNombre().equals(p.getProducto().getNombre()))) {
                throw new Exception("No se puede agregar el mismo producto por segunda vez");
            }
        }
    }

    private String listProducts(){
        String productList = ""; 

        for(ProductoProductor p: productos){
            if (p != null && p.getFedederado() != true) {
                productList += "\n\t" + p.toString();
            }
        }
        return productList; 
    }
}
