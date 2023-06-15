import java.util.ArrayList;

/**
 * Clase GranProductor que representa un gran productor. Esta clase extiende la clase Productor
 * y permite gestionar la informaci�n relacionada
 * con un gran productor, incluyendo los productos que cultiva y asigna.
 * 
 * Autor: Alexandre Insua Moreira
 * Versi�n: 1.0
 */
public class GranProductor extends NoFederado {

    ArrayList<ProductoProductor> productos; // Lista de productos asignados al gran productor

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
     * Devuelve una representaci�n en forma de cadena de caracteres del objeto GranProductor.
     * 
     * @return Una representaci�n en forma de cadena de caracteres del objeto GranProductor.
     */
    public String toString() {
        return getNombre() + " Gran productor";
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
}
