/**
 * 
 */
package es.pgg.modulos.buscadores.google.json;

import java.util.List;

/**
 * @author PatxiGG
 * 
 */
public class ResponseData {
	private List<GoogleResult> results;
	private Cursor cursor;

	public List<GoogleResult> getResults() {
		return results;
	}

	public void setResults(List<GoogleResult> results) {
		this.results = results;
	}

	public Cursor getCursor() {
		return cursor;
	}

	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}

	public String toString() {
		return "Results[" + results + "]\nCursors[" + cursor + "]";
	}
}
