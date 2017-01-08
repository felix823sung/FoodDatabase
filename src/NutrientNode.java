/**
 * A custom node, which stores three Strings
 * @author Felix Sung and Jonathan Sun
 *
 */
public class NutrientNode
{
	// Initialize variables
	private String units;
	private String description;
	private String NutrientNum;

	/**
	 * The constructor for the node, takes in three parameters
	 * @param NutrientNum the number associated with the nutrient
	 * @param units the units that the nutrient is measured in
	 * @param description a description of the nutrient
	 */
	public NutrientNode(String NutrientNum, String units, String description)
	{
		this.NutrientNum = NutrientNum;
		this.units = units;
		this.description = description;
	}

	/**
	 * A method that gets the units of the nutrient
	 * @return the units that the nutrient is measured in
	 */
	public String getUnits()
	{
		return units;
	}

	/**
	 * A method that gets the description of the nutrient
	 * @return the description of the nutrient
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * A method that gets the nutrient number of the nutrient
	 * @return the nutrient number of the nutrient
	 */
	public String getNutrientNum()
	{
		return NutrientNum;
	}

}