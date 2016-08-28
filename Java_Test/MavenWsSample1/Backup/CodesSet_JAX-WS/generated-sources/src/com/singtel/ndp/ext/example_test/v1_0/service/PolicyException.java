
package com.singtel.ndp.ext.example_test.v1_0.service;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-b02-
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "PolicyException", targetNamespace = "http://www.csapi.org/schema/parlayx/common/v2_1")
public class PolicyException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private org.csapi.schema.parlayx.common.v2_1.PolicyException faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public PolicyException(String message, org.csapi.schema.parlayx.common.v2_1.PolicyException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public PolicyException(String message, org.csapi.schema.parlayx.common.v2_1.PolicyException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: org.csapi.schema.parlayx.common.v2_1.PolicyException
     */
    public org.csapi.schema.parlayx.common.v2_1.PolicyException getFaultInfo() {
        return faultInfo;
    }

}