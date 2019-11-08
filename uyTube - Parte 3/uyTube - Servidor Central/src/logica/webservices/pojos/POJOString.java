package logica.webservices.pojos;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class POJOString {
	
	private List<String> lista;
	
	public POJOString() {
		this.lista = null;
	}
	
	public POJOString(List<String> lista) {
		this.lista = lista;
	}
	
	public List<String> getLista() {
		return lista;
	}
	
	public void setLista(List<String> lista) {
		this.lista = lista;
	}

}
