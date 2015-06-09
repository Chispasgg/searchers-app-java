/**
 * 
 */
package es.pgg;

import es.pgg.beans.FrasesProcesadasBean;
import es.pgg.modulos.Modulos;
import es.pgg.utils.Constantes;

/**
 * @author PatxiGG
 *
 */
public class MAIN {

	public static void main(String[] args) throws Exception {
		System.out.println("Inicio");
		String frase = " I drove the red car carefully.";
		String idioma = "en";

		procesarFrase(frase, idioma);

		System.out.println("Fin");
	}

	private static void procesarFrase(String frase, String idioma) {

		FrasesProcesadasBean fraseNueva = new FrasesProcesadasBean(frase,
				idioma);
		System.out.println("###############################################");
		System.out.println("Bing results Direct call");
		Constantes.EJECUTAR_BING = true;
		Constantes.EJECUTAR_GOOGLE = false;
		Constantes.UTILIZAR_JSOUP = true;

		// buscadores
		try {
			fraseNueva = Modulos.probarBuscadores(fraseNueva);
			System.out.println(fraseNueva.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
		}

		System.out.println("###############################################");
		System.out.println("Google results Direct call");
		Constantes.EJECUTAR_BING = false;
		Constantes.EJECUTAR_GOOGLE = true;
		Constantes.UTILIZAR_JSOUP = true;
		// buscadores
		try {
			fraseNueva = Modulos.probarBuscadores(fraseNueva);
			System.out.println(fraseNueva.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
		}

		System.out.println("###############################################");
		System.out.println("Bing results API call");
		Constantes.EJECUTAR_BING = true;
		Constantes.EJECUTAR_GOOGLE = false;
		Constantes.UTILIZAR_JSOUP = false;

		// buscadores
		try {
			fraseNueva = Modulos.probarBuscadores(fraseNueva);
			System.out.println(fraseNueva.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
		}

		System.out.println("###############################################");
		System.out.println("Google results API call");
		Constantes.EJECUTAR_BING = false;
		Constantes.EJECUTAR_GOOGLE = true;
		Constantes.UTILIZAR_JSOUP = false;
		// buscadores
		try {
			fraseNueva = Modulos.probarBuscadores(fraseNueva);
			System.out.println(fraseNueva.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
		}

	}

}
