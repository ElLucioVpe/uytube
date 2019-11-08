package logica.webservices.pojos;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import logica.dt.ListaDeReproduccionDt;

@XmlType
public class POJOListadrDt {
	
	private List<ListaDeReproduccionDt> lista;
	
	public POJOListadrDt() {
		this.lista = null;
	}
	
	public POJOListadrDt(List<ListaDeReproduccionDt> lista) {
		this.lista = lista;
	}
	
	public List<ListaDeReproduccionDt> getLista() {
		return lista;
	}
	
	public void setLista(List<ListaDeReproduccionDt> lista) {
		this.lista = lista;
	}

}
