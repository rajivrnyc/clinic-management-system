package clinic;

import java.util.ArrayList;

/**
 * A class to represent a single patient within the clinic.
 * Each patient has a first name, last name, date of birth, and 
 * can have a list of staff members assigned to their care.
 */
public class Patient {
  private String firstName;
  private String lastName;
  private String dateOfBirth;
  private List<ClincalStaff> allocated;
  
  
  /**
   * A constructor for the patient class for a patient attending the clinic.
   * 
   * @param firstName The patient's first name.
   * @param lastName The patient's last name.
   * @param dateofBirth The patient's date of birth.
   */
  public Patient(String firstName, String lastName, String dateOfBirth) {
	  this.firstName = firstName;
	  this.lastName = lastName;
	  this.dateOfBirth = dateOfBirth;
	  this.allocated = new ArrayList<>();
  }
}
