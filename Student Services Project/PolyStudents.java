/***************************************************************************** 
 * Author: Jeremy Rich
 * Course and Section: CIT 38800 Section 24579
 * Assignment Number: Four
 * Due Date and Time: 12/11/18 by 8:00 AM
 * File Name: PolyStudents.java
 * Description:  This is the modified copy of InternationalTest that implements
 * polymorphism.
 * University student data project. It does the following:
 * 1: Opens the valid data file named allstudents.dat
 * 2: Reads the file into a String array
 * 3: Tokenizes each string
 * 4: Uses the tokens to construct objects of BasicStudent and
 * InternationalStudent classes.
 * 5: Places each object into an array with type of Students
 * 6: Prints the data from each object to the screen for testing purposes
 * This class also uses "try with resources" to auto-close all files opened
 * AND has all methods set to private to help retain data integrity
 * ***************************************************************************/ 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class PolyStudents 
{
  private static int lineCount; // to hold the record count of the data file
  private static Students[] allStudents; // object array
  private static int reconNumber; // to hold the record count in the recon file
  private static File dataFile = new File("c:" + File.separator + "student" +
          File.separator + "allstudents.dat"); // path to data input file
  private static File reconFile = new File("c:" + File.separator + "student" +
          File.separator + "allstudents.rec"); // path to the recon file
  private static String inputArray[]; // to hold initial input
  
  public static void main(String[] args) 
  {
    getReconCount(); // method call
    countFileLines(); // method call
    if(reconNumber != lineCount)
      exitWithReconError(); // method call if count does not match
    fillInputArray(); // method call
    instantiateObjects(); // method call
    testInstantiations(); // method call
    System.exit(0); // call to exit program
  }
  
  private static void getReconCount() // gets recon file count
  { // uses try with resources below
    try(BufferedReader readRec = new BufferedReader(new FileReader(reconFile)))
    {
      reconNumber = Integer.parseInt(readRec.readLine());
    } // end try block
    catch(IOException readError)
    {
      System.out.println("ERROR USING RECON FILE; INFORMATION FOLLOWS:\n");
			System.out.println("PROGRAM TERMINATING");
      readError.printStackTrace();
			System.exit(1);
    } // end catch block
  } // end getReconCount()
          
  private static void countFileLines() // counts the lines in the data file
  { // uses try with resources below
    try(BufferedReader count = new BufferedReader(new FileReader(dataFile)))
    { // uses try with resources to auto-close file
      while(count.readLine() != null)
        lineCount++;
    } // end try
    catch(IOException countError)
    {
      System.out.println("ERROR READING DATA FILE FOR COUNT..." + ""
              + "INFORMATION FOLLOWS:\n");
			System.out.println("PROGRAM TERMINATING");
      countError.printStackTrace();
			System.exit(1);
    } // end catch
  } // end method
 
  private static void exitWithReconError() // program exit with bad count
  {
    System.err.println("ERROR!\n" + "THE RECONCILIATION FILE COUNT DOES NOT " +
            "MATCH THE DATA FILE LINE COUNT\nPLEASE CONTACT YOUR SYSTEM " + 
            "ADMINISTRATOR\nRECONCILIATION COUNT: " + reconNumber +
            "\nDATA FILE COUNT:" + lineCount);
    System.exit(99);
  }
 
  private static void fillInputArray() // reads input file into a String array
  {
    inputArray = new String[reconNumber];
		try(BufferedReader inStream = new BufferedReader(new FileReader(dataFile)))
		{
      for(int index = 0; index < reconNumber; index++)
        inputArray[index] = inStream.readLine();
		} // end try block
		catch(IOException readError)
		{
			System.out.println("ERROR IN READING INPUT FILE: INFORMATION FOLLOWS");
			System.out.println("PROGRAM TERMINATING");
      readError.printStackTrace();
			System.exit(1);
		} // end catch block
  } // end readInputFile method
  
  private static void instantiateObjects() // instantiates objects into an array
  {
    allStudents = new Students[reconNumber];
    // The following 5 variables are not needed, but were created for clarity
    String fullName, lastName, firstName, country, visa;
    int age;
    double gpa;
    // End of clarity section //
    for(int index = 0; index < reconNumber; index++)
    {
      StringTokenizer tokens = new StringTokenizer(inputArray[index], "|");
      // The following is not needed, but created for clarity //
      fullName = tokens.nextToken();
      lastName = tokens.nextToken();
      firstName = tokens.nextToken();
      gpa = Double.parseDouble(tokens.nextToken());
      age = Integer.parseInt(tokens.nextToken());
      // If more tokens are present in the line, it is an InternationalStudent
      // else, it is a BasicStudent
      if (tokens.hasMoreTokens()) {
    	  country = tokens.nextToken();
          visa = tokens.nextToken();
          allStudents[index] = new InternationalStudent(fullName, lastName, firstName, 
                  age, gpa, country, visa);
      }
      else {
    	  allStudents[index] = new BasicStudent(fullName, lastName, firstName, 
                  age, gpa);
      }
    } // end for loop
  } // end method instantiateObjects()
  
  private static void testInstantiations() // tests objects via simple display
  {
    for(int index = 0; index < reconNumber; index++)
    {
      //changed array name
      System.out.println("Full Name: " + allStudents[index].getFullName());
      System.out.println("Last Name: " + allStudents[index].getLastName());
      System.out.println("First Name: " + allStudents[index].getFirstName());
      System.out.println("Age: " + allStudents[index].getAge());
      System.out.println("GPA: " + allStudents[index].getGpa());
      System.out.println("Home Country: " + allStudents[index].getHomeCountry());
      System.out.println("Visa Number: " + allStudents[index].getVisaNumber());
      System.out.println("===================================================");
    } // end for loop
    System.out.println("Total Records Processed: " + 
    		//changed to include only the BasicStudent record count 
            BasicStudent.recordCount);  
  } // end method testInstantiations()
} // end class InternationalStudent