
package com.djs.myfirstservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;


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
 *         &lt;element name="YouOperationFault" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "youOperationFault"
})
@XmlRootElement(name = "YouOperationFault")
public class YouOperationFault {

    @XmlElement(name = "YouOperationFault", required = true)
    protected String youOperationFault;

    /**
     * Gets the value of the youOperationFault property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYouOperationFault() {
        return youOperationFault;
    }

    /**
     * Sets the value of the youOperationFault property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYouOperationFault(String value) {
        this.youOperationFault = value;
    }

    /**
     * Generates a String representation of the contents of this type.
     * This is an extension method, produced by the 'ts' xjc plugin
     * 
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, JAXBToStringStyle.DEFAULT_STYLE);
    }

}