/**
 * 
 */
package es.pgg.modulos.buscadores.bing.json;

/**
 * @author PatxiGG
 *
 */

import com.google.gson.annotations.Expose;

public class Metadata {

	@Expose
	private String uri;
	@Expose
	private String type;

	/**
	 * 
	 * @return The uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * 
	 * @param uri
	 *            The uri
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * 
	 * @return The type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 *            The type
	 */
	public void setType(String type) {
		this.type = type;
	}

}