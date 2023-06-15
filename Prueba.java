

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class Prueba.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Prueba
{
    /**
     * Default constructor for test class Prueba
     */
    public Prueba()
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
    public void asignarProducto()
    {
        Perecedero perecede1 = new Perecedero("Naranjas", 2.3f, 3.4f);
        ProductoProductor producto1 = new ProductoProductor(perecede1, 1.5f);
        PeqProductor peqProdu1 = new PeqProductor("Juan P.");
        peqProdu1.asignarProducto(producto1);
        peqProdu1.toString();
        peqProdu1.verInfo();
    }
}

