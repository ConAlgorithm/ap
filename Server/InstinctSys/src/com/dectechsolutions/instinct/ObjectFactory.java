
package com.dectechsolutions.instinct;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.dectechsolutions.instinct package. 
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
public class ObjectFactory 
{

    private static final QName _InstinctActionStringReturn_QNAME = new QName("http://dectechsolutions.com/Instinct", "InstinctActionStringReturn");
    private static final QName _InstinctActionStringReturnResponse_QNAME = new QName("http://dectechsolutions.com/Instinct", "InstinctActionStringReturnResponse");
    private static final QName _InstinctActionXMLStringReturn_QNAME = new QName("http://dectechsolutions.com/Instinct", "InstinctActionXMLStringReturn");
    private static final QName _InstinctActionXMLStringReturnResponse_QNAME = new QName("http://dectechsolutions.com/Instinct", "InstinctActionXMLStringReturnResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.dectechsolutions.instinct.
     * 
     */
    public ObjectFactory() 
    {
    }

    /**
     * Create an instance of {@link InstinctActionStringReturn }.
     * @return InstinctActionStringReturn 
     * 
     */
    public InstinctActionStringReturn createInstinctActionStringReturn() 
    {
        return new InstinctActionStringReturn();
    }

    /**
     * Create an instance of {@link InstinctActionStringReturnResponse }.
     * @return InstinctActionStringReturnResponse 
     * 
     */
    public InstinctActionStringReturnResponse createInstinctActionStringReturnResponse() 
    {
        return new InstinctActionStringReturnResponse();
    }

    /**
     * Create an instance of {@link InstinctActionXMLStringReturn }.
     * @return InstinctActionXMLStringReturn
     * 
     */
    public InstinctActionXMLStringReturn createInstinctActionXMLStringReturn() 
    {
        return new InstinctActionXMLStringReturn();
    }

    /**
     * Create an instance of {@link InstinctActionXMLStringReturnResponse }.
     * @return InstinctActionXMLStringReturnResponse
     * 
     */
    public InstinctActionXMLStringReturnResponse createInstinctActionXMLStringReturnResponse() 
    {
        return new InstinctActionXMLStringReturnResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstinctActionStringReturn }{@code >}}.
     * @param value 
     * @return JAXBElement<InstinctActionStringReturn>
     * 
     */
    @XmlElementDecl(namespace = "http://dectechsolutions.com/Instinct", name = "InstinctActionStringReturn")
    public JAXBElement<InstinctActionStringReturn> createInstinctActionStringReturn(InstinctActionStringReturn value) 
    {
        return new JAXBElement<InstinctActionStringReturn>(_InstinctActionStringReturn_QNAME, InstinctActionStringReturn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstinctActionStringReturnResponse }{@code >}}.
     * @param value 
     * @return JAXBElement<InstinctActionStringReturnResponse>
     * 
     */
    @XmlElementDecl(namespace = "http://dectechsolutions.com/Instinct", name = "InstinctActionStringReturnResponse")
    public JAXBElement<InstinctActionStringReturnResponse> createInstinctActionStringReturnResponse(InstinctActionStringReturnResponse value) 
    {
        return new JAXBElement<InstinctActionStringReturnResponse>(_InstinctActionStringReturnResponse_QNAME, InstinctActionStringReturnResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstinctActionXMLStringReturn }{@code >}}.
     * @param value 
     * @return JAXBElement<InstinctActionXMLStringReturn> 
     * 
     */
    @XmlElementDecl(namespace = "http://dectechsolutions.com/Instinct", name = "InstinctActionXMLStringReturn")
    public JAXBElement<InstinctActionXMLStringReturn> createInstinctActionXMLStringReturn(InstinctActionXMLStringReturn value) 
    {
        return new JAXBElement<InstinctActionXMLStringReturn>(_InstinctActionXMLStringReturn_QNAME, InstinctActionXMLStringReturn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstinctActionXMLStringReturnResponse }{@code >}}.
     * @param value 
     * @return JAXBElement<InstinctActionXMLStringReturnResponse> 
     * 
     */
    @XmlElementDecl(namespace = "http://dectechsolutions.com/Instinct", name = "InstinctActionXMLStringReturnResponse")
    public JAXBElement<InstinctActionXMLStringReturnResponse> createInstinctActionXMLStringReturnResponse(InstinctActionXMLStringReturnResponse value) 
    {
        return new JAXBElement<InstinctActionXMLStringReturnResponse>(_InstinctActionXMLStringReturnResponse_QNAME, InstinctActionXMLStringReturnResponse.class, null, value);
    }

}
