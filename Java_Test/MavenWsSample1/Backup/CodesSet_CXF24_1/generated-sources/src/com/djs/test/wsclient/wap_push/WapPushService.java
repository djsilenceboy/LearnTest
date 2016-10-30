package com.djs.learn.wsclient.wap_push;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.4.4
 * 2011-11-10T11:36:23.314+08:00
 * Generated source version: 2.4.4
 * 
 */
@WebServiceClient(name = "WapPushService", 
                  wsdlLocation = "file:/D:/WorkDJS/Test/JEE_Test/MavenWsSample1/src/main/wsdl/ndp_ext_parlayx_wappush_service_1_0.wsdl",
                  targetNamespace = "http://www.csapi.org/wsdl/parlayx/singtel/ndp/ext/wappush/v1_0/service") 
public class WapPushService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.csapi.org/wsdl/parlayx/singtel/ndp/ext/wappush/v1_0/service", "WapPushService");
    public final static QName ExtWapPush = new QName("http://www.csapi.org/wsdl/parlayx/singtel/ndp/ext/wappush/v1_0/service", "ExtWapPush");
    static {
        URL url = null;
        try {
            url = new URL("file:/D:/WorkDJS/Test/JEE_Test/MavenWsSample1/src/main/wsdl/ndp_ext_parlayx_wappush_service_1_0.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(WapPushService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/D:/WorkDJS/Test/JEE_Test/MavenWsSample1/src/main/wsdl/ndp_ext_parlayx_wappush_service_1_0.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public WapPushService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public WapPushService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WapPushService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns ExtWapPush
     */
    @WebEndpoint(name = "ExtWapPush")
    public ExtWapPush getExtWapPush() {
        return super.getPort(ExtWapPush, ExtWapPush.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ExtWapPush
     */
    @WebEndpoint(name = "ExtWapPush")
    public ExtWapPush getExtWapPush(WebServiceFeature... features) {
        return super.getPort(ExtWapPush, ExtWapPush.class, features);
    }

}
