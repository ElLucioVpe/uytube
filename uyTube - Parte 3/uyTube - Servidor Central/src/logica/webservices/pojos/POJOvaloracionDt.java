package logica.webservices.pojos;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import logica.dt.valoracionDt;

@XmlType
public class POJOvaloracionDt {
	
	private List<valoracionDt> lista;
	
	public POJOvaloracionDt() {
		this.lista = null;
	}
	
	public POJOvaloracionDt(List<valoracionDt> lista) {
		this.lista = lista;
	}
	
	public List<valoracionDt> getLista() {
		return lista;
	}
	
	public void setLista(List<valoracionDt> lista) {
		this.lista = lista;
	}

}
