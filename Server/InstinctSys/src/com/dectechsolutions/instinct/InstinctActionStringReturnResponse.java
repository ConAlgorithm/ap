
package com.dectechsolutions.instinct;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InstinctActionStringReturnResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InstinctActionStringReturnResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="InstinctActionStringReturnResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InstinctActionStringReturnResponse", propOrder = {
    "instinctActionStringReturnResult"
})
public class InstinctActionStringReturnResponse 
{

    @XmlElement(name = "InstinctActionStringReturnResult")
    protected String instinctActionStringReturnResult;

    /**
     * Gets the value of the instinctActionStringReturnResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstinctActionStringReturnResult() 
    {
        return instinctActionStringReturnResult;
    }

    /**
     * Sets the value of the instinctActionStringReturnResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstinctActionStringReturnResult(String value) 
    {
        this.instinctActionStringReturnResult = value;
    }

}
