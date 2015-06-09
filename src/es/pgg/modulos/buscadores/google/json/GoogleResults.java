/**
 * 
 */
package es.pgg.modulos.buscadores.google.json;

/**
 * @author PatxiGG
 * 
 */
public class GoogleResults {

	private ResponseData responseData;
	private String responseStatus;

	public String toString() {
		return "ResponseData[" + responseData + "]\nResponse Status: "
				+ responseStatus;
	}

	public ResponseData getResponseData() {
		return responseData;
	}

	public void setResponseData(ResponseData responseData) {
		this.responseData = responseData;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

}
