import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        Producto aceite = new NoPerecedero("Aceite", 3.5f, 0.5f);
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
        ProductoProductor pp7 = new ProductoProductor(aceite, 5f);
        ProductoProductor pp8 = new ProductoProductor(naranjas, 15f);

        NoFederado juanP = new PeqProductor("Juan P.");     
        juanP.asignarProducto(pp1);
        juanP.asignarProducto(pp2);
        juanP.asignarProducto(pp3);

        NoFederado vergara = new GranProductor("Grupo Vergara");
        vergara.asignarProducto(pp4);

        NoFederado soniaR = new PeqProductor("Sonia R.");
        soniaR.asignarProducto(pp5);
        soniaR.asignarProducto(pp6);

        NoFederado celsoP = new GranProductor("Celso P.");
        celsoP.asignarProducto(pp7);

        NoFederado alcantara = new GranProductor("Alc�ntara S.A.");
        alcantara.asignarProducto(pp8);

        agregarProductor(juanP);
        agregarProductor(vergara);
        agregarProductor(soniaR);
        agregarProductor(celsoP);
        agregarProductor(alcantara);

        Federado algodonFederado = new Federado("Algod�n");
        federados.add(algodonFederado);
        algodonFederado.federarProducto(juanP, pp2);
        algodonFederado.federarProducto(soniaR, pp6);

        Distribuidor gadial = new Distribuidor("Gadial S.A.", 200);
        Distribuidor disnosa = new Distribuidor("Disnosa", 180);
        Minorista reginaC = new Minorista("Regina C.", 5);
        Minorista millanD = new Minorista("Mill�n D.", 9);

        clientes.add(gadial);
        clientes.add(disnosa);
        clientes.add(reginaC);
        clientes.add(millanD);

        Logistica sutrans = new Logistica("Sutrans S.L.", 0.06f, 0.04f, 0.05f, 0.01f);
        Logistica segura = new Logistica("Hnos. Segura S.L.", 0.05f, 0.04f, 0.03f, 0.02f);

        logisticas.add(sutrans);
        logisticas.add(segura);

        listarProductos();
        listarProductores();
        listarFederados();
        listarClientes();
        listarLog�sticas();

        crearPedido(1l, reginaC, trigo, 50, sutrans);
        crearPedido(2l, gadial, algodon, 1000, segura);
        crearPedido(3l,disnosa, aceite, 2000, sutrans);
        crearPedido(4l, millanD, trigo, 10, "01/10/2023", sutrans);
        crearPedido(5l, gadial, naranjas, 3000, "15/10/2023", sutrans);

        listarPedidos();

        servirPedido(1l);
        servirPedido(2l);
        servirPedido(3l);
        trigo.setPrecio(0.55f);
        servirPedido(4l);
        servirPedido(5l);

        listarPedidos();
        listarVentas();

        listarProductos();
        listarProductores();
        listarFederados();
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
            Producto p = buscarProducto(producto.getProducto().getNombre());
            p.addProductor(productor);
            p.setProduccion(p.getProduccion() + producto.getProduccion());
            p.setDisponible(p.getDisponible() + producto.getDisponible());
        }

    }

    private Producto buscarProducto(String nombreProducto){
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombreProducto)) {
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

    /**
     * Muestra en la consola la lista de cotizacines de todos los productos activos en la cooperativa.
     */
    public void listarCotizaciones(){
        for (Producto producto: productos){
            producto.listarCotizaciones();
        }
    }

    /**
     * Muestra en la consola la lista de cotizacioens de un producto;
     */
    public void ListarCotizaciones(String nombreProducto){
        Producto producto = buscarProducto(nombreProducto);
        producto.listarCotizaciones();
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

    public void listarVentas(){
        System.out.println("\nLISTA DE VENTAS");
        for (NoFederado productor: productores){
            productor.listarVentas();
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
    public boolean crearPedido(long id, Cliente cliente, Producto producto, int cantidad, Logistica logistica){
        try {
            if(validateOrder(cliente, producto, cantidad)){
                Pedido pedido = new Pedido(id, cliente, producto, cantidad, logistica);
                pedido.actualizarCostesPedido();
                pedido.actualizarCantidadesDisponibles();
                pedidos.add(pedido);
                return true;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return false; 
    }

    public boolean crearPedido(long id, Cliente cliente, Producto producto, int cantidad, String fechaEntrega, Logistica logistica){
        try {
            if ( validateOrder(cliente, producto, cantidad) && validarFechaEntrega(fechaEntrega)){
                Pedido pedido = new Pedido(id, cliente, producto, cantidad, logistica, fechaEntrega);
                pedido.actualizarCostesPedido();
                pedido.actualizarCantidadesDisponibles();
                pedidos.add(pedido);
                return true;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return false; 
    }

    public boolean servirPedido(long pedidoId){
        Pedido pedido = buscarPedido(pedidoId);
        if (pedido != null){
            if(pedido.esPospuesto(pedido.getCreacion(), pedido.getEntrega())){
                System.out.println("todo: actualizar prezos");
                pedido.actualizarCostesPedido();
            }
            pedido.setEstadoEntregado();
            pedido.asignarVentas();
            return true;
        } else {
            System.err.println("No se encuentra el pedido con n�mero " + pedidoId);   
        }
        return false;
    }

    public void listarPedidos(){
        System.out.println("\nLISTA DE PEDIDOS");
        for(Pedido pedido: pedidos){
            System.out.println(" " + pedido.toString());
        }
    }  

    public Pedido buscarPedido(long id){
        for (Pedido pedido: pedidos){
            if (pedido.getId() == id){
                return pedido;
            }
        }
        return null;
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

    private boolean validarFechaEntrega(String fechaEntrega){
        LocalDate entrega = LocalDate.parse(fechaEntrega, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (entrega.isEqual(LocalDate.now()) || entrega.isAfter(LocalDate.now())){
            return true;
        }
        throw new IllegalArgumentException("La fecha de entrega del pedido no puede ser anterior a la fecha de creacion");
    }
    
    // INFORMES ESTAD�STICOS
    
    /** 
     *  Imprime las ventas totales para cada produto de la cooperativa.
     *  Se calcula a partir del total de los pedidos por producto.
     */
    public void listarVentasTotales(){
    // para cada produto filtro os pedidos, mapeo o total e resumo nunha cantidades
    }
    
    /**
     * Imprime los importes de cada productor y producto.
     * Se calcula a partir del monto da las ventas de cada produtor.
     */
    public void listarImportesProductores(){
        // para cada produtor obte�o un arrays dos produtos activos e para cada produto filtro as ventas e resumo o total
    }
    
    /**
     * Imprime los importes de cada empresa de log�stica.
     * Se calcula a partir de los costes de log�stica de los pedidos.
     */
    public void listarImportesLogisticas(){
        // para cada loxistica filtros os pedidos mapeos os custes e resumo o total
        
    }
    
    /**
     * Obtiene los beneficios de la cooperativa.
     * Se calcula a partir del beneficio de cada pedido.
     */
    public void beneficiosCooperativa(){
        // para cada produto, filtros os pedidos mapeo a beneficios e resulto o total, acumulo un total 
    }
    
    /**
     *  Imprime la evoluci�n de precios de referencia de cada producto.
     */
    public void listarEvolucionPreciosReferencia(){
        // para cada produto, pinto a evoluci�n de prezos    
    }
}

