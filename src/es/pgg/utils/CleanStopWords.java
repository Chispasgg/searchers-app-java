/**
 * 
 */
package es.pgg.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author PatxiGG
 * 
 */
public class CleanStopWords {

	private Set<String> stopWords = new HashSet<String>();

	/**
	 * Metodo para quitar SW y si se quiere, numeros
	 * 
	 * @param path
	 */
	public CleanStopWords(String path) {

		System.out.println("cargando StopWords");
		// fill stopWords
		cargarStopWords(path);
		// System.out.println("StopWords cargadas");

	}

	public CleanStopWords() {
	}

	/**
	 * Metodo solo para quitar numeros
	 */
	public String quitNumbers(String text) {
		String[] texAux = text.split(" ");
		String result = "";

		for (String word : texAux) {
			try {
				Integer.parseInt(word.trim());
			} catch (Exception e) {
				result += " " + word;
			}
		}

		return result;
	}

	public String quitSW(String text) {

		String[] texAux = text.split(" ");
		String result = "";

		for (String word : texAux) {
			if (!stopWords.contains(word.toLowerCase())) {
				result += " " + word;
				result=result.trim();
			}
		}

		return result.trim();
	}

	private void cargarStopWords(String path) {
		// leemeos todos los elementos que contiene esa clase
		// iniciar la lectura
		fileReader fr = new fileReader(path,
		// "ISO-8859-1"
				"UTF-8");
		String linea;
		while ((linea = fr.leeLinea()) != null) {
			linea = linea.trim();
			if (!linea.equals("")) {
				this.stopWords.add(linea.toLowerCase());
			}
		}
	}
}
