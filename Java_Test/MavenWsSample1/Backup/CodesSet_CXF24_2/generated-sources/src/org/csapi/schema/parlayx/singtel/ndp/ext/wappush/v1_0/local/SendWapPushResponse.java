
package org.csapi.schema.parlayx.singtel.ndp.ext.wappush.v1_0.local;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.csapi.schema.parlayx.singtel.ndp.ext.wappush.v1_0.WapPushRespData;


/**
 * <p>Java class for sendWapPushResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendWapPushResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wapPushResponse" type="{http://www.csapi.org/schema/parlayx/singtel/ndp/ext/wappush/v1_0}WapPushRespData"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendWapPushResponse", propOrder = {
    "wapPushResponse"
})
@XmlRootElement(name = "sendWapPushResponse")
public class SendWapPushResponse {

    @XmlElement(required = true)
    protected WapPushRespData wapPushResponse;

    /**
     * Gets the value of the wapPushResponse property.
     * 
     * @return
     *     possible object is
     *     {@link WapPushRespData }
     *     
     */
    public WapPushRespData getWapPushResponse() {
        return wapPushResponse;
    }

    /**
     * Sets the value of the wapPushResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link WapPushRespData }
     *     
     */
    public void setWapPushResponse(WapPushRespData value) {
        this.wapPushResponse = value;
    }

}
