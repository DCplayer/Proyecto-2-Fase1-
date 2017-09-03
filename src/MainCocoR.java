import com.sun.corba.se.impl.ior.IdentifiableFactoryFinderBase;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Created by DiegoCastaneda on 01/09/2017.
 */
public class MainCocoR {
    public static void main (String args[]){
        StringTokenizer tokens;
        String linea;
        ArrayList<String> contenido = new ArrayList<>();
        LectordeArchivos lector = new LectordeArchivos();


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
        String BasicSetRegex = "(" + stringRegex + ")|(" + identRegex + ")|((" + CharRegex + ")|(" + CharRegex + "--" + CharRegex + "))";
        String SetRegex = "(" + BasicSetRegex + ")" + "((\\+|-)(" + BasicSetRegex + "))*";
        String SetDeclRegex = "(" + identRegex+ ")=" + "(" + SetRegex+ ")";

        String KeywordDecl = "(" +identRegex + ")=(" +stringRegex +")";



        /*---------------------------------------------Creacion de Automatas----------------------------------------------------------*/
        /*Automata ident*/
        Arbol arbolIdent = new Arbol();
        ArrayList<NodosRamas> ident = arbolIdent.CrearElAFDDirecto(identRegex);
        int numeroParaElID = 0;

        for(NodosRamas nodoRama: ident){
            nodoRama.setId(numeroParaElID);
            numeroParaElID = numeroParaElID + 1;
        }

        /*Automata number*/
        Arbol arbolNumber = new Arbol();
        ArrayList<NodosRamas> number = arbolNumber.CrearElAFDDirecto(numberRegex);
        numeroParaElID = 0;

        for(NodosRamas nodoRama: number){
            nodoRama.setId(numeroParaElID);
            numeroParaElID = numeroParaElID + 1;
        }

        /*Automata string*/
        Arbol arbolString = new Arbol();
        ArrayList<NodosRamas> string = arbolString.CrearElAFDDirecto(stringRegex);
        numeroParaElID = 0;

        for(NodosRamas nodoRama: string){
            nodoRama.setId(numeroParaElID);
            numeroParaElID = numeroParaElID + 1;
        }

        /*Automata char*/
        Arbol arbolChar = new Arbol();
        ArrayList<NodosRamas> Char = arbolChar.CrearElAFDDirecto(charRegex);
        numeroParaElID = 0;

        for(NodosRamas nodoRama: Char){
            nodoRama.setId(numeroParaElID);
            numeroParaElID = numeroParaElID + 1;
        }

        /*Automata CHARACTERS*/
        Arbol arbolCharacters = new Arbol();
        ArrayList<NodosRamas> Characters = arbolCharacters.CrearElAFDDirecto(SetDeclRegex);
        numeroParaElID = 0;

        for(NodosRamas nodoRama: Characters){
            nodoRama.setId(numeroParaElID);
            numeroParaElID = numeroParaElID + 1;
        }

        /*Automata KEYWORDS*/
        Arbol arbolKeywords = new Arbol();
        ArrayList<NodosRamas> Keywords = arbolKeywords.CrearElAFDDirecto(KeywordDecl);
        numeroParaElID = 0;

        for(NodosRamas nodoRama: Keywords){
            nodoRama.setId(numeroParaElID);
            numeroParaElID = numeroParaElID + 1;
        }


        /*---------------------------------------------CreacionDirecta--------------------------------------------------------------*/


        /*Se tiene un HashSet que es el automata, entonces para encontrar la transicion con a donde va hay que ver
        * ambos ArrayList (transiciones y arrivals) de cada nodo del HashSet. Finalmente, ver si, del nodo en donde
        * estamos, digamos que se llama r, entonces tiene r.getConjunto.getContenido.equals(#) para ver si es el
        * ultimo*/

        /*Aqui Viene toda la historia de ifs, whiles y condicionales que ayudaran a verificar la aceptacion de COCOR*/

        ArrayList<String> lineas = lector.crearLector();


        System.out.println(lineas);




    }

    }



