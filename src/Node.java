/**
 * A Generic Node class
 * @author Felix Sung and Jonathan Sun
 *
 * @param <T> A Generic class
 */
public class Node<T>
{
	//Initialize variables
	private T item;
	private Node<T> next;

	/**
	 * The constructor of the Node object
	 * @param item the item to be stored in the node
	 */
	public Node(T item)
	{
		this.item=item;
	}
	/**
	 * A method that gets the next linked node of the current node
	 * @return the next linked node
	 */
	public Node<T> getNext()
	{
		return this.next;
	}

	/**
	 * A method that sets the next linked node
	 * @param next the given node to be set to the next node
	 */
	public void setNext(Node<T> next)
	{
		this.next = next;
	}

	/**
	 * A method that gets the item stored in the node
	 * @return the item stored in the node
	 */
	public T getItem()
	{
		return this.item;
	}
}