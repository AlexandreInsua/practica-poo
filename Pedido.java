import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * La clase Pedido representa un pedido realizado por un cliente para un producto espec�fico en una cantidad determinada.
 * Cada instancia de esta clase contiene informaci�n sobre el cliente que realiza el pedido, el producto solicitado
 * y la cantidad en kilogramos del producto solicitado.
 * 
 * @author Alexandre Insua Moreira
 * @version
 */
public class Pedido
{
    // N�mero de pedido
    private long id;
    // Fecha de creaci�n del pedido
    private LocalDate creacion;
    // Fecha de entrega del pedido
    private LocalDate entrega;  
    // Cliente que realiza el pedido
    private Cliente cliente;
    // Producto del pedido
    private Producto producto;
    // Cantidad del pedido en kilogramos
    private int cantidad;
    // Empresa de log�stica encargada de servir el pedido
    private Logistica logistica;
    // Estado del pedido
    private PedidoEstado estado; 
    // coste del producto en �
    private float coste;
    // Coste de la logistica en �
    private float costeLogistica;
    // Cantidad de margen de beneficio de la cooperativa en �
    private float beneficio;
    // Cantidad de IVA aplicado en �
    private float iva;
    // Cantidad e coste total en �
    private float total;
    // variable auxiliar para formatear precios
    private DecimalFormat priceFormatter;
    // Determina el n�mero de dias de aplazamiento para revisar los precios antes de la entreta
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
        creacion = LocalDate.now();
        estado = PedidoEstado.PENDIENTE; 
        coste = 0;
        costeLogistica = 0;
        beneficio = 0;
        iva = 0;
        total = 0;
        priceFormatter = new DecimalFormat("#.##");

