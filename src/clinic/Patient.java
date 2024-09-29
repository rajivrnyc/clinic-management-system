package clinic;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent a single patient within the clinic.
 * Each patient has a first name, last name, date of birth, and 
 * can have a list of staff members assigned to their care.
 */
public class Patient {
  private int roomNumber;
  private String firstName;
  private String lastName;
  private String dateOfBirth;
  private List<ClinicalStaff> allocated;
  
  
  /**
   * A constructor for the patient class for a patient attending the clinic.
   * 
   * @param roomNumber The number for the room the patient is currently in.
   * @param firstName The patient's first name.
   * @param lastName The patient's last name.
   * @param dateOfBirth The patient's date of birth.
   * 
   */
  public Patient(int roomNumber, String firstName, String lastName, String dateOfBirth) {
    this.roomNumber = roomNumber;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.allocated = new ArrayList<>();
  }
}
