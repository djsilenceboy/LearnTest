//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.11.08 at 05:01:36 PM GMT+08:00 
//


package com.djs.learn.wsclient.session_manager;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="destroySessionReturn" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "destroySessionReturn"
})
@XmlRootElement(name = "destroySessionResponse")
public class DestroySessionResponse {

    protected boolean destroySessionReturn;

    /**
     * Gets the value of the destroySessionReturn property.
     * 
     */
    public boolean isDestroySessionReturn() {
        return destroySessionReturn;
    }

    /**
     * Sets the value of the destroySessionReturn property.
     * 
     */
    public void setDestroySessionReturn(boolean value) {
        this.destroySessionReturn = value;
    }

}
