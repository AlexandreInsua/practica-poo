import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

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
    // Número de pedido
    private long id;
    // Fecha de creación del pedido
    private LocalDate creacion;
    // Fecha de entrega del pedido
    private LocalDate entrega;  
    // Cliente que realiza el pedido
    private Cliente cliente;
    // Producto del pedido
    private Producto producto;
    // Cantidad del pedido en kilogramos
    private int cantidad;
    // Estado del pedido
    private PedidoEstado estado;
    
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
        creacion = LocalDate.now();
        entrega = LocalDate.now().plusDays(10);
        id = LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant().toEpochMilli();
        estado = PedidoEstado.PENDIENTE;
        
    }

    /**
     * Devuelve el identificador único del pedido.
     * @return El identificador único del pedido.
     */
    public long getId(){
        return id;
    }

    /**
     * Devuelve la fecha de creación del pedido.
     * @return La fecha de creación del pedido.
     */
    public LocalDate getCreacion(){
        return creacion;
    }

    /**
     * Devuelve la fecha de entrega prevista para el pedido.
     * @return La fecha de entrega prevista para el pedido.
     */
    public LocalDate getEntrega(){
        return entrega;
    }

    /**
     * Obtiene el cliente que realizó este pedido.
     * @return El objeto Cliente que realizó el pedido.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Obtiene el producto asociado a este pedido.
     * @return El objeto Producto solicitado en este pedido.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Obtiene la cantidad del producto solicitado en este pedido.
     * @return La cantidad del producto solicitado en kilogramos.
     */
    public int getCantidad() {
        return cantidad;
    }
}
