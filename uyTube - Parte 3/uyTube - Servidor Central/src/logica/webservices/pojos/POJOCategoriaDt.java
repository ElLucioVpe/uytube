package logica.webservices.pojos;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import logica.dt.CategoriaDt;

@XmlType
public class POJOCategoriaDt {
	
	private List<CategoriaDt> lista;
	
	public POJOCategoriaDt() {
		this.lista = null;
	}
	
	public POJOCategoriaDt(List<CategoriaDt> lista) {
		this.lista = lista;
	}
	
	public List<CategoriaDt> getLista() {
		return lista;
	}
	
	public void setLista(List<CategoriaDt> lista) {
		this.lista = lista;
	}

}
