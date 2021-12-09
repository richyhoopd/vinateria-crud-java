package vinateriaSys;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class archivoDBVino {
	
	private String archivo;
	
	public archivoDBVino(String archivo) {
		this.archivo = archivo;
	}
	public String mostrarDatosArchivo() {
		
		try {
			FileInputStream file = new FileInputStream(this.archivo);
			InputStreamReader ist = new InputStreamReader(file);
			BufferedReader br = new BufferedReader(ist);
			
			String linea = br.readLine();
			String contenido = "";
			
			while(linea != null) {
				contenido = contenido + linea + System.lineSeparator();
				linea = br.readLine();
			}
			
			br.close();
			return contenido;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	public void escribirDatosArchivo(Vino s) {
		
		try {
			FileWriter fw = new FileWriter(this.archivo, true);
			fw.append(s.tomarNomVino() + ";" + s.tomarAnejo() + ";" + s.tomarPrecio() + ";" + s.tomarDescripcion() +";" + System.lineSeparator());
			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void limpiarArchivo() {
		
		try {
			FileWriter fw = new FileWriter(this.archivo, false);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String busqueda (String palabra, ArrayList<Vino> list2) {
		
		char an = palabra.charAt(0);
		
		for (Vino s : list2) {
			
			if(s.tomarNomVino().equals(palabra) ||
				Integer.parseInt(palabra) == s.tomarPrecio() ||
				s.tomarDescripcion().equals(palabra) ||
				s.tomarAnejo() == an) {
				System.out.println("Aqui esta: \n"+ s.tomarPrecio() + " " + s.tomarNomVino()  + " " + s.tomarDescripcion() + " " + s.tomarAnejo());
				String dados =  s.tomarPrecio() + ";" + s.tomarNomVino()  + ";" + s.tomarDescripcion() + ";" + s.tomarAnejo();
				return dados;
			}
		}
		return null;
	}
	
	public boolean guardaDatos( ArrayList<Vino> lista) {
		if (daDescripcion(lista) == false) {
			return false;
		}else {
			for (Vino s : lista) {
				escribirDatosArchivo(s);	
			}
			lista.clear();
			
		}
		return true;
	}
	
	private boolean daDescripcion(ArrayList<Vino> list) {
		
		boolean v = true;
		ArrayList<Vino> list2 = new ArrayList<>();
		creaObjeto(list2);
				
		Vino vino;
		vino = list.get(list.size() - 1) ;
		
		for (Vino s : list2) {
			if (vino.tomarPrecio() == s.tomarPrecio()) {
				JOptionPane.showMessageDialog(null, "Esta id ya existe, use una nueva porfavor.");
				v = false;
				break;
				
			}else {
				v = true;
			}
		}
		return v;
	}
	
	public void borraDatos(ArrayList<Vino> list2, String palabra) {
		
		creaObjeto(list2);
		boolean  checa = false;
		
		for (Vino s1 : list2) {
			System.out.println(s1.tomarNomVino());
			if(s1.tomarPrecio()== Integer.parseInt(palabra)) {
				list2.remove(s1);
				checa = true;
				break;
			}
		}
		
		if (checa == true) {
			limpiarArchivo();
			for (Vino s : list2) {
				escribirDatosArchivo(s);	
			}
		}
	}
	
	public void editaArchivo(ArrayList<Vino> list2, int number, Vino vino) {
		
;		creaObjeto(list2);

		for (Vino s : list2) {
			if(number == s.tomarPrecio()){
				
				s.setNome(vino.tomarNomVino());
				s.setMatricula(vino.tomarPrecio());
				s.setEndereco(vino.tomarDescripcion());
				s.setSexo(vino.tomarAnejo());
				
				System.out.println("Nuevo: "+ s.tomarNomVino() + s.tomarPrecio() + s.tomarDescripcion());
				
				if (vino.tomarNomVino().equals(s.tomarNomVino()) && vino.tomarPrecio() == s.tomarPrecio() && vino.tomarDescripcion().equals(s.tomarDescripcion()) && vino.tomarAnejo() == s.tomarAnejo()) {
					limpiarArchivo();

					guardaDatos(list2);
					
				}
			}
			
			if(list2.isEmpty()) {
				break;
			}
		}
	}

	public void creaObjeto(ArrayList<Vino> list2) {
		
		list2.clear();
		//remontando os dados do txt em um array de Student  
		//buscar pessoas e montar uma tabela
		ArrayList<String> arregloTemporal= new ArrayList<String>();//array de string para receber as linhas do txt
		try {
			FileInputStream file = new FileInputStream(this.archivo);
			InputStreamReader ist = new InputStreamReader(file);
			BufferedReader br = new BufferedReader(ist);
			
			String linea = br.readLine();
			
			while(linea != null) {
				
				if(linea != null && !linea.isEmpty()) {
					arregloTemporal.add(linea);//adicionando a linha do txt no array
				}
				linea = br.readLine();
			}
			
			br.close();
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String s : arregloTemporal) {
			
			String [] partidor = s.split(";");
			char vino = partidor[1].charAt(0);
			
			Vino vinoTemporal = new Vino(partidor[0], vino, Integer.parseInt(partidor[2]), partidor[3]);
			list2.add(vinoTemporal);
		}
		
	}
}
