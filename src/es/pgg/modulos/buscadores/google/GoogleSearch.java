/**
 * 
 */
package es.pgg.modulos.buscadores.google;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import es.pgg.modulos.buscadores.google.json.Cursor;
import es.pgg.modulos.buscadores.google.json.GoogleResult;
import es.pgg.modulos.buscadores.google.json.GoogleResults;
import es.pgg.modulos.buscadores.google.json.ResponseData;
import es.pgg.utils.Constantes;

/**
 * @author PatxiGG
 * 
 */
public class GoogleSearch {

	public static GoogleResults makeSearch(String google, String search,
			String charset) throws IOException {
		GoogleResults resultados = null;
		if (!Constantes.UTILIZAR_JSOUP) {
			// utilizamos la API
			resultados = ejecutarConLaAPI(google, search, charset);
		} else {
			// utilizamos JSOUP
			resultados = ejecutarConJSOUP(search, charset);

		}
		return resultados;
	}

	private static GoogleResults ejecutarConJSOUP(String search, String charset)
			throws IOException {
		// red

		GoogleResults results = null;
		Document docConexion = null;
		String query = URLEncoder.encode(search, Charset.defaultCharset()
				.name());
		Response response = null;

		try {
			response = Jsoup
					.connect(
							"https://www.google.com/search?ie=UTF-8&q=" + query)
					.userAgent(
							"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.46 Safari/535.11")
					.followRedirects(true).timeout(300000)
					.referrer("https://www.google.com/ncr").execute();

			docConexion = response.parse();
		} catch (IOException e) {
			if (response != null) {
				System.out.println("Estado mensaje: "
						+ response.statusMessage());
			}
			// esperamos 20 minutos
			SimpleDateFormat sdf = new SimpleDateFormat("Y-M-d (E) KK:mm:ss a");
			Calendar calendar = Calendar.getInstance();
			System.out.println(sdf.format(calendar.getTime())
					+ " Esperamos 20 minutos para volver a hacer una busqueda");
			try {
				Thread.sleep((1000 * 60 * 20));
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			response = Jsoup
					.connect(
							"https://www.google.com/search?ie=UTF-8&q=" + query)
					.userAgent(
							"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.46 Safari/535.11")
					.followRedirects(true).timeout(300000)
					.referrer("https://www.google.com/ncr").execute();
			docConexion = response.parse();
		}

		// falta obtener lo que kiero y meterlo en la lista resultado
		Elements links = docConexion.select("div[id=ires]");

		for (Element element : links.select("cite")) {
			element.remove();
		}

		for (Element element : links
				.select("li[class=action-menu-item ab_dropdownitem]")) {
			element.remove();
		}

		Elements finalTitleResults = links.select("h3[class=r]");
		Elements finalContentResults = links.select("span[class=st]");

		results = new GoogleResults();
		String description = finalContentResults.text() + " "
				+ finalTitleResults.text();
		description = description.trim();

		if (!description.equals("")) {
			GoogleResult gr = new GoogleResult();
			gr.setTitle("");
			gr.setContent(description);
			List<GoogleResult> lista = new ArrayList<GoogleResult>();
			lista.add(gr);

			Cursor c = new Cursor();
			c.setResultCount("1");

			ResponseData rd = new ResponseData();
			rd.setResults(lista);
			rd.setCursor(c);

			results.setResponseData(rd);

		}

		return results;
	}

	private static GoogleResults ejecutarConLaAPI(String google, String search,
			String charset) throws UnsupportedEncodingException, IOException {
		URL url = new URL(google + URLEncoder.encode(search, charset));
		// System.out.println("************************\n URL: " + url
		// + "\n***********************\n");
		Reader reader = new InputStreamReader(url.openStream(), charset);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		GoogleResults results = new Gson()
				.fromJson(reader, GoogleResults.class);

		if (!results.getResponseStatus().equals("200")) {
			System.out
					.println("Fallo al recibir los datos, peticiones agotadas, cod: "
							+ results.getResponseStatus());
			// revisar si es porque nos hemos cargado las peticiones o si es
			// porque realmente no hay resultados si nos hemos cargado las
			// peticiones, esperar unos 5 a 10 minutos para que nos vuelva a
			// permitir hacerlas

			try {
				// esperamos 5 minutos
				SimpleDateFormat sdf = new SimpleDateFormat(
						"Y-M-d (E) KK:mm:ss a");
				Calendar calendar = Calendar.getInstance();
				System.out
						.println(sdf.format(calendar.getTime())
								+ " Esperamos 20 minutos para volver a hacer una busqueda");
				Thread.sleep((1000 * 60 * 20));
				results = makeSearch(google, search, charset);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return results;
	}

}
