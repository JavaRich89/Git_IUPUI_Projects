import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Application {

	static public final String EOF = null; 
	  static public final int ARRAY_SIZE = 120000; 
	  static public final int REQUIRED_TOKEN_SIZE = 4;
	  static public String inputFile = "C:\\Project_Data_CSV\\Grocery_UPC_Database.csv"; 
	  static public File goodFile = new File("c:" + File.separator + "Project_Data_CSV" + File.separator + "GroceryList_Product_Data.csv"); 
	  static public File errorFile = new File("c:" + File.separator + "Project_Data_CSV" + File.separator + "suspense.txt"); 
	  static public BufferedReader inStream; 
	  static public BufferedWriter goodFileOut, errorFileOut; 
	  static public String[] inputArray;
	  
	  static public int rowsWritten = 0;
	  static public int keywordsFound = 0;
	  static public int totalRows = 0;
	  static public int notFound = 0;
	  
	  static public int CatBeveragesCount = 0;
	  static public int softDrinksCount = 0;
	  static public int fruitJuiceCount = 0;
	  static public int waterCount = 0;
	  static public int sportsDrinkCount = 0;
	  static public int coffeeCount = 0;
	  static public int specialtyDrinkCount = 0;
	  static public int beerCount = 0;
	  static public int wineCount = 0;
	  static public int spiritsCount = 0;
	  
	  static public int CatBreadBakeryCount = 0;
	  static public int breadCount = 0;
	  static public int loavesSticksCount = 0;
	  static public int rollsBunsCount = 0;
	  static public int tortillasCount = 0;
	  static public int bagelsCount = 0;
	  static public int donutsCount = 0;
	  static public int dessertsCount = 0;
	  
	  static public int CatSnacksCount = 0;
	  static public int crackersCount = 0;
	  static public int nutsCount = 0;
	  static public int cookiesCount = 0;
	  
	  static public int CatPantryCount = 0;
	  static public int condimentsCount = 0;
	  static public int cannedJarredCount = 0;
	  
	  static public int CatDairyCount = 0;
	  static public int milkCount = 0;
	  static public int cheeseCount = 0;
	  static public int yogurtCount = 0;
	  static public int butterCount = 0;
	  static public int eggsCount = 0;
	  
	  static public int CatBakingGoodsCount = 0;
	  static public int bakingCount = 0;

	  static public int CatFrozenGoodsCount = 0;
	  static public int pizzaCount = 0;
	  static public int breakfastCount = 0;
	  static public int potatoesCount = 0;
	  static public int icecreamCount = 0;
	  
	  static public int CatMeatCount = 0;
	  static public int lunchmeatCount = 0;
	  static public int poultryCount = 0;
	  static public int beefCount = 0;
	  static public int porkCount = 0;
	  static public int seafoodCount = 0;
	  
	  static public int CatProduceCount = 0;
	  static public int vegetablesCount = 0;
	  static public int fruitsCount = 0;
	  
	  //Category/subcategory/keyword arrays
	  //static public String categoryArray[];
	  //static public String subCategoryArray[];
	  //static public String keywordArray[];

	  public static void main(String args[]) throws FileNotFoundException
	  {
	    openInputFile();
	    fillInputArray();
	    openGoodFile();
	    openErrorFile();
	    transformData();
	    closeOutputFiles();
	    System.out.println("-----------------------------------------------------------------------------------------------------");
	    System.out.println("TOTAL KEYWORDS FOUND: " + keywordsFound);
	    System.out.println("TOTAL ROWS WRITTEN: " + rowsWritten);
	    System.out.println("TOTAL ROWS - KEYWORDS NOT FOUND: " + notFound);
	    System.out.println("-----------------------------------------------------------------------------------------------------");
	    System.out.println("-----------------------------------------------------------------------------------------------------");
	    System.out.println("Category Total: Beverages Found: " + CatBeveragesCount);
	    System.out.println("Soft Drinks Found: " + softDrinksCount);
	    System.out.println("Fruit Juice Found: " + fruitJuiceCount);
	    System.out.println("Water Found: " + waterCount);
	    System.out.println("Sports & Energy Drink Found: " + sportsDrinkCount);
	    System.out.println("Coffee Tea & Hot Chocolate Found: " + coffeeCount);
	    System.out.println("Specialty Drinks Found: " + specialtyDrinkCount);
	    System.out.println("Beer Found: " + beerCount);
	    System.out.println("Wine Found: " + wineCount);
	    System.out.println("Spirits Found: " + spiritsCount);
	    System.out.println("-----------------------------------------------------------------------------------------------------");
	    System.out.println("Category Total: Bread and Bakery Found: " + CatBreadBakeryCount);
	    System.out.println("Bread Found: " + breadCount);
	    System.out.println("Loaves and Breadsticks Found: " + loavesSticksCount);
	    System.out.println("Rolls and Buns Found: " + rollsBunsCount);
	    System.out.println("Tortillas & Flatbreads Found: " + tortillasCount);
	    System.out.println("Bagels & Muffins Found: " + bagelsCount);
	    System.out.println("Donuts & Pastries Found: " + donutsCount);
	    System.out.println("Desserts Found: " + dessertsCount);
	    System.out.println("-----------------------------------------------------------------------------------------------------");
	    System.out.println("Category Total: Snacks Found: " + CatSnacksCount);
	    System.out.println("Crackers Found: " + crackersCount);
	    System.out.println("Nuts Found: " + nutsCount);
	    System.out.println("Cookies Found: " + cookiesCount);
	    System.out.println("-----------------------------------------------------------------------------------------------------");
	    System.out.println("Category Total: Pantry Found: " + CatPantryCount);
	    System.out.println("Condiments Found: " + condimentsCount);
	    System.out.println("Canned and Jarred Found: " + cannedJarredCount);
	    System.out.println("-----------------------------------------------------------------------------------------------------");
	    System.out.println("Category Total: Dairy Found: " + CatDairyCount);
	    System.out.println("Milk Found: " + milkCount);
	    System.out.println("Cheese Found: " + cheeseCount);
	    System.out.println("Yogurt Found: " + yogurtCount);
	    System.out.println("Butter Found: " + butterCount);
	    System.out.println("Eggs Found: " + eggsCount);
	    System.out.println("-----------------------------------------------------------------------------------------------------");
	    System.out.println("Category Total: Baking Goods Found: " + CatBakingGoodsCount);
	    System.out.println("Baking Found: " + bakingCount);
	    System.out.println("-----------------------------------------------------------------------------------------------------");
	    System.out.println("Category Total: Frozen Foods Found: " + CatFrozenGoodsCount);
	    System.out.println("Pizza Found: " + pizzaCount);
	    System.out.println("Breakfast Found: " + breakfastCount);
	    System.out.println("Potatoes Found: " + potatoesCount);
	    System.out.println("Ice Cream Found: " + icecreamCount);
	    System.out.println("-----------------------------------------------------------------------------------------------------");
	    System.out.println("Category Total: Meat Found: " + CatMeatCount);
	    System.out.println("Lunch Meat Found: " + lunchmeatCount);
	    System.out.println("Poultry Found: " + poultryCount);
	    System.out.println("Beef Found: " + beefCount);
	    System.out.println("Pork Found: " + porkCount);
	    System.out.println("Seafood Found: " + seafoodCount);
	    System.out.println("-----------------------------------------------------------------------------------------------------");
	    System.out.println("Category Total: Produce Found: " + CatProduceCount);
	    System.out.println("Vegetables Found: " + vegetablesCount);
	    System.out.println("Fruits Found: " + fruitsCount);
	    System.exit(0);
	  } 
	  
	  public static void openInputFile()
	  {
		  File fileIn = new File(inputFile);
		  try {
			  inStream = new BufferedReader(new FileReader(fileIn));
		  }
		  catch(FileNotFoundException errorOpening) {
		      errorOpening.printStackTrace();
		      System.out.println("ERROR OPENING INPUT FILE; INFORMATION FOLLOWS:\n");
		      System.out.println("PROGRAM TERMINATING");
		      System.exit(1);
		  }
	  }
	  
	  private static void fillInputArray()
	  {
		  int i = 0;
		  String inString = null;
		  inputArray = new String[ARRAY_SIZE]; 
		  try {
				while((inString = inStream.readLine()) != EOF)
				{
					inputArray[i++] = inString;
				}
				inStream.close();
		  }
		  catch(IOException readError) {
				readError.printStackTrace();
				System.out.println("ERROR IN READING INPUT FILE");
				System.out.println("PROGRAM TERMINATING");
				System.exit(0);
		  }
	  }
	  
	  private static void openGoodFile()
	  {
		  
		  try
			{
				goodFileOut = new BufferedWriter(new FileWriter(goodFile));
			}
			catch(IOException errorOpening)
			{
				System.out.println("ERROR OPENING OUTPUT FILE");
				System.out.println("PROGRAM TERMINATING");
				errorOpening.printStackTrace();
				System.exit(0);
			}
	  }
	  
	  private static void openErrorFile()
	  {
		  try
			{
				errorFileOut = new BufferedWriter(new FileWriter(errorFile));
			}
			catch(IOException errorOpening)
			{
				System.out.println("ERROR OPENING OUTPUT FILE");
				System.out.println("PROGRAM TERMINATING");
				errorOpening.printStackTrace();
				System.exit(0);
			}
	  } 
	  
	  private static void transformData()
	  {
		  String errorLine, upc14, upc12, brand, name, category = "", subCategory = "";
		  int i = 0;
		  boolean setCategory = false;
		  
		  
		  //String categoryArray[] = {"Beverages","Bread and Bakery","Snacks","Pantry","Dairy","Baking Goods","Frozen Foods","Meat","Produce"};
		  /////String subCategoryArray[] = {"Soft Drinks","Fruits Juice","Water","Sports & Energy Drinks","Coffee, Tea & Chocolate","Specialty Drinks",
				  ////"Beer","Wine","Spirits","Sliced Bread","Loaves & Breadsticks","Rolls & Buns","Tortillas & Flatbreads",""};
		  
		  
		  
		  while(inputArray[i] != null) {
			  StringTokenizer tokens = new StringTokenizer(inputArray[i], ",");
			  if(tokens.countTokens() > 6) {
				  errorLine = inputArray[i];
				  writeLineToErrorFille(errorLine);
				  System.out.println("Incorrect Token Size.");
				  i++;
				  continue;
			  }
			  else {
				  try {
					  while (tokens.hasMoreTokens()) { 
						  
						setCategory = false;  
						  
					  	upc14 = tokens.nextToken().toString();
					  	upc12 = tokens.nextToken().toString();
					  	brand = tokens.nextToken().toString();
					  	name = tokens.nextToken().toString();
					  	//Account for commas in Name
					  	if(tokens.hasMoreTokens()) {
					  		String name2 = tokens.nextToken().toString();
					  		name = name + name2;
					  	}
					  	if(tokens.hasMoreTokens()) {
					  		String name3 = tokens.nextToken().toString();
					  		name = name + name3;
					  	}
					  	
					  	
					  	//CALCULATE CATEGORY AND SUBCATEGORY
						//Beverages
						String softDrinkArray[] = {"soda","pepsi","coca-cola","mountain dew","dr. pepper","sprite","sunkist","crush","fanta","big red","fresca","mellow yellow","sun drop"};
					    String fruitJuiceArray[] = {"ocean spray","welch's","minute maid","juicy juice","capri sun","kool-aid","tropicana","bolthouse farms","sunnyd","dole","naked","country time",
								  					"snapple","arizona","little hug","old orchard","florida's natural","v8","ready leaf"};
						String waterArray[] = {"bottled water","ice mountain","sparkling ice","vitaminwater","aquafina","propel","dasani","evian","pure life","essentia","lifeaid","smartwater"};
						String sportsDrinkArray[] = {"gatorade", "red bull", "monster energy", "5-hour energy", "powerade", "rockstar", "g2", "amp", "nos"};
						String coffeeArray[] = {"coffee", "tea", "hot chocolate", "starbucks", "folgers", "maxwell house", "mccafe", "frozen bean", "keurig", "high brew", "java"};
						String specialtyDrinkArray[] = {"crystal light", "sodastream", "mio", "nesquik", "true lemon", "tang", "nestea", "ovaltine"};
						String beerArray[] = {"lager", "ale", "ipa", "black & tan", "bud light", "coors", "mike's hard", "michelob", "budweiser", "corona", "busch", "miller", "modelo", "sierra nevada", "samuel adams", 
								  					"angry ochard", "yuengling", "dos equis", "heineken", "leinenkugel's", "natural light", "redds", "seagram's", "blue moon", "killian's", "keystone", "landshark"};
						String wineArray[] = {"oak leaf", "franzia", "arbor mist", "sutter home", "barefoot", "woodbridge", "beringer", "menage a trois", "bota box", "bella bolle", "carlo rossi", "easleys", "alamos", "oliver's"};
						String spiritsArray[] = {"whiskey", "rum", "vodka", "brandy", "amaretto", "tequila", "moonshine"};
						  
						//Bread and Bakery
						String breadArray[] = {"bread"};
						String loavesSticksArray[] = {"loave", "breadstick"};
						String rollsBunsArray[] = {"rolls","buns"};
						String tortillaArray[] = {"tortillas", "flatbread"};
						String bagelArray[] = {"bagels", "muffins"};
						String donutArray[] = {"donut", "pastry"};
						String dessertArray[] = {"brownies", "pie", "cake"};
						  
						//Snacks
						String crackersArray[] = {"crackers","cracker"};
						String nutsArray[] = {"almond", "nuts", "cashews", "mixed nuts"};
						String cookieArray[] = {"cookies", "cookie"};
						  
						//Pantry
						String condimentsArray[] = {"ketchup", "mustard", "peanut butter", "dressing", "hot sauce", "marinade", "gravy", "mayonnaise", "nutella", "jelly", "worcestershire", "horseradish", "marmalades", "tarter"};
						String cannedJarredArray[] = {"canned", "spaghetti sauce", "jarred"};
						  
						//Dairy
						String milkArray[] = {"milk"};
						String cheeseArray[] = {"cheese"};
						String yogurtArray[] = {"yogurt"};
						String butterArray[] = {"butter"};
						String eggsArray[] = {"eggs"};
						  
						//Baking Goods
						String bakingArray[] = {"flour", "sugar", "cake mix", "extract", "oil", "brownie mix", "pie crust", "pie filling", "food coloring", "icing"};
						  
						//Frozen Foods
						String pizzaArray[] = {"pizza"};
						String breakfastArray[] = {"waffles"};
						String potatoesArray[] = {"tater tots", "fries", "wedges", "hashbrowns", "french fried", "golden crinkles"};
						String icecreamArray[] = {"ice cream", "pop sicle", "cool whip", "pie", "fudge bars"};
						  
						//Meat
						String lunchmeatArray[] = {"lunch meat"};
						String poultryArray[] = {"poultry", "chicken", "turkey"};
						String beefArray[] = {"beef", "angus", "t-bone", "tbone", "ribeye", "new york strip", "top sirloin", "chuck roast", "london broil", "cube steak", "steak", "ground chuck"};
						String porkArray[] = {"pork", "pork sausage", "pork chops", "pork loin", "bacon"};
						String seafoodArray[] = {"shrimp", "scallops", "salmon", "tilapia", "lobster", "krab", "oysters", "clams", "calamari"};
						  
						//Store
						String vegetableArray[] = {"artichoke", "eggplant", "asparagus", "legumes", "peas", "broccoli", "brussels sprouts", "cabbage", "cauliflower", "celery", "collard greens", "kale", "spinach", "anise", 
								  "basil", "cilantro", "lettuce", "mushroom", "okra", "Chives", "Garlic", "onion", "parsley", "bell pepper", "chili pepper", "Jalapeño", "Habanero", "pepper", "rhubarb", "beet", "carrot", "water chestnut", 
								  "ginger", "parsnip", "wasabi", "radish", "potato", "yam", "turnip", "corn", "melon", "zucchini", "cucumber", "delicata", "patty pans", "pumpkin", "squash", "tat soi", "tomato", "watercress"};
						  
						String fruitArray[] = {"apples", "apricots", "avocados", "bananas", "boysenberries", "blueberries", "cherries", "Cantaloupe", "Clementine", "Cucumbers", "Dates", "Dewberries", "Dragon Fruit", "Elderberry", "Eggfruit", "Fig", "Grapefruit", 
								  "Grapes", "Gooseberries", "Guava", "Honeydew melon", "Hackberry", "Prune", "Plum", "Jackfruit", "Kiwi", "Lime", "Lemon", "Lychee", "Mango", "Mandarin Orange", "Mulberry", "Melon", "Nectarine", "Olive", "Oranges", "Papaya", "Pear", "Peach", 
								  "Pomegranate", "Pineapple", "Rambutan", "Raspberries", "Strawberries", "Tomato", "Tangerine", "Watermelon", "Wolfberry", "White Mulberry", "Yellow Passion Fruit", "Yunnan Hackberry", "Yangmei"};
					  	
						for (int j = 0; j < softDrinkArray.length; j++) {
							String keyword = softDrinkArray[j];
							String checkName = name.toLowerCase();
							if (checkName.contains(" " +keyword+ " ")) {
								category = "Beverages";
								subCategory = "Soft Drinks";
								keywordsFound++;
								CatBeveragesCount++;
								softDrinksCount++;
								setCategory = true;
								break;
							} 
						}
						
						if(!setCategory) {
							for (int j = 0; j < fruitJuiceArray.length; j++) {
								String keyword = fruitJuiceArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Beverages";
									subCategory = "Fruit Juice";
									keywordsFound++;
									CatBeveragesCount++;
									fruitJuiceCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < waterArray.length; j++) {
								String keyword = waterArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Beverages";
									subCategory = "Water";
									keywordsFound++;
									CatBeveragesCount++;
									waterCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < sportsDrinkArray.length; j++) {
								String keyword = sportsDrinkArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Beverages";
									subCategory = "Sports & Energy Drinks";
									keywordsFound++;
									CatBeveragesCount++;
									sportsDrinkCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < coffeeArray.length; j++) {
								String keyword = coffeeArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Beverages";
									subCategory = "Coffee Tea & Hot Chocolate";
									keywordsFound++;
									CatBeveragesCount++;
									coffeeCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < specialtyDrinkArray.length; j++) {
								String keyword = specialtyDrinkArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Beverages";
									subCategory = "Specialty Drinks";
									keywordsFound++;
									CatBeveragesCount++;
									specialtyDrinkCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < beerArray.length; j++) {
								String keyword = beerArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Beverages";
									subCategory = "Beer";
									keywordsFound++;
									CatBeveragesCount++;
									beerCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < wineArray.length; j++) {
								String keyword = wineArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Beverages";
									subCategory = "Wine";
									keywordsFound++;
									CatBeveragesCount++;
									wineCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < spiritsArray.length; j++) {
								String keyword = spiritsArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Beverages";
									subCategory = "Spirits";
									keywordsFound++;
									CatBeveragesCount++;
									spiritsCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < breadArray.length; j++) {
								String keyword = breadArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Bread & Bakery";
									subCategory = "Bread";
									keywordsFound++;
									CatBreadBakeryCount++;
									breadCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < loavesSticksArray.length; j++) {
								String keyword = loavesSticksArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Bread & Bakery";
									subCategory = "Loaves & Breadsticks";
									keywordsFound++;
									CatBreadBakeryCount++;
									loavesSticksCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < rollsBunsArray.length; j++) {
								String keyword = rollsBunsArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Bread & Bakery";
									subCategory = "Rolls & Buns";
									keywordsFound++;
									CatBreadBakeryCount++;
									rollsBunsCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < tortillaArray.length; j++) {
								String keyword = tortillaArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Bread & Bakery";
									subCategory = "Tortillas & Flatbreads";
									keywordsFound++;
									CatBreadBakeryCount++;
									tortillasCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < bagelArray.length; j++) {
								String keyword = bagelArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Bread & Bakery";
									subCategory = "Bagels & Muffins";
									keywordsFound++;
									CatBreadBakeryCount++;
									bagelsCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < donutArray.length; j++) {
								String keyword = donutArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Bread & Bakery";
									subCategory = "Donuts & Pastries";
									keywordsFound++;
									CatBreadBakeryCount++;
									donutsCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < dessertArray.length; j++) {
								String keyword = dessertArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Bread & Bakery";
									subCategory = "Desserts";
									keywordsFound++;
									CatBreadBakeryCount++;
									dessertsCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < crackersArray.length; j++) {
								String keyword = crackersArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Snacks";
									subCategory = "Crackers";
									keywordsFound++;
									CatSnacksCount++;
									crackersCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < nutsArray.length; j++) {
								String keyword = nutsArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Snacks";
									subCategory = "Nuts";
									keywordsFound++;
									CatSnacksCount++;
									nutsCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < cookieArray.length; j++) {
								String keyword = cookieArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Snacks";
									subCategory = "Cookies";
									keywordsFound++;
									CatSnacksCount++;
									cookiesCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < condimentsArray.length; j++) {
								String keyword = condimentsArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Pantry";
									subCategory = "Condiments";
									keywordsFound++;
									CatPantryCount++;
									condimentsCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < cannedJarredArray.length; j++) {
								String keyword = cannedJarredArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Pantry";
									subCategory = "Canned and Jarred";
									keywordsFound++;
									CatPantryCount++;
									cannedJarredCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < milkArray.length; j++) {
								String keyword = milkArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Dairy";
									subCategory = "Milk";
									keywordsFound++;
									CatDairyCount++;
									milkCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < cheeseArray.length; j++) {
								String keyword = cheeseArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Dairy";
									subCategory = "Cheese";
									keywordsFound++;
									CatDairyCount++;
									cheeseCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < yogurtArray.length; j++) {
								String keyword = yogurtArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Dairy";
									subCategory = "Yogurt";
									keywordsFound++;
									CatDairyCount++;
									yogurtCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < butterArray.length; j++) {
								String keyword = butterArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Dairy";
									subCategory = "Butter";
									keywordsFound++;
									CatDairyCount++;
									butterCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < eggsArray.length; j++) {
								String keyword = eggsArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Dairy";
									subCategory = "Eggs";
									keywordsFound++;
									CatDairyCount++;
									eggsCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < bakingArray.length; j++) {
								String keyword = bakingArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Baking Goods";
									subCategory = "Baking";
									keywordsFound++;
									CatBakingGoodsCount++;
									bakingCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < pizzaArray.length; j++) {
								String keyword = pizzaArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Frozen Foods";
									subCategory = "Pizza";
									keywordsFound++;
									CatFrozenGoodsCount++;
									pizzaCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < breakfastArray.length; j++) {
								String keyword = breakfastArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Frozen Foods";
									subCategory = "Breakfast";
									keywordsFound++;
									CatFrozenGoodsCount++;
									breakfastCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < potatoesArray.length; j++) {
								String keyword = potatoesArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Frozen Foods";
									subCategory = "Potatoes";
									keywordsFound++;
									CatFrozenGoodsCount++;
									potatoesCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < icecreamArray.length; j++) {
								String keyword = icecreamArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Frozen Foods";
									subCategory = "Ice Cream";
									keywordsFound++;
									CatFrozenGoodsCount++;
									icecreamCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < lunchmeatArray.length; j++) {
								String keyword = lunchmeatArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Meat";
									subCategory = "Lunch Meat";
									keywordsFound++;
									CatMeatCount++;
									lunchmeatCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < poultryArray.length; j++) {
								String keyword = poultryArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Meat";
									subCategory = "Poultry";
									keywordsFound++;
									CatMeatCount++;
									poultryCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < beefArray.length; j++) {
								String keyword = beefArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Meat";
									subCategory = "Beef";
									keywordsFound++;
									CatMeatCount++;
									beefCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < porkArray.length; j++) {
								String keyword = porkArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Meat";
									subCategory = "Pork";
									keywordsFound++;
									CatMeatCount++;
									porkCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < seafoodArray.length; j++) {
								String keyword = seafoodArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Meat";
									subCategory = "Seafood";
									keywordsFound++;
									CatMeatCount++;
									seafoodCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < vegetableArray.length; j++) {
								String keyword = vegetableArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Produce";
									subCategory = "Vegetable";
									keywordsFound++;
									CatProduceCount++;
									vegetablesCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						if(!setCategory) {
							for (int j = 0; j < fruitArray.length; j++) {
								String keyword = fruitArray[j];
								String checkName = name.toLowerCase();
								if (checkName.contains(" " +keyword+ " ")) {
									category = "Produce";
									subCategory = "Fruit";
									keywordsFound++;
									CatProduceCount++;
									fruitsCount++;
									setCategory = true;
									break;
								} 
							}
						}
						
						
						
						
						if(setCategory) {
							writeLineToGoodFille(upc14, upc12, brand, name, category, subCategory);
							rowsWritten++;
							System.out.println("Row written.");
							i++;
						} else {
							notFound++;
							i++;
							System.out.println("Row: " + i + " no keyword found.");
							continue;
						}
						
					  }
				  }
				  catch(NumberFormatException wrongFormat) {
						errorLine = inputArray[i];
						writeLineToErrorFille(errorLine);
						i++;
						continue;	
				  }
			  }
		  }
	  } 
	  
	  public static void writeLineToErrorFille(String badLine)
	  {
	    String outString = badLine;
	    try {
	    	errorFileOut.write(outString);
	    	errorFileOut.newLine();
	    }
	    catch(IOException writeError) {
			writeError.printStackTrace();
			System.out.println("PROGRAM TERMINATING; ERROR WRITING TO OUT FILE");
		}
	  } 

	  public static void writeLineToGoodFille(String iupc14, String iupc12, String ibrand, String iname, String icategory, String isubCategory)
	  {
		  String goodLine = (iupc14+","+iupc12+","+ibrand+","+iname+","+icategory+","+isubCategory);
		  try {
			  goodFileOut.write(goodLine);
			  goodFileOut.newLine();
		  }
		  catch(IOException writeError) {
			  writeError.printStackTrace();
			  System.out.println("PROGRAM TERMINATING; ERROR WRITING TO OUT FILE");
		  }
	  } 

	  public static void closeOutputFiles()
	  {
		  try {
			  goodFileOut.close();
			  errorFileOut.close();
		  } 
		  catch(IOException closeProblem) {
		      closeProblem.printStackTrace();
		      System.out.println("ERROR CLOSING FILE.");
		      System.out.println("PROGRAM TERMINATING");
		      System.exit(0);
		  } 
	  }

}
