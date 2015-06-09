/**
 * 
 */
package es.pgg.modulos.buscadores.google.json;

/**
 * @author PatxiGG
 * 
 */
public class GoogleResult {
	private String url;
	private String title;
	private String titleNoFormatting;
	private String content;

	public String getUrl() {
		return url;
	}

	public String getTitle() {
		return title;
	}

	public String getTitleNoFormatting() {
		return titleNoFormatting;
	}

	public String getContent() {
		return content;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTitleNoFormatting(String titleNoFormatting) {
		this.titleNoFormatting = titleNoFormatting;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String toString() {
		return "Result[url:" + url + ", title:" + title
				+ ", titleNoFormatting:" + titleNoFormatting + ", content: "
				+ content + "]";
	}
}
