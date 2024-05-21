package cr.ac.ucr.progra2.paraiso.prograproject.common;

public class PatronesProtocol {
    private static final int CREACIONAL_ONE = 0;

    private static final int ESTRUCTURAL_ONE = 1;

    private static final int COMPORTAMIENTO_ONE = 2;

    private static final int CREACIONAL_TWO = 3;

    private static final int ESTRUCTURAL_TWO = 4;

    private static final int COMPORTAMIENTO_TWO = 5;

    private static final int CREACIONAL_TREE = 6;

    private static final int TERMINO = 6;

    private static final int REINICIAR = 7;
    private int estado;

    private String[] palabras;
    public PatronesProtocol(){
        estado = CREACIONAL_ONE;
        palabras = new String[]{"Patrón creacional: Abstract Factory",
                "Patrón Estructural: Bridge","Patrón Comportamiento: Command",
                "Patrón Creacional: Prototype","Patrón Estructural: Composite",
                "Patrón Comportamiento: Iterator","Patrón Creacional: Singleton\nDesea seguir con la funcion (s/n)"};
    }
    //Protocolo para que el servidor sepa que indicarle al usuario
    public String procesarEntrada(String  entrada){
        //Iniciar la salida
        String salida = null;
        switch (estado){
            case CREACIONAL_ONE:
                salida = palabras[CREACIONAL_ONE];
                estado = ESTRUCTURAL_ONE;
                break;
            case ESTRUCTURAL_ONE:
                if (entrada.equalsIgnoreCase("Patron Dos")){
                    salida = palabras[ESTRUCTURAL_ONE];
                    estado = COMPORTAMIENTO_ONE;
                }else{
                    salida = "Debe responder\"Patron Dos\"Trate de nuevo. " + palabras[CREACIONAL_ONE];
                }
                break;
            case COMPORTAMIENTO_ONE:
                if (entrada.equalsIgnoreCase("Patron Tres")) {
                    salida = palabras[COMPORTAMIENTO_ONE];
                    estado = CREACIONAL_TWO;
                }else{
                    salida = "Debe responder\"Patron Tres\"Trate de nuevo. " + palabras[ESTRUCTURAL_ONE];
                }
                break;
            case CREACIONAL_TWO:
                if (entrada.equalsIgnoreCase("Patron Cuatro")) {
                    salida = palabras[CREACIONAL_TWO];
                    estado = ESTRUCTURAL_TWO;
                }else{
                    salida = "Debe responder\"Patron Cuatro\"Trate de nuevo. " + palabras[COMPORTAMIENTO_ONE];
                }
                break;
            case ESTRUCTURAL_TWO:
                if (entrada.equalsIgnoreCase("Patron Cinco")){
                    salida = palabras[ESTRUCTURAL_TWO];
                    estado = COMPORTAMIENTO_TWO;
                }else{
                    salida = "Debe responder\"Patron Cinco\"Trate de nuevo. " + palabras[CREACIONAL_TWO];
                }
                break;
            case COMPORTAMIENTO_TWO:
                if (entrada.equalsIgnoreCase("Patron Seis")) {
                    salida = palabras[COMPORTAMIENTO_TWO];
                    estado = CREACIONAL_TREE;
                }else{
                    salida = "Debe responder\"Patron Seis\"Trate de nuevo. " + palabras[ESTRUCTURAL_TWO];
                }
                break;
            case CREACIONAL_TREE:
                if (entrada.equalsIgnoreCase("Patron Siete")){
                    salida = palabras[CREACIONAL_TREE] + "\n Desea seguir con el funcionamiento(s/n)";
                    estado = TERMINO;
                }else{
                    salida = "Debe responder\"Patron Siete\"Trate de nuevo. " + palabras[COMPORTAMIENTO_TWO];
                }
                break;
        }
        if (estado == REINICIAR) {
            if (entrada.equalsIgnoreCase("s")) {
                estado = CREACIONAL_ONE;
            } else {
                salida = "Chao!";
            }
        }
        if (estado == TERMINO)
            estado = REINICIAR;
        //Retornar el mensaje del servidor
        return salida;
    }//End method()
}//End class
