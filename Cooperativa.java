import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Cooperativa. Representa la cooperativa empresarial.
 * Esta clase administra productos, productores, clientes, pedidos y
 * las operaciones comerciales dentro de la cooperativa.
 * 
 * Autor: Alexandre Insua Moreira
 * Versi�n: 1.0
 */
public class Cooperativa
{
    // Coleccion que representa a los productos dados de alta en la cooperativa
    ArrayList<Producto> productos;
    // Coleccion que representa a los grandes y peque�os produtores dados de alta en la cooperativa
    ArrayList<NoFederado> productores;
    // Coleccion que representa a los productores federados dados de alta en la cooperativa
    ArrayList<Federado> federados;
    // Coleccion que representa a las empresa de log�stica dadas de alta en la cooperativa
    ArrayList<Logistica> logisticas;
    // Coleccion que representa a los clientes dados de alta en la cooperativa
    ArrayList<Cliente> clientes;
    // Coleccion que representa a los pedidos creados en la cooperativa
    ArrayList<Pedido> pedidos;

    // L�mite m�nimo para un pedido de distribuidor 
    private final int PEDIDO_MIN_DISTRIBUIDOR = 1000;
    // L�mite m�ximo para un peido de cliente final 
    private final int PEDIDO_MAX_MINORISTA = 100;

    // Variable auxiliar para formateo de precios.
    DecimalFormat priceFormatter;

    /**
     * Constructor para objetos de la clase Cooperativa.
     * Inicializa las colecciones y realiza la ejecuci�n inicial de la aplicaci�n.
     */
    public Cooperativa()
    {
        productos = new ArrayList<Producto>();
        productores = new ArrayList<NoFederado>();
        federados = new ArrayList<Federado>();
        logisticas = new ArrayList<Logistica>();
        clientes = new ArrayList<Cliente>();
        pedidos = new ArrayList<Pedido>();
        priceFormatter = new DecimalFormat("#.##");

    }

    /**
     * M�todo principal que ejecuta una demostraci�n de la cooperativa.
     * Crea productos, productores, clientes, pedidos y 
     * ejecuta el m�todo ejecutaDemo.
     * 
     * @param args Los argumentos de l�nea de comandos (no se utilizan en esta aplicaci�n).
     *
     */
    public static void main(String[] args){
        System.out.println("INICIANDO LA APLICACI�N...");

        Cooperativa cooperativa = new Cooperativa();
        cooperativa.ejecutaDemo();

        System.out.println("SALIENDO DE LA APLICACI�N...");
        System.exit(0);
    }

