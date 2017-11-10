
package com.dectechsolutions.instinct;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InstinctActionXMLStringReturnResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InstinctActionXMLStringReturnResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="InstinctActionXMLStringReturnResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InstinctActionXMLStringReturnResponse", propOrder = {
    "instinctActionXMLStringReturnResult"
})
public class InstinctActionXMLStringReturnResponse 
{

    @XmlElement(name = "InstinctActionXMLStringReturnResult")
    protected String instinctActionXMLStringReturnResult;

    /**
     * Gets the value of the instinctActionXMLStringReturnResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstinctActionXMLStringReturnResult() 
    {
        return instinctActionXMLStringReturnResult;
    }

    /**
     * Sets the value of the instinctActionXMLStringReturnResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstinctActionXMLStringReturnResult(String value) 
    {
        this.instinctActionXMLStringReturnResult = value;
    }

}
