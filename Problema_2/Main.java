import java.util.*;
import java.io.*;
import java.lang.*;


	public class Main{

		/** Campo **/
		private static Integer [][] campo;
		/** Precio de MataPlagas**/
		private static Integer precio;

		public static void main(String[] args)throws IOException{

			try{
			 	BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
			 	String linea;
			 	int contador=0;
			 	String[] textoSeparado;
			 	while((linea=br.readLine())!= null ){
			 		textoSeparado=linea.split(" "); /**Desglosamos la línea de entrada**/

			 		if(textoSeparado[0].equals("0") && textoSeparado[1].equals("0") && textoSeparado[2].equals("0"))return; /**Termina el programa**/
			 		if(contador<=0){ 
			 			/**Creamos un campo**/
			 			campo= new Integer[Integer.parseInt(textoSeparado[0])][Integer.parseInt(textoSeparado[1])];
			 			precio=Integer.parseInt(textoSeparado[2]);

			 		}else if(contador>0 && textoSeparado[0].equals("A")){ //Recibimos Mensajes
			 			Plaga plaga= new Plaga(Integer.parseInt(textoSeparado[1]),  Integer.parseInt(textoSeparado[2]), Integer.parseInt(textoSeparado[3]));
			 			Integer existe=campo[plaga.getY()][plaga.getX()];
			 			if(existe!=null){
			 				existe+=plaga.getNumeroDePlagas();
			 				campo[plaga.getY()][plaga.getX()]= existe;
			 			}else{
			 				campo[plaga.getY()][plaga.getX()]= plaga.getNumeroDePlagas();

			 			}
			 			

			 			//System.out.println(plaga.toString());
			 		}else if(textoSeparado[0].equals("P")){

			 			Integer x1=Integer.parseInt(textoSeparado[1]);
			 			Integer y1=Integer.parseInt(textoSeparado[2]);
			 			Integer x2=Integer.parseInt(textoSeparado[3]);
			 			Integer y2=Integer.parseInt(textoSeparado[4]);
			 			Pregunta pregunta=new Pregunta(x1,y1,x2,y2);
			 			
						

						int acumulado=0;//Devuelve la suma pedida

						
						if(pregunta.recorreTodo()){
							Integer elMasPequenoX=x1<=x2?x1:x2;

						Integer elMasPequenoY=y1<y2?y1:y2;//elMasPequenoX==x1?y1: y2;

						Integer elMasGrandeX= elMasPequenoX==x1? x2:x1;

						Integer elMasGrandeY= y1>y2?y1:y2;//elMasGrandeX==x2?y2: y1;
							for(int i=elMasPequenoX; i<=elMasGrandeX; i++){
								
								for(int j=elMasPequenoY; j<= elMasGrandeY; j++){
										if(campo[j][i]==null)campo[j][i]=0;
										acumulado+=campo[j][i];
								}
							}
								
						}else if(pregunta.recorreDerecha()){
								Integer elMasPequenoX=x1<=x2?x1:x2;
								Integer dependeDeX=elMasPequenoX==x1? y1:y2;//Se quedara fijo
								Integer elMasGrandeX= x1>x2? x1:x2;
								for(int i=elMasPequenoX; i<=elMasGrandeX; i++){
									if(campo[dependeDeX][i]==null)campo[dependeDeX][i]=0;
										acumulado+=campo[dependeDeX][i];
								}
						}else if(pregunta.recorreAbajo()){
								Integer elMasPequenoY= y1<y2?y1:y2;
								Integer dependeDeY=elMasPequenoY==y1?x1:x2;
								Integer elMasGrandeY=y1>y2?y1:y2;
								for(int i=elMasPequenoY; i<=elMasGrandeY; i++){
									if(campo[i][dependeDeY]==null)campo[i][dependeDeY]=0;
										acumulado+=campo[i][dependeDeY];
								}

							}
						//System.out.println(pregunta.toString());
						System.out.println(acumulado*precio);
		 			
						}
						
			 				/*for(int i=0; i<campo.length ;i++)
			 					for(int j=0; j<campo.length; j++)
			 						if(campo[j][i]!=null)
			 							System.out.println("Campo ["+j+"]["+i+"] : "+ campo[j][i]);
			 						else
			 							System.out.println("Campo ["+j+"]["+i+"] : "+ "null");*/
			 			
			 				contador++;
			 			}//FIN DEL WHILE
				 		
			 		System.out.println("");
			 		br.close();
			 		

			}catch(Exception e){
				System.out.println(e.toString());
			}
			
		}


static class Plaga{
			private Integer numeroDePlagas;
			private Integer x;
			private Integer y;
			Plaga(Integer numeroDePlagas, Integer x, Integer y){
				this.numeroDePlagas=numeroDePlagas;
				this.x=x;
				this.y=y;
			}

			public Integer getNumeroDePlagas(){
				return this.numeroDePlagas;
			}
			public Integer getX(){
				return this.x;
			}

			public Integer getY(){
				return this.y;
			}

			@Override
			public String toString(){
				return "Plagas: "+ this.getNumeroDePlagas()+ " En : ("+ this.getX()+","+this.getY()+")";
			}
		}

		static class Pregunta{
			private Integer x1;
			private Integer y1;
			private Integer x2;
			private Integer y2;
			Pregunta(Integer x1,Integer y1, Integer x2, Integer y2){
				this.x1=x1;
				this.x2=x2;
				this.y1=y1;
				this.y2=y2;
			}

			public boolean recorreDerecha(){
				return y1==y2 && x1!=x2;
			}

			public boolean recorreAbajo(){
				return x1==x2 && y1!=y2;
			}

			public boolean recorreTodo(){
				return x1!=x2 && y1!=y2;
			}

			@Override
			public String toString(){
				return "De: ("+x1+", "+y1+") a ("+x2+", "+ y2+")";
			}
		}


	}
		

	 	





















	