    // DEMO
    /**
     * Ejecuta una demostraci�n de las operaciones de la cooperativa.
     * Este m�todo crea objetos y realiza operaciones para mostrar el funcionamiento de la cooperativa.
     */
    public void ejecutaDemo(){
        System.out.println("Creando los productos...");
        Producto aceite = new NoPerecedero("Aceite", 3.5f, 0.5f);
        Producto algodon = new NoPerecedero("Algod�n", 1.5f, 0.23f);
        Producto naranjas = new Perecedero("Naranjas", 2.3f, 0.55f);
        Producto tomates = new Perecedero("Tomates", 6.0f, 0.95f);
        Producto melocotones = new Perecedero("Melocotones", 2.4f, 0.47f);
        Producto ciruelas = new Perecedero("Ciruelas", 2.5f, 0.67f);
        Producto trigo = new NoPerecedero("Trigo", 2.4f, 0.47f);

        System.out.println("Agregando los productos al...");
        agregarProducto(aceite);
        agregarProducto(algodon);
        agregarProducto(naranjas);
        agregarProducto(tomates);
        agregarProducto(melocotones);  
        agregarProducto(ciruelas);
        agregarProducto(trigo);

        System.out.println("Creando los productores...");
        NoFederado juanP = new PeqProductor("Juan P.");     
        NoFederado vergara = new GranProductor("Grupo Vergara");
        NoFederado soniaR = new PeqProductor("Sonia R.");
        NoFederado alcantara = new GranProductor("Alc�ntara S.A.");
        NoFederado celsoP = new GranProductor("Celso P.");

        System.out.println("Creando las relaciones entre produtores y productos...");

        ProductoProductor pp1 = new ProductoProductor(naranjas,1.5f);
        ProductoProductor pp2 = new ProductoProductor(algodon,0.5f);
        ProductoProductor pp3 = new ProductoProductor(melocotones,1.5f);
        ProductoProductor pp4 = new ProductoProductor(ciruelas,2.6f);
        ProductoProductor pp5 = new ProductoProductor(trigo,1.3f);
        ProductoProductor pp6 = new ProductoProductor(algodon,0.2f);
        ProductoProductor pp7 = new ProductoProductor(aceite, 5f);
        ProductoProductor pp8 = new ProductoProductor(naranjas, 15f);

        System.out.println("Asignando los productos a los productores...");
        juanP.asignarProducto(pp1);
        juanP.asignarProducto(pp2);
        juanP.asignarProducto(pp3);
        vergara.asignarProducto(pp4);
        soniaR.asignarProducto(pp5);
        soniaR.asignarProducto(pp6);      
        celsoP.asignarProducto(pp7);
        alcantara.asignarProducto(pp8);

        System.out.println("Agregando los productores al sistema...");
        agregarProductor(juanP);
        agregarProductor(vergara);
        agregarProductor(soniaR);
        agregarProductor(celsoP);
        agregarProductor(alcantara);

        System.out.println("Federando el algod�n...");
        Federado algodonFederado = new Federado("Algod�n");
        agregarFederado(algodonFederado);
        federarProducto(algodonFederado,juanP, pp2);
        federarProducto(algodonFederado,soniaR, pp6);

        System.out.println("Creando los clientes...");
        Distribuidor gadial = new Distribuidor("Gadial S.A.", 200);
        Distribuidor disnosa = new Distribuidor("Disnosa", 180);
        Minorista reginaC = new Minorista("Regina C.", 5);
        Minorista millanD = new Minorista("Mill�n D.", 9);

        System.out.println("Agregando los clientes al sistema...");
        clientes.add(gadial);
        clientes.add(disnosa);
        clientes.add(reginaC);
        clientes.add(millanD);

        System.out.println("Creando las empresas de log�stica...");
        Logistica sutrans = new Logistica("Sutrans S.L.", 0.06f, 0.04f, 0.05f, 0.01f);
        Logistica segura = new Logistica("Hnos. Segura S.L.", 0.05f, 0.04f, 0.03f, 0.02f);

        System.out.println("Agregando las empresas de log�stica al sistema...");
        logisticas.add(sutrans);
        logisticas.add(segura);

        listarProductos();
        listarProductores();
        listarFederados();
        listarClientes();
        listarLog�sticas();

        System.out.println("\nCreando los pedidos...");
        crearPedido(1l, reginaC, trigo, 50, sutrans);
        crearPedido(2l, gadial, algodon, 1000, segura);
        crearPedido(3l,disnosa, aceite, 2000, sutrans);
        crearPedido(4l, millanD, trigo, 10, "01/10/2023", sutrans);
        crearPedido(5l, gadial, naranjas, 3000, "15/10/2023", sutrans);

        listarPedidos();

        System.out.println("\nSirviendo los pedidos...");
        servirPedido(1l);
        servirPedido(2l);
        servirPedido(3l);
        System.out.println("\nActualizando cotizaci�n del trigo...");
        actualizaCotizaci�n("Trigo", 0.55f);
        servirPedido(4l);
        servirPedido(5l);

        System.out.println("\nMostrando la informaci�n despu�s de las operaciones...");
        listarPedidos();
        listarVentas();
        listarProductos();
        listarProductores();
        listarFederados();

        System.out.println("\nMostrando la informaci�n estad�stida final...");
        listarVentasTotales();
        listarImportesProductores();
        listarImportesLogisticas();
        listarBeneficiosCooperativa();
        listarEvolucionPreciosReferencia();
    }

