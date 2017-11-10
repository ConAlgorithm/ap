package instinct.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SubmitApplication_StringResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "submitApplicationStringResult" })
@XmlRootElement(name = "SubmitApplication_StringResponse")
public class SubmitApplicationStringResponse 
{

	@XmlElement(name = "SubmitApplication_StringResult")
	protected String submitApplicationStringResult;

	/**
	 * Gets the value of the submitApplicationStringResult property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSubmitApplicationStringResult() 
	{
		return submitApplicationStringResult;
	}

	/**
	 * Sets the value of the submitApplicationStringResult property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSubmitApplicationStringResult(String value) 
	{
		this.submitApplicationStringResult = value;
	}

}
