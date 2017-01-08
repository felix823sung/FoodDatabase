/**
 * The Food object, where most of the information about each food is stored
 * @author Felix Sung and Jonathan Sun
 *
 */
public class Food
{
	// Reference numbers to the array, denotes what each element is
	final static int NBD_No = 0;
	final static int FdGrp_Cd = 1;
	final static int Long_Desc = 2;
	final static int ComName = 3;
	final static int ManufacName = 4;
	final static int Ref_desc = 5;
	// Variable to determine whether the food is an added food
	private boolean isAdded;

	// HybridLinkedLists of type String used to store more complicated
	// information
	private HybridLinkedList<String> Src_Cd, Nutr_Val, FOOTNOTE, nutrients;
	// A WeightLinkedList to store information regarding measurements of the
	// food
	private WeightLinkedList Wgt;

	// The String array that stores basic information from the food description
	// file
	private String[] foodData = new String[6];

	/**
	 * The constructor of the Food object, instantiates many of the variables
	 */
	Food()
	{
		Src_Cd = new HybridLinkedList<String>();
		Nutr_Val = new HybridLinkedList<String>();
		FOOTNOTE = new HybridLinkedList<String>();
		nutrients = new HybridLinkedList<String>();
		Wgt = new WeightLinkedList();
		isAdded = false;
	}

	/**
	 * Changes the isAdded boolean to true
	 */
	public void Added()
	{
		isAdded = true;
	}

	/**
	 * Checks to see if the food is an added food
	 * @return true or false depending of the isAdded variable
	 */
	public boolean isAddedFood()
	{
		return isAdded;
	}

	/**
	 * Method that gets the basic information stored within the String array
	 * @param item the index of the element the user requires
	 * @return the String element stored at that index
	 */
	public String getData(int item)
	{
		return foodData[item];
	}

	/**
	 * Allows the user to set the nutrient list
	 * @param nutrients the HybridLinkedList of type String to add
	 */
	public void setNutrients(HybridLinkedList<String> nutrients)
	{
		this.nutrients = nutrients;
	}

	/**
	 * Allows the user to set the weight list
	 * @param wgt the WeightLinkedList to add
	 */
	public void setWgt(WeightLinkedList wgt)
	{
		this.Wgt = wgt;
	}

	/**
	 * Allows the user to set the data within the String array
	 * @param item the index to be added to
	 * @param data the information to be added
	 */
	public void setData(int item, String data)
	{
		foodData[item] = data;
	}

	/**
	 * A method that gets the HybridLinkedList containing the Src_Cd
	 * @return the HybridLinkedList of type String containing the Src_Cd
	 */
	public HybridLinkedList<String> getSrc_Cd()
	{
		return Src_Cd;
	}

	/**
	 * A method that gets the HybridLinkedList containing the Nutr_Val
	 * @return the HybridLinkedList of type String containing the Nutr_Val
	 */
	public HybridLinkedList<String> getNutr_Val()
	{
		return Nutr_Val;
	}
	
	/**
	 * A method that gets the HybridLinkedList containing the nutrients
	 * @return the HybridLinkedList of type String containing the nutrients
	 */
	public HybridLinkedList<String> getNutrients()
	{
		return nutrients;
	}

	/**
	 * A method that gets the WeightLinkedList containing the weight information
	 * @return the WeightLinkedList containing the weight information
	 */
	public WeightLinkedList getWgt()
	{
		return Wgt;
	}

	/**
	 * A method that gets the HybridLinkedList containing the footnote information
	 * @return the HybridLinkedList of type String containing the footnotes
	 */
	public HybridLinkedList<String> getFOOTNOTE()
	{
		return FOOTNOTE;
	}

}
