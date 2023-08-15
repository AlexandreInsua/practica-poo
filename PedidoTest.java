import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class PedidoTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PedidoTest
{
       Logistica logistic = new Logistica("Log�stica 1", 5, 4, 4, 3);
    /**
     * Default constructor for test class PedidoTest
     */
    public PedidoTest()
    {
        logistic = new Logistica("Log�stica 1", 5, 4, 4, 3);
    
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
    public void esPospuesto_test()
    {
        Minorista minorista = new Minorista("Test", 1);
        Perecedero perecedero = new Perecedero("Test", 1, 1);
        Pedido pedido1 = new Pedido(1l, minorista, perecedero, 1, logistic, "25/08/2023");
        assertEquals(true, pedido1.esPospuesto(pedido1.getCreacion(), pedido1.getEntrega()));
        
        Pedido pedido2 = new Pedido(1l, minorista, perecedero, 1, logistic );
        assertEquals(false, pedido2.esPospuesto(pedido2.getCreacion(), pedido2.getEntrega()));
        
        Pedido pedido3 = new Pedido(1l, minorista, perecedero, 1, logistic, LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        assertEquals(false, pedido3.esPospuesto(pedido3.getCreacion(), pedido3.getEntrega()));
        
        Pedido pedido4 = new Pedido(1l, minorista, perecedero, 1, logistic, LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        assertEquals(false, pedido4.esPospuesto(pedido4.getCreacion(), pedido4.getEntrega()));
    }
}