    // PRODUCTOS
    /**
     * Agrega un producto a la lista de productos y muestra un mensaje en la consola indicando si se agreg� correctamente o no.
     * @param producto el producto que se va a agregar a la lista
     */
    public void agregarProducto(Producto producto) {
        try {
            if (anadeProducto(producto)) {            }
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    /**
     * Actualiza la cotizaci�n de un producto
     * 
     * @param producto El nombre de producto que se va a actualizar
     * @param cotizacion El precio de referencia nuevo
     */
    public void actualizaCotizaci�n(String nombreProducto, float precio){
        Producto producto = buscarProducto(nombreProducto);
        if (producto != null){
            producto.setPrecio(precio);
        }
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

    private boolean anadeProducto(Producto pro) throws Exception {
        for (Producto p: productos){
            if (pro.getNombre().equalsIgnoreCase(p.getNombre())){
                throw new IllegalArgumentException("El producto ya est� incluido. No se puede agregar el mismo producto de nuevo");
            }
        }
        productos.add(pro);
        return true;
    }

    private void actualizarCantidadDisponible(NoFederado productor){
        List<ProductoProductor> productos = productor.getProductos();
        for (ProductoProductor producto: productos){
            if(producto != null){
                Producto p = buscarProducto(producto.getProducto().getNombre());
                p.addProductor(productor);
                p.setProduccion(p.getProduccion() + producto.getProduccion());
                p.setDisponible(p.getDisponible() + producto.getDisponible());    
            }
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

    // PRODUCTORES NO FEDERADOS
    /**
     * Agrega un nuevo productor no federado a la lista de productores.
     * Adem�s, actualiza la cantidad disponible de productos del productor reci�n agregado.
     * 
     * @param productor El productor no federado que se agregar� a la lista.
     */
    public void agregarProductor(NoFederado productor){
        try {
            if (anadeProductor(productor)) { 
                actualizarCantidadDisponible(productor);
            } 
        } catch (Exception e){
            System.err.println(e);
        }
    }

    /**
     * Muestra en la consola la lista de productores no federados activos en la cooperativa.
     */
    public void listarProductores(){
        System.out.println("\nPRODUCTORES ACTIVOS EN LA COOPERATIVA");
        for (NoFederado productor: productores){
            System.out.println(productor.toString());
        }
    }

    /**
     * Muestra en la consola la lista de ventas realizadas por los productores no federados.
     */
    public void listarVentas(){
        System.out.println("\nLISTA DE VENTAS");
        for (NoFederado productor: productores){
            productor.listarVentas();
        }
    }

    private boolean anadeProductor(NoFederado productor) {
        for (NoFederado pro: productores){
            if(pro.getNombre().equalsIgnoreCase(productor.getNombre())){
                throw new IllegalArgumentException("No se puede agregar el productor porque el nombre ya existe");    
            }
        }
        productores.add(productor);
        return true;
    }

    // PRODUCTOS FEDERADOS
    /**
     * Agrega un nuevo productor federado a la lista de productores federados.
     * 
     * @param productor El productor federado que se agregar� a la lista.
     */
    public void agregarFederado(Federado productor){
        try {
            anadeFederado(productor);
        } catch (Exception e){
            System.err.println(e);
        }
    }

    /**
     * Muestra en la consola la lista de productores federados activos en la cooperativa.
     */
    public void listarFederados(){
        System.out.println("\nPRODUCTOS FEDERADOS");
        for(Federado federado: federados){
            System.out.println(federado.toString());
        }
    }

    /**
     * Federa un producto de un productor a un productor federado.
     * 
     * @param federado El productor federado que recibir� la federaci�n del producto.
     * @param productor El productor que posee el producto a federar.
     * @param productoProductor El producto a ser federado.
     */
    public void federarProducto(Federado federado, Productor productor, ProductoProductor productoProductor){
        try {
            if (verificaProducto(federado, productoProductor) && esFederado(productoProductor)) {
                federado.federarProducto(productor, productoProductor);
            }
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }

    private boolean anadeFederado(Federado productor) throws IllegalArgumentException {
        for (Productor pro: federados){
            if(pro.getNombre().equalsIgnoreCase(productor.getNombre())){
                throw new IllegalArgumentException("No se puede agregar el productor federado porque el nombre ya existe");    
            }
        }
        federados.add(productor);
        return true;
    }

    private boolean verificaProducto(Federado federado, ProductoProductor productoProductor) throws IllegalArgumentException{
        if (!federado.getNombre().equalsIgnoreCase(productoProductor.getProducto().getNombre())){
            throw new IllegalArgumentException("No se puede federar por que los productos no coinciden");    
        }
        return true;
    }

    private boolean esFederado(ProductoProductor producto){
        if(producto.getFedederado()){
            throw new IllegalArgumentException("No se puede federar un producto ya federado.");    
        }
        return true;
    }

    // CLIENTES
    /**
     * Agrega un nuevo cliente a la lista de clientes activos en la cooperativa.
     * 
     * @param cliente El cliente que se va a agregar a la lista.
     */
    public void agregarCliente(Cliente cliente) {
        try {
            anadeCliente(cliente);           
        } catch (Exception e){
            System.err.println(e);
        }
        clientes.add(cliente);

    }

    /**
     * Muestra en la consola la lista de clientes activos en la cooperativa.
     */
    public void listarClientes(){
        System.out.println("\nCLIENTES ACTIVOS");
        for(Cliente cliente: clientes){
            System.out.println(" " + cliente.toString());
        }
    }

    private boolean anadeCliente(Cliente cliente) throws IllegalArgumentException{
        for (Cliente c: clientes){
            if(c.getNombre().equalsIgnoreCase(cliente.getNombre())){
                throw new IllegalArgumentException("No se puede agregar el cliente porque el nombre ya existe");    
            }
        }
        return true;
    }

    // LOG�STICAS
    /**
     * Agrega una nueva empresa de log�stica a la lista de empresas activas en la cooperativa.
     * 
     * @param logistica La empresa de log�stica que se va a agregar a la lista.
     */
    public void agregarLog�stica(Logistica logistica){
        try{
            anadeLogistica(logistica);
        } catch(Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Muestra en la consola la lista de empresas de log�stica activas en la cooperativa.
     */
    public void listarLog�sticas(){
        System.out.println("\nEMPRESAS DE LOG�STICAS ACTIVAS");
        for(Logistica logistica: logisticas){
            System.out.println(" " + logistica.toString());
        }
    }

    private boolean anadeLogistica(Logistica logistica) throws IllegalArgumentException {
        for (Logistica l: logisticas){
            if (l.getNombre().equalsIgnoreCase(logistica.getNombre())){
                throw new IllegalArgumentException("No se puede agregar el cliente porque el nombre ya existe");    
            }
        }
        return true;
    }

    // PEDIDOS
    /**
     * Crea y agrega un nuevo pedido a la lista de pedidos activos en la cooperativa.
     * 
     * @param id Identificador �nico del pedido.
     * @param cliente El cliente que realiza el pedido.
     * @param producto El producto que se solicita en el pedido.
     * @param cantidad La cantidad del producto solicitado.
     * @param logistica La empresa de log�stica encargada de la entrega.
     * @return `true` si el pedido se crea y agrega correctamente, `false` en caso contrario.
     */
    public boolean crearPedido(long id, Cliente cliente, Producto producto, int cantidad, Logistica logistica){
        try {
            if(validarPedido(cliente, producto, cantidad)){
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

    /**
     * Crea y agrega un nuevo pedido a la lista de pedidos activos en la cooperativa.
     * 
     * @param id Identificador �nico del pedido.
     * @param cliente El cliente que realiza el pedido.
     * @param producto El producto que se solicita en el pedido.
     * @param cantidad La cantidad del producto solicitado.
     * @param fechaEntrega La fecha de entrega deseada para el pedido.
     * @param logistica La empresa de log�stica encargada de la entrega.
     * @return `true` si el pedido se crea y agrega correctamente, `false` en caso contrario.
     */
    public boolean crearPedido(long id, Cliente cliente, Producto producto, int cantidad, String fechaEntrega, Logistica logistica){
        try {
            if (validarPedido(cliente, producto, cantidad) && validarFechaEntrega(fechaEntrega)){
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

    /**
     * Marca un pedido como servido y actualiza sus costos y ventas relacionadas.
     * 
     * @param pedidoId El identificador �nico del pedido a ser servido.
     * @return `true` si el pedido se marca como servido correctamente, `false` en caso contrario.
     */
    public boolean servirPedido(long pedidoId){
        Pedido pedido = buscarPedido(pedidoId);
        if (pedido != null){
            if(pedido.esPospuesto(pedido.getCreacion(), pedido.getEntrega())){
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

    /**
     * Muestra en la consola la lista de pedidos activos en la cooperativa.
     */
    public void listarPedidos(){
        System.out.println("\nLISTA DE PEDIDOS");
        for(Pedido pedido: pedidos){
            System.out.println(" " + pedido.toString());
        }
    }  

    private Pedido buscarPedido(long id){
        for (Pedido pedido: pedidos){
            if (pedido.getId() == id){
                return pedido;
            }
        }
        return null;
    }

    private boolean validarPedido(Cliente cliente, Producto producto, int cantidad){
        if (validarCantidad(cliente, cantidad) && validarCantidadDisponible(producto, cantidad)){
            return true;
        } 
        return false;
    }

    private boolean validarCantidad(Cliente cliente, int cantidad){
        if (cliente instanceof Distribuidor && cantidad < PEDIDO_MIN_DISTRIBUIDOR){
            throw new IllegalArgumentException("El pedido de un distribuidor debe ser como m�nimo de 1000kg");
        }
        
        if (cliente instanceof Minorista && cantidad > PEDIDO_MAX_MINORISTA){
            throw new IllegalArgumentException("El pedido de un consumidor final debe ser como m�ximo de 100kg");
        }
        return true;
    }

    private boolean validarCantidadDisponible(Producto producto, int cantidad){
        if (producto.getDisponible() < cantidad){
            throw new IllegalArgumentException("La cantidad disponible de " + producto.getNombre() 
                + " es de " + producto.getDisponible() +" kg y se piden " + cantidad + " kg. No se puede realizar el pedido." );
        }
        return true;
    }

    private boolean validarFechaEntrega(String fechaEntrega){
        LocalDate entrega;
        try {
            entrega = LocalDate.parse(fechaEntrega, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if (entrega.isEqual(LocalDate.now()) || entrega.isAfter(LocalDate.now())){
                return true;
            }
        } catch (Exception e){
            throw new IllegalArgumentException("La fecha de entrega no corresponde con el formato dd/MM/yyyy");
        }
        throw new IllegalArgumentException("La fecha de entrega del pedido no puede ser anterior a la fecha de creacion");
    }

    // INFORMES ESTAD�STICOS

    /** 
     *  Imprime las ventas totales para cada produto de la cooperativa.
     *  Se calcula a partir del total de los pedidos por producto.
     */
    public void listarVentasTotales(){
        System.out.println("\nVENTAS TOTALES: ");
        float totalProdutos = 0; 
        for (Producto producto: productos){
            float total = pedidos.stream()
                .filter( pedido -> pedido.getProducto() == producto)
                .map(Pedido::getTotal)
                .reduce(0f,(subtotal, parcial) -> subtotal + parcial);
            if(total > 0){
                totalProdutos += total;
                System.out.println(" Subtotal de " + producto.getNombre() +": " + priceFormatter.format(total) +"�");
            }
        }
        System.out.println(" Total de ventas: " + priceFormatter.format(totalProdutos) + "�");
    }

    /**
     * Imprime los importes de cada productor y producto.
     * Se calcula a partir del monto da las ventas de cada produtor.
     */
    public void listarImportesProductores(){
        System.out.println("\nIMPORTES POR PRODUCTORES: ");
        // TODO listar 
        for(NoFederado productor: productores){
            System.out.println(" Importes obtenidos por " + productor.getNombre());

            List<ProductoProductor> productosProductor = productor.getProductos();
            ArrayList<Venta> ventas = productor.getVentas();
            float totalVentas= 0; 

            for(ProductoProductor productoProductor: productosProductor){
                if (productoProductor != null){
                    float total = ventas.stream()
                        .filter( venta ->  productoProductor.getProducto() == venta.getPedido().getProducto())
                        .map(Venta::getBeneficio)
                        .reduce(0f, (subtotal, parcial) -> subtotal + parcial);
                    if(total > 0){
                        totalVentas += total;
                        System.out.println("\tSubtotal de " + productoProductor.getProducto().getNombre() +": " + priceFormatter.format(total) +"�");
                    }
                }
            }
            System.out.println("\tTotal de ventas: " + priceFormatter.format(totalVentas) + "�");
        }
    }

    /**
     * Imprime los importes de cada empresa de log�stica.
     * Se calcula a partir de los costes de log�stica de los pedidos.
     */
    public void listarImportesLogisticas(){
        System.out.println("\nIMPORTES POR EMPRESAS DE LOG�STICA: ");
        float totalLogisticas = 0;
        for(Logistica logistica: logisticas){
            float total = pedidos.stream()
                .filter (pedido -> pedido.getLogistica() == logistica)
                .map(Pedido::getCosteLogistica)
                .reduce(0f, (subtotal, parcial) -> subtotal + parcial);
            if(total > 0){
                totalLogisticas += total;
                System.out.println(" Subtotal de " + logistica.getNombre() +": " + priceFormatter.format(total) +"�");
            }
        }
        System.out.println(" Total de logisticas: " + priceFormatter.format(totalLogisticas) + "�");
    }

    /**
     * Obtiene los beneficios de la cooperativa.
     * Se calcula a partir del beneficio de cada pedido.
     */
    public void listarBeneficiosCooperativa(){
        System.out.println("\nBENEFICIOS DE LA COOPERATIVA");
        float totalBeneficios = 0;
        for(Producto producto: productos){
            float total = pedidos.stream()
                .filter (pedido -> pedido.getProducto() == producto)
                .map(Pedido::getBeneficio)
                .reduce(0f, (subtotal, parcial) -> subtotal + parcial);
            if(total > 0){
                totalBeneficios += total;
                System.out.println(" Subtotal de " + producto.getNombre() +": " + priceFormatter.format(total) +"�");
            } 
        }
        System.out.println(" Total de beneficios: " + priceFormatter.format(totalBeneficios) + "�");
    }

    /**
     *  Imprime la evoluci�n de precios de referencia de cada producto.
     */
    public void listarEvolucionPreciosReferencia(){
        System.out.println("\nEVOLUCI�N DE PRECIOS");
        for(Producto producto: productos){
            producto.listarCotizaciones();
        }
    }
}

