
/**
 * GeneralException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */

package com.djs.test.wsclient.sessionmanager;

public class GeneralException extends java.lang.Exception{

    private static final long serialVersionUID = 1320118240528L;
    
    private com.djs.test.wsclient.sessionmanager.SessionManagerServiceStub.Fault faultMessage;

    
        public GeneralException() {
            super("GeneralException");
        }

        public GeneralException(java.lang.String s) {
           super(s);
        }

        public GeneralException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public GeneralException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.djs.test.wsclient.sessionmanager.SessionManagerServiceStub.Fault msg){
       faultMessage = msg;
    }
    
    public com.djs.test.wsclient.sessionmanager.SessionManagerServiceStub.Fault getFaultMessage(){
       return faultMessage;
    }
}
    