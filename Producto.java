import java.util.ArrayList;
import java.time.LocalDate;
import java.text.DecimalFormat;

/**
 * La clase abstracta Producto representa un producto disponible en la cooperativa.
 * 
 * Esta clase proporciona información sobre el nombre, rendimiento, precio de referencia,
 * productores, cotizaciones, producción anual y disponibilidad de un producto.
 * Además, incluye métodos para acceder y modificar esta información.
 * 
 * @author Alexandre Insua Moreira.
 * @version 1.0
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
    // Relación de diferentes cotizaciones del producto
    private ArrayList<Cotizacion> cotizaciones;
    // Cantidad total producida anualmente
    private float produccion;
    // Cantidad de producto disponible
    private float disponible;
    // variable auxiliar para formatear precios
    DecimalFormat priceFormatter;

    /**
     * Constructor de objectos de la clase Productor.
     * 
     * @param nombre nombre del producto.
     * @param rendimiento rendimiento (en tm.) del produto por hectárea.
     * @param precio precio (en Euros) de referencia del producto.
     */
    public Producto(String nombre, float rendimiento, float precio)
    {
        this.nombre = nombre;
        this.rendimiento = rendimiento;
        this.precio = precio;

        productores = new ArrayList();
        cotizaciones = new ArrayList();
        agregraCotizacion(precio);
        produccion = 0;
        disponible = 0;
        priceFormatter = new DecimalFormat("#.##");
    }

    /** 
     * Obtiene el nombre del producto.
     * 
     * @return El nombre del producto.
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Establece el rendimiento del producto.
     * 
     * @param rendimiento El nuevo rendimiento del producto (en toneladas métricas por hectárea).
     */
    public void setRendimiento(float rendimiento){
        this.rendimiento = rendimiento;
    }

    /**
     * Obtiene el rendimiento del producto.
     * 
     * @return El rendimiento del producto (en toneladas métricas por hectárea).
     */
    public float getRendimiento(){
        return rendimiento;
    }

    /**
     * Establece el precio de referencia del producto.
     * 
     * @param precio El nuevo precio de referencia del producto (en Euros).
     */
    public void setPrecio(float precio ){
        this.precio = precio;
        agregraCotizacion(precio);
    }

    /**
     * Obtiene el precio de referencia del producto.
     * 
     * @return El precio de referencia del producto (en Euros).
     */
    public float getPrecio(){
        return precio;
    }

    /**
     * Obtiene la lista de productores que cultivan el producto.
     * 
     * @return La lista de productores que cultivan el producto
     */
    public ArrayList<NoFederado> getProdutores(){
        return productores;
    }

    /**
     * Agrega un productor a la lista de productores que cultivan este producto.
     * 
     * @param productor El productor a agregar.
     */
    public void addProductor(NoFederado productor){
        productores.add(productor);
    }

    /**
     * Establece la cantidad total producida anualmente del producto.
     * 
     * @param produccion La nueva cantidad total producida anualmente del producto.
     */
    public void setProduccion(float produccion ){
        this.produccion = produccion;
    }

    /**
     * Obtiene la cantidad total producida anualmente del producto.
     * 
     * @return La cantidad total producida anualmente del producto.
     */
    public float getProduccion(){
        return produccion;
    }

    /**
     * Establece la cantidad disponible del producto en inventario.
     * 
     * @param disponible La nueva cantidad disponible del producto.
     */
    public void setDisponible(float disponible){
        this.disponible = disponible;
    }

    /**
     * Obtiene la cantidad disponible del producto en inventario.
     * 
     * @return La cantidad disponible del producto.
     */
    public float getDisponible(){
        return disponible;
    }

    /**
     * Agrega una cotización a la lista de cotizaciones del producto.
     * 
     * @param precio El precio de la cotización a agregar.
     */
    public void agregraCotizacion(float precio){
        cotizaciones.add(new Cotizacion(LocalDate.now(), precio));
    }

    /**
     * Lista las cotizaciones del producto en la consola.
     */
    public void listarCotizaciones(){
        System.out.println("Evolución de los precios de " + getNombre());
        for(Cotizacion cotizacion: cotizaciones){
            System.out.println(" " + cotizacion.toString());
        }
    }
}
