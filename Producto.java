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
     * @param rendimiento rendimiento (en tm.) del produto por hectáreaf.
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

    /** 
     * Obtiene el nombre del producto.
     * @return El nombre del producto.
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Establece el rendimiento del producto.
     * @param rendimiento El nuevo rendimiento del producto (en toneladas métricas por hectárea).
     */
    public void setRendimiento(float r){
        rendimiento = r;
    }

    /**
     * Obtiene el rendimiento del producto.
     * @return El rendimiento del producto (en toneladas métricas por hectárea).
     */
    public float getRendimiento(){
        return rendimiento;
    }

    /**
     * Establece el precio de referencia del producto.
     * @param precio El nuevo precio de referencia del producto (en Euros).
     */
    public void setPrecio(float p){
        precio = p;
    }

    /**
     * Obtiene el precio de referencia del producto.
     * @return El precio de referencia del producto (en Euros).
     */
    public float getPrecio(){
        return precio;
    }

    /**
     * Agrega un productor a la lista de productores que cultivan este producto.
     * @param productor El productor a agregar.
     */
    public void addProductor(Productor p){
        productores.add(p);
    }

    /**
     * Establece la cantidad total producida anualmente del producto.
     * @param produccion La nueva cantidad total producida anualmente del producto.
     */
    public void setProduccion(float p){
        produccion = p;
    }

    /**
     * Obtiene la cantidad total producida anualmente del producto.
     * @return La cantidad total producida anualmente del producto.
     */
    public float getProduccion(){
        return produccion;
    }

    /**
     * Establece la cantidad disponible del producto en inventario.
     * @param disponible La nueva cantidad disponible del producto.
     */
    public void setDisponible(float d){
        disponible = d;
    }

    /**
     * Obtiene la cantidad disponible del producto en inventario.
     * @return La cantidad disponible del producto.
     */
    public float getDisponible(){
        return disponible;
    }
}
