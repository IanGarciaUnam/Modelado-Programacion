import java.util.*;
import java.io.*;
import java.lang.*;


	public class Main{

		
		/** Precio de MataPlagas**/
		private static Integer precio;
		/** Campo **/
		private static FenwickTree2d kampo;

		public static void main(String[] args)throws IOException{

			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        	//PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

			 	String linea;
			 	int contador=0;
			 	String[] textoSeparado;
			 	while((linea = br.readLine()) != null && !linea.equals("0 0 0")){
			 		textoSeparado=linea.split(" "); /**Desglosamos la línea de entrada**/

			 		
	
			 		kampo = new FenwickTree2d(Integer.parseInt(textoSeparado[0]) + 1, Integer.parseInt(textoSeparado[1]) + 1);
			 		precio = Integer.parseInt(textoSeparado[2].trim());

			 		int q = Integer.parseInt(br.readLine().trim());

			 		for(int a = 0 ;a < q; a++){
			 			 textoSeparado = br.readLine().split(" ");

			 			 if(textoSeparado[0].equals("A")){ //Recibimos Mensajes
			 				Plaga plaga= new Plaga(Integer.parseInt(textoSeparado[1].trim()),  Integer.parseInt(textoSeparado[2].trim()), Integer.parseInt(textoSeparado[3].trim()));
			 				kampo.agrega(plaga.getX(), plaga.getY(), plaga.getNumeroDePlagas());
			 			//System.out.println(plaga.toString());
			 			}else if(textoSeparado[0].equals("P")){
			 				Integer x1= Integer.parseInt(textoSeparado[1]);
			 				Integer y1= Integer.parseInt(textoSeparado[2]);
			 				Integer x2 = Integer.parseInt(textoSeparado[3]);
			 				Integer y2= Integer.parseInt(textoSeparado[4]);
			 				int resultado=kampo.suma(Math.min(x1,x2),Math.min(y1,y2),Math.max(x1,x2),Math.max(y1,y2));
			 				System.out.print(resultado*precio + "\n");
						}


			 		}
					System.out.print("\n");
					//System.out.flush();

			
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

		

		static class FenwickTree2d{
			private int[][] interno;
			private int maxX,maxY;

			public FenwickTree2d(int maxX, int maxY){
				this.maxX=maxX;
				this.maxY=maxY;
				this.interno= new int[maxX][maxY];
			}	

			public void agrega(int x, int y, int valor){
				for (int i = x; i < interno.length; i |= i + 1)
            		for (int j = y; j < interno[0].length; j |= j + 1)
                		interno[i][j] += valor;
			}

			/**
			*
			*suma ([0][0]) ([x][y])
			*/
			public int suma(int x, int y){
				int resultado = 0;
       		 for (int i = x; i >= 0; i = (i & (i + 1)) - 1)
            	for (int j = y; j >= 0; j = (j & (j + 1)) - 1)
                	resultado += interno[i][j];
        	return resultado;
			}

			public int suma(int x1, int y1, int x2, int y2){
				return suma(x2, y2) - suma(x1 - 1, y2) - suma(x2, y1 - 1) + suma(x1 - 1, y1 - 1);
			}

			public int get(int x, int y) {
        		return suma(x, y, x, y);
    		}

    		public void set(int x, int y, int value) {
        		agrega(x, y, -get(x, y) + value);
    		}


		}

	}