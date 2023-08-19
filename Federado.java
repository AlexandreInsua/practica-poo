import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase Federado que representa un producto federado.
 * 
 * Esta clase hereda de la clase Productor y se utiliza para gestionar
 * la federación de productos por parte de pequeños productores.
 * 
 * @author Alexandre Insua Moreira.
 * @version 1.0
 */
public class Federado extends Productor
{
    // Colección de de productos federados
    private ArrayList<ProductoFederado> productosFederados;

    // Límite de extensión de produccion federado en ha
    private final int EXTENSION_LIMITE = 5;

    /**
     * Constructor de la clase Federado que recibe el nombre del producto como parámetro.
     * 
     * @param nombreProducto El nombre del producto.
     */
    public Federado(String nombreProducto)
    {
        super(nombreProducto);
        productosFederados = new ArrayList<ProductoFederado>();
    }

    /**
     * Federa un producto para un productor específico, verificando si cumple con los requisitos necesarios.
     * Verifica que el productor que federa el produto sea de la clase {@link PeqProdutor}, que el producto
     * que se va a federar sea el adecuado y que no se supere el limite de la extensión para pequeños 
     * produtores.
     * 
     * @param productor El productor al que se desea federar el producto.
     * @param producto El producto del productor que se desea federar.
     * @return {@code true} si el producto se federó exitosamente, {@code false} si no se pudo federar debido a restricciones o excepciones.
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
     * Devuelve una representación en forma de cadena de caracteres del productor federado,
     * incluyendo su nombre y la información de los productos federados.
     * 
     * @return Una cadena que contiene información sobre el productor y sus productos federados.
     */
    public String toString(){ 
        return  getNombre() + productosFederados.stream()
        .map( producto -> producto.toString())
        .reduce( "", (acc, str)-> acc + "\n\t" + str );
    }

    private boolean esPequenoProductor(Productor productor){
        if (!(productor instanceof PeqProductor)) {
            throw new IllegalArgumentException("Un productor que no sea pequeño no puede federar productos.");    
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
            throw new Exception("No se puede federar el producto porque sobrepasa la extensión límite de hectáreas permitidas.");
        }
        return true;
    }
}
