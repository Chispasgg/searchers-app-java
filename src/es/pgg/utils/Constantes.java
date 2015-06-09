/**
 * 
 */
package es.pgg.utils;

/**
 * @author PatxiGG
 *
 */
public class Constantes {

	// buscadores
	public static final String BING_URL_SITE = "https://api.datamarket.azure.com/Bing/Search/Web?Adult=%%27Off%%27&Query=%%27%s%%27&$format=JSON";
	public static boolean EJECUTAR_GOOGLE = false;
	public static boolean EJECUTAR_BING = false;
	public static boolean UTILIZAR_JSOUP = true;
	public static final String BING_API_KEY = "<your bing key here>";

	// filtros alphanumericos
	public static final String CARACTERES_INVALIDOS = "[^A-Za-z0-9\\- ]";
	public static final String CARACTERES_DEMASIADOS_ESPACIOS = "[\\ ]+";
	public static final String ELEMENTO_SUSTITUTO_CARACTER_INVALIDO = " ";

	// recurso de la patita
	public static final String STOPWORDS_PATH = "conf/EnglishStopWords.txt";
	private static CleanStopWords CLEAN_STOPWORDS = null;

	public static CleanStopWords STOPWORDS_CLEANER() {
		if (CLEAN_STOPWORDS == null) {
			CLEAN_STOPWORDS = new CleanStopWords(Constantes.STOPWORDS_PATH);
		}
		return CLEAN_STOPWORDS;
	}

	// otros
	public static final boolean NOTIFICAR_PROGRESO = true;

}
