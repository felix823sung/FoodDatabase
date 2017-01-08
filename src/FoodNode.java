/**
 * The custom FoodNode, a specified Node for a Food object
 * @author Felix Sung and Jonathan Sun
 *
 */
public class FoodNode
{
	private Food item;
	private FoodNode next;

	public FoodNode(Food item)
	{
		this.item = item;
	}

	public FoodNode getNext()
	{
		return this.next;
	}

	public void setNext(FoodNode next)
	{
		this.next = next;
	}

	public Food getItem()
	{
		return this.item;
	}
}