
/**
 * PolicyException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */

package com.djs.test.wsclient.wap_push;

public class PolicyException extends java.lang.Exception{

    private static final long serialVersionUID = 1320118242104L;
    
    private com.djs.test.wsclient.wap_push.WapPushServiceStub.PolicyExceptionE faultMessage;

    
        public PolicyException() {
            super("PolicyException");
        }

        public PolicyException(java.lang.String s) {
           super(s);
        }

        public PolicyException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public PolicyException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.djs.test.wsclient.wap_push.WapPushServiceStub.PolicyExceptionE msg){
       faultMessage = msg;
    }
    
    public com.djs.test.wsclient.wap_push.WapPushServiceStub.PolicyExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    