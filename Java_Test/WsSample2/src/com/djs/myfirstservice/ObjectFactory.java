
package com.djs.myfirstservice;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.djs.myfirstservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.djs.myfirstservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MyOperationResponse }
     * 
     */
    public MyOperationResponse createMyOperationResponse() {
        return new MyOperationResponse();
    }

    /**
     * Create an instance of {@link NewOperation }
     * 
     */
    public NewOperation createNewOperation() {
        return new NewOperation();
    }

    /**
     * Create an instance of {@link NewOperation2 }
     * 
     */
    public NewOperation2 createNewOperation2() {
        return new NewOperation2();
    }

    /**
     * Create an instance of {@link YouOperation }
     * 
     */
    public YouOperation createYouOperation() {
        return new YouOperation();
    }

    /**
     * Create an instance of {@link MyOperation }
     * 
     */
    public MyOperation createMyOperation() {
        return new MyOperation();
    }

    /**
     * Create an instance of {@link NewOperationResponse }
     * 
     */
    public NewOperationResponse createNewOperationResponse() {
        return new NewOperationResponse();
    }

    /**
     * Create an instance of {@link NewOperationResponse2 }
     * 
     */
    public NewOperationResponse2 createNewOperationResponse2() {
        return new NewOperationResponse2();
    }

    /**
     * Create an instance of {@link YouOperationResponse }
     * 
     */
    public YouOperationResponse createYouOperationResponse() {
        return new YouOperationResponse();
    }

    /**
     * Create an instance of {@link YouOperationFault }
     * 
     */
    public YouOperationFault createYouOperationFault() {
        return new YouOperationFault();
    }

}
