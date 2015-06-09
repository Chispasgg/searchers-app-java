/**
 * 
 */
package es.pgg.modulos.buscadores.bing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import es.pgg.modulos.buscadores.bing.json.BingResult;
import es.pgg.modulos.buscadores.bing.json.D;
import es.pgg.modulos.buscadores.bing.json.ResultadosBing;
import es.pgg.utils.Constantes;

/**
 * @author PatxiGG
 *
 */
public class BingSearch {

	public static ResultadosBing makeSearch(String search) throws Exception {
		ResultadosBing resultados = null;
		// miramos con que ejecutarlo
		if (!Constantes.UTILIZAR_JSOUP) {
			// utilizamos la API
			resultados = ejecutarConLaAPI(search);
		} else {
			// ejecutamos JSOUP
			resultados = ejecutarConJSOUP(search);
		}
		return resultados;
	}

	private static ResultadosBing ejecutarConJSOUP(String search)
			throws IOException {

		ResultadosBing results = null;
		Document docConexion = null;
		String query = URLEncoder.encode(search, Charset.defaultCharset()
				.name());
		try {
			docConexion = Jsoup
					.connect(
							"https://www.bing.com/search?q=" + query
									+ "&setmkt=en-us&setlang=en-us")
					.userAgent(
							"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
					.timeout(4000).post();
		} catch (IOException e) {

			// esperamos 5 minutos
			SimpleDateFormat sdf = new SimpleDateFormat("Y-M-d (E) KK:mm:ss a");
			Calendar calendar = Calendar.getInstance();
			System.out.println(sdf.format(calendar.getTime())
					+ " Esperamos 20 minutos para volver a hacer una busqueda");
			try {
				Thread.sleep((1000 * 60 * 20));
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			docConexion = Jsoup
					.connect(
							"https://www.bing.com/search?q=" + query
									+ "&setmkt=en-us&setlang=en-us")
					.userAgent(
							"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
					.timeout(4000).post();

		}

		// falta obtener lo que kiero y meterlo en la lista resultado
		Elements links = docConexion.select("ol[id=b_results]");
		links = links.select("li[class=b_algo]");

		for (Element element : links.select("div[class=b_attribution]")) {
			element.remove();
		}

		results = new ResultadosBing();
		String description = links.text();
		description = description.trim();
		if (!description.equals("")) {
			BingResult br = new BingResult();
			br.setTitle("");
			br.setDescription(description);
			List<BingResult> lista = new ArrayList<BingResult>();
			lista.add(br);
			D d = new D();
			d.setResults(lista);
			results.setD(d);
		}

		return results;
	}

	private static ResultadosBing ejecutarConLaAPI(String search)
			throws IOException {
		String accountKey = Constantes.BING_API_KEY;
		String bingUrlPattern = Constantes.BING_URL_SITE;

		String query = URLEncoder.encode(search, Charset.defaultCharset()
				.name());

		String bingUrl = String.format(bingUrlPattern, query);

		String accountKeyEnc = Base64.getEncoder().encodeToString(
				(accountKey + ":" + accountKey).getBytes());

		URL url = new URL(bingUrl);
		URLConnection connection = url.openConnection();
		connection
				.setRequestProperty("Authorization", "Basic " + accountKeyEnc);

		// crear el lector del json
		BufferedReader rd = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));

		String line = "";
		String lineD = "";
		while ((line = rd.readLine()) != null) {
			lineD += line;
		}
		lineD = lineD.replaceAll("\"__", "\"");

		ResultadosBing results = new Gson().fromJson(lineD,
				ResultadosBing.class);

		return results;
	}
}
