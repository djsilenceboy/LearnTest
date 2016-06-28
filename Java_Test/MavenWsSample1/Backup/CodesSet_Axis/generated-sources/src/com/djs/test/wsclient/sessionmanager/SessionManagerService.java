/**
 * SessionManagerService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.djs.test.wsclient.sessionmanager;

public interface SessionManagerService extends javax.xml.rpc.Service {
    public java.lang.String getSessionManagerAddress();

    public com.djs.test.wsclient.sessionmanager.SessionManager getSessionManager() throws javax.xml.rpc.ServiceException;

    public com.djs.test.wsclient.sessionmanager.SessionManager getSessionManager(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
