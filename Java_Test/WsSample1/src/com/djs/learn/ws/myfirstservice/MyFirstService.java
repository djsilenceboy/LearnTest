package com.djs.learn.ws.myfirstservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.2.12
 * Thu Aug 22 10:43:31 SGT 2013
 * Generated source version: 2.2.12
 * 
 */
 
@WebService(targetNamespace = "http://www.example.org/MyFirstService/", name = "MyFirstService")
@XmlSeeAlso({ObjectFactory.class})
public interface MyFirstService {

    @WebResult(name = "out", targetNamespace = "")
    @RequestWrapper(localName = "NewOperation", targetNamespace = "http://www.example.org/MyFirstService/", className = "com.djs.ws.myfirstservice.NewOperation")
    @WebMethod(operationName = "NewOperation", action = "http://www.example.org/MyFirstService/NewOperation")
    @ResponseWrapper(localName = "NewOperationResponse", targetNamespace = "http://www.example.org/MyFirstService/", className = "com.djs.ws.myfirstservice.NewOperationResponse")
    public java.lang.String newOperation(
        @WebParam(name = "in", targetNamespace = "")
        java.lang.String in
    );
}