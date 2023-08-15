
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class LogisticaTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LogisticaTest

{
    Logistica logistic;
    /**
     * Default constructor for test class LogisticaTest
     */
    public LogisticaTest()
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
        logistic = new Logistica("Logística 1", 0.06f, 0.04f, 0.05f, 0.01f);
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
    public void createLogistica_test()
    {
        logistic = new Logistica("Logística 1", 5, 4, 4, 3);
    }

    
    @Test
    public void calcularPrecioLogisticaPedido_tonelada_perecedero()
    {
        Distribuidor cliente = new Distribuidor("Gadisa", 180);
        Perecedero producto = new Perecedero("Nectarina", 2.5f, 0.75f);
        Pedido pedido = new Pedido(1l, cliente, producto, 180, logistic);
        assertEquals(582.0, logistic.calcularPrecioLogisticaPedido(pedido), 0.1);
    }

    @Test
    public void calcularPrecioLogisticaPedido_tonelada_no_perecedero()
    {
        Distribuidor cliente = new Distribuidor("Gadisa", 180);
        NoPerecedero producto = new NoPerecedero("Aceite", 2.5f, 0.5f);
        Pedido pedido = new Pedido(1l, cliente, producto, 2000, logistic);
        assertEquals(2115, logistic.calcularPrecioLogisticaPedido(pedido), 0.1);
    }
}

