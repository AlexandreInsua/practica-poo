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
    // Cantidad del cultivo en kg producida anualmente
    private float produccion;
    // La cantidad del cultivo en kg disponible para su venta
    private float disponible;
    // Bandera que indica si el producto est� federado
    private boolean federado;
    // Tonelada m�trica en kg
    private final int TM = 1000;
    
    /**
     * Constructor para objetos de la clase ProductoProductor.
     * 
     * @param produto El producto a asignar al productor.
     * @param extension La extensi�n del cultivo en ha que se va a asignar al productor.
     */
    public ProductoProductor(Producto producto, float extension) {
        this.producto = producto;
        this.extension = extension ;
        produccion = producto.getRendimiento() * TM * extension;
        disponible = producto.getRendimiento() * TM * extension ;
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

    public void setDisponible(float d) {
        disponible = d;
    }

    public float getProduccion(){
        return produccion; 
    }
        
    public float getDisponible() {
        return disponible;
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
