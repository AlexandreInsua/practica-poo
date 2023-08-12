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
    private ArrayList<NoFederado> productores;
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
    public Producto(String nombre, float rendimiento, float precio)
    {
        this.nombre = nombre;
        this.rendimiento = rendimiento;
        this.precio = precio;

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
    public void setRendimiento(float rendimiento){
        this.rendimiento = rendimiento;
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
    public void setPrecio(float precio ){
        this.precio = precio;
    }

    /**
     * Obtiene el precio de referencia del producto.
     * @return El precio de referencia del producto (en Euros).
     */
    public float getPrecio(){
        return precio;
    }

    /**
     * Obtiene la lista de productores que cultivan el producto.
     * @return La lista de productores que cultivan el producto
     */
    public ArrayList<NoFederado> getProdutores(){
        return productores;
    }
    
    /**
     * Agrega un productor a la lista de productores que cultivan este producto.
     * @param productor El productor a agregar.
     */
    public void addProductor(NoFederado productor){
        productores.add(productor);
    }

    /**
     * Establece la cantidad total producida anualmente del producto.
     * @param produccion La nueva cantidad total producida anualmente del producto.
     */
    public void setProduccion(float produccion ){
        this.produccion = produccion;
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
    public void setDisponible(float disponible){
        this.disponible = disponible;
    }

    /**
     * Obtiene la cantidad disponible del producto en inventario.
     * @return La cantidad disponible del producto.
     */
    public float getDisponible(){
        return disponible;
    }
}
