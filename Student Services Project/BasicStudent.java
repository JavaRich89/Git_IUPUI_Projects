/***************************************************************************** 
 * Author: Jeremy Rich
 * Course and Section: CIT 38800 Section 24579
 * Assignment Number: Four
 * Due Date and Time: 12/11/18 by 8:00 AM
 * File Name: BasicStudent.java
 * Description:  This class is the the ADT used to instantiate BasicStudent  
 * objects that contain the following verified data:
 * 1: Student first, last, and full name.  The full named is in a formatted
 * version
 * 2: Student age
 * 3: Student current gpa
 * 4: Student Visa number and home country are inherited from Students, but
 * are only needed for polymorphism.
 * These objects are to be used as a part of a larger student system
 * ***************************************************************************/
public class BasicStudent implements Students
{
  private int age;
  private double gpa;
  private String lastName;
  private String firstName;
  private String fullName;
  public static int recordCount;
  
  public final String STRING_ERROR = "Unknown";
  public final int FULL_NAME_LENGTH = 6;
  public final int LAST_NAME_LENGTH = 2;
  public final int FIRST_NAME_LENGTH = 2;
  public final int MAXIMUM_AGE = 75;
  public final int MINIMUM_AGE = 16;
  public final int OUT_OF_RANGE_AGE = 999;
  public final double MINIMUM_GPA = 0.00;
  public final double MAXIMUM_GPA = 4.00;
  public final int OUT_OF_RANGE_GPA = 99;
  
  public BasicStudent()
  {
    recordCount++; 
  } // end zero parameter constructor
  
  public BasicStudent(String completeName, String lName, String fName, int lAge,
          double lGpa)
  {
    recordCount++; 
    setFullName(completeName);
    setLastName(lName);
    setFirstName(fName);
    setAge(lAge);
    setGpa(lGpa);
  } // end 5 parameter constructor
  
  public void setFullName(String completeName)
  {
    if(completeName != null && completeName.length() >= FULL_NAME_LENGTH)
      fullName = completeName;
    else
      fullName = STRING_ERROR;
  } // end method setFullName --- sets the full name variable after validation
  
  public void setLastName(String lName)
  {
    if(lName != null && lName.length() >= LAST_NAME_LENGTH)
      lastName = lName;
    else
      lastName = STRING_ERROR;
  } // end method setLastName --- sets the last name variable after validation
  
  public void setFirstName(String fName)
  {
    if(fName != null && fName.length() >= FIRST_NAME_LENGTH)
      firstName = fName;
    else
      firstName = STRING_ERROR;
  } // end method setFirstName --- sets the first name variable after validation
  
  public void setAge(int lAge)
  {
    if(lAge >= MINIMUM_AGE && lAge <= MAXIMUM_AGE)
      age = lAge;
    else
      age = OUT_OF_RANGE_AGE;
  } // end method setAge --- sets the age variable after validation
  
  public void setGpa(double lGpa)
  {
    if(lGpa >= MINIMUM_GPA && lGpa <= MAXIMUM_GPA)
      gpa = lGpa;
    else
      gpa = OUT_OF_RANGE_GPA;
  } // end method setGpa --- sets the gpa variable after validation  
  
  @Override
  public String getFullName()
  {
    return fullName;
  } // end method getFullName --- returns the full name
  
  @Override
  public String getLastName()
  {
    return lastName;
  } // end method getLastName --- returns the last name
  
  @Override
  public String getFirstName()
  {
    return firstName;
  } // end method getFirstName --- returns the first name
  
  @Override
  public int getAge()
  {
    return age;
  } // end method getAge --- returns the age
  
  @Override
  public double getGpa()
  {
    return gpa;
  } // end method getGpa --- returns the gpa
  
  //Added because they are inherited from Students and must
  //override the abstract methods in the Students interface.
  @Override
  public String getVisaNumber() 
  {
	return "N/A";
  }
  
  @Override
  public String getHomeCountry()
  {
	  return "United States";
  }
} // end class BasicStudent