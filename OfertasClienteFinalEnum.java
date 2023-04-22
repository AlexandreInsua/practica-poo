
/**
 * Enumerado  OfertasClienteFinalEnum. Representa las diferentes ofertas que 
 * pueden ser realizadas a un pedido realizado por un cliente final.
 * 
 * @author Alexandre Insua Moreira.
 * @version 
 */
public enum OfertasClienteFinalEnum
{
    BASICA(1.0f), 

    OFERTA_05(0.95f), 

    OFERTA_10(0.90f);

    private final float descuento;

    OfertasClienteFinalEnum(float descuento) {
        this.descuento = descuento;
    }

    public float getDescuento() {
        return descuento;
    }

}
