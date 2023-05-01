import java.util.ArrayList;

/**
 * Clase abtracta Producto. Representa genérica de un producto disponible
 * en la cooperativa.
 * 
 * @author Alexandre Insua Moreira.
 * @version 
 */
public abstract class Producto
{
    // nombre del producto
    private String nombre;
    // rendimiento del producto en toneladas métricas por hectárea
    private float rendimiento;
    // precio de referencia del producto en Euros
    private float precio; 
    // Productores que cultivan el producto
    private ArrayList<Productor> productores;
    // Cantidad total producida anualmente
    private float produccion;
    // Cantidad de producto disponible
    private float disponible;

    /**
     * Constructor de objectos de la clase Productor.
     * @param nombre nombre del producto.
     * @param rendimiento rendimiento (en tm.) del produto por hectáreas.
     * @param precio precio (en Euros) de referencia del producto.
     */
    public Producto(String n, float r, float p)
    {
        nombre = n;
        rendimiento = r;
        precio = p;

        productores = new ArrayList();
        produccion = 0;
        disponible = 0;
    }

    public String getNombre(){
        return nombre;
    }

    public void setRendimiento(float r){
        rendimiento = r;
    }

    public float getRendimiento(){
        return rendimiento;
    }

    public void setPrecio(float p){
        precio = p;
    }

    public float getPrecio(){
        return precio;
    }

    public void addProductor(Productor p){
        productores.add(p);
        // actualizar producción y disponible
    }

    public float getProduccion(){
        return produccion;
    }

    public float disponible(){
        return disponible;
    }
}
