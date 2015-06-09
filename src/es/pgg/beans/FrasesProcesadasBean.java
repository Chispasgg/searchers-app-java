package es.pgg.beans;

import java.util.ArrayList;
import java.util.List;

public class FrasesProcesadasBean {

	private String frase = "";
	private String idioma = "en";

	private List<String> listaResultados = null;

	public FrasesProcesadasBean(String frase, String idioma) {
		this.frase = frase.trim().toLowerCase();
		this.idioma = idioma.trim().toLowerCase();
		this.listaResultados = new ArrayList<String>();
	}

	public String getFrase() {
		return frase;
	}

	public void setFrase(String frase) {
		this.frase = frase;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public List<String> getListaResultados() {
		return listaResultados;
	}

	public void setListaResultados(
			List<String> listaCodigosPalabrasDesambiguadas) {
		this.listaResultados = listaCodigosPalabrasDesambiguadas;
	}

	public void addResult(String result) {
		if (this.listaResultados == null) {
			this.listaResultados = new ArrayList<String>();
		}
		this.listaResultados.add(result.trim());
	}

	@Override
	public String toString() {
		String resultado = "\n===============================\nMain Sentence: "
				+ this.frase + "\nResults:\n";
		for (String result : listaResultados) {
			resultado += "- " + result + "\n";
		}
		return resultado;
	}

}
