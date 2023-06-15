
/**
 * Write a description of class NoFederado here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class NoFederado extends Productor
{

    public NoFederado(String nombre)
    {
        super(nombre);
    }

    public abstract boolean asignarProducto(ProductoProductor producto);       
}
