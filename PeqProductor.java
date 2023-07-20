import java.util.Arrays;
import java.util.List;

/**
 * Clase PeqProductor que representa a un peque�o productor no federado.
 * Extiende de la clase abstracta NoFederado y proporciona una implementaci�n espec�fica
 * para las operaciones relacionadas con los productos asignados al productor.
 * @author Alexandre Insua Moreira
 * @version 
 */
public class PeqProductor extends NoFederado
{

    // Lista de productos asignados al gran productor
    private ProductoProductor[] productos;
    // Campo auxiliar para controlar el n�mero de productos durante su asignaci�n
    private int contador;
    // Campo auxiliar para controlar la extensi�n total de producci�n
    private float extensionTotal;
    // L�mite de extensi�n total m�xima para los peque�os produtores
    private final int EXTENSION_LIMITE = 5;

    /**
     * Constructor de la clase PeqProductor.
     *
     * @param nombre El nombre del peque�o productor.
     */
    public PeqProductor(String nombre)
    {
        super(nombre);
        productos = new ProductoProductor[5];
        contador = 0;

    }

    /**
     * Asigna un producto al peque�o productor.
     *
     * @param p El producto a ser asignado al peque�o productor.
     * @return true si el producto se asign� correctamente, false en caso contrario.
     * @throws Exception si no se puede agregar el producto debido a restricciones del productor.
     */
    public boolean asignarProducto(ProductoProductor p){
        boolean result = false;

        try {
            checkProductNumber();
            checkIsProduct(p);
            checkTotalCropArea(p);

            if (contador < productos.length){
                productos[contador] = p;
                updateTotalCropArea(p.getExtension());
                contador++;
                result = true;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return result; 
    }

    /**
     * Devuelve la lista de productos asignados al productor.
     * @return Una lista de objetos ProductoProductor que representa los productos asignados
     *         al productor.
     */
    public List<ProductoProductor> getProductos(){
        return Arrays.asList(productos);
    }

    /**
     * Imprime la lista de productos asignados al peque�o productor.
     *
     * @return Una lista de objetos ProductoProductor que representa los productos asignados
     *         al peque�o productor.
     */
    public void verInfo(){
        System.out.println(toString());
    }

    /**
     * Devuelve una representaci�n en cadena del peque�o productor y su lista de productos.
     *
     * @return Una cadena que representa al peque�o productor y su lista de productos asignados.
     */
    public String toString(){
        return getNombre() + " Peque�o productor" + listProducts();
    }

    private void checkProductNumber() throws Exception {
        if (contador >= 5) {
            throw new Exception("Un peque�o productor s�lo puede cultivar cinco productos diferentes.");
        }
    }

    private void checkIsProduct (ProductoProductor p) throws Exception {
        for (int i = 0; i < productos.length; i++){
            if ( productos[i] != null && (productos[i] == p || productos[i].getProducto().getNombre().equals(p.getProducto().getNombre()))) {
                throw new IllegalArgumentException("No se puede agregar el mismo producto por segunda vez");
            }

        }
    }

    private void checkTotalCropArea(ProductoProductor p) throws Exception {
        if (extensionTotal + p.getExtension() > EXTENSION_LIMITE ){
            throw new Exception("Un peque�o productor s�lo puede cultivar un m�ximo de " + EXTENSION_LIMITE + " hect�reas");
        }
    }

    private void updateTotalCropArea(float cropArea){
        extensionTotal += cropArea;

    }

    private String listProducts(){
        String productList = ""; 

        for(ProductoProductor p: productos){
            if (p != null && p.getFedederado() != true) {
                productList += "\n\t" + p.toString();
            }
        }
        return productList; 
    }
}

