package Lab_01;

import java.io.*;
import java.util.Iterator;

public class Poema {
	public static void main(String[] args) {
		String ruta = "C:\\Users\\ASUS\\Documents\\poema.txt";
		String texto = "";
		BufferedReader bf = null;
		try  {
			bf = new BufferedReader(new FileReader(ruta));
			String line = "";
			while ((line = bf.readLine()) != null) {
				texto += line + "\n";

			}
		}

		catch (IOException e) {
			System.out.println("erro al abri archivo");
		}
		mostrarTabla(Frecuencias(texto));
		
		try {
			
			System.out.println(padding(Unicode8(texto)));
			System.out.println(padding(Unicode8(texto)).length());
		} catch (Exception e) {
			// TODO: handle exception
		}
//		Unicode8(texto);
		

	}

	public static String Sustitucion(String texto) {
		// sustituciones: axu, hxt, ñxe, kxl, vxf, wxb, zxy,
		try {
			StringBuilder poema = new StringBuilder(texto);
			for (int i = 0; i < poema.length(); i++) {
				switch (poema.charAt(i)) {
				case 'a':
				case 'A':
					poema.setCharAt(i, 'u');
					break;
				case 'h':
				case 'H':
					poema.setCharAt(i, 't');
					break;
				case 'ñ':
				case 'Ñ':
					poema.setCharAt(i, 'e');
					break;
				case 'k':
				case 'K':
					poema.setCharAt(i, 'l');
					break;
				case 'v':
				case 'V':
					poema.setCharAt(i, 'f');
					break;
				case 'w':
				case 'W':
					poema.setCharAt(i, 'b');
					break;
				case 'z':
				case 'Z':
					poema.setCharAt(i, 'y');
					break;
				}

			}
			return poema.toString();

		} catch (Exception e) {
			System.out.println("Error en conversion");
			throw e;
		}
	}

	public static String EliminaTildes(String texto) {
		try {
			StringBuilder poema = new StringBuilder(texto);
			for (int i = 0; i < poema.length(); i++) {
				switch (poema.charAt(i)) {
				case 'á':
				case 'Á':
					poema.setCharAt(i, 'a');
					break;
				case 'é':
				case 'É':
					poema.setCharAt(i, 'e');
					break;
				case 'í':
				case 'Í':
					poema.setCharAt(i, 'i');
					break;
				case 'ó':
				case 'Ó':
					poema.setCharAt(i, 'o');
					break;
				case 'ú':
				case 'Ú':
					poema.setCharAt(i, 'u');
					break;
				}
			}
			return poema.toString();

		} catch (Exception e) {
			System.out.println("Error en conversion");
			throw e;
		}
	}

	public static int[] Frecuencias(String texto) {
		try {
			int[] frecuencia = new int[26];
			for (int i = 0; i < texto.length(); i++) {
				char caracter = texto.charAt(i);
				if ('A' < caracter && 'Z' > caracter) {
					int letra = caracter - 'A';
					frecuencia[letra]++;
				}

			}
			return frecuencia;
		} catch (Exception e) {
			System.out.println("Erro de conteo");
			throw e;
		}

	}

	public static void mostrarTabla(int[] tabla) {
		System.out.println("TABLA DE FRECUENCIA\n");
		for (int i = 0; i < tabla.length; i++) {
			System.out.println((char) (i + 'A') + "\t " + tabla[i]);
		}
	}

	public static String Unicode8(String poema) throws Exception {
		try {
			String unicode = "";
			StringBuilder texto = new StringBuilder(poema);
			for (int i = 0; i < texto.length(); i++) {
				if(texto.charAt(i) != '\n') {
					String caracte = String.valueOf(texto.charAt(i));
					byte[] caracter = caracte.getBytes();
					for (byte b : caracter) {
						unicode += b;
					}
				}
								 
			}
			return unicode;
		} catch (Exception e) {
			System.out.println("erros de codifcaion");
			throw e;
		}
	}
	public static String padding(String poema) {
		try {
			String Padding = "";
			int contador = 1;
			StringBuilder texto = new StringBuilder(poema);
			for (int i = 0; i < texto.length(); i++) {
				Padding += texto.charAt(i);
				if(contador%13 == 0) {
					Padding += "EPIS";
				}
				contador++;
								 
			}
			int cantidad = Padding.length();
			int sobrante = cantidad % 5;
			if(sobrante == 0) {
				return Padding;
			}
			else {
				int cantZ = 5-sobrante;
				for (int i = 0; i < cantZ; i++) {
					Padding += "Z";
				}
				return Padding;
			}
			
		} catch (Exception e) {
			System.out.println("erros de codifcaion");
			throw e;
		}
	}

}
