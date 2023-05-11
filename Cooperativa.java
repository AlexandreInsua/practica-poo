import java.util.ArrayList;

/**
 * Clase Cooperativa. Representa la cooperativa empresarial.
 * 
 * @author Alexandre Insua Moreira
 * @version 
 */
public class Cooperativa
{
    ArrayList<Producto> productos;
    ArrayList<Productor> productores;
    ArrayList<Logistica> logisticas;
    ArrayList<Cliente> clientes;
    ArrayList<Pedido> pedidos;

    /**
     * Constructor para objetos de la clase Cooperativa
     */
    public Cooperativa()
    {
        productos = new ArrayList<Producto>();
        productores = new ArrayList<Productor>();
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

    private void test(){
        Producto p1 = new NoPerecedero("Aceite", 3.5f, 0.8f);
        Producto p2 = new NoPerecedero("Algodón", 1.5f, 0.23f);
        Producto p3 = new Perecedero("Naranjas", 2.3f, 0.55f);
        Producto p4 = new Perecedero("Tomates", 6.0f, 0.95f);
        Producto p5 = new Perecedero("Melocotones", 2.4f, 0.47f);

        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);
        productos.add(p5);
        listarProductos();

    }

    // PRODUCTOS
    /**
     * Agrega un nuevo producto a la lista de productos. Si el producto ya está en la lista, se lanza una excepción.
     *
     * @param pro el producto que se va a agregar a la lista
     * @return true si el producto se agregó correctamente a la lista
     * @throws Exception si el producto ya está en la lista
     */
    private boolean addProduct(Producto pro) throws Exception {

        for (Producto p: productos){
            if (pro.getNombre().equals(p.getNombre())){
                throw new Exception("El producto ya está incluido. No se puede agregar el mismo producto de nuevo");
            }
        }

        productos.add(pro);
        return true;
    }

    /**
     * Agrega un producto a la lista de productos y muestra un mensaje en la consola indicando si se agregó correctamente o no.
     *
     * @param producto el producto que se va a agregar a la lista
     */
    public void agregarProducto(Producto producto) {
        try {
            if (addProduct(producto)) {
                System.out.println("Producto agregado");
            }
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    /**
     * Muestra en la consola la lista de productos activos en la cooperativa.
     */
    public void listarProductos(){
        System.out.println("PRODUCTOS ACTIVOS EN LA COOPERATIVA");
        for (Producto producto: productos){
            System.out.println(producto.toString());
        }
    }

    // PRODUCTORES
    private boolean addProductor(Productor p) {

        return true; 
    }

    public void agregarProductor(Productor p){
        productores.add(p);
    }
}
