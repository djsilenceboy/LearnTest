
package com.djs.learn.myfirstservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="out" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="out2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="NewAttribute" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"out", "out2"})
@XmlRootElement(name = "MyOperationResponse")
public class MyOperationResponse
{

	@XmlElement(required = true)
	protected String out;
	@XmlElement(required = true)
	protected String out2;
	@XmlAttribute(name = "NewAttribute")
	protected String newAttribute;

	/**
	 * Gets the value of the out property.
	 * 
	 * @return
	 *         possible object is
	 *         {@link String }
	 */
	public String getOut(){
		return out;
	}

	/**
	 * Sets the value of the out property.
	 * 
	 * @param value
	 *        allowed object is
	 *        {@link String }
	 */
	public void setOut(String value){
		this.out = value;
	}

	/**
	 * Gets the value of the out2 property.
	 * 
	 * @return
	 *         possible object is
	 *         {@link String }
	 */
	public String getOut2(){
		return out2;
	}

	/**
	 * Sets the value of the out2 property.
	 * 
	 * @param value
	 *        allowed object is
	 *        {@link String }
	 */
	public void setOut2(String value){
		this.out2 = value;
	}

	/**
	 * Gets the value of the newAttribute property.
	 * 
	 * @return
	 *         possible object is
	 *         {@link String }
	 */
	public String getNewAttribute(){
		return newAttribute;
	}

	/**
	 * Sets the value of the newAttribute property.
	 * 
	 * @param value
	 *        allowed object is
	 *        {@link String }
	 */
	public void setNewAttribute(String value){
		this.newAttribute = value;
	}
}
