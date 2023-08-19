import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase Federado que representa un producto federado.
 * 
 * Esta clase hereda de la clase Productor y se utiliza para gestionar
 * la federaci�n de productos por parte de peque�os productores.
 * 
 * @author Alexandre Insua Moreira.
 * @version 1.0
 */
public class Federado extends Productor
{
    // Colecci�n de de productos federados
    private ArrayList<ProductoFederado> productosFederados;

    // L�mite de extensi�n de produccion federado en ha
    private final int EXTENSION_LIMITE = 5;

    /**
     * Constructor de la clase Federado que recibe el nombre del producto como par�metro.
     * 
     * @param nombreProducto El nombre del producto.
     */
    public Federado(String nombreProducto)
    {
        super(nombreProducto);
        productosFederados = new ArrayList<ProductoFederado>();
    }

    /**
     * Federa un producto para un productor espec�fico, verificando si cumple con los requisitos necesarios.
     * Verifica que el productor que federa el produto sea de la clase {@link PeqProdutor}, que el producto
     * que se va a federar sea el adecuado y que no se supere el limite de la extensi�n para peque�os 
     * produtores.
     * 
     * @param productor El productor al que se desea federar el producto.
     * @param producto El producto del productor que se desea federar.
     * @return {@code true} si el producto se feder� exitosamente, {@code false} si no se pudo federar debido a restricciones o excepciones.
     * */
    public boolean federarProducto(Productor productor, ProductoProductor producto)
    {
        boolean response = false;
        try {
            if (esPequenoProductor(productor) && esProductoProductor(productor, producto) && verificaExtenstionTotal(producto)) {
                productosFederados.add(new ProductoFederado(productor, producto));
                producto.setFederado(true);
                response = true;}
        } catch (Exception e) {
            System.err.println(e);
        }

        return response;
    }

    /**
     * Devuelve una representaci�n en forma de cadena de caracteres del productor federado,
     * incluyendo su nombre y la informaci�n de los productos federados.
     * 
     * @return Una cadena que contiene informaci�n sobre el productor y sus productos federados.
     */
    public String toString(){ 
        return  getNombre() + productosFederados.stream()
        .map( producto -> producto.toString())
        .reduce( "", (acc, str)-> acc + "\n\t" + str );
    }

    private boolean esPequenoProductor(Productor productor){
        if (!(productor instanceof PeqProductor)) {
            throw new IllegalArgumentException("Un productor que no sea peque�o no puede federar productos.");    
        }
        return true;
    }

    private boolean esProductoProductor(Productor productor, ProductoProductor producto){

        if (!producto.getProducto().getNombre().equals(getNombre())){
            throw new IllegalArgumentException("No se puede federar un producto diferente.");
        }

        List<ProductoFederado> filtrados = productosFederados.stream()
            .filter(
                productoFederado -> 
                    productoFederado.getProdutor().getNombre() == productor.getNombre() 
                    && productoFederado.getProductoProductor().getProducto().getNombre() == producto.getProducto().getNombre()
            )
            .collect(Collectors.toList());

        if (filtrados.size() > 0){
            throw new IllegalArgumentException("No se puede federar un producto que ya ha sido federado.");
        }
        return true;
    }

    private boolean verificaExtenstionTotal(ProductoProductor producto) throws Exception {
        float suma = productosFederados.stream()
            .map(ProductoFederado::getProductoProductor)
            .map(ProductoProductor::getExtension)
            .reduce(0f, (a, b) -> a + b);

        if ( suma + producto.getExtension() > EXTENSION_LIMITE){
            throw new Exception("No se puede federar el producto porque sobrepasa la extensi�n l�mite de hect�reas permitidas.");
        }
        return true;
    }
}
