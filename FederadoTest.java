
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class FederadoTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class FederadoTest
{
    Perecedero peras;
    Perecedero moras;

    PeqProductor david;
    PeqProductor juan;
    PeqProductor sonia;
    PeqProductor antonio;
    GranProductor maria;

    Federado perasFederado;

    ProductoProductor perasDavid; 
    ProductoProductor morasDavid;
    ProductoProductor perasJuan;
    ProductoProductor perasSonia;
    ProductoProductor perasAntonio;
    ProductoProductor perasMaria;
    /**
     * Default constructor for test class FederadoTest
     */
    public FederadoTest()
    {
        peras = new Perecedero("Peras", 5f, 0.75f);
        moras = new Perecedero("Moras", 3f, 2.75f);    

        david = new PeqProductor("David S");
        juan = new PeqProductor("Juan P");
        sonia = new PeqProductor("Sonia R.");
        antonio = new PeqProductor("Antonio O.");
        maria = new GranProductor("María E.");

        perasFederado = new Federado("Peras");

        perasDavid = new ProductoProductor(peras, 2.3f);
        david.asignarProducto(perasDavid);
        
        morasDavid = new ProductoProductor(moras, 3.3f);
        david.asignarProducto(morasDavid);
                
        perasJuan = new ProductoProductor(peras, 2f);
        juan.asignarProducto(perasJuan);
        
        perasSonia = new ProductoProductor(peras, 0.7f);
        sonia.asignarProducto(perasSonia);
        
        perasAntonio = new ProductoProductor(peras, 0.1f);
        antonio.asignarProducto(perasAntonio);        
        
        perasMaria = new ProductoProductor(peras,20);
        maria.asignarProducto(perasMaria);

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
    public void federar_un_producto()
    {        
        assertEquals(true, perasFederado.federarProducto(david, perasDavid));
    }

    @Test
    public void federar_varios_productos()
    {      
        assertEquals(true, perasFederado.federarProducto(david, perasDavid));
        assertEquals(true, perasFederado.federarProducto(juan, perasJuan));
        assertEquals(true, perasFederado.federarProducto(sonia, perasSonia));
    }

    @Test
    public void federar_producto_diferente(){
        assertEquals(false, perasFederado.federarProducto(david, morasDavid));
    }
    
    @Test
    public void federar_gran_productor(){
        assertEquals(false, perasFederado.federarProducto(maria, perasMaria));
    }
    
    @Test
    public void federar_mismo_producto_dos_veces()
    {        
        assertEquals(true, perasFederado.federarProducto(david, perasDavid));
        assertEquals(false, perasFederado.federarProducto(david, perasDavid));
    }
    
       @Test
    public void federar_varios_productos_sobrepasa_limite()
    {      
        assertEquals(true, perasFederado.federarProducto(david, perasDavid));
        assertEquals(true, perasFederado.federarProducto(juan, perasJuan));
        assertEquals(true, perasFederado.federarProducto(sonia, perasSonia));
        assertEquals(false, perasFederado.federarProducto(antonio, perasAntonio));
    }

}

