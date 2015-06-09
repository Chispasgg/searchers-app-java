/**
 * 
 */
package es.pgg.modulos.buscadores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import es.pgg.beans.FrasesProcesadasBean;
import es.pgg.modulos.buscadores.bing.BingSearch;
import es.pgg.modulos.buscadores.bing.json.BingResult;
import es.pgg.modulos.buscadores.bing.json.ResultadosBing;
import es.pgg.modulos.buscadores.google.GoogleSearch;
import es.pgg.modulos.buscadores.google.json.GoogleResult;
import es.pgg.modulos.buscadores.google.json.GoogleResults;
import es.pgg.utils.Constantes;

/**
 * @author PatxiGG
 *
 */

public class Buscadores {

	public FrasesProcesadasBean ejecutarBuscadores(
			FrasesProcesadasBean contenedorFrase) throws Exception {

		List<String> results = new ArrayList<String>();

		// ////////////////////////////////
		// BUSCADORES WEB
		// //////////////////////////////

		if (Constantes.EJECUTAR_GOOGLE) {
			results = ejecutarGoogle(contenedorFrase);
		} else if (Constantes.EJECUTAR_BING) {
			results = ejecutarBing(contenedorFrase);
		}

		contenedorFrase.setListaResultados(results);

		return contenedorFrase;
	}

	// ************************************************
	// PASOS
	// ************************************************

	private List<String> ejecutarBing(FrasesProcesadasBean contenedorFrase)
			throws Exception {
		System.out.println("Buscador Bing");
		List<String> listaResultado = new ArrayList<String>();

		ResultadosBing rb = BingSearch.makeSearch(contenedorFrase.getFrase());
		for (BingResult result : rb.getD().getResults()) {

			String titulo = Jsoup.clean(result.getTitle(), new Whitelist());
			String contenido = Jsoup.clean(result.getDescription(),
					new Whitelist());
			listaResultado.add("Title: " + titulo + "\nContent: " + contenido);

		}
		System.out.println("Buscador Bing Terminado");
		return listaResultado;
	}

	private List<String> ejecutarGoogle(FrasesProcesadasBean contenedorFrase)
			throws IOException {
		System.out.println("Buscador Google");

		List<String> listaResultados = new ArrayList<String>();

		/*
		 * private static final String NEWS_SEARCH_ENDPOINT =
		 * "http://ajax.googleapis.com/ajax/services/search/news"; private
		 * static final String LOCAL_SEARCH_ENDPOINT =
		 * "http://ajax.googleapis.com/ajax/services/search/local"; private
		 * static final String WEB_SEARCH_ENDPOINT =
		 * "http://ajax.googleapis.com/ajax/services/search/web"; private static
		 * final String BOOK_SEARCH_ENDPOINT =
		 * "http://ajax.googleapis.com/ajax/services/search/books"; private
		 * static final String IMAGE_SEARCH_ENDPOINT =
		 * "http://ajax.googleapis.com/ajax/services/search/images"; private
		 * static final String VIDEO_SEARCH_ENDPOINT =
		 * "http://ajax.googleapis.com/ajax/services/search/videos"; private
		 * static final String BLOG_SEARCH_ENDPOINT =
		 * "http://ajax.googleapis.com/ajax/services/search/blogs";
		 */

		String google = "http://ajax.googleapis.com/ajax/services/search/web?";
		String version = "v=1.0&";
		String lang = "hl=en&";
		String startPosition = "start=";
		String query = "&q=";

		String charset = "UTF-8";
		int[] posOFsearch = { 0, 4 };

		GoogleResults results = null;
		List<GoogleResult> listaResultadosBusqueda = new ArrayList<GoogleResult>();

		// hacemos la llamada
		results = GoogleSearch.makeSearch(google + version + lang
				+ startPosition + posOFsearch[0] + query,
				contenedorFrase.getFrase(), charset);

		// obtenemos los resultados de las busquedas
		String auxNumeroResultados = results.getResponseData().getCursor()
				.getResultCount();
		int numeroResultadosBusqueda = -1;

		// comprobamos si existen resultados
		if (auxNumeroResultados != null) {
			numeroResultadosBusqueda = new Integer(
					auxNumeroResultados.replaceAll("[\\.|\\,]", ""));
		}

		if (numeroResultadosBusqueda > 0) {
			for (GoogleResult result : listaResultadosBusqueda) {

				// guardamos el contenido que nos interesa
				listaResultados.add("Title: " + result.getTitle()
						+ "\nContent: " + result.getContent());
			}
		}
		System.out.println("Buscador Google Terminado");
		return listaResultados;
	}

	// ************************************************
	// METODOS AUXILIARES
	// ************************************************

}
