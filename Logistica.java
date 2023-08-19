import java.text.DecimalFormat;
/**
 * Clase que representa una empresa de log�stica.
 * Esta clase maneja los precios y c�lculos relacionados con la log�stica de los pedidos.
 * Los precios se estructuran en funci�n del tipo de producto (perecedero y no perecedero)
 * y del tipo de log�stica dependiendo de la distancia que se debe recorrer
 * (peque�a log�stica y gran log�stica.
 * El c�lculo de los costes de cada pedido se basa en la suma de los costes de la gran log�stica
 * y la peque�a log�stica, pero la forma en que se calculan estos parciales var�an seg�n
 * el tipo de producto del pedido.
 * 
 * @author Alexandre Insua Moreira
 * @version 1.0
 */
public class Logistica
{
    // Nombre de la empresa de log�stica
    private String nombre;
    // Precio fijo por kil�metro para gran log�stica de los productos perecederos
    private float precioGLP;
    // Precio fijo por kil�metro para peque�a log�stica de los productos perecederos
    private float precioPLP;
    // Precio fijo por kil�metro para gran log�stica de los productos no perecederos
    private float precioGLNP;
    // Precio fijo por kil�metro para peque�a log�stica de los productos no perecederos
    private float precioPLNP;
    // longitud en km del tramo minimo de la gran log�stica de los productos perecederos
    private final int TRAMO_PERECEDERO = 100;
    // longitud en km del tramo de la gran log�stica de los productos no perecederos
    private final int TRAMO_NO_PERECEDERO = 50;
    // modificador del coste los tramos de la gran log�stica de los productos perecederos
    private final float MODIFICADOR = 0.5f;
    // constante que representa el valor en kilogramos de una tonelada m�trica
    private final int TM = 1000;
    // variable auxiliar para formatear datos
    DecimalFormat priceFormatter;

    /**
     * Constructor de la clase Logistica.
     *
     * Este constructor crea una instancia de Logistica con los par�metros proporcionados,
     * asignando los valores de nombre y precios correspondientes despu�s de validar los precios.
     *
     * @param nombre Nombre de la log�stica.
     * @param pglp precio de gran log�stica de los produtos perecederos (GLP).
     * @param pplp precio de la peque�a log�stica de los produtos perecederos (PLP).
     * @param pglnp Precio de Producto Perecedero (GLNP).
     * @param pplnp Precio de Producto No Perecedero (PLNP).
     * 
     * @throws IllegalArgumentException Si la condici�n de validaci�n no se cumple y los precios no satisfacen
     *                                  las relaciones requeridas.
     */
    public Logistica(String nombre, float pglp, float pplp, float pglnp,float pplnp)
    {

        if (!validarPrecios(pglp, pplp, pglnp, pplnp)){
            throw new IllegalArgumentException("No se puede crear la log�stica porque los precios son incorrectos.");
        } 
        this.nombre = nombre;
        precioGLP = pglp;
        precioPLP = pplp;
        precioGLNP = pglnp;                ;
        precioPLNP = pplnp;  
        priceFormatter = new DecimalFormat("#.##");
    }

    /**
     * Devuelve el nombre de la log�stica.
     * 
     * @return El nombre almacenado en esta instancia.
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Calcula el precio total de la log�stica para un pedido dado.
     * 
     * @param pedido El pedido para el cual se calcular� el precio de la log�stica.
     * @return El costo total de la log�stica para el pedido.
     */
    public float calcularPrecioLogisticaPedido(Pedido pedido){
        float sumaCostesTonelada = 0;

        if (pedido.getProducto() instanceof Perecedero){
            sumaCostesTonelada = calcularCosteGranLogistica(
                calculaDistanciaGranLogistica(pedido.getCliente().getDistancia(),TRAMO_PERECEDERO ),
                precioGLP)
            + calcularCostePequenaLogistica(
                calcularDistanciaPequenaLogistica(pedido.getCliente().getDistancia(),TRAMO_PERECEDERO),
                precioPLP, pedido.getCantidad());
        } else if (pedido.getProducto() instanceof NoPerecedero){
            sumaCostesTonelada = calcularNumeroTramos(pedido.getCliente().getDistancia()) * calcularCosteTramo(pedido.getProducto().getPrecio(), pedido.getCantidad())
            + calcularCosteGranLogistica(calculaDistanciaGranLogistica(pedido.getCliente().getDistancia(),TRAMO_NO_PERECEDERO ),precioGLNP) * calcularToneladas(pedido.getCantidad())
            + calcularCostePequenaLogistica(calcularDistanciaPequenaLogistica(pedido.getCliente().getDistancia(),TRAMO_NO_PERECEDERO),precioPLNP, pedido.getCantidad());
        }
        return sumaCostesTonelada;
    }

    /**
     * Devuelve una representaci�n en cadena de la instancia de Logistica.
     * 
     * @return Una cadena que contiene el nombre de la log�stica y sus precios asociados.
     */
    public String toString(){
        return getNombre() 
        + "\n\tOferta de precios para grandes y peque�os trayectos."
        + "\n\t Productos perecederos:  " + priceFormatter.format(precioGLP) + " �/km y " + priceFormatter.format(precioPLP) + " �/km"
        + "\n\t Productos no perecederos:  " + priceFormatter.format(precioGLNP) + " �/km y " + priceFormatter.format(precioPLNP) + " �/km"
        ; 
    }

    /**
     * Valida los precios de los los costes de los diferentes tipos de logistica
     * en funci�n del tipo de producto.
     * Verifica que se cumpla el precio de la gran log�stica sea mayor 
     * que el coste de la peque�a log�stica para el mismo tipo de producto.
     * Verifica que el precio de la gran log�stica de los produtos pereceros
     * sea mayor que el de la gran log�stica de los no perecederos.
     *
     * @param pglp precio de gran log�stica de los produtos perecederos (GLP).
     * @param pplp precio de la peque�a log�stica de los produtos perecederos (PLP).
     * @param pglnp Precio de Producto Perecedero (GLNP).
     * @param pplnp Precio de Producto No Perecedero (PLNP).
     * @return true si el precio GLP es mayor que los precios PLP y GLNP es mayor que PLNP 
     *         y GLP es mayor que GLNP, false en caso contrario.
     */
    private boolean validarPrecios(float pglp, float pplp, float pglnp,float pplnp){
        if(pglp > pplp && pglp > pglnp && pglnp > pplnp){
            return true;
        }
        return false;
    }

    private int calcularDistanciaPequenaLogistica(int distancia, int tramo){
        return distancia % tramo; 
    }

    private int calculaDistanciaGranLogistica(int distancia, int tramo) {
        return distancia -= calcularDistanciaPequenaLogistica(distancia, tramo);
    }

    private int calcularToneladas(int cantidad) {
        return cantidad % TM == 0 ? (int) (cantidad / TM ): (int) ( cantidad / TM + 1);    
    }

    private int calcularNumeroTramos(int distancia){
        return distancia / TRAMO_NO_PERECEDERO;
    }

    private float calcularCosteTramo(float precio, int cantidad){
        return MODIFICADOR * precio * cantidad;
    }

    private float calcularCosteGranLogistica(int distancia, float precio){
        return distancia * precio;
    }

    private float calcularCostePequenaLogistica(int distancia, float precio, int cantidad){
        return distancia * precio * cantidad;
    }
}
