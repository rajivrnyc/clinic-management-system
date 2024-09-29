package clinic;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent a single patient within the clinic.
 * Each patient has a first name, last name, date of birth, and 
 * can have a list of staff members assigned to their care.
 */
public class Patient implements PatientInterface{

  private int roomNumber;
  private String firstName;
  private String lastName;
  private String dateOfBirth;
  private List<ClinicalStaff> allocated;
  private boolean approval;
  
  
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
    if (roomNumber < 0) {
      throw new IllegalArgumentException("The patient's room number cannot be less than 0.");
    }
    if (firstName == null || lastName == null || dateOfBirth == null) {
      throw new IllegalArgumentException("First name, last name and date of birth cannot be null.");
    }
    this.roomNumber = roomNumber;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.approval = false;
    this.allocated = new ArrayList<>();
  }
  
  @Override
  public int getRoomNumber() {
    return this.roomNumber;
  }
  
  @Override
  public String getFirstName() {
    return this.firstName;
  }
  
  @Override
  public String getLastName() {
    return this.lastName;
  }
  
  @Override
  public String getDateOfBirth() {
    return this.dateOfBirth;
  }
  
  @Override
  public void setApproval(boolean approvalStatus) {
    this.approval = approvalStatus;
  }
  
  @Override
  public boolean getApproval() {
    return this.approval;
  }
  
  /**
   * Method used to interpret the Patient portion of a text file passed in
   * to the model.
   * @param patientText A text block with information describing a patient in the clinic.
   * @return A patient object based on the information inputed into the method.
   */
  public static Patient textPatient(String patientText) {
    String[] splitPatient = patientText.split(" ");
    int tempRoomNumber = Integer.parseInt(splitPatient[0]);
    String tempFirstName = splitPatient[1];
    String tempLastName = splitPatient[2];
    String tempDateOfBirth = splitPatient[3];
    return new Patient(tempRoomNumber, tempFirstName, tempLastName, tempDateOfBirth);
  }
}
