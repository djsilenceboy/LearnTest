/**
 * SendDataServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.djs.test.wsclient.example_test;

public class SendDataServiceLocator extends org.apache.axis.client.Service implements com.djs.test.wsclient.example_test.SendDataService {

    public SendDataServiceLocator() {
    }


    public SendDataServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SendDataServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SendDataPort
    private java.lang.String SendDataPort_address = "http://localhost:7001/example_test/services/SendData";

    public java.lang.String getSendDataPortAddress() {
        return SendDataPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SendDataPortWSDDServiceName = "SendDataPort";

    public java.lang.String getSendDataPortWSDDServiceName() {
        return SendDataPortWSDDServiceName;
    }

    public void setSendDataPortWSDDServiceName(java.lang.String name) {
        SendDataPortWSDDServiceName = name;
    }

    public com.singtel.ndp.ext.example_test.v1_0._interface.SendDataPort getSendDataPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SendDataPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSendDataPort(endpoint);
    }

    public com.singtel.ndp.ext.example_test.v1_0._interface.SendDataPort getSendDataPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.djs.test.wsclient.example_test.SendDataBindingStub _stub = new com.djs.test.wsclient.example_test.SendDataBindingStub(portAddress, this);
            _stub.setPortName(getSendDataPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSendDataPortEndpointAddress(java.lang.String address) {
        SendDataPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.singtel.ndp.ext.example_test.v1_0._interface.SendDataPort.class.isAssignableFrom(serviceEndpointInterface)) {
                com.djs.test.wsclient.example_test.SendDataBindingStub _stub = new com.djs.test.wsclient.example_test.SendDataBindingStub(new java.net.URL(SendDataPort_address), this);
                _stub.setPortName(getSendDataPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SendDataPort".equals(inputPortName)) {
            return getSendDataPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://singtel.com/ndp/ext/example_test/v1_0/service", "SendDataService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://singtel.com/ndp/ext/example_test/v1_0/service", "SendDataPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SendDataPort".equals(portName)) {
            setSendDataPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
