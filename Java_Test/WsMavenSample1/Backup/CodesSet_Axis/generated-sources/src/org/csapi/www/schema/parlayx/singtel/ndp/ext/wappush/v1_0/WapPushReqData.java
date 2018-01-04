/**
 * WapPushReqData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.parlayx.singtel.ndp.ext.wappush.v1_0;

public class WapPushReqData  implements java.io.Serializable {
    private org.apache.axis.types.URI[] addresses;

    private java.math.BigInteger pushType;

    private java.lang.String url;

    private java.lang.String message;

    private java.util.Calendar deliveryTimestamp;

    public WapPushReqData() {
    }

    public WapPushReqData(
           org.apache.axis.types.URI[] addresses,
           java.math.BigInteger pushType,
           java.lang.String url,
           java.lang.String message,
           java.util.Calendar deliveryTimestamp) {
           this.addresses = addresses;
           this.pushType = pushType;
           this.url = url;
           this.message = message;
           this.deliveryTimestamp = deliveryTimestamp;
    }


    /**
     * Gets the addresses value for this WapPushReqData.
     * 
     * @return addresses
     */
    public org.apache.axis.types.URI[] getAddresses() {
        return addresses;
    }


    /**
     * Sets the addresses value for this WapPushReqData.
     * 
     * @param addresses
     */
    public void setAddresses(org.apache.axis.types.URI[] addresses) {
        this.addresses = addresses;
    }

    public org.apache.axis.types.URI getAddresses(int i) {
        return this.addresses[i];
    }

    public void setAddresses(int i, org.apache.axis.types.URI _value) {
        this.addresses[i] = _value;
    }


    /**
     * Gets the pushType value for this WapPushReqData.
     * 
     * @return pushType
     */
    public java.math.BigInteger getPushType() {
        return pushType;
    }


    /**
     * Sets the pushType value for this WapPushReqData.
     * 
     * @param pushType
     */
    public void setPushType(java.math.BigInteger pushType) {
        this.pushType = pushType;
    }


    /**
     * Gets the url value for this WapPushReqData.
     * 
     * @return url
     */
    public java.lang.String getUrl() {
        return url;
    }


    /**
     * Sets the url value for this WapPushReqData.
     * 
     * @param url
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }


    /**
     * Gets the message value for this WapPushReqData.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this WapPushReqData.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the deliveryTimestamp value for this WapPushReqData.
     * 
     * @return deliveryTimestamp
     */
    public java.util.Calendar getDeliveryTimestamp() {
        return deliveryTimestamp;
    }


    /**
     * Sets the deliveryTimestamp value for this WapPushReqData.
     * 
     * @param deliveryTimestamp
     */
    public void setDeliveryTimestamp(java.util.Calendar deliveryTimestamp) {
        this.deliveryTimestamp = deliveryTimestamp;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WapPushReqData)) return false;
        WapPushReqData other = (WapPushReqData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.addresses==null && other.getAddresses()==null) || 
             (this.addresses!=null &&
              java.util.Arrays.equals(this.addresses, other.getAddresses()))) &&
            ((this.pushType==null && other.getPushType()==null) || 
             (this.pushType!=null &&
              this.pushType.equals(other.getPushType()))) &&
            ((this.url==null && other.getUrl()==null) || 
             (this.url!=null &&
              this.url.equals(other.getUrl()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.deliveryTimestamp==null && other.getDeliveryTimestamp()==null) || 
             (this.deliveryTimestamp!=null &&
              this.deliveryTimestamp.equals(other.getDeliveryTimestamp())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAddresses() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAddresses());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAddresses(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPushType() != null) {
            _hashCode += getPushType().hashCode();
        }
        if (getUrl() != null) {
            _hashCode += getUrl().hashCode();
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getDeliveryTimestamp() != null) {
            _hashCode += getDeliveryTimestamp().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WapPushReqData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/singtel/ndp/ext/wappush/v1_0", "WapPushReqData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addresses");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addresses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pushType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pushType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url");
        elemField.setXmlName(new javax.xml.namespace.QName("", "url"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("", "message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryTimestamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deliveryTimestamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
