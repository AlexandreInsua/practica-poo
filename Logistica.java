import java.text.DecimalFormat;
/**
 * Clase que representa una empresa de logística.
 * Esta clase maneja los precios y cálculos relacionados con la logística de los pedidos.
 * Los precios se estructuran en función del tipo de producto (perecedero y no perecedero)
 * y del tipo de logística dependiendo de la distancia que se debe recorrer
 * (pequeña logística y gran logística.
 * El cálculo de los costes de cada pedido se basa en la suma de los costes de la gran logística
 * y la pequeña logística, pero la forma en que se calculan estos parciales varían según
 * el tipo de producto del pedido.
 * 
 * @author Alexandre Insua Moreira
 * @version 1.0
 */
public class Logistica
{
    // Nombre de la empresa de logística
    private String nombre;
    // Precio fijo por kilómetro para gran logística de los productos perecederos
    private float precioGLP;
    // Precio fijo por kilómetro para pequeña logística de los productos perecederos
    private float precioPLP;
    // Precio fijo por kilómetro para gran logística de los productos no perecederos
    private float precioGLNP;
    // Precio fijo por kilómetro para pequeña logística de los productos no perecederos
    private float precioPLNP;
    // longitud en km del tramo minimo de la gran logística de los productos perecederos
    private final int TRAMO_PERECEDERO = 100;
    // longitud en km del tramo de la gran logística de los productos no perecederos
    private final int TRAMO_NO_PERECEDERO = 50;
    // modificador del coste los tramos de la gran logística de los productos perecederos
    private final float MODIFICADOR = 0.5f;
    // constante que representa el valor en kilogramos de una tonelada métrica
    private final int TM = 1000;
    // variable auxiliar para formatear datos
    DecimalFormat priceFormatter;

    /**
     * Constructor de la clase Logistica.
     *
     * Este constructor crea una instancia de Logistica con los parámetros proporcionados,
     * asignando los valores de nombre y precios correspondientes después de validar los precios.
     *
     * @param nombre Nombre de la logística.
     * @param pglp precio de gran logística de los produtos perecederos (GLP).
     * @param pplp precio de la pequeña logística de los produtos perecederos (PLP).
     * @param pglnp Precio de Producto Perecedero (GLNP).
     * @param pplnp Precio de Producto No Perecedero (PLNP).
     * 
     * @throws IllegalArgumentException Si la condición de validación no se cumple y los precios no satisfacen
     *                                  las relaciones requeridas.
     */
    public Logistica(String nombre, float pglp, float pplp, float pglnp,float pplnp)
    {

        if (!validarPrecios(pglp, pplp, pglnp, pplnp)){
            throw new IllegalArgumentException("No se puede crear la logística porque los precios son incorrectos.");
        } 
        this.nombre = nombre;
        precioGLP = pglp;
        precioPLP = pplp;
        precioGLNP = pglnp;                ;
        precioPLNP = pplnp;  
        priceFormatter = new DecimalFormat("#.##");
    }

    /**
     * Devuelve el nombre de la logística.
     * 
     * @return El nombre almacenado en esta instancia.
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Calcula el precio total de la logística para un pedido dado.
     * 
     * @param pedido El pedido para el cual se calculará el precio de la logística.
     * @return El costo total de la logística para el pedido.
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
     * Devuelve una representación en cadena de la instancia de Logistica.
     * 
     * @return Una cadena que contiene el nombre de la logística y sus precios asociados.
     */
    public String toString(){
        return getNombre() 
        + "\n\tOferta de precios para grandes y pequeños trayectos."
        + "\n\t Productos perecederos:  " + priceFormatter.format(precioGLP) + " €/km y " + priceFormatter.format(precioPLP) + " €/km"
        + "\n\t Productos no perecederos:  " + priceFormatter.format(precioGLNP) + " €/km y " + priceFormatter.format(precioPLNP) + " €/km"
        ; 
    }

    /**
     * Valida los precios de los los costes de los diferentes tipos de logistica
     * en función del tipo de producto.
     * Verifica que se cumpla el precio de la gran logística sea mayor 
     * que el coste de la pequeña logística para el mismo tipo de producto.
     * Verifica que el precio de la gran logística de los produtos pereceros
     * sea mayor que el de la gran logística de los no perecederos.
     *
     * @param pglp precio de gran logística de los produtos perecederos (GLP).
     * @param pplp precio de la pequeña logística de los produtos perecederos (PLP).
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
