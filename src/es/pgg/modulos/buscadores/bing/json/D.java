/**
 * 
 */
package es.pgg.modulos.buscadores.bing.json;

/**
 * @author PatxiGG
 *
 */

import java.util.ArrayList;
import java.util.List;

public class D {

	private List<BingResult> results = new ArrayList<BingResult>();

	// @Expose
	// private String next;

	/**
	 * 
	 * @return The results
	 */
	public List<BingResult> getResults() {
		return results;
	}

	/**
	 * 
	 * @param results
	 *            The results
	 */
	public void setResults(List<BingResult> results) {
		this.results = results;
	}

	// /**
	// *
	// * @return The next
	// */
	// public String getNext() {
	// return next;
	// }
	//
	// /**
	// *
	// * @param next
	// * The next
	// */
	// public void setNext(String next) {
	// this.next = next;
	// }

}