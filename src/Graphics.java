// Importing all the needed java libraries
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

/**
 * A program to create the graphic user interface for the food database
 * @author Jonathan Sun and Felix Sung
 */
@SuppressWarnings("serial")
public class Graphics extends javax.swing.JPanel
{
	// create a new loader
	private Loader main = new Loader();
	// create a new Hybrid data structure to store all of the read in
	// information
	private Hybrid dataBase;

	// creating an array containing nutrient information
	private NutrientNode[] nutrients = DataReader.readNutDef();

	// creating objects that will be accessed throughout the code and its
	// methods
	private HybridLinkedList<String> addedNutrients;
	private WeightLinkedList weightList;
	private String[][] tableData;
	private Food currentFood;

	// setting up all of the custom fonts
	private Font headerFont = new Font("Helvetica Neue", 1, 15);
	private Font titleFont = new Font("Helvetica Neue", 1, 20);
	private Font weightFont = new Font("Helvetica Neue", 1, 10);

	// creating all the components for the central window
	private JTextArea detailTextArea;
	private JComboBox<String> foodGroupSearchComboBox;
	private JTable foodTable;
	private JPanel mainPanel;
	private JButton newItemButton;
	private JTextField searchTextField;
	private JSeparator mainSeparator;
	private JLabel title;
	private JButton searchButton;
	private JScrollPane tableScrollPane;
	private JScrollPane detailScrollPane;

	// create the main frame that will contain all the components for the main
	// window
	private JFrame frame;

	// creating the "add frame" components and frames
	private JFrame addFrame;
	private JPanel addPanel;
	private JPanel weightPanel;
	private JPanel titleLabelPanel;
	private JPanel NBDLabelPanel;
	private JPanel foodGroupLabelPanel;
	private JPanel descLabelPanel;
	private JPanel comNameLabelPanel;
	private JPanel nutrLabelPanel;
	private JPanel weightLabelPanel;
	private JPanel addNutrPanel;
	private JPanel addFoodPanel;
	private JLabel addTitle;
	private JSeparator separatorOne;
	private JLabel NBDNumLabel;
	private JTextField NBDNumTextField;
	private JSeparator separatorTwo;
	private JLabel foodGroupLabel;
	private JComboBox<String> foodGroupComboBox;
	private JSeparator separatorThree;
	private JLabel descLabel;
	private JTextField descTextField;
	private JSeparator separatorFour;
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JSeparator separatorFive;
	private JLabel nutrientLabel;
	private JComboBox<String> nutrientComboBox;
	private JButton addNutrientButton;
	private JSeparator separatorSix;
	private JLabel weightLabel;
	private JTextField modifierTextField;
	private JTextField measurementDescriptionTextField;
	private JTextField weightTextField;
	private JButton EnterNewFoodButton;

	// constructor for a graphics object that calls the method to set up each
	// component
	public Graphics() throws IOException
	{
		initComponents();
	}

	/**
	 * Modifies the table data if a search field is used as well as updates the
	 * table in the GUI
	 * @param searchText the text that will be searched for
	 * @param foodGroup the food group to search within
	 */
	private void setSearchTable(String searchText, int foodGroup)
	{
		// using the Search class' search function to generate a double linked
		// list of foods that meet the search criteria
		DoubleLinkedList listOfEligibleFood = Search.search(searchText,
				foodGroup,
				dataBase);
		// create a new array that stores all the information needed by the food
		// table
		tableData = new String[listOfEligibleFood.size()][4];
		// create a temporary node using the previously fetched doubly linked
		// list
		FoodNode tempNode = listOfEligibleFood.getHead();

		// set the NBD number, food group, description and common name for each
		// food in the doubly linked list
		for (int i = 0; i < listOfEligibleFood.size(); i++)
		{
			tableData[i][0] = tempNode.getItem().getData(0);
			tableData[i][1] = tempNode.getItem().getData(1);
			tableData[i][2] = tempNode.getItem().getData(2);
			tableData[i][3] = tempNode.getItem().getData(3);
			tempNode = tempNode.getNext();
		}

		// set up the table model using the newly generated table data
		DefaultTableModel tableModel = new DefaultTableModel(tableData,
				new String[] { "NBD Number", "Food Group", "Description",
						"Common Name" })

		{
			// prevent the table from being edited by the user
			@Override
			public boolean isCellEditable(int row, int column)
			{
				//sets all cells to uneditable
				return false;
			}
		};

		// set the model for the food table
		foodTable.setModel(tableModel);

		// adding and removing the foodTable so that it visually updates when
		// the changes are made
		tableScrollPane.remove(foodTable);
		mainPanel.remove(tableScrollPane);
		tableScrollPane.add(foodTable);
		mainPanel.add(tableScrollPane);
		tableScrollPane.setViewportView(foodTable);

	}

