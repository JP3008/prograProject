package cr.ac.ucr.progra2.paraiso.prograproject.common;

public class TinMarinProtocol {
    private static final int TIN = 0;

    private static final int DE_DO = 1;

    private static final int CUCARA = 2;

    private static final int TITERE = 3;

    private static final int YO_NO_FUI = 4;

    private static final int PEGALE = 5;

    private static final int QUE_ELLA = 6;

    private static final int TERMINO = 6;

    private static final int REINICIAR = 7;
    private int estado;

    private String[] palabras;
    public TinMarinProtocol(){
        estado = TIN;
        palabras = new String[]{"Tin","De do","Cucara","Titere","Yo no fui","Pegale","Que ella fue\nDesea seguir con la funcion (s/n)"};
    }
    //Protocolo para que el servidor sepa que indicarle al usuario
    public String procesarEntrada(String  entrada){
        //Iniciar la salida
        String salida = null;
        switch (estado){
            case TIN:
                salida = palabras[TIN];
                estado = DE_DO;
                break;
            case DE_DO:
                if (entrada.equalsIgnoreCase("Marin")){
                    salida = palabras[DE_DO];
                    estado = CUCARA;
                }else{
                    salida = "Debe responder\"Marin\"Trate de nuevo. Tin";
                }
                break;
            case CUCARA:
                if (entrada.equalsIgnoreCase("Pingue")) {
                    salida = palabras[CUCARA];
                    estado = TITERE;
                }else{
                    salida = "Debe responder\"Pingue\"Trate de nuevo. De do";
                }
                break;
            case TITERE:
                if (entrada.equalsIgnoreCase("Macara")) {
                    salida = palabras[TITERE];
                    estado = YO_NO_FUI;
                }else{
                    salida = "Debe responder\"Macara\"Trate de nuevo. Cucara";
                }
                break;
            case YO_NO_FUI:
                if (entrada.equalsIgnoreCase("Fue")){
                    salida = palabras[YO_NO_FUI];
                    estado = PEGALE;
                }else{
                    salida = "Debe responder\"Fue\"Trate de nuevo. Titere";
                }
                break;
            case PEGALE:
                if (entrada.equalsIgnoreCase("Fue Tete")) {
                    salida = palabras[PEGALE];
                    estado = QUE_ELLA;
                }else{
                    salida = "Debe responder\"Fue Tete\"Trate de nuevo. Yo no fui";
                }
                break;
            case QUE_ELLA:
                if (entrada.equalsIgnoreCase("Pegale")){
                    salida = palabras[QUE_ELLA] + "\n Desea seguir con el funcionamiento(s/n)";
                    estado = TERMINO;
                }else{
                    salida = "Debe responder\"Pegale\"Trate de nuevo. Pegale";
                }
                break;
        }
        if (estado == REINICIAR) {
            if (entrada.equalsIgnoreCase("s")) {
                estado = TIN;
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
