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
    private final int LIMITE_PRODUCCION = 5;
    private final int PEDIDO_MIN_DISTRIBUIDOR = 1000;
    private final int PEDIDO_MAX_MINORISTA = 100;
    private final float MARGEN_DISTRIBUIDOR = 0.05f;
    private final float MARGEN_MINORISTA = 0.15f;
    private final float IVA = 0.10f;

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
        System.out.println("INICIANDO LA APLICACI�N...");

        test();
        System.out.println("SALIENDO DE LA APLICACI�N...");
        // System.exit(0);
    }

    // TEST
    public void test(){
        Producto aceite = new NoPerecedero("Aceite", 3.5f, 0.8f);
        Producto algodon = new NoPerecedero("Algod�n", 1.5f, 0.23f);
        Producto naranjas = new Perecedero("Naranjas", 2.3f, 0.55f);
        Producto tomates = new Perecedero("Tomates", 6.0f, 0.95f);
        Producto melocotones = new Perecedero("Melocotones", 2.4f, 0.47f);
        Producto ciruelas = new Perecedero("Ciruelas", 2.5f, 0.67f);
        Producto trigo = new NoPerecedero("Trigo", 2.4f, 0.47f);

        agregarProducto(aceite);
        agregarProducto(algodon);
        agregarProducto(naranjas);
        agregarProducto(tomates);
        agregarProducto(melocotones);  
        agregarProducto(ciruelas);
        agregarProducto(trigo);

        ProductoProductor pp1 = new ProductoProductor(naranjas,1.5f);
        ProductoProductor pp2 = new ProductoProductor(algodon,0.5f);
        ProductoProductor pp3 = new ProductoProductor(melocotones,1.5f);
        ProductoProductor pp4 = new ProductoProductor(ciruelas,2.6f);
        ProductoProductor pp5 = new ProductoProductor(trigo,1.3f);
        ProductoProductor pp6 = new ProductoProductor(algodon,0.2f);

        NoFederado juanP = new PeqProductor("Juan P.");     
        juanP.asignarProducto(pp1);
        juanP.asignarProducto(pp2);
        juanP.asignarProducto(pp3);

        NoFederado soniaR = new PeqProductor("Sonia R.");
        soniaR.asignarProducto(pp5);
        soniaR.asignarProducto(pp6);

        agregarProductor(juanP);
        agregarProductor(soniaR);

        Federado algodonFederado = new Federado("Algod�n");
        federados.add(algodonFederado);
        algodonFederado.federarProducto(juanP, pp2);
        algodonFederado.federarProducto(soniaR, pp6);

        Distribuidor gadial = new Distribuidor("Gadial S.A.", 180);
        Distribuidor disnosa = new Distribuidor("Disnosa", 205);
        Minorista reginaC = new Minorista("Regina C.", 5);
        Minorista millanD = new Minorista("Mill�n D.", 9);

        clientes.add(gadial);
        clientes.add(disnosa);
        clientes.add(reginaC);
        clientes.add(millanD);

        Logistica l1 = new Logistica("Sutrans S.L.", 0.06f, 0.04f, 0.05f, 0.01f);
        Logistica l2 = new Logistica("Hnos. Segura S.L.", 0.05f, 0.04f, 0.03f, 0.02f);

        logisticas.add(l1);
        logisticas.add(l2);

        crearPedido(reginaC, trigo, 50, l1);
        crearPedido(gadial, algodon, 1000, l2);

        listarProductos();
        listarProductores();
        listarFederados();
        listarClientes();
        listarLog�sticas();
        listarPedidos();
    }

    // PRODUCTOS
    /**
     * Agrega un nuevo producto a la lista de productos. Si el producto ya est� en la lista, se lanza una excepci�n.
     * @param pro el producto que se va a agregar a la lista
     * @return true si el producto se agreg� correctamente a la lista
     * @throws Exception si el producto ya est� en la lista
     */
    private boolean addProduct(Producto pro) throws Exception {

        for (Producto p: productos){
            if (pro.getNombre().equals(p.getNombre())){
                throw new IllegalArgumentException("El producto ya est� incluido. No se puede agregar el mismo producto de nuevo");
            }
        }

        productos.add(pro);
        return true;
    }

    /**
     * Agrega un producto a la lista de productos y muestra un mensaje en la consola indicando si se agreg� correctamente o no.
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
            p.setProduccion(p.getProduccion() + producto.getProduccion());
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
        }
        catch (Exception e) {
            System.err.println(e);
        }
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

    // CLIENTES
    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void listarClientes(){
        System.out.println("\nCLIENTES ACTIVOS");
        for(Cliente cliente: clientes){
            System.out.println(" " + cliente.toString());
        }
    }

    // LOG�STICAS
    public void agregarLog�stica(Logistica logistica){
        logisticas.add(logistica);
    }

    public void listarLog�sticas(){
        System.out.println("\nEMPRESAS DE LOG�STICAS ACTIVAS");
        for(Logistica logistica: logisticas){
            System.out.println(" " + logistica.toString());
        }
    }

    // PEDIDOS
    public boolean crearPedido( Cliente cliente, Producto producto, int cantidad, Logistica logistica){
        try {
            if(validateOrder(cliente, producto, cantidad)){
                Pedido pedido = new Pedido(cliente, producto, cantidad);
                // TODO actualizar costes
                pedido.setCoste(pedido.getCantidad() * pedido.getProducto().getPrecio());
                pedido.setBeneficio(pedido.getCoste() * getProfitMarginConst(pedido) );
                pedido.setLogistica(logistica.calcularPrecioLogisticaPedido(pedido));
                pedido.setIva((pedido.getCoste() + pedido.getBeneficio() + pedido.getLogistica() ) * getVATConst(pedido));
                pedido.setTotal(pedido.getCoste() + pedido.getBeneficio() + pedido.getLogistica() + pedido.getIva());

                    // TODO actualizar cantidades disponibles

                pedidos.add(pedido);
                return true;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return false; 
    }

    public boolean servirPedido(Pedido pedido){
        // TODO 
        return false;
    }

    public void listarPedidos(){
        System.out.println("\nLISTAR PEDIDOS");
        for(Pedido pedido: pedidos){
            System.out.println(" " + pedido.toString());
        }
    }  

    // Valida el pedido
    private boolean validateOrder(Cliente cliente, Producto producto, int cantidad){
        if (validateAmountByOrder(cliente, cantidad) && validateAvailableAmount(producto, cantidad)){
            return true;
        } 
        return false;
    }
    // Valida la cantidad del pedido en funcion del tipo de cliente
    private boolean validateAmountByOrder(Cliente cliente, int cantidad){
        if (cliente instanceof Distribuidor && cantidad < PEDIDO_MIN_DISTRIBUIDOR){
            throw new IllegalArgumentException("El pedido de un distribuidor debe ser como m�nimo de 1000kg");
        }
        if (cliente instanceof Minorista && cantidad > PEDIDO_MAX_MINORISTA){
            throw new IllegalArgumentException("El pedido de un consumidor final debe ser como m�ximo de 100kg");
        }
        return true;
    }
    // valida que la cantidad del pedido est� disponible
    private boolean validateAvailableAmount(Producto producto, int cantidad){
        if (producto.getDisponible() < cantidad){
            throw new IllegalArgumentException("La cantidad disponible de " + producto.getNombre() + " es de " + producto.getDisponible() +" kg y se piden " + cantidad + " kg. No se puede realizar el pedido." );
        }
        return true;
    }
    
    // obtiene la constante para calcular el margen de beneficio   
    private float getProfitMarginConst(Pedido pedido){
        if (pedido.getCliente() instanceof Distribuidor){
            return MARGEN_DISTRIBUIDOR;
        }
        return MARGEN_MINORISTA;
    }
    
    // obiene la constante para calcular la cantidad de iva
    private float getVATConst(Pedido pedido){
        if (pedido.getCliente() instanceof Minorista){
            return IVA;
        }
        return 0;
    }
    
}

