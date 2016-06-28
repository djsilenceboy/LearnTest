
package com.djs.test.wsclient.wap_push;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendWapPush complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendWapPush">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wapPushRequest" type="{http://www.csapi.org/schema/parlayx/singtel/ndp/ext/wappush/v1_0}WapPushReqData"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendWapPush", propOrder = {
    "wapPushRequest"
})
@XmlRootElement(name = "sendWapPush")
public class SendWapPush {

    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/singtel/ndp/ext/wappush/v1_0/local", required = true)
    protected WapPushReqData wapPushRequest;

    /**
     * Gets the value of the wapPushRequest property.
     * 
     * @return
     *     possible object is
     *     {@link WapPushReqData }
     *     
     */
    public WapPushReqData getWapPushRequest() {
        return wapPushRequest;
    }

    /**
     * Sets the value of the wapPushRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link WapPushReqData }
     *     
     */
    public void setWapPushRequest(WapPushReqData value) {
        this.wapPushRequest = value;
    }

}
