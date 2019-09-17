/***************************************************************************** 
 * Author: Jeremy Rich
 * Course and Section: CIT 38800 Section 24579
 * Assignment Number: Four
 * Due Date and Time: 12/11/18 by 8:00 AM
 * File Name: InternationalStudent.java
 * Description:  This class is the the ADT used to instantiate 
 * InternationalStudent objects that contain the following verified data:
 * 1: Student first, last, full name, student age, and current gpa that are all
 * inherited from the BasicStudent class
 * 2: Home Country and Visa number are added by this class.
 * InternationalStudent extends (inherits from) BasicStudent
 * These objects are to be used as a part of a larger student system
 * 
 * Nothing was changed in this class for assignment 4.
 * ***************************************************************************/
public class InternationalStudent extends BasicStudent
{
  private String homeCountry;
  private String visaNumber;
  
  public final int COUNTRY_MIN = 4;
  public final int COUNTRY_MAX = 32;
  public final int VISA_LENGTH = 8;
  
  public InternationalStudent()
  {
    // The zero parameter constructor
  }
  
  public InternationalStudent(String completeName, String lName, String fName, 
          int lAge, double lGpa, String inCountry, String inVisaNumber)
  {
    super(completeName, lName, fName, lAge, lGpa); // superclass construct' call
    setHomeCountry(inCountry);
    setVisaNumber(inVisaNumber);
  }
  
  public void setHomeCountry(String inCountry)
  {
    if(inCountry != null && inCountry.length() >= COUNTRY_MIN && 
            inCountry.length() <= COUNTRY_MAX)
      homeCountry = inCountry;
    else
      homeCountry = STRING_ERROR; // STRING_ERROR is inherited
  } // end setHomeCountry
  
  public void setVisaNumber(String inVisaNumber)
  {
    if(inVisaNumber != null && inVisaNumber.length() == VISA_LENGTH)
      visaNumber = inVisaNumber;
    else
      visaNumber = STRING_ERROR;
  } // end setVisaNumber
  
  @Override
  public String getFullName()
  {
    return super.getFullName().toUpperCase();
  } // end method getFullName --- returns the full name in upper case
  
  @Override
  public String getLastName()
  {
    return super.getLastName().toUpperCase();
  } // end method getLastName --- returns the last name in upper case
  
  @Override
  public String getFirstName()
  {
    return super.getFirstName().toUpperCase();
  } // end method getFirstName --- returns the first name
  
  @Override
  public String getVisaNumber()
  {
    return visaNumber;
  } // end method
  
  @Override
  public String getHomeCountry()
  {
    return homeCountry;
  } // end method
} // end class InternationalStudent