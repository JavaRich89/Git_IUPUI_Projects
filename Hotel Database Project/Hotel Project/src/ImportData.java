import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;






public class ImportData {
	static public final String EOF = null;
	  static public final int ARRAY_SIZE = 1000;
	  //test input path data
	  static public String inputPath = "C:\\Users\\The Rich Family\\eclipse-workspace-FX\\Hotel Project\\testdata";
	  //Main file path
	  //static public String inputPath = "C:\\Users\\The Rich Family\\eclipse-workspace-FX\\Hotel Project\\data";
	  static public File csvFile = new File("C:\\Users\\The Rich Family\\eclipse-workspace-FX\\Hotel Project\\csvoutput\\reviews_data.csv");
	  static public File errorFile = new File("C:\\Users\\The Rich Family\\eclipse-workspace-FX\\Hotel Project\\csvoutput\\suspense.txt");
	  static public BufferedReader inStream;
	  static public BufferedWriter csvFileOut, errorFileOut;
	  static public String inputArray[], fileNameArray[], ouputArray[];
	  static public int errorCounter = 1;



	  public static void importReviews()
	  {
		  openCSVFile();
		  openErrorFile();
		  File [] subFiles = null; //
		  File mainDir = new File(inputPath); //main input path
		  if (mainDir.isDirectory()) {
			  File [] mainDirList = mainDir.listFiles(); //lists sub directories
			  for (File subDir : mainDirList) { //for each sub directory in main directory
				  if (subDir.isDirectory()) {
					  subFiles = subDir.listFiles(); //lists all files in sub directory
					  for (File file : subFiles) { //selects individual file
						  if (file.isFile()) {
							  //Pull Location, city, Hotel Name from filename
							  extractFileName(file);
							  //READ, PARSE, WRITE Reviews
							  try {
								  inStream = new BufferedReader(new FileReader(file));
								  fillInputArray();
								  parseData();
							  }
							  catch(FileNotFoundException errorOpening) {
							      errorOpening.printStackTrace();
							      System.out.println("ERROR OPENING INPUT FILE; INFORMATION FOLLOWS:\n");
							      System.out.println("PROGRAM TERMINATING");
							      System.exit(1);
							  }
						  }
					  }
				  }
			  }
		  }
		  closeOutputFiles();
	  }

	  static void fillInputArray()
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

	  static void openCSVFile()
	  {

		  try
			{
				csvFileOut = new BufferedWriter(new FileWriter(csvFile));
			}
			catch(IOException errorOpening)
			{
				System.out.println("ERROR OPENING OUTPUT FILE");
				System.out.println("PROGRAM TERMINATING");
				errorOpening.printStackTrace();
				System.exit(0);
			}
	  }

