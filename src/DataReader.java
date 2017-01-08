import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
/**
 * The class containing all the readers for the different text files
 * @author Felix Sung and Jonathan Sun
 *
 */
public class DataReader
{
	/**
	 * A static method that reads from the "AddedFoods" text file, where
	 * previously added foods are stored, along with their relevant information
	 * @return a DoubleLinkedList containing the food objects
	 * @throws IOException
	 */
	public static DoubleLinkedList readAddedFood() throws IOException
	{
		// Creates a DoubleLinkedList
		DoubleLinkedList addedFood = new DoubleLinkedList();

		// Creates the BufferedReader to read in from the text file
		BufferedReader s = new BufferedReader(new FileReader("AddedFoods.txt"));
		// Initializes three StringTokenizers
		StringTokenizer st, nutr, wgts;

		// Reads in the first line of the text file
		String line = s.readLine();

		// Goes through the text file line by line until the end of the text
		// file is reached
		while (line != null)
		{
			// Instantiates the first StringTokenizer, using the read line and a
			// token of "|"
			st = new StringTokenizer(line, "|");
			// Creates a temporary Food object, in order to store the
			// information
			Food temp = new Food();
			// Sets the Food object's NBD number, food group, long description,
			// and common name
			temp.setData(0, st.nextToken());
			temp.setData(1, st.nextToken());
			temp.setData(2, st.nextToken());
			temp.setData(3, st.nextToken());
			// Instantiates the second StringTokenizer, using the next token of
			// the first StringTokenizer and a token of "+"
			nutr = new StringTokenizer(st.nextToken(), "+");
			// Creates a HybridLinkedList of type string to store the
			// information
			HybridLinkedList<String> nutrient = new HybridLinkedList<String>();
			// Goes through the line, adding each token to the HybridLinkedList
			while (nutr.hasMoreTokens())
			{
				nutrient.add(nutr.nextToken());
			}
			// Sets the Food object's nutrients
			temp.setNutrients(nutrient);
			// Instantiates the third StringTokenizer, using the next token of
			// the
			// first StringTokenizer and a token of "["
			wgts = new StringTokenizer(st.nextToken(), "[");
			// Creates a WeightLinkedList to store the information
			WeightLinkedList weights = new WeightLinkedList();
			// Goes through the line, adding the three consecutive tokens into
			// the WeightLinkedList
			while (wgts.hasMoreTokens())
			{
				weights.add(wgts.nextToken(), wgts.nextToken(),
						wgts.nextToken());
			}
			// Set the Food object's weights to the WeightLinkedClass
			temp.setWgt(weights);
			// Set the Food object as an added Food
			temp.Added();
			// Add the Food object to the DoubleLinkedList
			addedFood.add(temp);
			// Reads in the next line
			line = s.readLine();
		}
		s.close();
		// Returns the DoubleLinkedList of added Foods read from the text file
		return addedFood;
	}

	/**
	 * A static method that reads in the crucial information regarding each food
	 * from the "FOOD_DES" text file.
	 * @param foodList the DoubleLinkedList that all the foods will be added to
	 * @throws IOException
	 */
	public static void readFoodDesc(DoubleLinkedList foodList)
			throws IOException
	{
		// Creates the BUfferedReader to read in from the file
		BufferedReader s = new BufferedReader(new FileReader("FOOD_DES.txt"));
		// Initializes a StringTokenizer object
		StringTokenizer st;
		// Reads in the first line of the text file
		String line = s.readLine();
		// Goes through the text file line by line until there are no more lines
		while (line != null)
		{
			// Instantiates the StringTokenizer, using the read line and a token
			// of "^"
			st = new StringTokenizer(line, "^");
			// Creates a temporary Food object in order to store the information
			Food temp = new Food();
			// Reads in a series of six tokens, and trims the "~" off the edges.
			// Adds them to the Food object
			String length = st.nextToken();
			temp.setData(0, length.substring(1, length.length() - 1));
			length = st.nextToken();
			temp.setData(1, length.substring(1, length.length() - 1));
			length = st.nextToken();
			temp.setData(2, length.substring(1, length.length() - 1));
			st.nextToken();
			length = st.nextToken();
			temp.setData(3, length.substring(1, length.length() - 1));
			length = st.nextToken();
			temp.setData(4, length.substring(1, length.length() - 1));
			st.nextToken();
			length = st.nextToken();
			temp.setData(5, length.substring(1, length.length() - 1));
			// Adds the Food object to the DoubleLinkedList
			foodList.add(temp);
			// Reads in the next line
			line = s.readLine();
		}
		s.close();
	}

