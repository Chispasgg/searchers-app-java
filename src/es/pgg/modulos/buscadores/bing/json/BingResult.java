/**
 * 
 */
package es.pgg.modulos.buscadores.bing.json;

/**
 * @author PatxiGG
 *
 */

import com.google.gson.annotations.Expose;

public class BingResult {

//	@Expose
//	private Metadata metadata;
	@Expose
	private String ID;
	@Expose
	private String Title;
	@Expose
	private String Description;
	@Expose
	private String DisplayUrl;
	@Expose
	private String Url;

//	/**
//	 * 
//	 * @return The metadata
//	 */
//	public Metadata getMetadata() {
//		return metadata;
//	}
//
//	/**
//	 * 
//	 * @param metadata
//	 *            The metadata
//	 */
//	public void setMetadata(Metadata metadata) {
//		this.metadata = metadata;
//	}

	/**
	 * 
	 * @return The ID
	 */
	public String getID() {
		return ID;
	}

	/**
	 * 
	 * @param ID
	 *            The ID
	 */
	public void setID(String ID) {
		this.ID = ID;
	}

	/**
	 * 
	 * @return The Title
	 */
	public String getTitle() {
		return Title;
	}

	/**
	 * 
	 * @param Title
	 *            The Title
	 */
	public void setTitle(String Title) {
		this.Title = Title;
	}

	/**
	 * 
	 * @return The Description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * 
	 * @param Description
	 *            The Description
	 */
	public void setDescription(String Description) {
		this.Description = Description;
	}

	/**
	 * 
	 * @return The DisplayUrl
	 */
	public String getDisplayUrl() {
		return DisplayUrl;
	}

	/**
	 * 
	 * @param DisplayUrl
	 *            The DisplayUrl
	 */
	public void setDisplayUrl(String DisplayUrl) {
		this.DisplayUrl = DisplayUrl;
	}

	/**
	 * 
	 * @return The Url
	 */
	public String getUrl() {
		return Url;
	}

	/**
	 * 
	 * @param Url
	 *            The Url
	 */
	public void setUrl(String Url) {
		this.Url = Url;
	}

}