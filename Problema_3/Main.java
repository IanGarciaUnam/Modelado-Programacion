import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

public class Main{

	public static void main(String[] args)throws IOException{
		Map<Integer, Equipo> tablero = new HashMap<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//Equipo e=null;
		//Equipo f=null;
        //PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		int instancia=1;


		while(true){
			String firstToRead=br.readLine();
			Integer totalDeLineas=Integer.parseInt(firstToRead);
			Integer noEquipos=totalDeLineas;
        	totalDeLineas = totalDeLineas*(totalDeLineas-1)/2;
        	if(totalDeLineas==0){
        		System.out.println();
        		return;
        	}
        	//System.out.println("Lineas: "+ totalDeLineas );
        	tablero.clear();

        	Equipo[] arreglo_equipos= new Equipo[noEquipos];
		 

        	for(int i=0; i<= totalDeLineas-1 ; i++){
        		Equipo f=null;
        		Equipo e=null; 
        		String prev=br.readLine();
        		//System.out.println("PREV: "+ prev);

        		String[] linea=prev.split(" ");
				Integer x=Integer.parseInt(linea[0]);
				Integer de_x=Integer.parseInt(linea[1]);
        		Integer z=Integer.parseInt(linea[2]);
        		Integer de_z= Integer.parseInt(linea[3]);

        		if(arreglo_equipos[x-1]==null)
        			arreglo_equipos[x-1]=e=new Equipo(x, de_x, de_z);
        		else{
        			e=arreglo_equipos[x-1];
        			//System.out.println(e.toString());
        			e.setNuevo(de_x,de_z);
        		}

        		if(arreglo_equipos[z-1]==null)
        			arreglo_equipos[z-1]=f=new Equipo(z, de_z, de_x);
        		else{
        			 f=arreglo_equipos[z-1];
        			//System.out.println(f.toString());
        			f.setNuevo(de_z,de_x);
        		}
        		

        		Equipo.setMarcador(e, de_x,f , de_z);//Actualizamos el marcador de puntos
        		/**if(tablero.containsKey(x)){ // Si Ya habiamos registrado antes al equipo
        			 e= tablero.get(x);
        			e.setNuevo(de_x, de_z);
        		}else{
        			tablero.put(x, e=new Equipo(x,de_x, de_z));
        		}	


        		if(tablero.containsKey(z)){// Si Ya habiamos registrado antes al equipo
        			 f= tablero.get(z);
        			f.setNuevo(de_z, de_x);	
        		}else{
        			tablero.put(z, f=new Equipo(z,de_z, de_x));
        		}**/
        		        
        	}//FIN DEL FOR
        	
        	/**
        	int j=0;
        	for(Equipo eq: tablero.values()){
        		//System.out.println(eq.toString() );
        		arreglo_equipos[j]=eq;
        		j++;
        	}**/
		

        	if(instancia>1)
			System.out.println("\n\nInstancia "+instancia);
			else{
			System.out.println("Instancia "+instancia);	
			}
        	Arrays.sort(arreglo_equipos);
			//Collections.reverse(tablero_f);

			int i=1;
			for(Equipo d: arreglo_equipos){

				if(i==arreglo_equipos.length){
					System.out.print(d.getNombreEquipo().trim());
					
					//System.out.println(d.getNombreEquipo()+" Puntaje: "+d.getPuntaje()+": ratio: " + d.ratio());
				}else{
					//System.out.println(d.getNombreEquipo()+" Puntaje: "+d.getPuntaje() + ": ratio: "+ d.ratio());
					System.out.print(d.getNombreEquipo()+ " ");
				}

				i++;
			}
			
			instancia++;
        	
			}


		}
         

        
        

	}




class Equipo implements Comparable<Equipo>{


	int points;
	int pc;//Puntos en contra
	int pf;//Puntos a favor
	double ppp;
	int juegos;
	int nombre;
	Equipo(Integer nombre, Integer anotadas, Integer recibidas){
		this.nombre=nombre;
		this.pf=anotadas;
		this.pc=recibidas;
		this.juegos=0;
		points=0;
	}

	public String getNombreEquipo(){
		return String.valueOf(this.nombre);
	}

	public int getPuntaje(){
		return points;
	}
	public static void setMarcador(Equipo e, int de_e, Equipo f, int de_f){
		if(de_e > de_f){
			e.points+=2;
			f.points+=1;
		
		}else{
			e.points+=1;
			f.points+=2;			
		}
		e.juegos++;
		f.juegos++;
	}


	public Double getRatio(){//Es la relación entre las anotadas y las recibidas, en caso de que las recibidas sean 0 tomamos al numero de jugadas
		if(pc==0){
			ppp= (double)pf/(double)juegos;
			return ppp;
		}
			return (double)pf/(double)pc;


	}

	@Override
	public int compareTo(Equipo e){
		
		if(this.points-e.points==0){
			if(Double.compare(this.getRatio(),e.getRatio()) == 0 ){
				if(this.pf == e.pf){
					return Integer.compare(e.juegos, this.juegos);
				}
				return Integer.compare(e.pf, this.pf);
			}
			return Double.compare(e.getRatio(),this.getRatio());
		}
		return Integer.compare(e.points, this.points);
			
	}


	


	public void setNuevo(Integer anotadas, Integer recibidas){
		pf+=anotadas;
		pc+=recibidas;

	}

	@Override
	public String toString(){
		return "Nombre: "+ this.nombre + " Partidos: " + this.juegos+ " Ratio: "+ this.getRatio()+ " Puntos a favor: "+ this.pf +" Puntos en contra: "+ this.pc + " Puntaje: "+ this.points;
	}
}







