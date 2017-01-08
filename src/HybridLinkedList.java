/**
 * A hybrid linked list, which is a single linked list with reference to the tail
 * @author Felix Sung and Jonathan Sun
 * @param <T> a Generic list
 */
public class HybridLinkedList<T>
{
	//Initialize variables
	private Node<T> head;
	private Node<T> tail;
	private int size = 0;

	/**
	 * The constructor of the HybridLinkedList object
	 */
	HybridLinkedList()
	{
		this.head = null;
	}

	/**
	 * A method to get the head of the list
	 * @return the Node that is the current head of the list
	 */
	public Node<T> getHead()
	{
		return head;
	}

	/**
	 * A method to add a generic object to the list
	 * @param item the item to be added
	 */
	public void add(T item)
	{
		if (head == null)
		{
			head = new Node<T>(item);
			tail=head;
		}
		else
		{
			tail.setNext(new Node<T>(item));
			tail=tail.getNext();
		}
		size++;
	}
}
