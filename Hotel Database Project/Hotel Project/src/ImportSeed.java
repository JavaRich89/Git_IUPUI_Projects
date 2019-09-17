import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ImportSeed {

	static ArrayList<String> lineCSV = new ArrayList<String>();
	private static String fileInput = "C:\\Users\\The Rich Family\\eclipse-workspace-FX\\Hotel Project\\seed\\seed.txt";
	private static final String FILEOUTPUT = "C:\\Users\\The Rich Family\\eclipse-workspace-FX\\Hotel Project\\csvoutput\\seed.csv";

	public static void importSeed() {

		try (BufferedReader br = new BufferedReader(new FileReader(fileInput))) {

			String sCurrentLine;

			//read text file and insert into lineCSV array list
			while ((sCurrentLine = br.readLine()) != null) {
				//split string line into array
				String str = sCurrentLine;
				String[] line = str.split(",|-|\\+");

				//test
				//for (int i = 0; i < line.length; i++) {
				//	System.out.println(line[i]);
				//}

				//insert line array into arraylist
				for (int i = 0; i < line.length; i++) {
					lineCSV.add(line[i]);
				}
			};

			//TEST
			//System.out.println(lineCSV);

		} catch (IOException e) {
			e.printStackTrace();
		};
		writeData(lineCSV);

	}

	public static void writeData(ArrayList<String> lines) {

		//get size of array list
		int size = lines.size();

		//initialize 2D array
		String[][] CSV = new String[size][3];

		//initialize input variables
		int catID = 0;
		String seedWord = "";
		String polarity = "";

		//convert array list into CSV 2d array
		for (int i = 0; i < CSV.length; i++) {

			//checks polarity. sets polarity then skips them
			if (lines.get(i).contains("*") || lines.get(i).contains("positive") || lines.get(i).contains("negative")) {
				if (lines.get(i).contains("*")) {
					catID++;
				}
				if (lines.get(i).contains("positive")) {
					polarity = "+1";
				}
				if (lines.get(i).contains("negative")) {
					polarity = "-1";
				}
				continue;
			}

			for (int j = 0; j < CSV.length; j++) {
				//inserts elements into proper places in 2d array
				if (j == 0) {
					//convert catID to string to store in array row
					String stringCatID = Integer.toString(catID);
					CSV[i][j] = stringCatID;
				}
				else if (j == 1) {
					//grab seed word and store in array row
					seedWord = lines.get(i).toString();
					CSV[i][j] = seedWord;
				}
				else if (j == 2) {
					//add polarity
					CSV[i][j] = polarity;
				}
			}
		}


		//format CSV array for writing (remove null rows, append for writing to csv)
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < CSV.length; i++) {
			for(int j = 0; j < 3; j++) {
				if (CSV[i][j] != null) {
				  builder.append(CSV[i][j]+"");
				  if(j < 3 - 1) {
					  builder.append(",");
				  }
				}
				//writes over null rows with the next row (eliminating null rows)
				else {
					//Skips NULL rows
					while (CSV[i][j] == null) {
					i++;
					}
					builder.append(CSV[i][j]+"");
					if(j < 3) {
						builder.append(",");
					}
				}
		    }
		    builder.append("\n");
		}

		//TEST
		//System.out.println(builder);

		//write formatted 2D array to csv file
		File csvFile = new File(FILEOUTPUT);

		//check to see if file already exists
		if (!csvFile.exists()) {
			try {
				FileWriter csvW = new FileWriter(csvFile);
				BufferedWriter buffW = new BufferedWriter(csvW);

				buffW.write(builder.toString());
				buffW.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("File already exists.");
		}
	}
}
