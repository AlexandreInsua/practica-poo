import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * La clase Pedido representa un pedido realizado por un cliente 
 * para un producto específico en una cantidad determinada.
 * Cada instancia de esta clase contiene información sobre 
 * el cliente que realiza el pedido, el producto solicitado
 * y la cantidad en kilogramos del producto solicitado.
 * 
 * @author Alexandre Insua Moreira
 * @version 1.0
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
    // Empresa de logística encargada de servir el pedido
    private Logistica logistica;
    // Estado del pedido
    private PedidoEstado estado; 
    // private precio de referencia del producto en €
    private float precio;
    // coste del producto en €
    private float coste;
    // Coste de la logistica en €
    private float costeLogistica;
    // Cantidad de margen de beneficio de la cooperativa en €
    private float beneficio;
    // Cantidad de IVA aplicado en €
    private float iva;
    // Cantidad e coste total en €
    private float total;
    // variable auxiliar para formatear precios
    private DecimalFormat priceFormatter;
    // Determina el número de dias de aplazamiento para revisar los precios antes de la entreta
    private final int DIAS_REVISION_PRECIOS = 10;
    // Margen de beneficio de la cooperativa aplicable a los pedidos realizados por un distribuidor
    private final float MARGEN_DISTRIBUIDOR = 0.05f;
    // Margen de beneficio de la cooperativa aplicable a los pedidos realizados por un consumidor final
    private final float MARGEN_MINORISTA = 0.15f;
    // IVA aplicable a los pedidos realizados por un consumidor final
    private final float IVA = 0.10f;

    /**
     * Constructor para objetos de la clase Pedido.
     * 
     * @param cliente El cliente que realiza el pedido.
     * @param producto El producto solicitado en el pedido.
     * @param cantidad La cantidad del producto solicitado en kilogramos.
     */
    public Pedido(Long id, Cliente cliente, Producto producto, int cantidad, Logistica logistica)
    {
        this.id = id;
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.logistica = logistica;
        precio = producto.getPrecio();
        creacion = LocalDate.now();
        entrega = LocalDate.now().plusDays(10);
        estado = PedidoEstado.PENDIENTE; 
        coste = 0;
        costeLogistica = 0;
        beneficio = 0;
        iva = 0;
        total = 0;
        priceFormatter = new DecimalFormat("#.##");
    }

    /**
     * Constructor para objetos de la clase Pedido.
     * 
     * @param cliente El cliente que realiza el pedido.
     * @param producto El producto solicitado en el pedido.
     * @param cantidad La cantidad del producto solicitado en kilogramos.
     * @param fechaEntrega La fecha de entrega del pedido.
     */
    public Pedido(Long id, Cliente cliente, Producto producto, int cantidad, Logistica logistica, String fechaEntrega){
        this(id, cliente, producto, cantidad, logistica);
        this.entrega = LocalDate.parse(fechaEntrega, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Devuelve el identificador único del pedido.
     * 
     * @return El identificador único del pedido.
     */
    public long getId(){
        return id;
    }

    /**
     * Devuelve la fecha de creación del pedido.
     * 
     * @return La fecha de creación del pedido.
     */
    public LocalDate getCreacion(){
        return creacion;
    }

    /**
     * Devuelve la fecha de creación del pedido en formato de string "dd/MM/yyyy".
     * 
     * @return La fecha de creación del pedido en formato de string "dd/MM/yyyy".
     */
    public String getCreacionString(){
        return creacion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Devuelve la fecha de entrega prevista para el pedido.
     * 
     * @return La fecha de entrega prevista para el pedido.
     */
    public LocalDate getEntrega(){
        return entrega;
    }

    /**
     * Devuelve la fecha de entrega del pedido en formato de string "dd/MM/yyyy".
     * 
     * @return La fecha de entrega del pedido en formato de string "dd/MM/yyyy".
     */
    public String getEntregaString(){
        return entrega.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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

    /**
     * Obtiene la logística asignada al pedido.
     * 
     * @return la logística asignada al pedido.
     */
    public Logistica getLogistica(){
        return logistica;
    }

    /**
     * Obtiene el estado del pedido.
     * 
     * @return El estado del producto
     */
    public PedidoEstado getEstado(){
        return estado;
    }

    /**
     * Obtiene el estado del pedido.
     * 
     * @return El estado del producto
     */
    public String getEstadoString(){
        return estado == PedidoEstado.PENDIENTE ? "Pendiente" : "Entregado";
    }

    /**
     * Establece una representación en forma de cadena de caracteres del estado del pedido.
     */
    public void setEstadoEntregado(){
        estado =  PedidoEstado.ENTREGADO;
    }

     /**
     * Establece el precio de referencia del producto en €.
     * 
     * @param El precio de referencia del producto  en €.
     */
    public void setPrecio(float precio){
        this.precio = precio;
    }
    
    /**
     * Obtiene una representación en forma de cadena de caracteres del precio de referencia del producto en €.
     * 
     * @return El precio de referencia del producto en €.
     */
    public String getPrecioString(){
        return priceFormatter.format(precio);
    }
    
    /**
     * Establece el coste de la mercancía del pedido en €.
     * 
     * @param El coste de la mercancía del pedido en €.
     */
    public void setCoste(float coste){
        this.coste = coste;
    }

    /**
     * Obtiene el coste de la mercancía del pedido en €.
     * 
     * @return El coste de la mercancía del pedido en €.
     */
    public float getCoste(){
        return coste;
    }

    /**
     * Obtiene una representación en forma de cadena de caracteres del coste de la mercancía del pedido en €.
     * 
     * @return El coste de la mercancía del pedido en €.
     */
    public String getCosteString(){
        return priceFormatter.format(coste);
    }

    /**
     * Establece el coste de la logística del pedido en €.
     * 
     * @param El coste de la logística del pedido en €.
     */
    public void setCosteLogistica(float costeLogistica){
        this.costeLogistica = costeLogistica;
    }

    /**
     * Obtiene el coste de la logística del pedido en €.
     * 
     * @return El coste de la logística del pedido en €.
     */
    public float getCosteLogistica(){
        return costeLogistica;
    }

    /**
     * Obtiene una representación en forma de cadena de caracteres del coste de la logística del pedido en €.
     * 
     * @return El coste de la logística del pedido en €.
     */
    public String getCosteLogisticaString(){
        return priceFormatter.format(costeLogistica);
    }

    /**
     * Establece el margen beneficio de la cooperativa en €.
     * 
     * @param El margen beneficio de la cooperativa pedido en €.
     */
    public void setBeneficio(float beneficio){
        this.beneficio = beneficio;
    }

    /**
     * Obtiene el margen beneficio de la cooperativa en €.
     * 
     * @return El margen beneficio de la cooperativa en €.
     */
    public float getBeneficio(){
        return beneficio;
    }

    /**
     * Obtiene una representación en forma de cadena de caracteres del margen beneficio de la cooperativa en €.
     * 
     * @return El margen beneficio de la cooperativa en €.
     */
    public String getBeneficioString(){
        return priceFormatter.format(beneficio);
    }

    /**
     * Establece la cantidad de iva del pedido en €.
     * 
     * @param La cantidad de iva pedido en €.
     */
    public void setIva(float iva){
        this.iva = iva;
    }

    /**
     * Obtiene la cantidad de iva  del pedido en €.
     * 
     * @return La cantidad de iva  del pedido en €.
     */
    public float getIva(){
        return iva;
    }

    /**
     * Obtiene una representación en forma de cadena de caracteres del IVA 
     * si el cliente es una instancia de la clase Minorista o una cadena vacía en caso contrario;
     * 
     * @return una representación en forma de cadena de caracteres del IVA.
     */
    public String getIvaString(){
        if (cliente instanceof Minorista){
            return ",€ IVA: " + priceFormatter.format(iva);
        }
        return "";
    }

    /**
     * Establece el coste total del pedido en €.
     * 
     * @param El coste total del pedido en €.
     */
    public void setTotal(float total){
        this.total = total;
    }

    /**
     * Obtiene el coste total del pedido en €.
     * 
     * @return el coste total del pedido en €.
     */
    public float getTotal(){
        return total;
    }

    /**
     * Obtiene una representación en forma de cadena de caracteres del coste total del pedido en €.
     * 
     * @return el coste total del pedido en €.
     */
    public String getTotalString(){
        return priceFormatter.format(total);
    }

    /**
     * Comprueba si la entrega del pedido se ha pospuesto más allá de un período de revisión de precios.
     *
     * @param creacion Fecha de creación del pedido.
     * @param entrega Fecha de entrega planificada pedido.
     * @return <code>true</code> si la entrega se ha pospuesto más allá del período de revisión de precios,
     *         <code>false</code> en caso contrario.
     */
    public boolean esPospuesto(LocalDate creacion, LocalDate entrega){
        return Period.between(creacion, entrega).getYears() > 0 || Period.between(creacion, entrega).getMonths() > 0 || Period.between(creacion, entrega).getDays() > DIAS_REVISION_PRECIOS;
    }

    /**
     * Actualiza los costes del pedido según el precio de referencia del producto,
     * la oferta de la logística y el tipo de cliente. Los costes calculados son
     * el coste bruto de la mercancía, que recibe el productor, el beneficio de 
     * la cooperativa, el coste de la logística, el IVA si es aplicable y el total
     * de los costes.
     */
    public void actualizarCostesPedido(){
        setPrecio(producto.getPrecio());
        setCoste(cantidad * producto.getPrecio());
        setBeneficio(coste * getConstanteMargenBeneficios());
        setCosteLogistica(logistica.calcularPrecioLogisticaPedido(this));
        setIva((coste + beneficio + costeLogistica) * getConstanteIVA());
        setTotal(coste + beneficio + costeLogistica + iva);
    }

    /**
     * Actualiza la cantidad disponible de un producto. De cada productor que
     * tiene producción, se descuenta una cantidad proporcional, se actualiza 
     * la cantidad disponible en el producto asignado al productor y en el total
     * del producto.
     */
    public void actualizarCantidadesDisponibles(){
        ArrayList<NoFederado> nf = producto.getProdutores();
        nf.forEach(productor -> {
                ProductoProductor productoProductor = productor.buscarProducto(producto.getNombre());
                float ratio = productoProductor.getDisponible() / producto.getDisponible();
                float partida = cantidad * ratio;
                float nuevoDisponible = productoProductor.getDisponible() - partida;
                productoProductor.setDisponible(nuevoDisponible);
            });
        producto.setDisponible(producto.getDisponible() - cantidad);
    }

    /**
     * Para cada productor que participa en la provisión del pedido,
     * se calcula la partida proporcional correspondiente y se crea 
     * un objeto de tipo venta y se le asigna
     */
    public void asignarVentas(){
        ArrayList<NoFederado> nf = producto.getProdutores();
        nf.forEach(productor -> {
                ProductoProductor productoProductor = productor.buscarProducto(producto.getNombre());
                float ratio = productoProductor.getDisponible() / producto.getDisponible();
                float partida = cantidad * ratio;
                productor.agregarVenta(new Venta(this, partida, producto.getPrecio()));
            });
    }

    /**
     *  Devuelve una representación en forma de cadena de caracteres del objeto Pedido.
     *  
     *  @return la representación en forma de cadena de caracteres del objeto Pedido.
     */
    public String toString(){
        return "Nº: "+ getId() +" Fecha: "+getCreacionString() +" Entrega: "+ getEntregaString() + " " + getEstadoString() 
        +"\n\tCliente: "+ getCliente().getNombre() +"(" + getCliente().getClass().getName() + ") Producto: "+ getProducto().getNombre().toUpperCase() 
        +" ("+ getPrecioString() + " €/kg)  Cantidad: "+ getCantidad()+" kg" 
        +"\n\tBruto: "+ getCosteString() +"€ Logística: "+ getCosteLogisticaString() + "€ Beneficio: "+ getBeneficioString() + getIvaString() + "€ Total: "+ getTotalString() + "€"
        +"\n";
    }

    private float getConstanteMargenBeneficios(){
        if (cliente instanceof Distribuidor){
            return MARGEN_DISTRIBUIDOR;
        }
        return MARGEN_MINORISTA;
    }

    private float getConstanteIVA(){
        if (cliente instanceof Minorista){
            return IVA;
        }
        return 0;
    }
}
