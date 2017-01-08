import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class that deals with added foods by the user, contains a method to write the
 * information to a text file as well as create the food, and also a method to
 * update the data structure
 * @author Felix Sung and Jonathan Sun
 *
 */
public class AddedFoodWriter
{
	/**
	 * A method that receives information from the GUI, and prints out the
	 * information to the "AddedFoods" text file. Returns a DoubleLinkedList
	 * containing the added food, to be added to the data structure
	 * @param NBDNum the unique identifier for each food
	 * @param fdGroup the food group identifier in number form
	 * @param desc a description of the food being added
	 * @param comNam any common names that the added food might have
	 * @param nutrs a HybridLinkedList of type String containing the nutrients
	 *            within this food in number form
	 * @param wgts a WeightLinkedList containing all weight measurements that
	 *            this food may have
	 * @return a DoubleLinkedList containing the added food
	 * @throws IOException
	 */
	public static DoubleLinkedList addFood(String NBDNum, String fdGroup,
			String desc,
			String comNam, HybridLinkedList<String> nutrs, WeightLinkedList wgts)
			throws IOException
	{
		// Creates the PrintWriter object in order to print to the text file,
		// also appends and writes to the end of the file
		PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter(
				"AddedFoods.txt", true)));
		// Prints the basic information separated by "|"
		p.print(NBDNum + "|" + fdGroup + "|" + desc + "|" + comNam + "|");
		// Gets the head of the nutrients list
		Node<String> temp = nutrs.getHead();
		// Prints out all of the nutrients in number form separated by "+"
		while (temp != null)
		{
			String str = temp.getItem();
			p.print(str);
			temp = temp.getNext();
			if (temp != null)
			{
				p.print("+");
			}
		}
		// Prints out a "|" as a information type divider
		p.print("|");
		// Gets the head of the weights list
		WeightNode wgt = wgts.getHead();
		// Prints out all of the weight measurements separated by "["
		while (wgt != null)
		{
			p.print(wgt.getAmount() + "[" + wgt.getMsre_Desc() + "["
					+ wgt.getGm_Wgt());
			wgt = wgt.getNext();
			if (wgt != null)
			{
				p.print("[");
			}
		}
		// Print out "|" as ending
		p.println("|");
		p.close();
		// Creates a temporary food item, and adds all the given data to it
		Food item = new Food();
		item.setData(0, NBDNum);
		item.setData(1, fdGroup);
		item.setData(2, desc);
		item.setData(3, comNam);
		item.setNutrients(nutrs);
		item.setWgt(wgts);
		// Set it to an added item
		item.Added(); 
		// Create a new DoubleLinkedList to store the Food object
		DoubleLinkedList newAddedFoods = new DoubleLinkedList();
		// Add the Food object to the DoubleLinkedList
		newAddedFoods.add(item);
		// Return the DoubleLinkedList containing the Food object
		return newAddedFoods;
	}

	/**
	 * A method that adds any added Food objects to the database
	 * @param database the data structure the Food object will be added to
	 * @param list the DoubleLinkedList containing the Food object
	 * @throws IOException
	 */
	public static void update(Hybrid database, DoubleLinkedList list)
			throws IOException
	{
		// Add the DoubleLinkedList to the data structure
		database.add(list);
		// Replace the number form of food group with the word form
		DataReader.readFoodGroup(list);
	}

}
