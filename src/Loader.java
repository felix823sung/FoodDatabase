import java.io.IOException;

/**
 * The program to create and load in the food database
 * @author Felix Sung and Jonathan Sun
 *
 */
public class Loader
{
	// Create a data structure to store the Food objects
	private Hybrid foodStructure = new Hybrid();
	// Create a DoubleLinkedList to temporary store all of the Food objects
	private DoubleLinkedList foodList = new DoubleLinkedList();
	Loader() throws IOException
	{
		// Reads the information for each food, adding it to the data structure
		DataReader.readFoodDesc(foodList);
		DoubleLinkedList addedFoods = DataReader.readAddedFood();
		foodStructure.add(foodList);
		foodStructure.add(addedFoods);
		DataReader.readFoodGroup(foodList);
		DataReader.readFoodGroup(addedFoods);
		DataReader.readFootnote(foodList);
		DataReader.readNutData(foodList);
		DataReader.readWeight(foodList);
	}
	/**
	 * A method that gets the food database
	 * @return the food database
	 */
	public Hybrid getDatabase()
	{
		return foodStructure;
	}
}
