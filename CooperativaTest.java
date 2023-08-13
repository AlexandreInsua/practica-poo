import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CooperativaTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CooperativaTest
{
    Cooperativa c;
    NoPerecedero aceite;
    Distribuidor distribuidor;
    Minorista minorista;
    Logistica logistica;
    /**
     * Default constructor for test class CooperativaTest
     */
    public CooperativaTest()
    {
        c = new Cooperativa();
        aceite = new NoPerecedero("Aceite", 3.5f, 0.8f);
        distribuidor = new Distribuidor("Distribuidor", 180);
        minorista = new Minorista("Minorista", 180);
        logistica = new Logistica("Logística", 0.06f, 0.04f, 0.05f, 0.01f);
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
    public void crearPedido_validate_amount_by_order()
    {
        assertEquals(false, c.crearPedido(1l, distribuidor, aceite, 999, logistica));
        assertEquals(false, c.crearPedido(1l, minorista, aceite, 101, logistica));
    }

    @Test
    public void crearPedido_validateAvailableAmount_test()
    {
        aceite.setDisponible(0);
        assertEquals(false, c.crearPedido(1l, distribuidor, aceite, 1000, logistica));
    }
}