	/**
	 * A static method that reads in the word representation of the food's food
	 * groups, and changes the current value of food group to the word form
	 * @param foodList the DoubleLinkedList containing the foods
	 * @throws IOException
	 */
	public static void readFoodGroup(DoubleLinkedList foodList)
			throws IOException
	{
		// Create the BufferedReader that will read in from the "FD_Group" text
		// file
		BufferedReader s = new BufferedReader(new FileReader("FD_GROUP.txt"));
		// Create a String array big enough to hold all the different food
		// groups
		String[] groupList = new String[37];
		// Initializes a StringTokenizer object
		StringTokenizer st;
		// Reads in the first line of the text file
		String line = s.readLine();
		// Goes through each line of the text file until it reaches the end
		while (line != null)
		{
			// Instantiates the StringTokenizer, using the read line and a token
			// of "^"
			st = new StringTokenizer(line, "^");
			// Stores the next two tokens in two variables, the numbered version
			// and the word version of the food groups
			String groupNum = st.nextToken();
			String groupName = st.nextToken();
			// Adds the word version of the food groups to the appropriate spot
			// in the array, based on the integer value of the second and third
			// numbers of the numbered version of the food groups
			groupList[Integer.parseInt(groupNum.substring(1, 3))] = groupName
					.substring(1, groupName.length() - 1);
			line = s.readLine();
		}
		s.close();
		// Gets the head of the list of foods
		FoodNode food = foodList.getHead();
		// Goes through the list until the end
		while (food != null)
		{
			// Overwrites the numbered version of the food groups with the word
			// version of the food groups
			Food item = food.getItem();
			item.setData(
					1,
					groupList[Integer.parseInt(item.getData(1).substring(0, 2))]);
			food = food.getNext();
		}
	}

	/**
	 * A static method that reads from the "FOOTNOTE" text file, which contains
	 * relevant information for certain foods
	 * @param foodList the DoubleLinkedList of all the foods
	 * @throws IOException
	 */
	public static void readFootnote(DoubleLinkedList foodList)
			throws IOException
	{
		// Creates a BufferedReader to read from the text file
		BufferedReader s = new BufferedReader(new FileReader("FOOTNOTE.txt"));
		// Gets the head of the foodList
		FoodNode food = foodList.getHead();
		// Creates a StringTokenizer that uses the first line of the file and a
		// token of "^"
		StringTokenizer st = new StringTokenizer(s.readLine(), "^");
		// Stores the next token into a variable, trimming the "~"s
		String length = st.nextToken();
		String NBDNum = length.substring(1, length.length() - 1);
		// Goes through the list of foods until the end
		while (food != null)
		{
			// Checks whether or not the current food item matches the variable
			while (food.getItem().getData(0).equals(NBDNum))
			{
				// Since the current food matches the variable, adds the
				// appropriate information to the food
				st.nextToken();
				st.nextToken();
				st.nextToken();
				length = st.nextToken();
				// Adds the information to a HybridLinkedList of type String
				food.getItem().getFOOTNOTE()
						.add(length.substring(1, length.length() - 1));
				// Reads in the next line and stores it in a variable
				String end = s.readLine();
				// If it is not the last line, instantiates the StringTokenizer
				// with the new read line, and stores the next token
				if (end != null)
				{
					st = new StringTokenizer(end, "^");
					length = st.nextToken();
					NBDNum = length.substring(1, length.length() - 1);
				}
				// Should the end of the text file been reached, exites the
				// method
				else
				{
					s.close();
					return;
				}
			}
			// Sets the FoodNode object to the next FoodNode
			food = food.getNext();
		}
		s.close();
	}

