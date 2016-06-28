/**
 * WapPushService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.djs.test.wsclient.wap_push;

public interface WapPushService extends javax.xml.rpc.Service {
    public java.lang.String getExtWapPushAddress();

    public org.csapi.www.wsdl.parlayx.singtel.ndp.ext.wappush.v1_0._interface.ExtWapPush getExtWapPush() throws javax.xml.rpc.ServiceException;

    public org.csapi.www.wsdl.parlayx.singtel.ndp.ext.wappush.v1_0._interface.ExtWapPush getExtWapPush(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
