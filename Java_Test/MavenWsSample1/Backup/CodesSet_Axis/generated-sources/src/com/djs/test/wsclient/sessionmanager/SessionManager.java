/**
 * SessionManager.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.djs.test.wsclient.sessionmanager;

public interface SessionManager extends java.rmi.Remote {
    public java.lang.String getSession() throws java.rmi.RemoteException, com.djs.test.wsclient.sessionmanager.GeneralException;
    public void changeApplicationPassword(java.lang.String sessionId, java.lang.String oldPassword, java.lang.String newPassword) throws java.rmi.RemoteException, com.djs.test.wsclient.sessionmanager.GeneralException;
    public int getSessionRemainingLifeTime(java.lang.String sessionId) throws java.rmi.RemoteException, com.djs.test.wsclient.sessionmanager.GeneralException;
    public java.lang.String refreshSession(java.lang.String sessionId) throws java.rmi.RemoteException, com.djs.test.wsclient.sessionmanager.GeneralException;
    public boolean destroySession(java.lang.String sessionId) throws java.rmi.RemoteException, com.djs.test.wsclient.sessionmanager.GeneralException;
}
