package logica.webservices;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class POJO {
	
	private List lista;
	
	public POJO() {
		this.lista = null;
	}
	
	public POJO(List lista) {
		this.lista = lista;
	}
	
	public List getLista() {
		return lista;
	}
	
	public void setLista(List lista) {
		this.lista = lista;
	}

}
