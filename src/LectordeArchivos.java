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
        if(lineas.size() == 0){
            System.out.println("Archivo Vacio");
            return false;
        }
        StringTokenizer primeraLinea = new StringTokenizer(lineas.get(0));
        if(!primeraLinea.nextToken().equals("COMPILER")){
            System.out.println("Syntax error: COMPILER not found");
            return false;
        }
        String identificador = primeraLinea.nextToken();
        if(!simularAFD(ident, identificador)){
            System.out.println("Syntax error: ident not viable ");
        }




    }








}
