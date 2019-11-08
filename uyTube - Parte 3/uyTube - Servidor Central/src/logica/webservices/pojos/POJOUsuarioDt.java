package logica.webservices.pojos;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import logica.dt.UsuarioDt;

@XmlType
public class POJOUsuarioDt {
	
	private List<UsuarioDt> lista;
	
	public POJOUsuarioDt() {
		this.lista = null;
	}
	
	public POJOUsuarioDt(List<UsuarioDt> lista) {
		this.lista = lista;
	}
	
	public List<UsuarioDt> getLista() {
		return lista;
	}
	
	public void setLista(List<UsuarioDt> lista) {
		this.lista = lista;
	}

}
