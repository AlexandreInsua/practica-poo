import java.util.List;

/**
 * Clase abstracta Productor - Representa un Productor gen�rico no instanciable.
 * Implementa un identificador �nico para cada producto, el nombre y los productos
 * que proporciona a la cooperativa.
 * 
 * @author: Alexandre Insua Moreira.
 * @version: 
 */
public abstract class Productor
{
    private long id;
    private String nombre;

    Productor(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }
}
