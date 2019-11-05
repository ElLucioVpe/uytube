
package logica.webservices;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the logica.webservices package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListaDeReproduccionDt_QNAME = new QName("http://webservices.logica/", "listaDeReproduccionDt");
    private final static QName _Categoria_QNAME = new QName("http://webservices.logica/", "categoria");
    private final static QName _ListaDeReproduccion_QNAME = new QName("http://webservices.logica/", "listaDeReproduccion");
    private final static QName _CanalDt_QNAME = new QName("http://webservices.logica/", "canalDt");
    private final static QName _Usuario_QNAME = new QName("http://webservices.logica/", "usuario");
    private final static QName _UsuarioDt_QNAME = new QName("http://webservices.logica/", "usuarioDt");
    private final static QName _Canal_QNAME = new QName("http://webservices.logica/", "canal");
    private final static QName _Video_QNAME = new QName("http://webservices.logica/", "video");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: logica.webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CanalDt }
     * 
     */
    public CanalDt createCanalDt() {
        return new CanalDt();
    }

    /**
     * Create an instance of {@link ListaDeReproduccion }
     * 
     */
    public ListaDeReproduccion createListaDeReproduccion() {
        return new ListaDeReproduccion();
    }

    /**
     * Create an instance of {@link ListaDeReproduccionDt }
     * 
     */
    public ListaDeReproduccionDt createListaDeReproduccionDt() {
        return new ListaDeReproduccionDt();
    }

    /**
     * Create an instance of {@link Categoria }
     * 
     */
    public Categoria createCategoria() {
        return new Categoria();
    }

    /**
     * Create an instance of {@link Usuario }
     * 
     */
    public Usuario createUsuario() {
        return new Usuario();
    }

    /**
     * Create an instance of {@link UsuarioDt }
     * 
     */
    public UsuarioDt createUsuarioDt() {
        return new UsuarioDt();
    }

    /**
     * Create an instance of {@link Canal }
     * 
     */
    public Canal createCanal() {
        return new Canal();
    }

    /**
     * Create an instance of {@link Video }
     * 
     */
    public Video createVideo() {
        return new Video();
    }

    /**
     * Create an instance of {@link ArrayList }
     * 
     */
    public ArrayList createArrayList() {
        return new ArrayList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListaDeReproduccionDt }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "listaDeReproduccionDt")
    public JAXBElement<ListaDeReproduccionDt> createListaDeReproduccionDt(ListaDeReproduccionDt value) {
        return new JAXBElement<ListaDeReproduccionDt>(_ListaDeReproduccionDt_QNAME, ListaDeReproduccionDt.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Categoria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "categoria")
    public JAXBElement<Categoria> createCategoria(Categoria value) {
        return new JAXBElement<Categoria>(_Categoria_QNAME, Categoria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListaDeReproduccion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "listaDeReproduccion")
    public JAXBElement<ListaDeReproduccion> createListaDeReproduccion(ListaDeReproduccion value) {
        return new JAXBElement<ListaDeReproduccion>(_ListaDeReproduccion_QNAME, ListaDeReproduccion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CanalDt }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "canalDt")
    public JAXBElement<CanalDt> createCanalDt(CanalDt value) {
        return new JAXBElement<CanalDt>(_CanalDt_QNAME, CanalDt.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Usuario }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "usuario")
    public JAXBElement<Usuario> createUsuario(Usuario value) {
        return new JAXBElement<Usuario>(_Usuario_QNAME, Usuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioDt }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "usuarioDt")
    public JAXBElement<UsuarioDt> createUsuarioDt(UsuarioDt value) {
        return new JAXBElement<UsuarioDt>(_UsuarioDt_QNAME, UsuarioDt.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Canal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "canal")
    public JAXBElement<Canal> createCanal(Canal value) {
        return new JAXBElement<Canal>(_Canal_QNAME, Canal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Video }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "video")
    public JAXBElement<Video> createVideo(Video value) {
        return new JAXBElement<Video>(_Video_QNAME, Video.class, null, value);
    }

}
