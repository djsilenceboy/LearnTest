package com.djs.myfirstservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.6
 * 2013-08-26T23:17:24.563+08:00
 * Generated source version: 2.7.6
 * 
 */
@WebService(targetNamespace = "http://www.example.org/MyServices/", name = "MyFirstService")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface MyFirstService {

    @WebResult(name = "NewOperationResponse", targetNamespace = "http://www.example.org/MyServices/", partName = "parameters")
    @WebMethod(operationName = "NewOperation", action = "http://www.example.org/MyFirstService/NewOperation")
    public NewOperationResponse newOperation(
        @WebParam(partName = "parameters", name = "NewOperation", targetNamespace = "http://www.example.org/MyServices/")
        NewOperation parameters
    );

    @WebResult(name = "YouOperationResponse", targetNamespace = "http://www.example.org/MyServices/", partName = "parameters")
    @WebMethod(operationName = "YouOperation", action = "http://www.example.org/MyFirstService/YouOperation")
    public YouOperationResponse youOperation(
        @WebParam(partName = "parameters", name = "YouOperation", targetNamespace = "http://www.example.org/MyServices/")
        YouOperation parameters
    ) throws YouOperationFault_Exception;

    @WebResult(name = "MyOperationResponse", targetNamespace = "http://www.example.org/MyServices/", partName = "parameters")
    @WebMethod(operationName = "MyOperation", action = "http://www.example.org/MyFirstService/MyOperation")
    public MyOperationResponse myOperation(
        @WebParam(partName = "parameters", name = "MyOperation", targetNamespace = "http://www.example.org/MyServices/")
        MyOperation parameters
    );
}