	/**
	 * A static method that reads in the Nutritional Data from the "NUT_DATA"
	 * text file, and stores the necessary information
	 * @param foodList the DoubleLinkedList containing all the foods
	 * @throws IOException
	 */
	public static void readNutData(DoubleLinkedList foodList)
			throws IOException
	{
		// Creates the BufferedReader that will read in from the text file
		BufferedReader s = new BufferedReader(new FileReader("NUT_DATA.txt"));

		// Calls the readSrcCode method, and stores the result in a String array
		String[] srcList = DataReader.readSrcCode();
		// Gets the beginning of the foodList
		FoodNode food = foodList.getHead();
		// Creates a StringTokenizer object using the first line of the file, a
		// token of "^", as well as returnDeliminator set to true
		StringTokenizer st = new StringTokenizer(s.readLine(), "^", true);
		// Stores the next token in a variable, trimming the "~"s
		String length = st.nextToken();
		String NBDNum = length.substring(1, length.length() - 1);
		// Goes through the foodList until the end
		while (food != null)
		{
			// Checks if the current Food object matches the variable
			while (food.getItem().getData(0).equals(NBDNum))
			{
				// Adds the appropriate information into the Food object
				st.nextToken();
				length = st.nextToken();
				// Adds the nutrient info
				food.getItem().getNutrients()
						.add(length.substring(1, length.length() - 1));
				st.nextToken();
				// Adds the Nutr_Val info
				food.getItem().getNutr_Val().add(st.nextToken());
				st.nextToken();
				st.nextToken();
				st.nextToken();
				// Checks the next token, as the field may be null
				String check = st.nextToken();
				// Should the field be null, adds the next appropriate token as
				// the Src_Cd info
				if (check.equals("^"))
				{
					length = st.nextToken();
					food.getItem()
							.getSrc_Cd()
							.add(srcList[Integer.parseInt(length.substring(1,
									length.length() - 1))]);
				}
				// Should the field not be null, adds the next appropriate token
				// as the Src_Cd info
				else
				{
					st.nextToken();
					length = st.nextToken();
					food.getItem()
							.getSrc_Cd()
							.add(srcList[Integer.parseInt(length.substring(1,
									length.length() - 1))]);
				}
				// Reads in the next line
				String line = s.readLine();
				// Checks if the end of the text file has been reached
				if (line != null)
				{
					// If it is not the end, instantiates the StringTokenizer
					// again using the new read line, a token of "^", and
					// returnDeliminator to true
					st = new StringTokenizer(line, "^", true);
					length = st.nextToken();
					NBDNum = length.substring(1, length.length() - 1);
				}
				else
				{
					// Should the end of the text file been reached, quits the
					// method
					s.close();
					return;
				}
			}
			// Sets the current FoodNode to the next FoodNode
			food = food.getNext();
		}
		s.close();
	}

	/**
	 * A static method that reads from the "NUTR_DEF" text file, and creates an
	 * instance of each nutrient
	 * @return an array of NutrientNodes that contains every nutrient
	 * @throws IOException
	 */
	public static NutrientNode[] readNutDef() throws IOException
	{
		// Creates the BufferedReader that will read in from the text file
		BufferedReader s = new BufferedReader(new FileReader("NUTR_DEF.txt"));
		// Initializes a StringTokenizer object
		StringTokenizer st;
		// Creates an array of NutrientNodes of enough size to fit the max value
		// of the nutrients
		NutrientNode[] nutList = new NutrientNode[860];
		// Reads in the first line of the file
		String line = s.readLine();
		// Goes through the text file until the end is reached
		while (line != null)
		{
			// Instantiates the StringTokenizer object using the read line, a
			// token of "^", and returnDeliminator to true
			st = new StringTokenizer(line, "^", true);
			// Stores the appropriate tokens into three variables
			String nutrientNum = st.nextToken();
			st.nextToken();
			String units = st.nextToken();
			st.nextToken();
			// Checks the next token, as a field may be null
			String check = st.nextToken();
			String desc;
			// Should the field be null, appropriate token is stored
			if (check.equals("^"))
			{
				desc = st.nextToken();
			}
			// Should the field not be null, appropriate token is stored
			else
			{
				st.nextToken();
				desc = st.nextToken();
			}
			// Sets the appropriate index (given by the variable nutrientNum) of
			// the array to a NutrientNode containing the information from the
			// three variables, trimming the "~"s
			nutList[Integer.parseInt(nutrientNum.substring(1,
					nutrientNum.length() - 1))] = new NutrientNode(
					nutrientNum.substring(1, nutrientNum.length() - 1),
					units.substring(1, units.length() - 1),
					desc.substring(1, desc.length() - 1));
			// Reads in the next line
			line = s.readLine();
		}
		s.close();
		// Return the array of all the nutrients
		return nutList;
	}

