package com.singtel.ndp.ext.example_test.v1_0._interface;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.4.4
 * 2011-11-10T10:21:50.195+08:00
 * Generated source version: 2.4.4
 * 
 */
@WebService(targetNamespace = "http://singtel.com/ndp/ext/example_test/v1_0/interface", name = "SendDataPort")
@XmlSeeAlso({com.singtel.schema.example_test.local.ObjectFactory.class, org.csapi.schema.parlayx.common.v2_1.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SendDataPort {

    @WebResult(name = "sendDataResponse", targetNamespace = "http://singtel.com/schema/example_test/local", partName = "result")
    @WebMethod
    public com.singtel.schema.example_test.local.SendDataResponse sendData(
        @WebParam(partName = "parameters", name = "sendDataRequest", targetNamespace = "http://singtel.com/schema/example_test/local")
        com.singtel.schema.example_test.local.SendDataRequest parameters
    ) throws org.csapi.wsdl.parlayx.common.v2_0.faults.ServiceException, org.csapi.wsdl.parlayx.common.v2_0.faults.PolicyException;
}
