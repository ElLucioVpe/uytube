
package logica.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para listaHistorialDt complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="listaHistorialDt">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.logica/}listaDeReproduccionDt">
 *       &lt;sequence>
 *         &lt;element name="visitas" type="{http://webservices.logica/}visitaDt" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaHistorialDt", propOrder = {
    "visitas"
})
public class ListaHistorialDt
    extends ListaDeReproduccionDt
{

    @XmlElement(nillable = true)
    protected List<VisitaDt> visitas;

    /**
     * Gets the value of the visitas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the visitas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVisitas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VisitaDt }
     * 
     * 
     */
    public List<VisitaDt> getVisitas() {
        if (visitas == null) {
            visitas = new ArrayList<VisitaDt>();
        }
        return this.visitas;
    }

}
