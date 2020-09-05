import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.math.BigInteger;
import java.util.*;

public class SuperCalculadora {

	public static void main(String[] args) throws IOException {
		
		listaac a = new listaac();
		listaac b = new listaac();
		listaac re = new listaac();
		
		System.out.println("Por favor indique el PATH de tu archivo ");
		Scanner ent=new Scanner(System.in);
		String leer;		 
		leer =  ent.next();
		int menos=9;
		int m = Math.max(0, leer.length() - menos);
		String salida =leer.subSequence(0, m).toString();
		//System.out.println(salida);
		File file =new File(leer);
		FileReader reader = new FileReader (file);  //va la direccion del archivo
		BufferedReader li = new BufferedReader(reader);
		
		String linea1 = li.readLine();   //lee la primer linea
		String linea2 = li.readLine();	//lee la segunda linea
		
		//System.out.println(linea1);
		//System.out.println(linea2);
			
		
		for(int i = 0; i < linea1.length(); i++){	//inserta la primer lenia en una lista
            int num = linea1.charAt(i)-'0';
            a.insertar(num);
        }
		for(int i = 0; i < linea2.length(); i++){	//inserta la segunda linea en otra lista
            int num1 = linea2.charAt(i)-'0';
            b.insertar(num1);
        }
		
		//a.imprimir();
		//b.imprimir();
		
		int max = re.max(a, b);
		listaac suma = re.sumar(a, b);
		//suma.imprimirrevez();
		listaac resta = re.restar(a, b);
		//resta.imprimirrevez();
		listaac multiplicar = re.mux(a, b);
		//multiplicar.imprimirrevez();
		
		String max1 =  max+"";
		String suma1 = archivo(suma);
		String resta1 = archivo(resta);
		String multiplicar1 = archivo(multiplicar);
		//System.out.println(suma1);
		
		FileWriter fw=new FileWriter(salida+"output.txt");
        BufferedWriter bw=new BufferedWriter(fw);
        bw.write(max1);
        bw.newLine();
        bw.write(suma1);
        bw.newLine();
        bw.write(resta1);
        bw.newLine();
        bw.write(multiplicar1);
        bw.flush();
        
        bw.close();
		reader.close();
	}
	public static String archivo(listaac x) {
		StringBuilder sumaregre = new StringBuilder();
		listadob aux=x.fin;
		int i=0;
		while(aux!=null) {
			sumaregre.insert(i, aux.dato);
			
			aux=aux.anterior;
		}
		return sumaregre.toString();
	}
	/*public static String sumar(String a, String b) {
		BigInteger val1=new BigInteger(a);
		BigInteger val2=new BigInteger(b);
		
		return val1.add(val2).toString();
	}*/

}
class listadob{
	int dato;
	listadob siguiente;
	listadob anterior;
}
class listaac{
	 listadob inicio = null;
	 listadob fin =null;
	public  void insertar(int dato) {
		listadob nuevo = new listadob();
		nuevo.anterior=null;
		nuevo.siguiente=null;
		nuevo.dato=dato;
		if((inicio==null)&&(fin==null)) {
			inicio=nuevo;
			fin=nuevo;
		}else {
			nuevo.siguiente=inicio;
			inicio.anterior=nuevo;
			inicio=nuevo;
		}
	}
	public void insertarfinal(int dato) {
		listadob nuevo=new listadob();
		if((inicio==null)&&(fin==null)) {
			inicio=nuevo;
			fin=nuevo;
		}else {
			nuevo.anterior=fin;
			fin.siguiente=nuevo;
			fin=nuevo;
		}
	}
	
	public void imprimir ()
    {
        listadob reco = fin;
        while (reco != null) {
            System.out.print (reco.dato);
            reco = reco.anterior;
        }
        System.out.println();
    }
	public void imprimirrevez ()
    {
        listadob reco = inicio;
        while (reco != null) {
            System.out.print (reco.dato);
            reco = reco.siguiente;
        }
        System.out.println();
    }

	public int max(listaac x, listaac y) {
		listadob nu1 = x.fin;
		listadob nu2 = y.fin;
		int contax=0, contay=0;
		while(nu1!=null) {
			contax++;
			nu1=nu1.anterior;
		}
		while(nu2!=null) {
			contay++;
			nu2=nu2.anterior;
		}
		if(contax>contay) {
			return 1;
		}else if(contax<contay) {
			return 2;
		}
		nu1 = x.fin;
		nu2 = y.fin;
		while((nu1!=null)&&(nu2!=null)) {
			int cx, cy;
			cx=nu1.dato;
			cy=nu2.dato;
			if(cx>cy) {
				return 1;
			}else if(cx<cy) {
			return 2;}
			nu1=nu1.anterior;
			nu2=nu2.anterior;
		}
		return -1;
	}
	