	/**
	 * Sets up the table data needed to display the food table using every food
	 */
	private void setTable()
	{
		// fetching a new database of every food (including added foods)
		dataBase = main.getDatabase();

		int totalFoods = 0;

		// counts how many foods there are
		for (int i = 0; i < 25; i++)
		{
			totalFoods += dataBase.getList(i).size();

		}

		// creates a new array to store the table data for each food
		tableData = new String[totalFoods][4];
		int yIndex = 0;

		// fills up the table data by cycling through each food and fetching the
		// NBD number, food group, description and common name
		for (int i = 0; i < 25; i++)
		{
			DoubleLinkedList temp = dataBase.getList(i);
			FoodNode tempNode = temp.getHead();
			for (int j = 0; j < temp.size(); j++)
			{
				tableData[yIndex][0] = tempNode.getItem().getData(0);
				tableData[yIndex][1] = tempNode.getItem().getData(1);
				tableData[yIndex][2] = tempNode.getItem().getData(2);
				tableData[yIndex][3] = tempNode.getItem().getData(3);
				tempNode = tempNode.getNext();
				yIndex++;
			}
		}
	}

	/**
	 * Sets up all of the components that will be used in the GUI
	 * @throws IOException
	 */
	private void initComponents() throws IOException
	{
		// sets the data for the table
		setTable();

		// instantiates all of the needed panels as well as sets their layouts
		mainPanel = new JPanel();
		weightPanel = new JPanel();
		weightLabelPanel = new JPanel();
		weightLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		titleLabelPanel = new JPanel();
		titleLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		NBDLabelPanel = new JPanel();
		NBDLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		foodGroupLabelPanel = new JPanel();
		foodGroupLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		descLabelPanel = new JPanel();
		descLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		comNameLabelPanel = new JPanel();
		comNameLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		nutrLabelPanel = new JPanel();
		nutrLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		addNutrPanel = new JPanel();
		addNutrPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		addFoodPanel = new JPanel();
		addFoodPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		// instantiates all the needed components for the main frame as well as
		// sets their fonts
		title = new JLabel("FOOD DATABASE");
		title.setFont(titleFont);
		foodGroupSearchComboBox = new JComboBox<>();

		// sets up the options for the dropdown search filter
		foodGroupSearchComboBox.setModel(new DefaultComboBoxModel<>(
				new String[] { "Dairy and Egg Products",
						"Spices and Herbs",
						"Baby Foods", "Fats and Oils", "Poultry Products",
						"Soups, Sauces, and Gravies",
						"Sausages and Luncheon Meats",
						"Breakfast Cereals", "Fruits and Fruit Juices",
						"Pork Products",
						"Vegetables and Vegetable Products",
						"Nut and Seed Products",
						"Beef Products", "Beverages",
						"Finfish and Shellfish Products",
						"Legumes and Legume Products",
						"Lamb, Veal, and Game Products",
						"Baked Products", "Sweets", "Cereal Grains and Pasta",
						"Fast Foods", "Meals, Entrees, and Side Dishes",
						"Snacks",
						"American Indian/Alaska Native Foods",
						"Restaurant Foods", "All Categories" }));

		foodGroupSearchComboBox.setSelectedIndex(25);
		mainSeparator = new JSeparator();
		searchTextField = new JTextField();
		tableScrollPane = new JScrollPane();
		foodTable = new JTable();
		searchButton = new JButton();
		searchButton.setText("Search");
		detailScrollPane = new JScrollPane();
		detailTextArea = new JTextArea();
		detailTextArea.setColumns(20);
		detailTextArea.setRows(5);
		detailScrollPane.setViewportView(detailTextArea);
		detailTextArea.setFont(headerFont);
		detailTextArea.setEditable(false);
		tableScrollPane = new JScrollPane();
		newItemButton = new JButton();
		newItemButton.setText("Add New Item");

		// instantiates all the components needed for the "add frame" where new
		// food objects can be added
		addTitle = new JLabel("Add Food Item Data");
		separatorOne = new JSeparator();
		addTitle.setFont(titleFont);
		NBDNumLabel = new JLabel("Enter Nutrition DataBase Number");
		NBDNumLabel.setFont(headerFont);
		NBDNumTextField = new JTextField();
		separatorTwo = new JSeparator();
		foodGroupLabel = new JLabel("Select The Food Group");
		foodGroupLabel.setFont(headerFont);
		foodGroupComboBox = new JComboBox<String>();
		// sets all of the dropdown options for the foodGroupComboBox
		foodGroupComboBox.setModel(new DefaultComboBoxModel<>(
				new String[] { "(0100) Dairy and Egg Products",
						"(0200) Spices and Herbs",
						"(0300) Baby Foods", "(0400) Fats and Oils",
						"(0500) Poultry Products",
						"(0600) Soups, Sauces, and Gravies",
						"(0700) Sausages and Luncheon Meats",
						"(0800) Breakfast Cereals",
						"(0900) Fruits and Fruit Juices",
						"(1000) Pork Products",
						"(1100) Vegetables and Vegetable Products",
						"(1200) Nut and Seed Products",
						"(1300) Beef Products", "(1400) Beverages",
						"(1500) Finfish and Shellfish Products",
						"(1600) Legumes and Legume Products",
						"(1700) Lamb, Veal, and Game Products",
						"(1800) Baked Products", "(1900) Sweets",
						"(2000) Cereal Grains and Pasta",
						"(2100) Fast Foods",
						"(2200) Meals, Entrees, and Side Dishes",
						"(2500) Snacks",
						"(3500) American Indian/Alaska Native Foods",
						"(3600) Restaurant Foods" }));
		separatorThree = new JSeparator();
		descLabel = new JLabel("Enter Description");
		descLabel.setFont(headerFont);
		descTextField = new JTextField();
		separatorFour = new JSeparator();
		nameLabel = new JLabel("Enter Any Common Names");
		nameLabel.setFont(headerFont);
		nameTextField = new JTextField();
		separatorFive = new JSeparator();
		nutrientLabel = new JLabel("Add Nutrient(s)");
		nutrientLabel.setFont(headerFont);
		nutrientComboBox = new JComboBox<String>();
		// fetching a list of all the nutrients for the nutrientComboBox
		NutrientNode[] nutr = DataReader.readNutDef();
		String[] nutrientString = new String[150];
		int index = 0;
		for (int nutrIndex = 0; nutrIndex < 860; nutrIndex++)
		{
			if (nutr[nutrIndex] != null)
			{
				nutrientString[index] = nutr[nutrIndex].getNutrientNum() + " "
						+ nutr[nutrIndex].getDescription()
						+ " " + nutr[nutrIndex].getUnits();
				index++;
			}
		}
		nutrientComboBox.setModel(new DefaultComboBoxModel<>(nutrientString));
		addNutrientButton = new JButton("Add Nutrient");
		separatorSix = new JSeparator();
		weightLabel = new JLabel(
				"Modifier (number)              Measurement Description         Weight(in grams)");
		weightLabel.setFont(weightFont);
		modifierTextField = new JTextField();
		measurementDescriptionTextField = new JTextField();
		weightTextField = new JTextField();
		EnterNewFoodButton = new JButton();
		EnterNewFoodButton.setText("Enter Item Into DataBase");

		// create all the frames needed to hold components
		frame = new JFrame("Food DataBase");
		addFrame = new JFrame("Add Food Item");
		addPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 255, 255));
		addPanel.setBackground(new Color(255, 255, 255));

		// set the table model to contain the every food and prevents the food
		// table from being edited
		DefaultTableModel tableModel = new DefaultTableModel(tableData,
				new String[] { "NBD Number", "Food Group", "Description",
						"Common Name" })

		{
			@Override
			public boolean isCellEditable(int row, int column)
			{
				// sets all cells to uneditable
				return false;
			}
		};

		// sets the food data to display the all the foods
		foodTable.setModel(tableModel);
		tableScrollPane.setViewportView(foodTable);

		// Adding in the action listeners the buttons, combo boxes and necessary
		// text fields
		searchButton.addActionListener(new SearchButtonListener());
		searchTextField.addActionListener(new SearchTextFieldListener());
		foodGroupSearchComboBox
				.addActionListener(new FoodGroupDropDownListener());
		newItemButton.addActionListener(new AddNewItemButtonListener());
		addNutrientButton.addActionListener(new AddNutrientButtonListener());
		EnterNewFoodButton.addActionListener(new EnterNewFoodButtonListener());

		// creates a new mouse listener that displays food details when a food
		// is clicked
		foodTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)
			{
				JTable target = (JTable) e.getSource();
				// fetches the table row that was clicked as well as the NBD
				// number associated with it
				int row = target.getSelectedRow();
				String NBDNo = tableData[row][0];

				// fetch the food with the previously fetched NBD number
				DoubleLinkedList listOfEligibleFood = Search.search("", 25,
						dataBase);
				FoodNode temp = listOfEligibleFood.getHead();
				for (int i = 0; i < listOfEligibleFood.size(); i++)
				{
					if (NBDNo.equals(temp.getItem().getData(0)))
					{
						currentFood = temp.getItem();
						break;
					}
					temp = temp.getNext();
				}

				// clear the text before displaying the new text
				detailTextArea.setText("");

				// display all of the details for the selected food
				String newLine = "\n";
				detailTextArea.append("Details:" + newLine + newLine
						+ "NBD Number: "
						+ currentFood.getData(0)
						+ newLine + "Common Name: " + currentFood.getData(3)
						+ newLine + "Manufacturer Name: "
						+ currentFood.getData(4) +
						newLine + "Food Group: " + tableData[row][1] + newLine
						+ "Inedible Parts: " + currentFood.getData(5) + newLine
						+ "Description: " + currentFood.getData(2) + newLine
						+ newLine
						+ "Weights: "
						);

				WeightLinkedList weightDisplayList = currentFood.getWgt();
				WeightNode tempWeightDisplayNode = weightDisplayList.getHead();
				while (tempWeightDisplayNode != null)
				{
					detailTextArea.append(newLine
							+ tempWeightDisplayNode.getAmount() + " "
							+ tempWeightDisplayNode.getMsre_Desc()
							+ "--> weighs "
							+ tempWeightDisplayNode.getGm_Wgt() + " grams ");
					tempWeightDisplayNode = tempWeightDisplayNode.getNext();
				}

				Node<String> tempNode = currentFood.getNutrients().getHead();

				detailTextArea.append(newLine + newLine + "Nutrients:");
				while (tempNode != null)
				{
					detailTextArea.append(newLine + "#"
							+ tempNode.getItem() + " "
							+ nutrients[Integer.parseInt(tempNode.getItem())]
									.getDescription()
							+ ", measured in "
							+ nutrients[Integer.parseInt(tempNode.getItem())]
									.getUnits());
					tempNode = tempNode.getNext();
				}

				// set the scrolled position the the top of the text
				// area
				detailTextArea.setCaretPosition(0);
			}
		});

		// create a new group layout and set the main panel to the newly made
		// group layout
		GroupLayout mainPanelLayout = new GroupLayout(
				mainPanel);
		mainPanel.setLayout(mainPanelLayout);

		// group layouts created by following this documentation:
		// https://docs.oracle.com/javase/7/docs/api/javax/swing/GroupLayout.ParallelGroup.html
		// https://docs.oracle.com/javase/tutorial/uiswing/layout/group.html
		// https://docs.oracle.com/javase/7/docs/api/javax/swing/GroupLayout.html
		// set up all the horizontal groups for the main panel layout
		mainPanelLayout
				.setHorizontalGroup(
				mainPanelLayout
						.createParallelGroup(
								GroupLayout.Alignment.LEADING)
						.addComponent(mainSeparator,
								GroupLayout.Alignment.TRAILING)
						.addGroup(
								GroupLayout.Alignment.TRAILING,
								mainPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												mainPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																detailScrollPane,
																GroupLayout.Alignment.TRAILING)
														.addGroup(
																mainPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				title)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED,
																				30,
																				Short.MAX_VALUE)
																		.addComponent(
																				foodGroupSearchComboBox,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				searchTextField,
																				GroupLayout.PREFERRED_SIZE,
																				126,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				searchButton))
														.addComponent(
																tableScrollPane,
																GroupLayout.Alignment.TRAILING)
														.addComponent(
																newItemButton,
																GroupLayout.Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap()));
		// set up all the vertical groups for the main panel layout
		mainPanelLayout
				.setVerticalGroup(
				mainPanelLayout
						.createParallelGroup(
								GroupLayout.Alignment.LEADING)
						.addGroup(
								mainPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												mainPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addGroup(
																mainPanelLayout
																		.createParallelGroup(
																				GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				foodGroupSearchComboBox,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				searchTextField,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				searchButton))
														.addComponent(
																title,
																GroupLayout.Alignment.TRAILING))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												mainSeparator,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												tableScrollPane,
												GroupLayout.PREFERRED_SIZE,
												285,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												detailScrollPane,
												GroupLayout.PREFERRED_SIZE,
												201,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												newItemButton,
												GroupLayout.PREFERRED_SIZE,
												23,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(
						GroupLayout.Alignment.LEADING)
						.addGroup(
								layout.createSequentialGroup()
										.addGap(18, 18, 18)
										.addComponent(
												mainPanel,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGap(18, 18, 18)));

		layout.setVerticalGroup(
				layout.createParallelGroup(
						GroupLayout.Alignment.LEADING)
						.addGroup(
								layout.createSequentialGroup()
										.addGap(35, 35, 35)
										.addComponent(
												mainPanel,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGap(36, 36, 36)));
	}

	/**
	 * Sets up the main frame before it is displayed
	 */
	public void go()
	{
		frame.setSize(900, 630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(mainPanel);
		frame.setVisible(true);
	}

	/**
	 * ActionListener for when the search button is pressed
	 * @author Jonathan Sun and Felix Sung
	 */
	public class SearchButtonListener implements ActionListener
	{
		/**
		 * Modifies the food table once the search button is pressed
		 */
		public void actionPerformed(ActionEvent e)
		{
			setSearchTable(searchTextField.getText(),
					foodGroupSearchComboBox.getSelectedIndex());
		}
	}

	/**
	 * ActionListener for when the user presses enter while using the search
	 * text field
	 * @author Jonathan Sun and Felix Sung
	 */
	public class SearchTextFieldListener implements ActionListener
	{
		/**
		 * modifies the food table once the user presses enter while using the
		 * search text field
		 */
		public void actionPerformed(ActionEvent e)
		{
			setSearchTable(searchTextField.getText(),
					foodGroupSearchComboBox.getSelectedIndex());
		}
	}

	/**
	 * ActionListener for when a food category is selected
	 * @author Jonathan Sun and Felix Sung
	 *
	 */
	public class FoodGroupDropDownListener implements ActionListener
	{
		/**
		 * modifies the food table once the a food category is selected
		 */
		public void actionPerformed(ActionEvent e)
		{
			setSearchTable(searchTextField.getText(),
					foodGroupSearchComboBox.getSelectedIndex());
		}
	}

	/**
	 * ActionListener for when the add new item button is pressed
	 * @author Jonathan Sun and Felix Sung
	 *
	 */
	public class AddNewItemButtonListener implements ActionListener
	{
		/**
		 * opens the new food frame once the add new item button is pressed
		 */
		public void actionPerformed(ActionEvent e)
		{
			// clear the previously entered in data
			foodGroupComboBox.setSelectedIndex(0);
			nutrientComboBox.setSelectedIndex(0);
			NBDNumTextField.setText("");
			descTextField.setText("");
			nameTextField.setText("");
			modifierTextField.setText("");
			measurementDescriptionTextField.setText("");
			weightTextField.setText("");

			// colors the panel white
			weightPanel.setBackground(new Color(255, 255, 255));

			// instantiates the added nutrients and weight list so they are
			// blank
			addedNutrients = new HybridLinkedList<String>();
			weightList = new WeightLinkedList();

			// sets up the frame
			addFrame.setSize(400, frame.getHeight());
			addFrame.setResizable(false);
			addFrame.setLocation(frame.getLocation().x + 900,
					frame.getLocation().y);

			// adds all of the components needed to the addPanel
			titleLabelPanel.add(addTitle);
			addPanel.add(titleLabelPanel);
			addPanel.add(separatorOne);
			NBDLabelPanel.add(NBDNumLabel);
			addPanel.add(NBDLabelPanel);
			addPanel.add(NBDNumTextField);
			addPanel.add(separatorTwo);
			foodGroupLabelPanel.add(foodGroupLabel);
			addPanel.add(foodGroupLabelPanel);
			addPanel.add(foodGroupComboBox);
			addPanel.add(separatorThree);
			descLabelPanel.add(descLabel);
			addPanel.add(descLabelPanel);
			addPanel.add(descTextField);
			addPanel.add(separatorFour);
			comNameLabelPanel.add(nameLabel);
			addPanel.add(comNameLabelPanel);
			addPanel.add(nameTextField);
			addPanel.add(separatorFive);
			nutrLabelPanel.add(nutrientLabel);
			addPanel.add(nutrLabelPanel);
			addPanel.add(nutrientComboBox);
			addNutrPanel.add(addNutrientButton);
			addPanel.add(addNutrPanel);
			addPanel.add(separatorSix);
			weightLabelPanel.add(weightLabel);
			addPanel.add(weightLabelPanel);
			weightPanel.add(modifierTextField);
			weightPanel.add(measurementDescriptionTextField);
			weightPanel.add(weightTextField);
			addPanel.add(weightPanel);
			addFoodPanel.add(EnterNewFoodButton);
			addPanel.add(addFoodPanel);

			// adds the addPanel to the add food frame
			addFrame.add(addPanel);

			// sets the layout of the addpanel
			addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));

			// makes all of the components and frames visible
			addFrame.setVisible(true);
			weightPanel.setVisible(true);
			weightLabelPanel.setVisible(true);
			titleLabelPanel.setVisible(true);
			NBDLabelPanel.setVisible(true);
			foodGroupLabelPanel.setVisible(true);
			descLabelPanel.setVisible(true);
			comNameLabelPanel.setVisible(true);
			nutrLabelPanel.setVisible(true);

			// sets layout of the weightPanel
			weightPanel.setLayout(new BoxLayout(weightPanel, BoxLayout.X_AXIS));
		}
	}

	/**
	 * ActionListener for when the EnterNewFoodButton is pressed
	 * @author Jonathan Sun and Felix Sung
	 */
	public class EnterNewFoodButtonListener implements ActionListener
	{
		/**
		 * Adds a new food object when the EnterNewFoodButton is pressed
		 */
		public void actionPerformed(ActionEvent e)
		{
			// adds an item to the weight list
			weightList.add(modifierTextField.getText(),
					measurementDescriptionTextField.getText(),
					weightTextField.getText());

			// Checks if all the fields have been filled out
			if (!NBDNumTextField.getText().equals("")
					&& !descTextField.getText().equals("")
					&& !nameTextField.getText().equals("")
					&& addedNutrients.getHead() != null
					&& weightList.getHead() != null)
			{
				try
				{
					// Passing in the required information to update the
					// database
					AddedFoodWriter.update(dataBase, AddedFoodWriter
							.addFood(NBDNumTextField.getText(), ((String)
									foodGroupComboBox.getSelectedItem())
											.substring(1, 5),
									descTextField.getText(),
									nameTextField.getText(), addedNutrients,
									weightList));

					// update the food table with the new food
					setSearchTable(searchTextField.getText(),
							foodGroupSearchComboBox.getSelectedIndex());
					// closes the frame
					addFrame.setVisible(false);
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}

			}
			// if the fields are not properly filled out, shows a warning
			// dialogue box and then cl
			else
			{
				JOptionPane.showMessageDialog(null,
						"Please Fill All Text Fields");
			}
		}
	}

	/**
	 * ActionListener for when the addNutrient button is pressed
	 * @author Jonathan Sun and Felix Sung
	 */
	public class AddNutrientButtonListener implements ActionListener
	{
		/**
		 * Adds the nutrients to the stored nutrients given the currently
		 * selected nutrient when the addNutrient button is pressed
		 */
		public void actionPerformed(ActionEvent e)
		{
			addedNutrients.add(((String) nutrientComboBox.getSelectedItem())
					.substring(0, 3));
		}
	}
}
