en pegProductor -> filtrar os produtos non federados
                -> refactorizar a presentación da información
no método test federar productos 
implementar a federación do productos, -> mostrar produtos federados.

ler de novo o enunciado para comprobar onde se necesita a información de produtor federado.
ver seguintes pasos

implementar loxística
    gran loxística
        fixo 0,5
        campo custo quilómetro  -> diferentes obxectos
        metodo calcular custe
            calcular tramos 
    pequena loxística
        campo custo quilómetro
        método calcular custe
              
implementar cliente
       campo nome
       campo distancia
       
       toString
        
        
implemantar pedido
    -> Cliente, Producto, Cantidad, fecha, número, entrega

     distribuidor
        min 1tm
        calcular loxística dependendo do tipo de produto 
            fixo 0,5
    minorista
        máximo 100 kg
        calcular loxística dependendo do tipo de produto 
        fixo 0.15
        iva 0.10
    
    
Pensar a implementación para a actualizar os productos según dos dados de alta

pensar en reusar unha constante para os límites de extensión (federado, PeqProdutor)

public class Constantes {
    public static final int VALOR_CONSTANTE = 10;
}

import pacote.Constantes;
public class MinhaClasse {
        int resultado = Constantes.VALOR_CONSTANTE * 2;
}
