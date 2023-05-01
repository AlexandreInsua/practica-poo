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
    public void listarProductos(){
        System.out.println("PRODUCTOS ACTIVOS EN LA COOPERATIVA");
        for (Producto p: productos){
            System.out.println(p.toString());
        }
    }
}
