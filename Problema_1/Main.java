import java.io.*;
import java.util.*;

public class Main{


private static Integer noCanicasEnBusqueda=0;
private static Integer tamano=0;
private static ArrayList<Integer> numeros;
private static Queue<Integer> objetivos;
	

	public static void main(String[] args)throws IOException{
			int caso=0;
			numeros=new ArrayList<>();
			objetivos = new ArrayDeque<Integer>();

		try{
			 BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
			 String linea;
			 while((linea=br.readLine())!= null ){
			 	
			 	String[] convertidos=linea.split(" ");
			 	Integer[] locales= Main.convierteStringInt(convertidos);
			 	//System.out.println(linea);
			 	if(locales.length==2){


			 		if(!numeros.isEmpty()){
			 			caso++;
			 			Integer[] arg=numeros.toArray(new Integer[0]);
			 			Arrays.sort(arg);

			 			Map<Integer, Integer> diccionario = Main.convierteADiccionario(arg);
			 			Main.imprime(diccionario, objetivos, caso);
			 			numeros.clear();
			 			objetivos.clear();
			 			tamano=locales[0];
			 			noCanicasEnBusqueda=locales[1];
			 			//vuelta=false;
			 		}

			 		if(numeros.isEmpty()){
			 			//System.out.println("El compa no esta lleno");
			 			tamano=locales[0];
			 			noCanicasEnBusqueda=locales[1];
			 			//System.out.println("Tamaño: "+ tamano);
			 			//System.out.println("Caso: "+ noCanicasEnBusqueda);
			 		}
			 		if(tamano==0 && noCanicasEnBusqueda==0)return;
			 			
			 		
			 	}else{
			 		if(tamano<=0)
			 			objetivos.add(locales[0]);
			 		else
			 			numeros.add(locales[0]);
			 		
			 		tamano--;
			 	}
			}


		}catch(IOException ioe){
			System.out.println(ioe.toString());
		}
	}


	public static void imprime(Map<Integer, Integer> diccionario, Queue<Integer> cola, int caso){


				System.out.println("CASE# "+caso+":");
		for(Integer i: cola){

			if(diccionario.containsKey(i)){
				int index = diccionario.get(i);
				System.out.println(i+" found at "+ String.valueOf(index+1));
			}else{
				System.out.println(i + " not found");
			}
		}
	}

	public  static Map<Integer, Integer> convierteADiccionario(Integer[] arg){
		Map<Integer, Integer> diccionario = new HashMap<Integer, Integer>();

		for(int i=0; i< arg.length; i++){

			if(diccionario.containsKey(arg[i])){
				int indice= diccionario.get(arg[i]);
				if(indice>i){
					diccionario.replace(arg[i], indice, i);
				}
			}else{
				diccionario.put(arg[i], i);
			}

		}


		return diccionario;

	}

	//Para depuración eliminar
	public static void imprimeArreglo(Integer[] ar){
		for(Integer i: ar)
			System.out.print(i+",");
	}

	public static Integer[] convierteStringInt(String[] a){
		Integer[] enteros= new Integer[a.length];
		for(int i=0; i< a.length; i++)
			enteros[i]=Integer.parseInt(a[i]);

		return enteros;
	}

}
