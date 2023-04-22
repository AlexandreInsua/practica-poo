
/**
 * Enumerado OfertasDistribuidorEnum. Representa las diferentes ofertas que 
 * pueden ser realizadas a un pedido realizado por un distribuidor.
 * 
 * @author Alexandre Insua Moreira
 * @version 
 */
public enum OfertasDistribuidorEnum
{
    BASICA(1.0f), 

    OFERTA_075(0.925f), 

    OFERTA_125(0.875f);

    private final float descuento;

    OfertasDistribuidorEnum(float descuento) {
        this.descuento = descuento;
    }

    public float getDescuento() {
        return descuento;
    }

}
