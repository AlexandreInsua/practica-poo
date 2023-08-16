import java.text.DecimalFormat;

/**
 * La clase Venta representa una transacci�n de venta de mercanc�a relacionada con un pedido.
 * Contiene informaci�n sobre el pedido asociado, la cantidad de mercanc�a vendida y el precio.
 * Tambi�n proporciona m�todos para calcular el beneficio de la venta y generar una representaci�n en cadena.
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
    // Precio de la mercancia en el momento de servir el pedido en �
    float precio;
    // variable auxiliar para formatear precio
    private DecimalFormat priceFormatter;

    /**
     * Crea una nueva instancia de la clase Venta.
     * 
     * @param pedido El pedido relacionado con la venta.
     * @param cantidad La cantidad de mercanc�a vendida en kilogramos.
     * @param precio El precio de la mercanc�a en euros al momento de la venta.
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
     * El beneficio se calcula multiplicando la cantidad de mercanc�a vendida por el precio.
     * 
     * @return El beneficio de la venta en euros.
     */
    public float getBeneficio(){
        return cantidad * precio;
    }

    /**
     * Genera una representaci�n en cadena de la venta.
     * 
     * @return Una cadena que contiene detalles sobre la venta, incluido el n�mero de pedido,
     * la fecha de la venta, la cantidad de mercanc�a, el precio formateado y el beneficio formateado.
     */
    public String toString(){
        return "Pedido n�:"+ pedido.getId() +". Fecha venta: " + pedido.getEntregaString() 
        + ", " + pedido.getProducto().getNombre()+", " + cantidad + "kg (" + priceFormatter.format(precio) + "�/kg). Total: " + priceFormatter.format(getBeneficio()) + "�";
    }
}
