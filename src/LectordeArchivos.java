import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class LectordeArchivos {



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

    public ArrayList<String> crearLector(){
        ArrayList<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Ejemplo COCOR.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(!line.equals("")){
                    lineas.add(line);
                }

            }
        }
        catch (IOException e){

        }

        return lineas;
    }

    public boolean chequearSintaxisInicial(ArrayList<String> lineas, ArrayList<NodosRamas> ident){
        boolean resultado = true;
        if(lineas.size() == 0){
            System.out.println("Archivo Vacio");
            resultado =  false;
        }
        StringTokenizer primeraLinea = new StringTokenizer(lineas.get(0));
        if(!primeraLinea.nextToken().equals("COMPILER")){
            System.out.println("Syntax error: COMPILER not found");
            resultado =   false;
        }
        String identificador = primeraLinea.nextToken();
        if(!simularAFD(ident, identificador)){
            System.out.println("Syntax error: ident not viable ");
            resultado =   false;
        }


        StringTokenizer ultimaLinea = new StringTokenizer(lineas.get(lineas.size()-1));
        if (!ultimaLinea.nextToken().equals("END")){
            System.out.println("Syntax error: END not found");
            resultado =   false;
        }
        String identificadorFinal = ultimaLinea.nextToken();
        String identFinal = identificadorFinal.substring(0, identificadorFinal.length() - 2);
        String dot = identificadorFinal.substring(identificadorFinal.length() - 2, identificadorFinal.length() - 1);
        if(!identificador.equals(identFinal)){
            System.out.println("Syntax error: identificator not found");
            resultado =   false;
        }
        if(!dot.equals(".")){
            System.out.println("Syntax error: notation not found");
            resultado = false;
        }

        return resultado;
    }








}
