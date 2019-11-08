package logica.webservices.pojos;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import logica.dt.ComentarioDt;

@XmlType
public class POJOComentarioDt {
	
	private List<ComentarioDt> lista;
	
	public POJOComentarioDt() {
		this.lista = null;
	}
	
	public POJOComentarioDt(List<ComentarioDt> lista) {
		this.lista = lista;
	}
	
	public List<ComentarioDt> getLista() {
		return lista;
	}
	
	public void setLista(List<ComentarioDt> lista) {
		this.lista = lista;
	}

}
