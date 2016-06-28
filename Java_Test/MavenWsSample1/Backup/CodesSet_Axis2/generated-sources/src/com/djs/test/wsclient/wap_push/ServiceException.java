
/**
 * ServiceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */

package com.djs.test.wsclient.wap_push;

public class ServiceException extends java.lang.Exception{

    private static final long serialVersionUID = 1320118242120L;
    
    private com.djs.test.wsclient.wap_push.WapPushServiceStub.ServiceExceptionE faultMessage;

    
        public ServiceException() {
            super("ServiceException");
        }

        public ServiceException(java.lang.String s) {
           super(s);
        }

        public ServiceException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ServiceException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.djs.test.wsclient.wap_push.WapPushServiceStub.ServiceExceptionE msg){
       faultMessage = msg;
    }
    
    public com.djs.test.wsclient.wap_push.WapPushServiceStub.ServiceExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    