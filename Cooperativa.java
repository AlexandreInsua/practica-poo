import java.util.ArrayList;
import java.util.List;

/**
 * Clase Cooperativa. Representa la cooperativa empresarial.
 * 
 * @author Alexandre Insua Moreira
 * @version 
 */
public class Cooperativa
{
    private final int PRODUCCION_LIMITE = 5;

    ArrayList<Producto> productos;
    ArrayList<NoFederado> productores;
    ArrayList<Federado> federados;
    ArrayList<Logistica> logisticas;
    ArrayList<Cliente> clientes;
    ArrayList<Pedido> pedidos;

    /**
     * Constructor para objetos de la clase Cooperativa
     */
    public Cooperativa()
    {
        productos = new ArrayList<Producto>();
        productores = new ArrayList<NoFederado>();
        federados = new ArrayList<Federado>();
        logisticas = new ArrayList<Logistica>();
        clientes = new ArrayList<Cliente>();
        pedidos = new ArrayList<Pedido>();

        run();
    }

    private void run (){
        System.out.println("INICIANDO LA APLICACIÓN...");

        test();
        System.out.println("SALIENDO DE LA APLICACIÓN...");
        // System.exit(0);
    }

    // TEST
    private void test(){
        Producto p1 = new NoPerecedero("Aceite", 3.5f, 0.8f);
        Producto p2 = new NoPerecedero("Algodón", 1.5f, 0.23f);
        Producto p3 = new Perecedero("Naranjas", 2.3f, 0.55f);
        Producto p4 = new Perecedero("Tomates", 6.0f, 0.95f);
        Producto p5 = new Perecedero("Melocotones", 2.4f, 0.47f);
        Producto p6 = new Perecedero("Ciruelas", 2.5f, 0.67f);
        Producto p7 = new NoPerecedero("Trigo", 2.4f, 0.47f);

        agregarProducto(p1);
        agregarProducto(p2);
        agregarProducto(p3);
        agregarProducto(p4);
        agregarProducto(p5);  
        agregarProducto(p6);
        agregarProducto(p7);

        ProductoProductor pp1 = new ProductoProductor(p3,1.5f);
        ProductoProductor pp2 = new ProductoProductor(p2,0.5f);
        ProductoProductor pp3 = new ProductoProductor(p5,1.5f);
        ProductoProductor pp4 = new ProductoProductor(p6,2.6f);
        ProductoProductor pp5 = new ProductoProductor(p7,1.3f);
        ProductoProductor pp6 = new ProductoProductor(p2,0.2f);

        NoFederado pr1 = new PeqProductor("Juan P.");     
        pr1.asignarProducto(pp1);
        pr1.asignarProducto(pp2);
        pr1.asignarProducto(pp3);

        NoFederado pr2 = new PeqProductor("Sonia R.");
        pr2.asignarProducto(pp5);
        pr2.asignarProducto(pp6);

        agregarProductor(pr1);
        agregarProductor(pr2);

        Federado algodon = new Federado("Algodón");
        federados.add(algodon);
        algodon.federarProducto(pr1, pp2);
        algodon.federarProducto(pr2, pp6);
        
        listarProductos();
        
        listarProductores();
        listarFederados();
    }

    // PRODUCTOS
    /**
     * Agrega un nuevo producto a la lista de productos. Si el producto ya está en la lista, se lanza una excepción.
     * @param pro el producto que se va a agregar a la lista
     * @return true si el producto se agregó correctamente a la lista
     * @throws Exception si el producto ya está en la lista
     */
    private boolean addProduct(Producto pro) throws Exception {

        for (Producto p: productos){
            if (pro.getNombre().equals(p.getNombre())){
                throw new IllegalArgumentException("El producto ya está incluido. No se puede agregar el mismo producto de nuevo");
            }
        }

        productos.add(pro);
        return true;
    }

    /**
     * Agrega un producto a la lista de productos y muestra un mensaje en la consola indicando si se agregó correctamente o no.
     * @param producto el producto que se va a agregar a la lista
     */
    public void agregarProducto(Producto producto) {
        try {
            if (addProduct(producto)) {            }
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    private void updateAvailableProduct(NoFederado productor){
        List<ProductoProductor> productos = productor.getProductos();
        for (ProductoProductor producto: productos){
            Producto p = searchProduct(producto.getProducto().getNombre());
            p.addProductor(productor);
            p.setProduccion(p.getProduccion()+ producto.getExtension());
            p.setDisponible(p.getDisponible() + producto.getDisponible());
        }

    }

    private Producto searchProduct(String productName){
        for (Producto producto : productos) {
            if (producto.getNombre().equals(productName)) {
                return producto;
            }
        }
        return null;
    }

    /**
     * Muestra en la consola la lista de productos activos en la cooperativa.
     */
    public void listarProductos(){
        System.out.println("\nPRODUCTOS ACTIVOS EN LA COOPERATIVA");
        for (Producto producto: productos){
            System.out.println(producto.toString());
        }
    }

    // PRODUCTORES NO FEDERADOS
    private boolean addProductor(NoFederado p) {

        for (NoFederado pro: productores){

            if(pro.getNombre().equals(p.getNombre())){
                throw new IllegalArgumentException("No se puede agregar el productor porque el nombre ya existe");    
            }
        }

        productores.add(p);
        return true;

    }

    public void agregarProductor(NoFederado p){

        try {
            if (productores.add(p)) { 
                updateAvailableProduct(p);
            } 
        } catch (Exception e){
            System.err.println(e);
        }
    }

    public void listarProductores(){
        System.out.println("\nPRODUCTORES ACTIVOS EN LA COOPERATIVA");
        for (NoFederado productor: productores){
            System.out.println(productor.toString());
        }
    }

    // PRODUCTOS FEDERADOS
    public void federarProducto(Productor productor, ProductoProductor producto){
        try {
            isFederate(producto);
            // si, comprobar límite
        }
        catch (Exception e) {
            System.err.println(e);
        }
        // non crear produto federado
    }

    private boolean isFederate(ProductoProductor producto){
        if(producto.getFedederado()){
            throw new IllegalArgumentException("No se puede superar un producto ya federado.");    
        }
        return true;
    }

    public void listarFederados(){
        System.out.println("\nPRODUCTOS FEDERADOS");
        for(Federado federado: federados){
            System.out.println(federado.toString());
        }
    }
}

