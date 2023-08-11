
/**
 * 
 * 
 * @author Alexandre Insua Moreira
 * @version 
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
    // longitud en km del tramo miniomo de la gran logística
    private final int TRAMO_PERECEDERO = 100;
    // longitud en km del tramo de la gran logística de los productos perecederos
    private final int TRAMO_NO_PERECEDERO = 50;
    // modificador del coste los tramos de la gran logística de los productos perecederos
    private final float MODIFICADOR = 0.5f;
    // constante que representa el valor en kilogramos de una tonelada métrica
    private final float TM = 1000;

    /**
     * Constructor de la clase Logistica.
     *
     * Este constructor crea una instancia de Logistica con los parámetros proporcionados,
     * asignando los valores de nombre y precios correspondientes después de validar los precios.
     *
     * @param n Nombre de la logística.
     * @param pglp precio de gran logística de los produtos perecederos (GLP).
     * @param pplp precio de la pequeña logística de los produtos perecederos (PLP).
     * @param pglnp Precio de Producto Perecedero (GLNP).
     * @param pplnp Precio de Producto No Perecedero (PLNP).
     * 
     * @throws IllegalArgumentException Si la condición de validación no se cumple y los precios no satisfacen
     *                                  las relaciones requeridas.
     */

    public Logistica(String n, float pglp, float pplp, float pglnp,float pplnp)
    {

        if (!validatePrices(pglp, pplp, pglnp, pplnp)){
            throw new IllegalArgumentException("No se puede crear la logística porque los precios son incorrectos.");
        } 
        nombre = n;
        precioGLP = pglp;
        precioPLP = pplp;
        precioGLNP = pglnp;                ;
        precioPLNP = pplnp;

    }

    /**
     * Devuelve el nombre de la logística.
     * 
     * @return El nombre almacenado en esta instancia.
     */
    public String getNombre(){
        return nombre;
    }

    public float calcularPrecioLogisticaPedido(Pedido pedido){
        // Cliente cliente, Producto producto, float cantidad       
        float sumaCostesTonelada = 0;

        if (pedido.getProducto() instanceof Perecedero){
            sumaCostesTonelada = calculateBigLogisticsCost(
                calculateBigLogisticsDistance(pedido.getCliente().getDistancia(),TRAMO_PERECEDERO ),
                precioGLP)
            + calculateSmallLogisticsCost(
                calculateSmallLogisticsDistance(pedido.getCliente().getDistancia(),TRAMO_PERECEDERO),
                precioPLP, pedido.getCantidad());
        } else if (pedido.getProducto() instanceof NoPerecedero){
            sumaCostesTonelada = calculateSections(pedido.getCliente().getDistancia()) * calculateSectionCost(pedido.getProducto().getPrecio(), pedido.getCantidad())
                + calculateBigLogisticsCost(calculateBigLogisticsDistance(pedido.getCliente().getDistancia(),TRAMO_NO_PERECEDERO ),precioGLNP) * calculateTons(pedido.getCantidad())
                + calculateSmallLogisticsCost(calculateSmallLogisticsDistance(pedido.getCliente().getDistancia(),TRAMO_NO_PERECEDERO),precioPLNP, pedido.getCantidad());
        }
        return sumaCostesTonelada;
    }

    
    public String toString(){
        return getNombre() 
        + "\n\t  Oferta de precios para grandes y pequeños trayectos."
        + "\n\t  Productos perecederos:  " + precioGLP + " €/km y " + precioPLP + " €/km"
        + "\n\t  Productos no perecederos:  " + precioGLNP + " €/km y " + precioPLNP + " €/km"
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
    private boolean validatePrices(float pglp, float pplp, float pglnp,float pplnp){
        if(pglp > pplp && pglp > pglnp && pglnp > pplnp){
            return true;
        }
        return false;
    }

    int calculateSmallLogisticsDistance(int distancia, int tramo){
        return distancia % tramo; 
    }

    int calculateBigLogisticsDistance(int distancia, int tramo) {
        return distancia -= calculateSmallLogisticsDistance(distancia, tramo);
    }

    int calculateTons(int cantidad) {
        return cantidad % TM == 0 ? (int) (cantidad / TM ): (int) ( cantidad / TM + 1);    
    }

    int calculateSections(int distancia){
        return distancia / TRAMO_NO_PERECEDERO;
    }

    float calculateSectionCost(float precio, int cantidad){
        return MODIFICADOR * precio * cantidad;
    }

    float calculateBigLogisticsCost(int distancia, float precio){
        return distancia * precio;
    }

    float calculateSmallLogisticsCost(int distancia, float precio, int cantidad){
        return distancia * precio * cantidad;
    }
}
