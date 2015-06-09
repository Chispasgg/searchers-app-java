package es.pgg.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintStream;

/**
 * Clase que sirve para leer un fichero de texto
 * 
 * @author Chispas
 * 
 */

public class fileReader {

	private File archivo = null;
	private BufferedReader br = null;
	private InputStreamReader is = null;

	public fileReader(String path, String encoded) {

		try {
			archivo = new File(path);
			// System.out.println(archivo.getAbsolutePath());
			if (!archivo.canRead())
				System.out
						.println("Archivo no existe o es inaccesible en este momento");
			if (encoded == null) {
				is = new InputStreamReader(new FileInputStream(archivo));
			} else {
				is = new InputStreamReader(new FileInputStream(archivo),
						encoded);
			}
			br = new BufferedReader(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String leeLinea() {
		String linea = null;
		try {
			linea = br.readLine();
			// linea = linea.trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return linea;
	}

	public void close() {
		try {
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		try {
			br.close();
			is.close();
			if (!archivo.exists())
				System.out.println((new StringBuilder())
						.append("No existe el fichero ")
						.append(archivo.getPath()).toString());
			else if (!archivo.delete())
				System.out.println((new StringBuilder())
						.append("No se ha podido eliminar el archivo ")
						.append(archivo.getPath()).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static long numeroDeLineasDelArchivo(String pathArchivo) {
		long numeroLineas = -1;
		// obtenemos el numero de lineas que tiene el fichero
		try {
			LineNumberReader lnr = new LineNumberReader(new FileReader(
					pathArchivo));
			lnr.skip(Long.MAX_VALUE);
			numeroLineas = lnr.getLineNumber();
			lnr.close();
			lnr = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return numeroLineas;
	}

	public static void escribirEnArchivo(String pathArchivo, String texto,
			boolean anadir) {

		try {
			PrintStream out = new PrintStream((new FileOutputStream(
					pathArchivo, anadir)));
			out.println(texto);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