	/**
	 * A static method that reads from the "SRC_CD" text file, and determines
	 * the word version of the source code numbers
	 * @return an array of Strings containing the meaning of the source code
	 *         numbers
	 * @throws IOException
	 */
	public static String[] readSrcCode() throws IOException
	{
		// Creates a BufferedReader that reads in from the text file
		BufferedReader s = new BufferedReader(new FileReader("SRC_CD.txt"));
		// Reads in the first line of the file
		String line = s.readLine();
		// Initializes a StringTokenizer object, to be instantiated later
		StringTokenizer st;
		// Creates an array of Strings, of size big enough to hold the max value
		// of the numbered source code
		String[] srcList = new String[14];
		// Goes through the text file until the end is reached
		while (line != null)
		{
			// Instantiates the StringTokenizer object using the read line and a
			// token of "^"
			st = new StringTokenizer(line, "^");
			// Stores the following two tokens into two variables
			String srcNum = st.nextToken();
			String desc = st.nextToken();
			// Adds the word version of the source code to the appropriate spot
			// in the array, determined by the number value of the source code
			srcList[Integer.parseInt(srcNum.substring(1, srcNum.length() - 1))] = desc
					.substring(1, desc.length() - 1);
			// Reads in the next line
			line = s.readLine();
		}
		s.close();
		// Returns the array of Strings containing the word version of the
		// source codes
		return srcList;
	}

	/**
	 * A static method that reads from the "WEIGHT" text file, and stores
	 * information regarding the measurement of each food
	 * @param foodList the DoubleLinkedList containing all the foods
	 * @throws IOException
	 */
	public static void readWeight(DoubleLinkedList foodList)
			throws IOException
	{
		// Creates a BufferedReader that reads in from the text file
		BufferedReader s = new BufferedReader(new FileReader("WEIGHT.txt"));
		// Gets the head of the foodList
		FoodNode food = foodList.getHead();
		// Creates a StringTokenizer object that uses the first line of the file
		// and a token of "^"
		StringTokenizer st = new StringTokenizer(s.readLine(), "^");
		// Stores the first token in a variable, trimming the "~"s
		String length = st.nextToken();
		String NBDNum = length.substring(1, length.length() - 1);
		// Goes through the foodList, until the end is reached
		while (food != null)
		{
			// Checks whether the current food matches the variable
			while (food.getItem().getData(0).equals(NBDNum))
			{
				// Should the food match the variable, adds the appropriate
				// tokens to the food
				st.nextToken();
				String amount = st.nextToken();
				String desc = st.nextToken();
				String weight = st.nextToken();
				food.getItem()
						.getWgt()
						.add(amount, desc.substring(1, desc.length() - 1),
								weight);
				// Reads in the next line
				String end = s.readLine();
				// Checks if the end of the file has been reached
				if (end != null)
				{
					// Should it not be the end, the StringTokenizer is
					// instantiated using the new read line and a token of "^"
					st = new StringTokenizer(end, "^");
					//The next token is stored in a variable, trimming the "~"s
					length = st.nextToken();
					NBDNum = length.substring(1, length.length() - 1);
				}
				else
				{
					//Should the end of the text file have been reached, exits the method
					s.close();
					return;
				}
			}
			//Sets the current FoodNode to the next FoodNode
			food = food.getNext();
		}
		s.close();
	}
}