	  static void openErrorFile()
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
	  //special cases Canada, san francisco
	  static void extractFileName(File file) {
		  String country = "", state = "", city = "", hotelName = "";
		  fileNameArray = new String[4];
		  String fileName = file.getName().toString();
		  String[] line = fileName.split("_");

		  //testing
		  //System.out.println(fileName);
		  //for (int i = 0; i<line.length; i++) {
			//  System.out.println(line[i]);
		  //}

		  for (int i = 0; i < line.length; i++) {
			  if (i == 0) {
				  if (line[i].contains("usa") || line[i].contains("can")) {
					  country = line[i].substring(0, 3).toUpperCase();
				  }
				  else if (line[i].contains("uk")) {
					  country = line[i].substring(0, 2).toUpperCase();
				  }
				  else {
					  country = line[i].substring(0, 1).toUpperCase() + line[i].substring(1);
				  }
				  //testing
				  //System.out.println("COUNTRY CHECK: " +country);
			  }
			  if (i == 1) {
				  if (country.contains("China") || country.contains("India") || country.contains("CAN")) {
					  city = line[i].substring(0, 1).toUpperCase() + line[i].substring(1);
					  if (city.equals("New-delhi")) {
						  city = "New Delhi";
					  }
					  state = "N/A";

				  }
				  else if (country.contains("USA") || country.contains("UK")) {
					  if (line[i].contains("new york city")) {
						  city = "New York City";
						  state = "New York";
					  }
					  else if (line[i].contains("san francisco")) {
						  city = "San Francisco";
						  state = "California";
					  }
					  else {
						  state = line[i].substring(0, 1).toUpperCase() + line[i].substring(1);
					  }
				  }
				  else if (country.contains("CAN")) {
					  city = line[i].substring(0, 1).toUpperCase() + line[i].substring(1);
					  state = null;
				  }
				  //testing
				  //System.out.println("STATE/CITY CHECK: STATE = " + state + " or City = " + city);
			  }
			  if (i == 2) {
				  if (state == null) {
					  hotelName = line[i].substring(0, 1).toUpperCase() + line[i].substring(1);
				  }
				  else if (state.contains("Illinois") || state.contains("Nevada") || state.contains("England")) {
					  city = line[i].substring(0, 1).toUpperCase() + line[i].substring(1);
					  if (city.contains("Las-vegas")) {
						  city = "Las Vegas";
					  }
				  }
				  else {
					  //start parsing hotel name
					  hotelName = line[i].substring(0, 1).toUpperCase() + line[i].substring(1);
				  }
				//testing
				 //System.out.println("CITY/HOTEL NAME CHECK: CITY = " + city + " or HOTEL1 = " + hotelName);
			  }
			  if (i == 3) {
				  if (state == null) {
					  hotelName = hotelName + " " + line[i].substring(0, 1).toUpperCase() + line[i].substring(1);
				  }
				  else if (state.contains("Illinois") || state.contains("Nevada") || state.contains("England")) {
					  hotelName = line[i].substring(0, 1).toUpperCase() + line[i].substring(1);
				  }
				  else {
					  hotelName = hotelName + " " + line[i].substring(0, 1).toUpperCase() + line[i].substring(1);
				  }

			  }
			  if (i >= 4) {
				  hotelName = hotelName + " " + line[i].substring(0, 1).toUpperCase() + line[i].substring(1);

			  }
		  }


		  //build fileNameArray
		  for (int i = 0; i<fileNameArray.length; i++) {
			  if (i == 0) {
				  fileNameArray[i] = country;
			  }
			  else if (i == 1) {
				  fileNameArray[i] = state;
			  }
			  else if (i == 2) {
				  fileNameArray[i] = city;
			  }
			  else if (i == 3) {
				  fileNameArray[i] = hotelName;
			  }
		  }

	  }

