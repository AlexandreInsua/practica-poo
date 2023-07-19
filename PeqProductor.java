
/**
 * Clase PeqProductor que representa un productor pequeño.
 * @author Alexandre Insua Moreira
 * @version 
 */
public class PeqProductor extends NoFederado
{

    private ProductoProductor[] productos;
    private int contador;
    private float extensionTotal;
    private final int EXTENSION_LIMITE = 5;

    public PeqProductor(String nombre)
    {
        super(nombre);
        productos = new ProductoProductor[5];
        contador = 0;

    }

    public String toString(){
        return getNombre() + " Pequeño productor" + listProducts();
    }

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

    private void checkProductNumber() throws Exception {
        if (contador >= 5) {
            throw new Exception("Un pequeño productor sólo puede cultivar cinco productos diferentes.");
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
            throw new Exception("Un pequeño productor sólo puede cultivar un máximo de " + EXTENSION_LIMITE + " hectáreas");
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

    public void verInfo(){
        System.out.println(toString());
    }
}

