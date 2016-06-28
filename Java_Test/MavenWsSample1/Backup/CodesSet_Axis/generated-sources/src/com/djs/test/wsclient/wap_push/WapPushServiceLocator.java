/**
 * WapPushServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.djs.test.wsclient.wap_push;

public class WapPushServiceLocator extends org.apache.axis.client.Service implements com.djs.test.wsclient.wap_push.WapPushService {

    public WapPushServiceLocator() {
    }


    public WapPushServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WapPushServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ExtWapPush
    private java.lang.String ExtWapPush_address = "http://localhost:9080/WapPushService/services/wappush";

    public java.lang.String getExtWapPushAddress() {
        return ExtWapPush_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ExtWapPushWSDDServiceName = "ExtWapPush";

    public java.lang.String getExtWapPushWSDDServiceName() {
        return ExtWapPushWSDDServiceName;
    }

    public void setExtWapPushWSDDServiceName(java.lang.String name) {
        ExtWapPushWSDDServiceName = name;
    }

    public org.csapi.www.wsdl.parlayx.singtel.ndp.ext.wappush.v1_0._interface.ExtWapPush getExtWapPush() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ExtWapPush_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getExtWapPush(endpoint);
    }

    public org.csapi.www.wsdl.parlayx.singtel.ndp.ext.wappush.v1_0._interface.ExtWapPush getExtWapPush(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.djs.test.wsclient.wap_push.SendWapPushBindingStub _stub = new com.djs.test.wsclient.wap_push.SendWapPushBindingStub(portAddress, this);
            _stub.setPortName(getExtWapPushWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setExtWapPushEndpointAddress(java.lang.String address) {
        ExtWapPush_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.csapi.www.wsdl.parlayx.singtel.ndp.ext.wappush.v1_0._interface.ExtWapPush.class.isAssignableFrom(serviceEndpointInterface)) {
                com.djs.test.wsclient.wap_push.SendWapPushBindingStub _stub = new com.djs.test.wsclient.wap_push.SendWapPushBindingStub(new java.net.URL(ExtWapPush_address), this);
                _stub.setPortName(getExtWapPushWSDDServiceName());
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
        if ("ExtWapPush".equals(inputPortName)) {
            return getExtWapPush();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.csapi.org/wsdl/parlayx/singtel/ndp/ext/wappush/v1_0/service", "WapPushService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.csapi.org/wsdl/parlayx/singtel/ndp/ext/wappush/v1_0/service", "ExtWapPush"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ExtWapPush".equals(portName)) {
            setExtWapPushEndpointAddress(address);
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
