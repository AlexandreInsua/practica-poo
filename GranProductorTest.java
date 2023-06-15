
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class GranProductorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GranProductorTest
{
    private GranProductor alberto;
    private Perecedero ara;
    private NoPerecedero lino;
    private ProductoProductor arandanosAlberto;
    private ProductoProductor linoAlberto;
    private ProductoProductor linoAlberto2;

    /**
     * Default constructor for test class GranProductorTest
     */
    public GranProductorTest()
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
        alberto = new GranProductor("Alberto P");
        ara = new Perecedero("Arándanos", 2.4f, 5.3f);
        lino = new NoPerecedero("Lino", 2.0f, 0.4f);
        arandanosAlberto = new ProductoProductor(ara, 20f);
        linoAlberto = new ProductoProductor(lino, 30f);
        linoAlberto2 = new ProductoProductor(lino, 24f);
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
    public void add_product_great_productor_test()
    {
        assertEquals(true, alberto.asignarProducto(arandanosAlberto));
        assertEquals(true, alberto.asignarProducto(linoAlberto));
        assertEquals(false, alberto.asignarProducto(linoAlberto));
        assertEquals(false, alberto.asignarProducto(linoAlberto2));
    }
}

