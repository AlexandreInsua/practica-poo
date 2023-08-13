import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * La clase Cotizacion representa una cotizaci�n de precio con una fecha asociada.
 * 
 * Esta clase permite almacenar informaci�n sobre el precio y la fecha de una cotizaci�n.
 * Adem�s, proporciona un m�todo para obtener una representaci�n en cadena de la cotizaci�n.
 * 
 * @author Alexandre Insua Moreira
 * @version 1.0
 */
public class Cotizacion
{
    // Fecha de la cotizaci�n
    private LocalDate fecha;
    // Precio asignado en la cotizacion
    float precio;
    // variable auxiliar para formatear datos
    DecimalFormat priceFormatter = new DecimalFormat("#.##");

    /**
     * Crea una nueva instancia de la clase Cotizacion con la fecha y el precio especificados.
     * 
     * @param fecha La fecha de la cotizaci�n.
     * @param precio El precio asignado en la cotizaci�n.
     */
    public Cotizacion(LocalDate fecha, float precio)
    {
        this.fecha = fecha;
        this.precio = precio;
    }

    /**
     * Devuelve una representaci�n en cadena de la cotizaci�n.
     * 
     * @return Una cadena que contiene la fecha en el formato "dd/MM/yyyy" y el precio en euros por kilogramo.
     */
    public String toString(){
        return  fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +": "+ priceFormatter.format(precio) +"�/kg";
    }
}
