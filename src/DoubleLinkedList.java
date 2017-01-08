/**
 * A DoubleLinkedList class using custom FoodNodes
 * @author Felix Sung and Jonathan Sun
 *
 */
public class DoubleLinkedList
{
	//Initialize variables
	private FoodNode head;
	private FoodNode tail;
	private int size=0;

	/**
	 * The constructor for the DoubleLinkedList object
	 */
	DoubleLinkedList()
	{
		this.head = null;
	}
	/**
	 * A method that allows the user to get the head of the DoubleLinkedList
	 * @return a FoodNode, the head of the list
	 */
	public FoodNode getHead()
	{
		return head;
	}

	/**
	 * An add method, used to add additional Food objects to the list
	 * @param item
	 */
	public void add(Food item)
	{
		if (head == null)
		{
			head = new FoodNode(item);
			tail = head;
		}
		else
		{
			tail.setNext(new FoodNode(item));
			tail = tail.getNext();
		}
		size++;
	}
	/**
	 * A method to determine the current size of the list
	 * @return the size of the list
	 */
	public int size()
	{
		return size;
	}

}
