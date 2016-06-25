
package com.bea.wsdl.wlcp.wlng.session_manager.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-b02-
 * Generated source version: 2.1
 * 
 */
@WebService(name = "SessionManager", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SessionManager {


    /**
     * 
     * @return
     *     returns java.lang.String
     * @throws GeneralException_Exception
     */
    @WebMethod
    @WebResult(name = "getSessionReturn", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service")
    @RequestWrapper(localName = "getSession", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service", className = "com.bea.wsdl.wlcp.wlng.session_manager.service.GetSession")
    @ResponseWrapper(localName = "getSessionResponse", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service", className = "com.bea.wsdl.wlcp.wlng.session_manager.service.GetSessionResponse")
    public String getSession()
        throws GeneralException_Exception
    ;

    /**
     * 
     * @param sessionId
     * @param newPassword
     * @param oldPassword
     * @throws GeneralException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "changeApplicationPassword", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service", className = "com.bea.wsdl.wlcp.wlng.session_manager.service.ChangeApplicationPassword")
    @ResponseWrapper(localName = "changeApplicationPasswordResponse", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service", className = "com.bea.wsdl.wlcp.wlng.session_manager.service.ChangeApplicationPasswordResponse")
    public void changeApplicationPassword(
        @WebParam(name = "sessionId", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service")
        String sessionId,
        @WebParam(name = "oldPassword", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service")
        String oldPassword,
        @WebParam(name = "newPassword", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service")
        String newPassword)
        throws GeneralException_Exception
    ;

    /**
     * 
     * @param sessionId
     * @return
     *     returns int
     * @throws GeneralException_Exception
     */
    @WebMethod
    @WebResult(name = "getSessionRemainingLifeTimeReturn", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service")
    @RequestWrapper(localName = "getSessionRemainingLifeTime", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service", className = "com.bea.wsdl.wlcp.wlng.session_manager.service.GetSessionRemainingLifeTime")
    @ResponseWrapper(localName = "getSessionRemainingLifeTimeResponse", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service", className = "com.bea.wsdl.wlcp.wlng.session_manager.service.GetSessionRemainingLifeTimeResponse")
    public int getSessionRemainingLifeTime(
        @WebParam(name = "sessionId", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service")
        String sessionId)
        throws GeneralException_Exception
    ;

    /**
     * 
     * @param sessionId
     * @return
     *     returns java.lang.String
     * @throws GeneralException_Exception
     */
    @WebMethod
    @WebResult(name = "refreshSessionReturn", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service")
    @RequestWrapper(localName = "refreshSession", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service", className = "com.bea.wsdl.wlcp.wlng.session_manager.service.RefreshSession")
    @ResponseWrapper(localName = "refreshSessionResponse", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service", className = "com.bea.wsdl.wlcp.wlng.session_manager.service.RefreshSessionResponse")
    public String refreshSession(
        @WebParam(name = "sessionId", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service")
        String sessionId)
        throws GeneralException_Exception
    ;

    /**
     * 
     * @param sessionId
     * @return
     *     returns boolean
     * @throws GeneralException_Exception
     */
    @WebMethod
    @WebResult(name = "destroySessionReturn", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service")
    @RequestWrapper(localName = "destroySession", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service", className = "com.bea.wsdl.wlcp.wlng.session_manager.service.DestroySession")
    @ResponseWrapper(localName = "destroySessionResponse", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service", className = "com.bea.wsdl.wlcp.wlng.session_manager.service.DestroySessionResponse")
    public boolean destroySession(
        @WebParam(name = "sessionId", targetNamespace = "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service")
        String sessionId)
        throws GeneralException_Exception
    ;

}
