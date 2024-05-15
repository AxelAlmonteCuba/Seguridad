package Lab_01;

import java.io.*;
import java.util.*;

public class Poema {
	public static void main(String[] args) {
		String ruta = "C:\\Users\\ASUS\\Documents\\poema.txt";
		String texto = "";
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new FileReader(ruta));
			String line = "";
			while ((line = bf.readLine()) != null) {
				texto += line + "\n";

			}
		}

		catch (IOException e) {
			System.out.println("erro al abri archivo");
		}
		System.out.println("------------------------------------");
		System.out.println(texto);
		texto = Sustitucion(texto);
		System.out.println("------------------------------------");
		System.out.println(texto);
		texto = EliminaTildes(texto);
		System.out.println("------------------------------------");
		System.out.println(texto);
		texto = ConvertirMayuscula(texto);
		System.out.println("------------------------------------");
		System.out.println(texto);
		texto = EliminarEspaciosSignos(texto);
		System.out.println("------------------------------------");
		System.out.println(texto);
		String archivoModificado = "PRACTICA1_PRE.TXT";
		GuardarResultado(texto);
		texto = "";
		try {
			bf = new BufferedReader(new FileReader(archivoModificado));
			String line = "";
			while ((line = bf.readLine()) != null) {
				texto += line + "\n";

			}
		}

		catch (IOException e) {
			System.out.println("erro al abri archivo");
		}
		System.out.println("------------------------------------");
		System.out.println(texto);
		System.out.println("------------------------------------");
		mostrarTabla(Frecuencias(texto));
		int[] frecuencias = Frecuencias(texto);
		List<Character> letrasMasFrecuentes = encontrarLetrasMasFrecuentes(frecuencias, 5);
	    System.out.println("\nLas 5 letras más frecuentes son: " + letrasMasFrecuentes);
		// Ataque de Kasiski
		HashMap<String, List<Integer>> trigramas = EncontrarTrigramas(texto);
		HashMap<String, List<Integer>> distancias = CalcularDistancias(trigramas);
		System.out.println("-------------------------------------");
		System.out.println("Trigramas encontrados: " + trigramas);
		System.out.println("Distancias entre trigramas: " + distancias);

		try {
			String texto2 = Unicode8(texto);
			System.out.println("------------------------------------");
			System.out.println(texto2);
			texto = ConvertirSegunAlfabeto(texto);
			System.out.println("------------------------------------");
			System.out.println(texto);
			texto = padding(texto);
			System.out.println("------------------------------------");
			System.out.println(texto);
			System.out.println("Cantidad de letras despues del padding: " + ContarCaracteres(texto));

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

	public static List<Character> encontrarLetrasMasFrecuentes(int[] frecuencias, int cantidad) {
		Map<Character, Integer> mapaFrecuencias = new HashMap<>();
		for (int i = 0; i < frecuencias.length; i++) {
			char letra = (char) (i + 'A');
			mapaFrecuencias.put(letra, frecuencias[i]);
		}

		List<Character> letrasMasFrecuentes = new ArrayList<>();
		for (int i = 0; i < cantidad; i++) {
			char letraMasFrecuente = Collections.max(mapaFrecuencias.entrySet(), Map.Entry.comparingByValue()).getKey();
			letrasMasFrecuentes.add(letraMasFrecuente);
			mapaFrecuencias.remove(letraMasFrecuente);
		}

		return letrasMasFrecuentes;
	}

	public static String Unicode8(String poema) throws Exception {
		try {
			String unicode = "";
			StringBuilder texto = new StringBuilder(poema);
			for (int i = 0; i < texto.length(); i++) {
				if (texto.charAt(i) != '\n') {
					String caracte = String.valueOf(texto.charAt(i));
					byte[] caracter = caracte.getBytes();
					for (byte b : caracter) {
						unicode += b;
					}
				}
				else {
					unicode += "\n";
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
				if ('A' < texto.charAt(i) && 'Z' > texto.charAt(i)) {
					if (contador % 13 == 0) {

						Padding += "EPIS";
					}
					contador++;
				}

			}
			int cantidad = ContarCaracteres(Padding);
			System.out.println("Cantidad de letras antes del padding: " + cantidad);
			int sobrante = cantidad % 5;
			if (sobrante == 0) {
				return Padding;
			} else {
				int cantZ = 5 - sobrante;
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

	public static int ContarCaracteres(String texto) {
		int conteo = 0;

		for (int i = 0; i < texto.length(); i++) {
			char caracter = texto.charAt(i);
			if (caracter != '\n') {
				conteo++;
			}
		}

		return conteo;
	}

	public static String ConvertirMayuscula(String texto) {
		char[] characterPoema = texto.toCharArray();
		for (int i = 0; i < characterPoema.length; i++) {
			if (characterPoema[i] >= 'a' && characterPoema[i] <= 'z')
				characterPoema[i] = (char) (characterPoema[i] - 'a' + 'A');
		}
		String nuevo = new String(characterPoema);
		return nuevo;
	}

	public static String EliminarEspaciosSignos(String texto) {
		String nuevo = "";
		for (int i = 0; i < texto.length(); i++) {
			char caracter = texto.charAt(i);
			if (!(caracter == ' ' || esSignoDePuntuacion(caracter)))
				nuevo += caracter;
		}
		return nuevo;
	}

	private static boolean esSignoDePuntuacion(char caracter) {
		char[] signosDePuntuacion = { '.', ',', '!', '?', ';', ':', '"', '\'', '(', ')', '[', ']', '{', '}', '-', '_',
				'/' };
		for (int i = 0; i < signosDePuntuacion.length; i++) {
			if (caracter == signosDePuntuacion[i])
				return true;
		}
		return false;
	}

	public static String AlfabetoResultante(String texto) {
		char[] caracterLista = texto.toCharArray();
		String alfabeto = "";
		for (char caracter = 'A'; caracter <= 'Z'; caracter++) {
			for (int i = 0; i < caracterLista.length; i++) {
				if (caracterLista[i] == caracter && alfabeto.indexOf(caracter) == -1)
					alfabeto += caracter;
			}
		}
		return alfabeto;
	}

	public static int ObtenerLongitud(String texto) {
		int longitud = texto.length();
		return longitud;
	}

	public static String ConvertirSegunAlfabeto(String texto) {
		String alfabeto = "ZYXWVUTSRQPONMLKJIHGFEDCBA";
		char[] characterPoema = texto.toCharArray();
		char[] nuevo = new char[characterPoema.length];
		for (int i = 0; i < characterPoema.length; i++) {
			char caracter = characterPoema[i];
			char caracterCifrado = caracter;
			if ((caracter >= 'A' && caracter <= 'Z') || (caracter >= 'a' && caracter <= 'z')) {
				int indiceEnAlfabeto = 0;
				if (caracter >= 'a' && caracter <= 'z') {
					caracter -= 'a' - 'A';
				}
				indiceEnAlfabeto = caracter - 'A';
				if (indiceEnAlfabeto >= 0 && indiceEnAlfabeto < alfabeto.length()) {
					caracterCifrado = alfabeto.charAt(indiceEnAlfabeto);
				}
			}
			nuevo[i] = caracterCifrado;
		}
		return new String(nuevo);
	}

	public static void GuardarResultado(String texto) {
		String archivo = "PRACTICA1_PRE.TXT";
		try (FileWriter escritor = new FileWriter(archivo)) {
			escritor.write(texto);
			escritor.close();
			System.out.println("Los resultados se han guardado en el archivo " + archivo);
		} catch (IOException e) {
			System.out.println("Error al guardar los resultados en el archivo.");
			e.printStackTrace();
		}
	}

	public static HashMap<String, List<Integer>> EncontrarTrigramas(String texto) {
		HashMap<String, List<Integer>> trigramas = new HashMap<>();
		for (int i = 0; i < texto.length() - 2; i++) {
			String trigram = texto.substring(i, i + 3);
			if (!trigramas.containsKey(trigram)) {
				List<Integer> posiciones = new ArrayList<>();
				posiciones.add(i);
				trigramas.put(trigram, posiciones);
			} else {
				trigramas.get(trigram).add(i);
			}
		}
		return trigramas;
	}

	public static HashMap<String, List<Integer>> CalcularDistancias(HashMap<String, List<Integer>> trigramas) {
		HashMap<String, List<Integer>> distancias = new HashMap<>();
		for (Map.Entry<String, List<Integer>> entry : trigramas.entrySet()) {
			String trigram = entry.getKey();
			List<Integer> posiciones = entry.getValue();
			List<Integer> distanciasTrigrama = new ArrayList<>();
			if (posiciones.size() > 1) {
				for (int i = 1; i < posiciones.size(); i++) {
					int distancia = posiciones.get(i) - posiciones.get(i - 1);
					distanciasTrigrama.add(distancia);
				}
			}
			distancias.put(trigram, distanciasTrigrama);
		}
		return distancias;
	}

}
