package logica.webservices.pojos;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import logica.dt.VideoDt;

@XmlType
public class POJOVideoDt {
	
	private List<VideoDt> lista;
	
	public POJOVideoDt() {
		this.lista = null;
	}
	
	public POJOVideoDt(List<VideoDt> lista) {
		this.lista = lista;
	}
	
	public List<VideoDt> getLista() {
		return lista;
	}
	
	public void setLista(List<VideoDt> lista) {
		this.lista = lista;
	}
}