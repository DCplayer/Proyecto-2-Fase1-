import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class LectordeArchivos {
    private BufferedReader br;
    private StringTokenizer tokens;
    private String linea;
    private ArrayList<String> contenido = new ArrayList<>();


    public LectordeArchivos(){

    }

    public boolean simularAFD(ArrayList<NodosRamas> listaDeAFD, String s){
        NodosRamas elNodo = listaDeAFD.get(0);

        int index = 0;
        while (index < s.length()){
            String substri = s.substring(index, index+1);
            int numero = elNodo.getTransiciones().indexOf(substri);
            NodosRamas resul = elNodo.getArrivals().get(numero);
            elNodo = resul;
            index = index + 1;
        }
        for(Rama rama: elNodo.getConjunto()){
            if(rama.getContenido().equals("#")){
                return true;
        }

        }
        return false;

    }








}
