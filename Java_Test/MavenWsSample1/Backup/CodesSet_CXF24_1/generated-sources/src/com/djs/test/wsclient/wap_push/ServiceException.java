
package com.djs.learn.wsclient.wap_push;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.4
 * 2011-11-10T11:36:23.299+08:00
 * Generated source version: 2.4.4
 */

@WebFault(name = "ServiceException", targetNamespace = "http://www.csapi.org/schema/parlayx/common/v2_1")
public class ServiceException extends Exception {
    
    private org.csapi.schema.parlayx.common.v2_1.ServiceException serviceException;

    public ServiceException() {
        super();
    }
    
    public ServiceException(String message) {
        super(message);
    }
    
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message, org.csapi.schema.parlayx.common.v2_1.ServiceException serviceException) {
        super(message);
        this.serviceException = serviceException;
    }

    public ServiceException(String message, org.csapi.schema.parlayx.common.v2_1.ServiceException serviceException, Throwable cause) {
        super(message, cause);
        this.serviceException = serviceException;
    }

    public org.csapi.schema.parlayx.common.v2_1.ServiceException getFaultInfo() {
        return this.serviceException;
    }
}
