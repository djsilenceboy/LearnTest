/**
 * SendDataService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.djs.test.wsclient.example_test;

public interface SendDataService extends javax.xml.rpc.Service {
    public java.lang.String getSendDataPortAddress();

    public com.singtel.ndp.ext.example_test.v1_0._interface.SendDataPort getSendDataPort() throws javax.xml.rpc.ServiceException;

    public com.singtel.ndp.ext.example_test.v1_0._interface.SendDataPort getSendDataPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
