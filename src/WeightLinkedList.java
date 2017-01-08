/**
 * A custom list, uses custom WeightNodes
 * @author Felix Sung and Jonathan Sun
 *
 */
public class WeightLinkedList
{
	//Initialize variables
	private WeightNode head;
	private WeightNode tail;

	/**
	 * The constructor for the WeightLinkedList object
	 */
	WeightLinkedList()
	{
		this.head = null;
	}

	/**
	 * A method that gets the head of the list
	 * @return the WeightNode that is the head of the list
	 */
	public WeightNode getHead()
	{
		return head;
	}

	/**
	 * An add method that adds a WeightNode to the list
	 * @param amount the modifier for the description
	 * @param desc the description of the measurement
	 * @param weight the weight of the measurement in grams
	 */
	public void add(String amount, String desc, String weight)
	{
		if (head == null)
		{
			head = new WeightNode(amount, desc, weight);
			tail=head;
		}
		else
		{
			tail.setNext(new WeightNode(amount, desc, weight));
			tail=tail.getNext();
		}
	}
}
