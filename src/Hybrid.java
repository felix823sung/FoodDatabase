/**
 * The data structure to hold all the Food objects, an array of
 * DoubleLinkedLists
 * @author Felix Sung and Jonathan Sun
 *
 */
public class Hybrid
{

	// Reference numbers to each food group
	public final int Dairy_and_Egg_Products = 0;
	public final int Spices_and_Herbs = 1;
	public final int Baby_Foods = 2;
	public final int Fats_and_Oils = 3;
	public final int Poultry_Products = 4;
	public final int Soups_Sauces_Gravies = 5;
	public final int Sausages_and_Luncheon_Meats = 6;
	public final int Breakfast_Cereals = 7;
	public final int Fruits_and_Fruit_Juices = 8;
	public final int Pork_Products = 9;
	public final int Vegetables_and_Vegetable_Products = 10;
	public final int Nut_and_Seed_Products = 11;
	public final int Beef_Products = 12;
	public final int Beverages = 13;
	public final int Finfish_and_Shellfish_Products = 14;
	public final int Legumes_and_Legume_Products = 15;
	public final int Lamb_Veal_Game_Products = 16;
	public final int Baked_Products = 17;
	public final int Sweets = 18;
	public final int Cereal_Grains_and_Pasta = 19;
	public final int Fast_Foods = 20;
	public final int Meals_Entrees_Side_Dishes = 21;
	public final int Snacks = 22;
	public final int American_Indian_Alaska_Native_Foods = 23;
	public final int Restaurant_Foods = 24;

	// Creating the array of DoubleLinkedLists, of size 25
	DoubleLinkedList[] foodCategory = new DoubleLinkedList[25];

	/**
	 * The constructor of the data structure, instantiates the DoubleLinkedLists
	 */
	Hybrid()
	{
		for (int i = 0; i < 25; i++)
		{
			foodCategory[i] = new DoubleLinkedList();
		}

	}

	/**
	 * The add method of the data structure, allows the user to add foods into
	 * the data structure into the appropriate food group category
	 * @param foodList the DoubleLinkedList containing the foods to be added
	 */
	public void add(DoubleLinkedList foodList)
	{
		// Gets the head of the foodList
		FoodNode food = foodList.getHead();
		// Goes through the list until the end is reached
		while (food != null)
		{
			// Gets the Food item within the FoodNode
			Food item = food.getItem();
			// Gets the appropriate id from the foodGroup data of the food
			int id = Integer.parseInt(item.getData(1)) / 100;
			int category = id - 1;
			// Determines the correct category that the food belongs in
			if (id == 25)
			{
				category = 22;
			}
			else if (id > 25)
			{
				category = id - 12;
			}

			// Adds the Food object to the correct DoubleLinkedList
			foodCategory[category].add(item);
			// Sets the current FoodNode to the next FoodNode
			food = food.getNext();
		}
	}

	/**
	 * A method that gets the DoubleLinkedList of a particular food category
	 * @param category the index of the category in the data array
	 * @return the DoubleLinkedList containing the foods of that category
	 */
	public DoubleLinkedList getList(int category)
	{
		return foodCategory[category];
	}
}
