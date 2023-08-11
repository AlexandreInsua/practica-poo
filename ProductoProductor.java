/**
 * Clase ProductoProductor. Esta clase permite gestionar la información relacionada con un producto, su
 * extensión de cultivo asignada a un productor específico y su cantidad disponible.
 * 
 * Autor: Alexandre Insua Moreira
 * Versión: 1.0
 */
public class ProductoProductor {

    // El producto asignado al productor
    private Producto producto;
    // La extensión del cultivo asignada al productor
    private float extension; 
    // Cantidad del cultivo en kg producida anualmente
    private float produccion;
    // La cantidad del cultivo en kg disponible para su venta
    private float disponible;
    // Bandera que indica si el producto está federado
    private boolean federado;
    // Tonelada métrica en kg
    private final int TM = 1000;
    
    /**
     * Constructor para objetos de la clase ProductoProductor.
     * 
     * @param produto El producto a asignar al productor.
     * @param extension La extensión del cultivo en ha que se va a asignar al productor.
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
     * Obtiene la extensión del cultivo asignada al productor.
     * 
     * @return La extensión del cultivo asignada al productor.
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
     * Devuelve una representación en forma de cadena de caracteres del objeto ProductoProductor.
     * 
     * @return Una representación en forma de cadena de caracteres del objeto ProductoProductor.
     */
    public String toString() {
        return producto.getNombre() + ": " + extension + " Ha.";
    }
}
