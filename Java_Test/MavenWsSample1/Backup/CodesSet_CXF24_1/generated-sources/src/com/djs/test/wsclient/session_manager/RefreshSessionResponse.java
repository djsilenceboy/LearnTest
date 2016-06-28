
package com.djs.test.wsclient.session_manager;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="refreshSessionReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "refreshSessionReturn"
})
@XmlRootElement(name = "refreshSessionResponse")
public class RefreshSessionResponse {

    @XmlElement(required = true)
    protected String refreshSessionReturn;

    /**
     * Gets the value of the refreshSessionReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefreshSessionReturn() {
        return refreshSessionReturn;
    }

    /**
     * Sets the value of the refreshSessionReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefreshSessionReturn(String value) {
        this.refreshSessionReturn = value;
    }

}