	  public static void parseData()
	  {
		  String errorLine, postDate, postTitle, postContent;
		  int i = 0;
		  while(inputArray[i] != null) {
			  StringTokenizer tokens = new StringTokenizer(inputArray[i], "\t");
			  if(tokens.countTokens() != 2 && tokens.countTokens() != 3) {
				  errorLine = inputArray[i];
				  writeLineToErrorFille(errorLine, 1);
				  i++;
				  continue;
			  }
			  else if (tokens.countTokens() == 2) {
				  try {
					  while (tokens.hasMoreTokens()) {
					  	postDate = tokens.nextToken().toString();
					  	postTitle = "N/A";
						postContent = tokens.nextToken().toString();
						//replaces commas with % to escape csv commas, % are parsed back to commas in oracle
						StringBuilder content = new StringBuilder(postContent);
						for (int index = 0; index < content.length(); index++) {
						    if (content.charAt(index) == ',') {
						        content.setCharAt(index, '%');
						    }
						}
						postContent = content.toString();
						//If postContent starts with a "?", write to suspense. Skips a lot of void reviews to improve
						//processing time when loaded into the database. Cuts approx 20k of useless lines
						if (postContent.indexOf('?') == 0) {
							errorLine = inputArray[i];
							writeLineToErrorFille(errorLine, 2);
							i++;
							continue;
						}
						else {
						//write tokens to the files
						writeLineToGoodFille(postDate, postTitle, postContent);
						i++;
						}
					  }
				  }
				  catch(NumberFormatException wrongFormat) {
						errorLine = inputArray[i];
						writeLineToErrorFille(errorLine, 2);
						i++;
						continue;
				  }
			  }
			  else {
				  try {
					  while (tokens.hasMoreTokens()) {
					  	postDate = tokens.nextToken().toString();
					  	postTitle = tokens.nextToken().toString();
						postContent = tokens.nextToken().toString();
						//replaces commas with % to escape csv commas, % are parsed back to commas in oracle
						StringBuilder content = new StringBuilder(postContent);
						if (content.length() >= 32000) {
							content.insert(32000, ',');
							if (content.length() >= 64000) {
								content.insert(64000, ',');
								if (content.length() >= 96000) {
									content.insert(96000, ',');
									if (content.length() >= 128000) {
										content.insert(128000, ',');
									}
								}
							}
						}
						for (int index = 0; index < content.length(); index++) {
						    if (content.charAt(index) == ',') {
						        content.setCharAt(index, '%');
						    }
						}
						postContent = content.toString();
						//write tokens to the files
						writeLineToGoodFille(postDate, postTitle, postContent);
						i++;
					  }
				  }
				  catch(NumberFormatException wrongFormat) {
						errorLine = inputArray[i];
						writeLineToErrorFille(errorLine, 2);
						i++;
						continue;
				  }
			  }
		  }
	  }

	  public static void writeLineToErrorFille(String badLine, int type)
	  {
	    String outString = badLine;
	    String errorCount = "";
	    String fileName = "";
	    String errorType = "";
	    try {
	    	for (int i = 0; i<fileNameArray.length; i++) {
	    		fileName = fileName + " " + fileNameArray[i];
	    	}
	    	fileName = fileName + " = ";
	    	errorCount = Integer.toString(errorCounter) + " ";
	    	errorType = " %%Error Type = " + Integer.toString(type) + "%% ";
	    	errorFileOut.write(errorCount);
	    	errorFileOut.write(fileName);
	    	errorFileOut.write(errorType);
	    	errorFileOut.write(outString);
	    	errorCounter++;
	    	errorFileOut.newLine();
	    }
	    catch(IOException writeError) {
			writeError.printStackTrace();
			System.out.println("PROGRAM TERMINATING; ERROR WRITING TO OUT FILE");
		}
	  }

	  //changed to not use this function
	  public static void writeFileNameFille() {
		  //append ","
		  StringBuilder builder = new StringBuilder();
		  for(int i = 0; i < fileNameArray.length; i++) {
			  if (fileNameArray[i] != null) {
				  builder.append(fileNameArray[i]+"");
				  if(i < 4) {
					  builder.append(",");
				  }
			  }
			  //writes over null rows with the next row (eliminating null rows)
			  else {
				  //Skips NULL rows
				  while (fileNameArray[i] == null) {
					  i++;
				  }
				  builder.append(fileNameArray[i]+"");
				  if(i < 4) {
					  builder.append(",");
				  }
			  }
		  }
		  //write to file
		  try {
			  csvFileOut.write(builder.toString());
			  //csvFileOut.newLine();
		  }
		  catch(IOException writeError) {
			  writeError.printStackTrace();
			  System.out.println("PROGRAM TERMINATING; ERROR WRITING TO OUT FILE");
		  }
	  }

	  public static void writeLineToGoodFille(String postDate, String postTitle, String content)
	  {
		  String goodLine = (postDate+", "+postTitle+", "+content);
		  try {
			  writeFileNameFille();
			  csvFileOut.write(goodLine);
			  csvFileOut.newLine();
		  }
		  catch(IOException writeError) {
			  writeError.printStackTrace();
			  System.out.println("PROGRAM TERMINATING; ERROR WRITING TO OUT FILE");
		  }
	  }

	  public static void closeOutputFiles()
	  {
		  try {
			  csvFileOut.close();
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
