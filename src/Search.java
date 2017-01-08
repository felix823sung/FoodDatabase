import java.util.StringTokenizer;

/**
 * A search class that looks for key words within each food in the data
 * structure
 * @author Felix Sung and Jonathan Sun
 *
 */
public class Search
{
	/**
	 * A method that searches for foods that contain the key words within the
	 * data structure, can be specified by food groups
	 * @param str the String containing the key words
	 * @param foodGroup the index of the food group within the array located in
	 *            the data structure
	 * @param structure the data structure to be searched
	 * @return
	 */
	public static DoubleLinkedList search(String str, int foodGroup,
			Hybrid structure)
	{
		// Creates a DoubleLinkedList to store any food that matches the
		// criteria
		DoubleLinkedList foundList = new DoubleLinkedList();
		// Checks if the user wants to search a specific food group (<25 is yes,
		// 25 is no)
		if (foodGroup < 25)
		{
			// Gets the appropriate DoubleLinkedList from the data structure
			DoubleLinkedList list = structure.getList(foodGroup);
			// Gets the head of the list
			FoodNode food = list.getHead();
			// Creates a StringTokenizer object using the given String
			StringTokenizer st = new StringTokenizer(str);
			// Counts the number of tokens within the String
			int tokens = st.countTokens();
			// Goes through the list until the end is reached
			while (food != null)
			{
				// Gets the Food object of that FoodNode
				Food item = food.getItem();
				// Starts a counter at 0
				int count = 0;
				// Runs through all of the tokens within the given String
				for (int i = 0; i < tokens; i++)
				{
					// Assigns the next token to variable
					String check = st.nextToken();
					// Checks if the token can be found in the current food's
					// description or common names, if so, one is added to the
					// counter
					if (item.getData(2).toLowerCase()
							.indexOf(check.toLowerCase()) >= 0
							|| item.getData(3).toLowerCase()
									.indexOf(check.toLowerCase()) >= 0)
					{
						count++;
					}
				}
				// Checks if all of the tokens have been found in the food's
				// information, if so, adds the food to the foundList
				if (count == tokens)
				{
					foundList.add(item);
				}
				// Instantiates the StringTokenizer to the given string
				st = new StringTokenizer(str);
				// Sets the current FoodNode to the next FoodNode
				food = food.getNext();
			}
			// Returns the list of foods found
			return foundList;
		}
		// Should the user wish to search the entire data structure, a similar
		// process to above is followed for all of the DoubleLinkedLists in the
		// data structure
		else
		{
			for (int i = 0; i < 25; i++)
			{
				DoubleLinkedList list = structure.getList(i);
				FoodNode food = list.getHead();
				StringTokenizer st = new StringTokenizer(str);
				int tokens = st.countTokens();
				while (food != null)
				{
					Food item = food.getItem();
					int count = 0;
					for (int j = 0; j < tokens; j++)
					{
						String check = st.nextToken();
						if (item.getData(2).toLowerCase()
								.indexOf(check.toLowerCase()) >= 0
								|| item.getData(3).toLowerCase()
										.indexOf(check.toLowerCase()) >= 0)
						{
							count++;
						}
					}
					if (count == tokens)
					{
						foundList.add(item);
					}
					st = new StringTokenizer(str);
					food = food.getNext();
				}
			}
			return foundList;
		}
	}

}
