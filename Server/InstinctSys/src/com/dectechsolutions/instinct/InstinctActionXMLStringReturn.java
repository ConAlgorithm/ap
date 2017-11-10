
package com.dectechsolutions.instinct;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InstinctActionXMLStringReturn complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InstinctActionXMLStringReturn"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="actionString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InstinctActionXMLStringReturn", propOrder = {
    "actionString"
})
public class InstinctActionXMLStringReturn 
{

    protected String actionString;

    /**
     * Gets the value of the actionString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionString() 
    {
        return actionString;
    }

    /**
     * Sets the value of the actionString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionString(String value) 
    {
        this.actionString = value;
    }

}
