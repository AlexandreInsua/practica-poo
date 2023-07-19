import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase Federado que representa un producto Federado.
 * 
 * @author Alexandre Insua Moreira.
 * @version 
 */
public class Federado extends Productor
{
    private final int EXTENSION_LIMITE = 5;

    // relaci�n de productos federados
    private ArrayList<ProductoFederado> productosFederados;

    /**
     * Constructor de la clase Federado que recibe el nombre del producto como par�metro.
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
     * @param productor El productor al que se desea federar el producto.
     * @param producto El producto del productor que se desea federar.
     * @return {@code true} si el producto se feder� exitosamente, {@code false} si no se pudo federar debido a restricciones o excepciones.
     * */
    public boolean federarProducto(Productor productor, ProductoProductor producto)
    {
        boolean response = false;
        try {
            isSmallProductor(productor);
            checkIsProductorProdutor(productor, producto);
            checkTotalCropArea(producto);
            productosFederados.add(new ProductoFederado(productor, producto));
            producto.setFederado(true);
            response = true;
        } catch (Exception e) {
            System.err.println(e);
        }

        return response;
    }

    /**
     * Verifica si un productor es de la clase {@class PeqProductor}.
     * @param productor El productor que se desea verificar.
     * @return {@code true} si el productor es una instancia de la clase {@class PeqProductor}, {@code false} en caso contrario.
     * @throws IllegalArgumentException Si el productor no es un productor peque�o.
     */
    private boolean isSmallProductor(Productor productor){
        if (!(productor instanceof PeqProductor)) {
            throw new IllegalArgumentException("Un productor que no sea peque�o no puede federar productos.");    
        }
        return true;
    }

    /**
     * Verifica si el producto ya ha sido federado.
     * @param productor El productor que se desea federar uno de sus productos.
     * @param producto El producto del productor que se desea federar.
     * @return {@code true} si el producto a�n no se ha federado, {@code false} en caso contrario.
     * @throws IllegalArgumentException Si el producto no es diferente del federado.
     * @throws IllegalArgumentException Si el producto ya ha sido federado anteriormente.
     */
    private boolean checkIsProductorProdutor(Productor productor, ProductoProductor producto){

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

    /**
     * Verifica si el �rea total de cultivo, incluyendo el producto actual,
     * supera la extensi�n l�mite establecida.
     * @param producto El producto del productor que se desea agregar a la federaci�n.
     * @return {@code true} si el �rea total de cultivo no supera la extensi�n l�mite, {@code false} en caso contrario.
     * @throws Exception Si el �rea total de cultivo, incluyendo el producto actual, supera la extensi�n l�mite que se ha determinado.
     */
    private boolean checkTotalCropArea(ProductoProductor producto) throws Exception {
        float suma = productosFederados.stream()
            .map(ProductoFederado::getProductoProductor)
            .map(ProductoProductor::getExtension)
            .reduce(0f, (a, b) -> a+b);

        if ( suma + producto.getExtension() > EXTENSION_LIMITE){
            throw new Exception("No se puede federar el producto porque sobrepasa la extensi�n l�mite de hect�reas permitidas.");
        }
        return true;
    }

    public String toString(){ 
        return  getNombre() + productosFederados.stream()
        .map( producto -> producto.toString())
        .reduce( "", (acc, str)-> acc + "\n\t" + str );
    }
}
