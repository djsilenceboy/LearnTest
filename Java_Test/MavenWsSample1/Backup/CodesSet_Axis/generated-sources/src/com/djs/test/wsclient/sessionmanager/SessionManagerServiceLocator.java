/**
 * SessionManagerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.djs.learn.wsclient.sessionmanager;

public class SessionManagerServiceLocator extends org.apache.axis.client.Service implements com.djs.learn.wsclient.sessionmanager.SessionManagerService {

    public SessionManagerServiceLocator() {
    }


    public SessionManagerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SessionManagerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SessionManager
    private java.lang.String SessionManager_address = "http://localhost:6001/session_manager/SessionManager";

    public java.lang.String getSessionManagerAddress() {
        return SessionManager_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SessionManagerWSDDServiceName = "SessionManager";

    public java.lang.String getSessionManagerWSDDServiceName() {
        return SessionManagerWSDDServiceName;
    }

    public void setSessionManagerWSDDServiceName(java.lang.String name) {
        SessionManagerWSDDServiceName = name;
    }

    public com.djs.learn.wsclient.sessionmanager.SessionManager getSessionManager() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SessionManager_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSessionManager(endpoint);
    }

    public com.djs.learn.wsclient.sessionmanager.SessionManager getSessionManager(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.djs.learn.wsclient.sessionmanager.SessionManagerSoapBindingStub _stub = new com.djs.learn.wsclient.sessionmanager.SessionManagerSoapBindingStub(portAddress, this);
            _stub.setPortName(getSessionManagerWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSessionManagerEndpointAddress(java.lang.String address) {
        SessionManager_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.djs.learn.wsclient.sessionmanager.SessionManager.class.isAssignableFrom(serviceEndpointInterface)) {
                com.djs.learn.wsclient.sessionmanager.SessionManagerSoapBindingStub _stub = new com.djs.learn.wsclient.sessionmanager.SessionManagerSoapBindingStub(new java.net.URL(SessionManager_address), this);
                _stub.setPortName(getSessionManagerWSDDServiceName());
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
        if ("SessionManager".equals(inputPortName)) {
            return getSessionManager();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.bea.com/wsdl/wlcp/wlng/session_manager/service", "SessionManagerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.bea.com/wsdl/wlcp/wlng/session_manager/service", "SessionManager"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SessionManager".equals(portName)) {
            setSessionManagerEndpointAddress(address);
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
