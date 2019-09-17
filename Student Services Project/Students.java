/***************************************************************************** 
 * Author: Jeremy Rich
 * Course and Section: CIT 38800 Section 24579
 * Assignment Number: Four
 * Due Date and Time: 12/11/18 by 8:00 AM
 * File Name: Students.java
 * Description: This is an interface that is implemented by BasicStudent and
 * InternationalStudent classes. It contains abstract methods needed for the
 * creation of each object for each class.
 * ***************************************************************************/
//simple interface with only the necessary abstract get methods.
public interface Students {
	public String getFullName();
	public String getLastName();
	public String getFirstName();
	public int getAge();
	public double getGpa();
	public String getVisaNumber();
	public String getHomeCountry();
}
