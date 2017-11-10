package instinct.service.client;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.dectechsolutions.client package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory
{

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.dectechsolutions.client.
	 * 
	 */
	public ObjectFactory() 
	{
	}

	/**
	 * Create an instance of {@link InstinctFraudCheckString }.
	 * @return InstinctFraudCheckString 
	 * 
	 */
	public InstinctFraudCheckString createInstinctFraudCheckString() 
	{
		return new InstinctFraudCheckString();
	}

	/**
	 * Create an instance of {@link SubmitApplicationString }.
	 * @return SubmitApplicationString 
	 * 
	 */
	public SubmitApplicationString createSubmitApplicationString() 
	{
		return new SubmitApplicationString();
	}

	/**
	 * Create an instance of {@link InstinctFraudCheckStringResponse }.
	 * @return InstinctFraudCheckStringResponse 
	 * 
	 */
	public InstinctFraudCheckStringResponse createInstinctFraudCheckStringResponse() 
	{
		return new InstinctFraudCheckStringResponse();
	}

	/**
	 * Create an instance of {@link SubmitApplicationStringResponse }.
	 * @return SubmitApplicationStringResponse 
	 * 
	 */
	public SubmitApplicationStringResponse createSubmitApplicationStringResponse() 
	{
		return new SubmitApplicationStringResponse();
	}

	/**
	 * Create an instance of {@link InstinctFraudCheckXMLStringResponse }.
	 * @return InstinctFraudCheckXMLStringResponse 
	 * 
	 */
	public InstinctFraudCheckXMLStringResponse createInstinctFraudCheckXMLStringResponse() 
	{
		return new InstinctFraudCheckXMLStringResponse();
	}

	/**
	 * Create an instance of {@link InstinctFraudCheckXMLString }.
	 * @return InstinctFraudCheckXMLString 
	 * 
	 */
	public InstinctFraudCheckXMLString createInstinctFraudCheckXMLString() 
	{
		return new InstinctFraudCheckXMLString();
	}

}
