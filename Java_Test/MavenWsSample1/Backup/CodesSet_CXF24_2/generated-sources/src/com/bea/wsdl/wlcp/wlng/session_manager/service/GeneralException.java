
package com.bea.wsdl.wlcp.wlng.session_manager.service;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.4
 * 2011-11-10T10:21:49.493+08:00
 * Generated source version: 2.4.4
 */

@WebFault(name = "fault", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service")
public class GeneralException extends Exception {
    
    private com.bea.wsdl.wlcp.wlng.session_manager.service.Fault fault;

    public GeneralException() {
        super();
    }
    
    public GeneralException(String message) {
        super(message);
    }
    
    public GeneralException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralException(String message, com.bea.wsdl.wlcp.wlng.session_manager.service.Fault fault) {
        super(message);
        this.fault = fault;
    }

    public GeneralException(String message, com.bea.wsdl.wlcp.wlng.session_manager.service.Fault fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }

    public com.bea.wsdl.wlcp.wlng.session_manager.service.Fault getFaultInfo() {
        return this.fault;
    }
}
