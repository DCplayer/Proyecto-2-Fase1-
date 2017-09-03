import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Created by DiegoCastaneda on 01/09/2017.
 */
public class MainCocoR {
    public static void main (String args[]){
        BufferedReader br;
        StringTokenizer tokens;
        String linea;
        ArrayList<String> contenido = new ArrayList<>();

        String letter = "a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z|A|B|C|D|E|F|G|H|I" +
                "|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z";
        String digit = "0|1|2|3|4|5|6|7|8|9";
        String butQuote = "!|@|#|$|^|&|{|}|[|]|\'|>|<|;|:";
        String butApostrophw = "!|@|#|$|^|&|{|}|[|]|\"|>|<|;|:";

        String identRegex = "(" + letter+ ")"+ "(" + letter + "|" + digit + ")*";
        String numberRegex = "(" + digit+ ")"  + "(" + digit+ ")" + "*";
        String stringRegex = "\"" + "(" + letter + "|" + digit + "|" + butQuote + ")*" + "\"";
        String charRegex = "\'" + letter + "|" + digit + "|" + butApostrophw +"\'";

        String CharRegex = "(" + charRegex  +")|(CHR(" + numberRegex + "))";
        String BasicSetRegex = "(" + stringRegex + ")|(" + identRegex + ")|((" + CharRegex + ")|(" + CharRegex + ".."
                + CharRegex + "))";
        String SetRegex = "(" + BasicSetRegex + ")" + "((+|-)(" + BasicSetRegex + "))*";
        String SetDeclRegex = "(" + identRegex+ ")=" + "(" + SetRegex+ ")";

        String KeywordDecl = "";


        /*---------------------------------------------Creacion de Automatas----------------------------------------------------------*/
        Arbol arbolIdent = new Arbol();
        ArrayList<NodosRamas> identificador = arbolIdent.CrearElAFDDirecto(identRegex);
        HashSet<NodosRamas> ident = new HashSet<>();
        ident.addAll(identificador);
        int numeroParaElID = 0;

        for(NodosRamas nodoRama: ident){
            nodoRama.setId(numeroParaElID);
            numeroParaElID = numeroParaElID + 1;
        }

        Arbol arbolNumber = new Arbol();
        ArrayList<NodosRamas> numero = arbolNumber.CrearElAFDDirecto(numberRegex);
        HashSet<NodosRamas> number = new HashSet<>();
        number.addAll(numero);
        numeroParaElID = 0;

        for(NodosRamas nodoRama: number){
            nodoRama.setId(numeroParaElID);
            numeroParaElID = numeroParaElID + 1;
        }

        Arbol arbolString = new Arbol();
        ArrayList<NodosRamas> palabra = arbolString.CrearElAFDDirecto(stringRegex);
        HashSet<NodosRamas> string = new HashSet<>();
        string.addAll(palabra);
        numeroParaElID = 0;

        for(NodosRamas nodoRama: string){
            nodoRama.setId(numeroParaElID);
            numeroParaElID = numeroParaElID + 1;
        }


        Arbol arbolchar = new Arbol();
        ArrayList<NodosRamas> caracteres = arbolchar.CrearElAFDDirecto(charRegex);
        HashSet<NodosRamas> charr = new HashSet<>();
        charr.addAll(caracteres);
        numeroParaElID = 0;

        for(NodosRamas nodoRama: charr){
            nodoRama.setId(numeroParaElID);
            numeroParaElID = numeroParaElID + 1;
        }
        /*---------------------------------------------CreacionDirecta--------------------------------------------------------------*/


        /*Se tiene un HashSet que es el automata, entonces para encontrar la transicion con a donde va hay que ver
        * ambos ArrayList (transiciones y arrivals) de cada nodo del HashSet. Finalmente, ver si, del nodo en donde
        * estamos, digamos que se llama r, entonces tiene r.getConjunto.getContenido.equals(#) para ver si es el
        * ultimo*/





    }

    public static String Ramas(NodosRamas noEsR){
        String casual  ="{";
        for (Rama i: noEsR.getConjunto()){
            casual = casual + ""  + i.getID() + ", ";
        }
        casual = casual + "}";
        return  casual;
    }








    }



