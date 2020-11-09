import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class Main{

	public static void main(String[] args)throws Exception{
		Map<Integer, Equipo> tablero = new HashMap<>();
		List<Equipo> tablero_f = new LinkedList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Equipo e=null;
		Equipo f=null;
        //PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		int instancia=1;
		while(true){

			String firstToRead=br.readLine();
			if(firstToRead==null)return;
			Integer totalDeLineas=Integer.parseInt(firstToRead);
        	totalDeLineas = totalDeLineas*(totalDeLineas-1)/2;
        	for(int i=0; i<= totalDeLineas ; i++){
        		String[] linea=br.readLine().split(" ");
        			if(linea.length<=1 && linea[0].equals("0"))continue;
				
				Integer x=Integer.parseInt(linea[0]);
				Integer de_x=Integer.parseInt(linea[1]);
        		Integer z=Integer.parseInt(linea[2]);
        		Integer de_z= Integer.parseInt(linea[3]);

        		if(tablero.containsKey(x)){ // Si Ya habiamos registrado antes al equipo
        			 e= tablero.get(x);
        			e.setNuevo(de_x, de_z);
        		}else{
        			tablero.put(x, e=new Equipo(x,de_x, de_z));
        		}	


        		if(tablero.containsKey(z)){
        			 f= tablero.get(z);
        			f.setNuevo(de_z, de_x);	
        		}else{
        			tablero.put(z, f=new Equipo(z,de_z, de_x));
        		}
        		        Equipo.setMarcador(e,f);
        	}//FIN DEL FOR

        	for(Equipo eq: tablero.values())
        		tablero_f.add(eq);
		
			System.out.println("Instancia "+instancia);
        	Collections.sort(tablero_f);
		
			for(Equipo d: tablero_f)
				System.out.print(d.getNombreEquipo()+" ");
			System.out.println(" ");
			instancia++;
        	
		}


		}
         

        
        

	}




class Equipo implements Comparable<Equipo>{
	Integer anotados;
	Integer contraAnotados;
	Integer partidasJugadas;
	Integer promedioCanastasAnotadas=0;
	Integer puntuacion;
	Integer actualAnotados=0;
	Integer actualContraAnotados=0;
	Integer canastasContraAnotadas=0;
	Integer nombreEquipo;
	Equipo(Integer nombreEquipo,Integer anotados, Integer contraAnotados){
		this.nombreEquipo=nombreEquipo;
		partidasJugadas=1;
		this.anotados=anotados;
		this.contraAnotados=contraAnotados;
		this.promedioCanastasAnotadas=anotados;
		this.canastasContraAnotadas+=contraAnotados;
		this.puntuacion=0;

		actualAnotados=anotados;
		actualContraAnotados=contraAnotados;
	}



	public Integer getNombreEquipo(){
		return this.nombreEquipo;
	}
	public Integer getAnotados(){
		return this.anotados;
	}

	public void setNuevo(Integer anotados, Integer contraAnotados){
		this.setActualAnotados(anotados);
		this.setActualContraAnotados(contraAnotados);
	}
	public void setActualAnotados(Integer i){
		this.actualAnotados = i;
		this.setCanastasAnotadas(i);
	}

	public void setActualContraAnotados(Integer i){
		this.actualContraAnotados=i;
		this.setCanastasContraAnotadas(i);
	}

	public static void setMarcador(Equipo equipo1, Equipo equipo2){
		if(equipo1.actualAnotados> equipo2.actualAnotados){
			equipo1.setPuntuacion(2);
			equipo2.setPuntuacion(1);
			return;
		}else if(equipo1.actualAnotados < equipo2.actualAnotados){
			equipo1.setPuntuacion(1);
			equipo2.setPuntuacion(2);
			return;
		}

		if(Double.compare(equipo1.ratio(), equipo2.ratio())==1){
			equipo1.setPuntuacion(2);
			equipo2.setPuntuacion(1);
			return ;
		}else if(Double.compare(equipo1.ratio(), equipo2.ratio())==-1){
			equipo1.setPuntuacion(1);
			equipo2.setPuntuacion(2);
			return ;
		}

		if(equipo1.getPromedioCanastasAnotadas()> equipo2.getPromedioCanastasAnotadas()){
			equipo1.setPuntuacion(2);
			equipo2.setPuntuacion(1);
			return ;	
		}else if(equipo1.getPromedioCanastasAnotadas()<equipo2.getPromedioCanastasAnotadas()){
			equipo1.setPuntuacion(1);
			equipo2.setPuntuacion(2);
			return ;
		}





	}



	public int compareToo(Equipo e){
		return this.anotados -e.anotados;
	}

	@Override
	public int compareTo(Equipo e){
		return e.puntuacion-this.puntuacion;
	}

	public double ratio(){
		if(this.actualContraAnotados==0)return getPromedioCanastasAnotadas();
		return this.actualAnotados/this.actualContraAnotados;
	}

	public void setPuntuacion(Integer puntuacion){
		this.puntuacion+=puntuacion;
		
	}

	public Integer getPartidasJugadas(){
		return partidasJugadas;
	} 



	private void setCanastasAnotadas(Integer i){
		promedioCanastasAnotadas+=i;
		this.partidasJugadas+=1;
		
	}

	private void setCanastasContraAnotadas(Integer i){
		this.canastasContraAnotadas+=i;
	}

	public Integer getPromedioCanastasAnotadas(){
		return this.promedioCanastasAnotadas/this.partidasJugadas;
	}
}