        entrega = LocalDate.now().plusDays(10);
    }

    public Pedido(Long id, Cliente cliente, Producto producto, int cantidad, Logistica logistica, String fechaEntrega){
        this(id, cliente, producto, cantidad, logistica);
        this.entrega = LocalDate.parse(fechaEntrega, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Devuelve el identificador �nico del pedido.
     * @return El identificador �nico del pedido.
     */
    public long getId(){
        return id;
    }

    /**
     * Devuelve la fecha de creaci�n del pedido.
     * @return La fecha de creaci�n del pedido.
     */
    public LocalDate getCreacion(){
        return creacion;
    }

    /**
     * Devuelve la fecha de creaci�n del pedido en formato de string "dd/MM/yyyy".
     * @return La fecha de creaci�n del pedido en formato de string "dd/MM/yyyy".
     */
    public String getCreacionString(){
        return creacion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Devuelve la fecha de entrega prevista para el pedido.
     * @return La fecha de entrega prevista para el pedido.
     */
    public LocalDate getEntrega(){
        return entrega;
    }

    /**
     * Devuelve la fecha de entrega del pedido en formato de string "dd/MM/yyyy".
     * @return La fecha de entrega del pedido en formato de string "dd/MM/yyyy".
     */
    public String getEntregaString(){
        return entrega.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Obtiene el cliente que realiz� este pedido.
     * @return El objeto Cliente que realiz� el pedido.
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

    public Logistica getLogistica(){
        return logistica;
    }

    /**
     * Obtiene el estado del pedido
     * @return El estado del producto
     */
    public PedidoEstado getEstado(){
        return estado;
    }

    public void setEstadoEntregado(){
        estado =  PedidoEstado.ENTREGADO;
    }

    /**
     * Establece el coste de la mercanc�a del pedido en �.
     * @param El coste de la mercanc�a del pedido en �.
     */
    public void setCoste(float coste){
        this.coste = coste;
    }

    /**
     * Obtiene el coste de la mercanc�a del pedido en �.
     * @return El coste de la mercanc�a del pedido en �.
     */
    public float getCoste(){
        return coste;
    }

    /**
     * Obtiene una representaci�n en forma de cadena de caracteres del coste de la mercanc�a del pedido en �.
     * @return El coste de la mercanc�a del pedido en �.
     */
    public String getCosteString(){
        return priceFormatter.format(coste);
    }

    /**
     * Establece el coste de la log�stica del pedido en �.
     * @param El coste de la log�stica del pedido en �.
     */
    public void setCosteLogistica(float costeLogistica){
        this.costeLogistica = costeLogistica;
    }

    /**
     * Obtiene el coste de la log�stica del pedido en �.
     * @return El coste de la log�stica del pedido en �.
     */
    public float getCosteLogistica(){
        return costeLogistica;
    }

    /**
     * Obtiene una representaci�n en forma de cadena de caracteres del coste de la log�stica del pedido en �.
     * @return El coste de la log�stica del pedido en �.
     */
    public String getCosteLogisticaString(){
        return priceFormatter.format(costeLogistica);
    }

    /**
     * Establece el margen beneficio de la cooperativa en �.
     * @param El margen beneficio de la cooperativa pedido en �.
     */
    public void setBeneficio(float beneficio){
        this.beneficio = beneficio;
    }

    /**
     * Obtiene el margen beneficio de la cooperativa en �.
     * @return El margen beneficio de la cooperativa en �.
     */
    public float getBeneficio(){
        return beneficio;
    }

    /**
     * Obtiene una representaci�n en forma de cadena de caracteres del margen beneficio de la cooperativa en �.
     * @return El margen beneficio de la cooperativa en �.
     */
    public String getBeneficioString(){
        return priceFormatter.format(beneficio);
    }

    /**
     * Establece la cantidad de iva del pedido en �.
     * @param La cantidad de iva pedido en �.
     */
    public void setIva(float iva){
        this.iva = iva;
    }

    /**
     * Obtiene la cantidad de iva  del pedido en �.
     * @return La cantidad de iva  del pedido en �.
     */
    public float getIva(){
        return iva;
    }

    /**
     * Obtiene una representaci�n en forma de cadena de caracteres del IVA 
     * si el cliente es una instancia de la clase Minorista o una cadena vac�a en caso contrario;
     * @return una representaci�n en forma de cadena de caracteres del IVA.
     */
    public String getIvaString(){
        if (cliente instanceof Minorista){
            return ",� IVA: " + priceFormatter.format(iva);
        }
        return "";
    }

    /**
     * Establece el coste total del pedido en �.
     * @param El coste total del pedido en �.
     */
    public void setTotal(float total){
        this.total = total;
    }

    /**
     * Obtiene el coste total del pedido en �.
     * @return el coste total del pedido en �.
     */
    public float getTotal(){
        return total;
    }

    /**
     * Obtiene una representaci�n en forma de cadena de caracteres del coste total del pedido en �.
     * @return el coste total del pedido en �.
     */
    public String getTotalString(){
        return priceFormatter.format(total);
    }

    /**
     * Comprueba si la entrega del pedido se ha pospuesto m�s all� de un per�odo de revisi�n de precios.
     *
     * @param creacion Fecha de creaci�n del pedido.
     * @param entrega Fecha de entrega planificada pedido.
     * @return <code>true</code> si la entrega se ha pospuesto m�s all� del per�odo de revisi�n de precios,
     *         <code>false</code> en caso contrario.
     */
    public boolean esPospuesto(LocalDate creacion, LocalDate entrega){
        return Period.between(creacion, entrega).getDays() > DIAS_REVISION_PRECIOS;
    }

    public void actualizarCostesPedido(){
        setCoste(cantidad * producto.getPrecio());
        setBeneficio(coste * getConstanteMargenBeneficios());
        setCosteLogistica(logistica.calcularPrecioLogisticaPedido(this));
        setIva((coste + beneficio + costeLogistica) * getConstanteIVA());
        setTotal(coste + beneficio + costeLogistica + iva);
    }

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
     *  Devuelve una representaci�n en forma de cadena de caracteres del objeto Pedido.
     *  @return la representaci�n en forma de cadena de caracteres del objeto Pedido.
     */
    public String toString(){
        return "N�: "+ getId() +" Fecha: "+getCreacionString() +" Entrega: "+ getEntregaString() + " Estado: " + getEstado() 
        +"\n\tCliente: "+ getCliente().getNombre() +"(" + getCliente().getClass().getName() + ") Producto: "+ getProducto().getNombre() 
        +"("+ getProducto().getPrecio() + " �/kg)  Cantidad: "+ getCantidad()+" kg" 
        +"\n\tBruto: "+ getCosteString() +"� Log�stica: "+ getCosteLogisticaString() + "� Beneficio: "+ getBeneficioString() + getIvaString() + "� Total: "+ getTotalString() + "�";
    }
    
    
    // obtiene la constante para calcular el margen de beneficio   
    private float getConstanteMargenBeneficios(){
        if (cliente instanceof Distribuidor){
            return MARGEN_DISTRIBUIDOR;
        }
        return MARGEN_MINORISTA;
    }
    
        // obtiene la constante para calcular la cantidad de iva
    private float getConstanteIVA(){
        if (cliente instanceof Minorista){
            return IVA;
        }
        return 0;
    }
}
