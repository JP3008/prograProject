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
                if (entrada.equalsIgnoreCase("Marin")){
                    salida = palabras[ESTRUCTURAL_ONE];
                    estado = COMPORTAMIENTO_ONE;
                }else{
                    salida = "Debe responder\"Marin\"Trate de nuevo. Tin";
                }
                break;
            case COMPORTAMIENTO_ONE:
                if (entrada.equalsIgnoreCase("Pingue")) {
                    salida = palabras[COMPORTAMIENTO_ONE];
                    estado = CREACIONAL_TWO;
                }else{
                    salida = "Debe responder\"Pingue\"Trate de nuevo. De do";
                }
                break;
            case CREACIONAL_TWO:
                if (entrada.equalsIgnoreCase("Macara")) {
                    salida = palabras[CREACIONAL_TWO];
                    estado = ESTRUCTURAL_TWO;
                }else{
                    salida = "Debe responder\"Macara\"Trate de nuevo. Cucara";
                }
                break;
            case ESTRUCTURAL_TWO:
                if (entrada.equalsIgnoreCase("Fue")){
                    salida = palabras[ESTRUCTURAL_TWO];
                    estado = COMPORTAMIENTO_TWO;
                }else{
                    salida = "Debe responder\"Fue\"Trate de nuevo. Titere";
                }
                break;
            case COMPORTAMIENTO_TWO:
                if (entrada.equalsIgnoreCase("Fue Tete")) {
                    salida = palabras[COMPORTAMIENTO_TWO];
                    estado = CREACIONAL_TREE;
                }else{
                    salida = "Debe responder\"Fue Tete\"Trate de nuevo. Yo no fui";
                }
                break;
            case CREACIONAL_TREE:
                if (entrada.equalsIgnoreCase("Pegale")){
                    salida = palabras[CREACIONAL_TREE] + "\n Desea seguir con el funcionamiento(s/n)";
                    estado = TERMINO;
                }else{
                    salida = "Debe responder\"Pegale\"Trate de nuevo. Pegale";
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
