import java.text.DecimalFormat;

/**
 * La clase Venta representa una transacción de venta de mercancía relacionada con un pedido.
 * Contiene información sobre el pedido asociado, la cantidad de mercancía vendida y el precio.
 * También proporciona métodos para calcular el beneficio de la venta y generar una representación en cadena.
 * 
 * @author Alexandre Insua Moreira
 * @version 1.0
 */
public class Venta
{
    // Pedido relacionado con la venta
    Pedido pedido;
    // Cantidad de mercancia proporcional al pedido en kg
    float cantidad;
    // Precio de la mercancia en el momento de servir el pedido en €
    float precio;
    // variable auxiliar para formatear precio
    private DecimalFormat priceFormatter;

    /**
     * Crea una nueva instancia de la clase Venta.
     * 
     * @param pedido El pedido relacionado con la venta.
     * @param cantidad La cantidad de mercancía vendida en kilogramos.
     * @param precio El precio de la mercancía en euros al momento de la venta.
     */
    public Venta(Pedido pedido, float cantidad, float precio){
        this.pedido = pedido;
        this.cantidad = cantidad;
        this.precio = precio;
        priceFormatter = new DecimalFormat("#.##");        
    }

    /**
     * Obtiene el pedido relacionado con la venta.
     * 
     * @return El pedido relacionado con la venta.
     */
    public Pedido getPedido(){
        return pedido;
    }

    /**
     * Calcula y devuelve el beneficio de la venta.
     * El beneficio se calcula multiplicando la cantidad de mercancía vendida por el precio.
     * 
     * @return El beneficio de la venta en euros.
     */
    public float getBeneficio(){
        return cantidad * precio;
    }

    /**
     * Genera una representación en cadena de la venta.
     * 
     * @return Una cadena que contiene detalles sobre la venta, incluido el número de pedido,
     * la fecha de la venta, la cantidad de mercancía, el precio formateado y el beneficio formateado.
     */
    public String toString(){
        return "Pedido nº:"+ pedido.getId() +". Fecha venta: " + pedido.getEntregaString() 
        + ", " + pedido.getProducto().getNombre()+", " + cantidad + "kg (" + priceFormatter.format(precio) + "€/kg). Total: " + priceFormatter.format(getBeneficio()) + "€";
    }
}
