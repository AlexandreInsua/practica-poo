/**
 * Esta clase abstracta representa a un cliente de una cooperativa.
 * Contiene información básica como el nombre del cliente y la distancia
 * del cliente hasta la cooperativa.
 * @author Alexandre Insua Moreira
 * @version 1.0
 */
public abstract class Cliente {
    // nombre del cliente
    private String nombre;

    // distancia del cliente hasta la cooperativa
    private int distancia;

    /**

    Constructor de la clase Cliente.
    @param nombre El nombre del cliente.
    @param distancia La distancia del cliente hasta la cooperativa.
     */
    public Cliente(String nombre, int distancia) {
        this.nombre = nombre;
        this.distancia = distancia;
    }

    /**

    Obtiene el nombre del cliente.
    @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**

    Obtiene la distancia del cliente hasta la cooperativa.
    @return La distancia del cliente.
     */
    public int getDistancia() {
        return distancia;
    }
}