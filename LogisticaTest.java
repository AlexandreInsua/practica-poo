
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
    public void calculateBigLogisticsCost_test()
    {
        assertEquals(100, logistic.calculateBigLogisticsCost(10, 10 ), 0.1);
    }

    @Test
    public void calculateSmallLogisticsCost_test()
    {
        assertEquals(600, logistic.calculateSmallLogisticsCost(30, 0.01f, 2000), 0.1);
    }
    
    @Test
    public void calculateSections_test()
    {
        assertEquals(0, logistic.calculateSections(40), 0.1);
        assertEquals(1, logistic.calculateSections(50), 0.1);
        assertEquals(1, logistic.calculateSections(70), 0.1);
        assertEquals(2, logistic.calculateSections(100), 0.1);
        assertEquals(2, logistic.calculateSections(120), 0.1);
    }   

    @Test
    public void calculateSectionCost_test()
    {
        assertEquals(50, logistic.calculateSectionCost(10f, 10), 0.1);
    }

    @Test
    public void calculateTons_test()
    {
        assertEquals(1, logistic.calculateTons(10), 0.1);
        assertEquals(1, logistic.calculateTons(100), 0.1);
        assertEquals(1, logistic.calculateTons(999), 0.1);
        assertEquals(1, logistic.calculateTons(1000), 0.1);
        assertEquals(2, logistic.calculateTons(1001), 0.1);
        assertEquals(2, logistic.calculateTons(2000), 0.1);
    }

    @Test
    public void calculateSmallLogisticsDistance_test()
    {
        assertEquals(0, logistic.calculateSmallLogisticsDistance(0, 100), 0.1);
        assertEquals(50, logistic.calculateSmallLogisticsDistance(50, 100), 0.1);
        assertEquals(0, logistic.calculateSmallLogisticsDistance(100,100), 0.1);
        assertEquals(50, logistic.calculateSmallLogisticsDistance(150, 100), 0.1);
        assertEquals(50, logistic.calculateSmallLogisticsDistance(250, 100), 0.1);

        assertEquals(0, logistic.calculateSmallLogisticsDistance(0, 50), 0.1);
        assertEquals(25, logistic.calculateSmallLogisticsDistance(25, 50), 0.1);
        assertEquals(0, logistic.calculateSmallLogisticsDistance(50,50), 0.1);
        assertEquals(0, logistic.calculateSmallLogisticsDistance(100, 50), 0.1);
        assertEquals(25, logistic.calculateSmallLogisticsDistance(125, 50), 0.1);
        assertEquals(0, logistic.calculateSmallLogisticsDistance(150, 50), 0.1);
        assertEquals(25, logistic.calculateSmallLogisticsDistance(225, 50), 0.1);
        assertEquals(0, logistic.calculateSmallLogisticsDistance(250, 50), 0.1);
    }

    @Test
    public void calculateBigLogisticsDistance_test()
    {
        assertEquals(0, logistic.calculateBigLogisticsDistance(0, 100), 0.1);
        assertEquals(0, logistic.calculateBigLogisticsDistance(50, 100), 0.1);
        assertEquals(100, logistic.calculateBigLogisticsDistance(100,100), 0.1);
        assertEquals(100, logistic.calculateBigLogisticsDistance(150, 100), 0.1);
        assertEquals(200, logistic.calculateBigLogisticsDistance(250, 100), 0.1);

        assertEquals(0, logistic.calculateBigLogisticsDistance(0, 50), 0.1);
        assertEquals(0, logistic.calculateBigLogisticsDistance(25, 50), 0.1);
        assertEquals(50, logistic.calculateBigLogisticsDistance(50,50), 0.1);
        assertEquals(100, logistic.calculateBigLogisticsDistance(100, 50), 0.1);
        assertEquals(100, logistic.calculateBigLogisticsDistance(125, 50), 0.1);
        assertEquals(150, logistic.calculateBigLogisticsDistance(150, 50), 0.1);
        assertEquals(150, logistic.calculateBigLogisticsDistance(180, 50), 0.1);
        assertEquals(200, logistic.calculateBigLogisticsDistance(225, 50), 0.1);
        assertEquals(250, logistic.calculateBigLogisticsDistance(250, 50), 0.1);
    }

    @Test
    public void calcularPrecioLogisticaPedido_tonelada_perecedero()
    {
        Distribuidor cliente = new Distribuidor("Gadisa", 180);
        Perecedero producto = new Perecedero("Nectarina", 2.5f, 0.75f);
        Pedido pedido = new Pedido(cliente, producto, 180);
        assertEquals(582.0, logistic.calcularPrecioLogisticaPedido(pedido), 0.1);
    }

    @Test
    public void calcularPrecioLogisticaPedido_tonelada_no_perecedero()
    {
        Distribuidor cliente = new Distribuidor("Gadisa", 180);
        NoPerecedero producto = new NoPerecedero("Aceite", 2.5f, 0.5f);
        Pedido pedido = new Pedido(cliente, producto, 2000);
        assertEquals(2115, logistic.calcularPrecioLogisticaPedido(pedido), 0.1);
    }
}

