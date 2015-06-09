/**
 * 
 */
package es.pgg.modulos;

import es.pgg.beans.FrasesProcesadasBean;
import es.pgg.modulos.buscadores.Buscadores;

/**
 * @author PatxiGG
 *
 */
public class Modulos {

	public static FrasesProcesadasBean probarBuscadores(
			FrasesProcesadasBean frase) throws Exception {

		Buscadores busq = new Buscadores();

		frase = busq.ejecutarBuscadores(frase);
		// System.out.println(frase.toString().toLowerCase());
		System.out.println("Buscadores terminado");
		return frase;

	}

}
