/**
 * SendDataPort.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.singtel.ndp.ext.example_test.v1_0._interface;

public interface SendDataPort extends java.rmi.Remote {
    public com.singtel.schema.example_test.local.SendDataResponse sendData(com.singtel.schema.example_test.local.SendDataRequest parameters) throws java.rmi.RemoteException, org.csapi.www.schema.parlayx.common.v2_1.PolicyException, org.csapi.www.schema.parlayx.common.v2_1.ServiceException;
}