	public listaac sumar(listaac x, listaac y) {
		//listaac me= new listaac();
		int acarreo=0;
		listaac retur= new listaac();
		listadob nu1 = x.inicio;
		listadob nu2 = y.inicio;
		while((nu2!=null)&&(nu1!=null)) {
			int temp=0;
			temp=nu1.dato+nu2.dato+acarreo;
			if(temp>9) {
				temp=temp-10;
				
				retur.insertar(temp);
				acarreo=1;
			}else {
			retur.insertar(temp);
			acarreo=0;
			}
			nu1=nu1.siguiente;
			nu2=nu2.siguiente;
		}
		int tempo=0;
		if(nu2!=null) {
			while(nu2!=null) {
			tempo = acarreo+nu2.dato;
			retur.insertar(tempo);
			acarreo=0;
			nu2=nu2.siguiente;
			}
		}else if(nu1!=null) {
			while(nu1!=null) {
				tempo = acarreo+nu1.dato;
				retur.insertar(tempo);
				acarreo=0;
				nu1=nu1.siguiente;
				}
		}
		if(acarreo==1) {
			retur.insertar(acarreo);
		}
		return retur;
	}
	public listaac restar(listaac x, listaac y) {
		listaac retur= new listaac();
		int mayor= retur.max(x, y);
		listadob nu1 = x.inicio;
		listadob nu2 = y.inicio;
		int acarreo=0;
		if(mayor==1) {
			while(nu1!=null&&nu2!=null) {
				int temp=0;
				if((nu1.dato)<(nu2.dato+acarreo)) {
					nu1.dato=nu1.dato+10;
					temp=nu1.dato-nu2.dato-acarreo;
					retur.insertar(temp);
					acarreo=1;
				}else {
				temp=nu1.dato-nu2.dato-acarreo;
				retur.insertar(temp);
				acarreo=0;
				}
				nu1=nu1.siguiente;
				nu2=nu2.siguiente;
			}
			}else if(mayor==2) {
				acarreo = 0;
				while(nu2!=null&&nu1!=null) {
				int temp=0;
				
				if((nu2.dato)<(nu1.dato+acarreo)) {
						nu2.dato=nu2.dato+10;
						temp=nu2.dato-nu1.dato-acarreo;
						retur.insertar(temp);
						acarreo=1;
				}else {
					temp=nu2.dato-nu1.dato-acarreo;
					retur.insertar(temp);
					acarreo=0;
				}
				nu2=nu2.siguiente;
				nu1=nu1.siguiente;
			}
			}
		int tempo=0;
		if(nu2!=null) {
			while(nu2!=null) {
			tempo = nu2.dato-acarreo;
			if(tempo!=0) {
			retur.insertar(tempo);
			}
			acarreo=0;
			nu2=nu2.siguiente;
			}
		}else if(nu1!=null) {
			while(nu1!=null) {
				tempo = nu1.dato-acarreo;
				if(tempo!=0) {
				retur.insertar(tempo);
				}
				acarreo=0;
				nu1=nu1.siguiente;
				}
		}
		if(acarreo==1) {
			retur.insertar(acarreo);
		}
		return retur;
	}
	public listaac mux(listaac x, listaac y) {
		listadob a= x.inicio;
		
		listaac total=new listaac();
		
		//int respaldo=0;
		int i=0;
		while(a!=null) {
			listaac retur=new listaac();
			listadob b= y.inicio;
			int res=0,aco=0;
			while(b!=null) {
				 int temp=0;
				temp=(a.dato*b.dato)+res;
				//System.out.println(temp);
				if(temp<10) {
					retur.insertar(temp);
					res=0;
				}else {
					aco=(temp%10);//+res;
					//System.out.println(aco);
					retur.insertar(aco);
					/*else {
						respaldo=aco/10;
						aco=aco%10;
						retur.insertar(aco);
					}*/
					res=(temp/10);//+respaldo;
					//System.out.println(res);
				}
				
				b=b.siguiente;
				}
			if(res!=0) {
				retur.insertar(res);
			}
			int ceros=i;
			while(ceros!=0) {
			retur.insertarfinal(0);
			ceros--;
			}
			//retur.imprimirrevez();
			//retur.imprimir();
			
			//retur.insertarfinal(0);
			total=sumarfinal(total,retur);
			a=a.siguiente;
			i++;
			
		}
		
	
		return total;
	}
	public listaac sumarfinal(listaac x, listaac y) {
		//listaac me= new listaac();
		int acarreo=0;
		listaac retur= new listaac();
		listadob nu1 = x.fin;
		listadob nu2 = y.fin;
		while((nu2!=null)&&(nu1!=null)) {
			int temp=0;
			temp=nu1.dato+nu2.dato+acarreo;
			if(temp>9) {
				temp=temp-10;
				
				retur.insertar(temp);
				acarreo=1;
			}else {
			retur.insertar(temp);
			acarreo=0;
			}
			nu1=nu1.anterior;
			nu2=nu2.anterior;
		}
		int tempo=0;
		if(nu2!=null) {
			while(nu2!=null) {
			tempo = acarreo+nu2.dato;
			retur.insertar(tempo);
			acarreo=0;
			nu2=nu2.anterior;
			}
		}else if(nu1!=null) {
			while(nu1!=null) {
				tempo = acarreo+nu1.dato;
				retur.insertar(tempo);
				acarreo=0;
				nu1=nu1.anterior;
				}
		}
		if(acarreo==1) {
			retur.insertar(acarreo);
		}
		return retur;
	}
}
