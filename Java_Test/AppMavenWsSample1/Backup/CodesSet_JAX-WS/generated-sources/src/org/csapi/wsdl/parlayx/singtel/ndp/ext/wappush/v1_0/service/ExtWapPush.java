
package org.csapi.wsdl.parlayx.djs.sample.ext.wappush.v1_0.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import org.csapi.schema.parlayx.djs.sample.ext.wappush.v1_0.WapPushReqData;
import org.csapi.schema.parlayx.djs.sample.ext.wappush.v1_0.WapPushRespData;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-b02-
 * Generated source version: 2.1
 * 
 */
@WebService(name = "ExtWapPush", targetNamespace = "http://www.csapi.org/wsdl/parlayx/djs/sample/ext/wappush/v1_0/interface")
@XmlSeeAlso({
    org.csapi.schema.parlayx.djs.sample.ext.wappush.v1_0.local.ObjectFactory.class,
    org.csapi.schema.parlayx.djs.sample.ext.wappush.v1_0.ObjectFactory.class,
    org.csapi.schema.parlayx.common.v2_1.ObjectFactory.class
})
public interface ExtWapPush {


    /**
     * 
     * @param wapPushRequest
     * @return
     *     returns org.csapi.schema.parlayx.djs.sample.ext.wappush.v1_0.WapPushRespData
     * @throws ServiceException
     * @throws PolicyException
     */
    @WebMethod
    @WebResult(name = "wapPushResponse", targetNamespace = "http://www.csapi.org/schema/parlayx/djs/sample/ext/wappush/v1_0/local")
    @RequestWrapper(localName = "sendWapPush", targetNamespace = "http://www.csapi.org/schema/parlayx/djs/sample/ext/wappush/v1_0/local", className = "org.csapi.schema.parlayx.djs.sample.ext.wappush.v1_0.local.SendWapPush")
    @ResponseWrapper(localName = "sendWapPushResponse", targetNamespace = "http://www.csapi.org/schema/parlayx/djs/sample/ext/wappush/v1_0/local", className = "org.csapi.schema.parlayx.djs.sample.ext.wappush.v1_0.local.SendWapPushResponse")
    public WapPushRespData sendWapPush(
        @WebParam(name = "wapPushRequest", targetNamespace = "http://www.csapi.org/schema/parlayx/djs/sample/ext/wappush/v1_0/local")
        WapPushReqData wapPushRequest)
        throws PolicyException, ServiceException
    ;

}
