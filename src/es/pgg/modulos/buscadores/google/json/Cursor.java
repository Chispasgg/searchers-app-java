/**
 * 
 */
package es.pgg.modulos.buscadores.google.json;

/**
 * @author PatxiGG
 * 
 */
public class Cursor {
	private String currentPageIndex;
	private String resultCount;
	private String searchResultTime;

	public String toString() {
		return "CurrentPageIndex: " + currentPageIndex + "\nResult Count: "
				+ resultCount + "\nSearch Result Time: " + searchResultTime;
	}

	public String getCurrentPageIndex() {
		return currentPageIndex;
	}

	public void setCurrentPageIndex(String currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}

	public String getResultCount() {
		return resultCount;
	}

	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}

	public String getSearchResultTime() {
		return searchResultTime;
	}

	public void setSearchResultTime(String searchResultTime) {
		this.searchResultTime = searchResultTime;
	}
}
