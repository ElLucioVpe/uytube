
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

    private final static QName _CategoriaDt_QNAME = new QName("http://webservices.logica/", "categoriaDt");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: logica.webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CategoriaDt }
     * 
     */
    public CategoriaDt createCategoriaDt() {
        return new CategoriaDt();
    }

    /**
     * Create an instance of {@link ArrayList }
     * 
     */
    public ArrayList createArrayList() {
        return new ArrayList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoriaDt }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "categoriaDt")
    public JAXBElement<CategoriaDt> createCategoriaDt(CategoriaDt value) {
        return new JAXBElement<CategoriaDt>(_CategoriaDt_QNAME, CategoriaDt.class, null, value);
    }

}
