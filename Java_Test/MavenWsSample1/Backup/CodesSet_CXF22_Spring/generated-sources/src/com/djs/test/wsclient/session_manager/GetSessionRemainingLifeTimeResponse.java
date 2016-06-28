
package com.djs.test.wsclient.session_manager;

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
 *         &lt;element name="getSessionRemainingLifeTimeReturn" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "getSessionRemainingLifeTimeReturn"
})
@XmlRootElement(name = "getSessionRemainingLifeTimeResponse")
public class GetSessionRemainingLifeTimeResponse {

    protected int getSessionRemainingLifeTimeReturn;

    /**
     * Gets the value of the getSessionRemainingLifeTimeReturn property.
     * 
     */
    public int getGetSessionRemainingLifeTimeReturn() {
        return getSessionRemainingLifeTimeReturn;
    }

    /**
     * Sets the value of the getSessionRemainingLifeTimeReturn property.
     * 
     */
    public void setGetSessionRemainingLifeTimeReturn(int value) {
        this.getSessionRemainingLifeTimeReturn = value;
    }

}
