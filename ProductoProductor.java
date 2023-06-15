/**
 * Clase ProductoProductor. Esta clase permite gestionar la informaci�n relacionada con un producto, su
 * extensi�n de cultivo asignada a un productor espec�fico y su cantidad disponible.
 * 
 * Autor: Alexandre Insua Moreira
 * Versi�n: 1.0
 */
public class ProductoProductor {

    // El producto asignado al productor
    private Producto producto;
    // La extensi�n del cultivo asignada al productor
    private float extension; 
    // La extensi�n del cultivo disponible para su venta
    private float disponible;
    // Bandera que indica si el producto est� federado
    private boolean federado;

    /**
     * Constructor para objetos de la clase ProductoProductor.
     * 
     * @param produto El producto a asignar al productor.
     * @param extension La extensi�n del cultivo a asignar al productor.
     */
    public ProductoProductor(Producto producto, float extension) {
        this.producto = producto;
        this.extension = extension ;
        disponible = extension ;
        federado = false;
    }

    public void setFederado(boolean federado) {
        this.federado = federado;
    }

    /**
     * Obtiene el producto asignado al productor.
     * 
     * @return El producto asignado al productor.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Obtiene la extensi�n del cultivo asignada al productor.
     * 
     * @return La extensi�n del cultivo asignada al productor.
     */
    public float getExtension() {
        return extension;
    }

    public boolean getFedederado(){
        return federado;
    }

    /**
     * Devuelve una representaci�n en forma de cadena de caracteres del objeto ProductoProductor.
     * 
     * @return Una representaci�n en forma de cadena de caracteres del objeto ProductoProductor.
     */
    public String toString() {
        return producto.getNombre() + ": " + extension + " Ha.";
    }
}
