/**
 * The custom WeightNode class, stores three Strings
 * @author Felix
 *
 */
public class WeightNode
{
	// Initialize variables
	private String amount;
	private WeightNode next;
	private String msre_Desc;
	private String gm_Wgt;

	/**
	 * The constructor for the WeightNode, requires three parameters
	 * @param amount the modifier for the description
	 * @param msre_Desc the description of the measurement
	 * @param gm_Wgt the weight of the measurement in grams
	 */
	public WeightNode(String amount, String msre_Desc, String gm_Wgt)
	{
		this.amount = amount;
		this.msre_Desc = msre_Desc;
		this.gm_Wgt = gm_Wgt;
	}

	/**
	 * A method that gets the next linked node
	 * @return the next linked node
	 */
	public WeightNode getNext()
	{
		return this.next;
	}

	/**
	 * A method to set the next linked node
	 * @param next the WeightNode to be set as the next linked WeightNode
	 */
	public void setNext(WeightNode next)
	{
		this.next = next;
	}

	/**
	 * A method to get the amount aspect of the weight information
	 * @return the modifier for the measurement
	 */
	public String getAmount()
	{
		return amount;
	}

	/**
	 * A method to get the description aspect of the weight information
	 * @return the description of the measurement
	 */
	public String getMsre_Desc()
	{
		return msre_Desc;
	}

	/**
	 * A method to get the weight aspect of the weight information
	 * @return the weight of the measurement in grams
	 */
	public String getGm_Wgt()
	{
		return gm_Wgt;
	}

}