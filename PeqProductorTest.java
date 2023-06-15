
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class PeqProductorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PeqProductorTest
{
    private Perecedero nar;
    private NoPerecedero alg;
    private Perecedero mel;
    private Perecedero nar2;
    private Perecedero cir;
    private NoPerecedero tri;
    private NoPerecedero ave;

    private ProductoProductor naranjasJuan;
    private ProductoProductor algodonJuan;
    private ProductoProductor melocotonJuan;
    private ProductoProductor nar2Juan;
    private ProductoProductor cirJuan;
    private ProductoProductor triJuan;
    private ProductoProductor aveJuan;
    private ProductoProductor algodon2Juan;

    private ProductoProductor cirSonia ;
    private ProductoProductor triSonia ;
    private ProductoProductor algodonSonia ;

    private PeqProductor juan;
    private PeqProductor sonia;

    /**
     * Default constructor for test class PeqProductorTest
     */
    public PeqProductorTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        nar = new Perecedero("Naranjas", 2.3f, 3.4f);
        alg = new NoPerecedero("Algodón", 1.2f, 3.1f);
        mel = new Perecedero("Melocotones", 4f, 1.3f);
        nar2 = new Perecedero("Naranjas", 2.3f, 3.4f);
        cir = new Perecedero("Ciruelas", 2.5f, 1.5f);
        tri = new NoPerecedero("Trigo", 0.95f, 4.9f);
        ave = new NoPerecedero("Avena", 0.85f, 3.5f);

        naranjasJuan = new ProductoProductor(nar, 1.5f);
        algodonJuan = new ProductoProductor(alg, 0.5f);
        algodon2Juan = new ProductoProductor(alg, 6.5f);
        melocotonJuan = new ProductoProductor(mel, 1.5f);
        nar2Juan = new ProductoProductor(nar2, 1.5f);
        cirJuan = new ProductoProductor(cir, 0.5f);
        triJuan = new ProductoProductor(tri, 0.25f);
        aveJuan = new ProductoProductor(ave, 0.25f);

        cirSonia = new ProductoProductor(cir, 2.6f);
        triSonia = new ProductoProductor(tri, 1.3f);
        algodonSonia = new ProductoProductor(alg, 0.2f);

        juan = new PeqProductor("Juan P");
        sonia = new PeqProductor("Sonia R.");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void agregar_un_producto()
    {
        juan.asignarProducto(naranjasJuan);
    }

    @Test
    public void agregar_varios_productos()
    {
        assertEquals(true, juan.asignarProducto(naranjasJuan));
        assertEquals(true, juan.asignarProducto(algodonJuan));
        assertEquals(true, juan.asignarProducto(melocotonJuan));
    }

    @Test
    public void agregar_dos_productos_mismo_nombre()
    {
        assertEquals(true, juan.asignarProducto(naranjasJuan));
        assertEquals(false, juan.asignarProducto(nar2Juan));
    }

    @Test
    public void agregar_produto_mismo_objecto()
    {
        assertEquals(true, juan.asignarProducto(naranjasJuan));
        assertEquals(false, juan.asignarProducto(naranjasJuan));
    }

    @Test
    public void agregar_seis_productos()
    {
        assertEquals(true, juan.asignarProducto(naranjasJuan));
        assertEquals(true, juan.asignarProducto(algodonJuan));
        assertEquals(true, juan.asignarProducto(melocotonJuan));
        assertEquals(true, juan.asignarProducto(cirJuan));
        assertEquals(true, juan.asignarProducto(triJuan));
        assertEquals(false, juan.asignarProducto(aveJuan));

    }

    @Test
    public void agregar_producto_limite_extension()
    {
        assertEquals(false, juan.asignarProducto(algodon2Juan));
    }
}

