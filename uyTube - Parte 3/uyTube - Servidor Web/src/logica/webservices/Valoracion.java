
package logica.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para valoracion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="valoracion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gustar" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="usuario" type="{http://webservices.logica/}usuario" minOccurs="0"/>
 *         &lt;element name="valoracionPK" type="{http://webservices.logica/}valoracionPK" minOccurs="0"/>
 *         &lt;element name="video" type="{http://webservices.logica/}video" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "valoracion", propOrder = {
    "gustar",
    "usuario",
    "valoracionPK",
    "video"
})
public class Valoracion {

    protected Boolean gustar;
    protected Usuario usuario;
    protected ValoracionPK valoracionPK;
    protected Video video;

    /**
     * Obtiene el valor de la propiedad gustar.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGustar() {
        return gustar;
    }

    /**
     * Define el valor de la propiedad gustar.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGustar(Boolean value) {
        this.gustar = value;
    }

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link Usuario }
     *     
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link Usuario }
     *     
     */
    public void setUsuario(Usuario value) {
        this.usuario = value;
    }

    /**
     * Obtiene el valor de la propiedad valoracionPK.
     * 
     * @return
     *     possible object is
     *     {@link ValoracionPK }
     *     
     */
    public ValoracionPK getValoracionPK() {
        return valoracionPK;
    }

    /**
     * Define el valor de la propiedad valoracionPK.
     * 
     * @param value
     *     allowed object is
     *     {@link ValoracionPK }
     *     
     */
    public void setValoracionPK(ValoracionPK value) {
        this.valoracionPK = value;
    }

    /**
     * Obtiene el valor de la propiedad video.
     * 
     * @return
     *     possible object is
     *     {@link Video }
     *     
     */
    public Video getVideo() {
        return video;
    }

    /**
     * Define el valor de la propiedad video.
     * 
     * @param value
     *     allowed object is
     *     {@link Video }
     *     
     */
    public void setVideo(Video value) {
        this.video = value;
    }

}
