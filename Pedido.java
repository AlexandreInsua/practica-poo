
/**
 * La clase Pedido representa un pedido realizado por un cliente para un producto específico en una cantidad determinada.
 * Cada instancia de esta clase contiene información sobre el cliente que realiza el pedido, el producto solicitado
 * y la cantidad en kilogramos del producto solicitado.
 * 
 * @author Alexandre Insua Moreira
 * @version
 */
public class Pedido
{

    // Cliente que realiza el pedido
    private Cliente cliente;
    // Producto del pedido
    private Producto producto;
    // Cantidad del pedido en kilogramos
    private int cantidad;

    /**
     * Constructor para objetos de la clase Pedido.
     * 
     * @param cliente El cliente que realiza el pedido.
     * @param producto El producto solicitado en el pedido.
     * @param cantidad La cantidad del producto solicitado en kilogramos.
     */
    public Pedido(Cliente cliente, Producto producto, int cantidad)
    {
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;

    }

    /**
     * Obtiene el cliente que realizó este pedido.
     * 
     * @return El objeto Cliente que realizó el pedido.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Obtiene el producto asociado a este pedido.
     * 
     * @return El objeto Producto solicitado en este pedido.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Obtiene la cantidad del producto solicitado en este pedido.
     * 
     * @return La cantidad del producto solicitado en kilogramos.
     */
    public int getCantidad() {
        return cantidad;
    }
}
