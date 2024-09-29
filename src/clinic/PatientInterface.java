package clinic;

/**
 * Represents a patient within the clinic.
 */
public interface PatientInterface {

  /**
     * Gets the room number of the room the patient is currently assigned to.
     * 
     * @return an int representing the room number that the patient is assigned to.
     */
  int getRoomNumber();

  /**
     * Gets the first name of the patient.
     * 
     * @return A String representing the first name of the patient.
     */
  String getFirstName();

  /**
    * Gets the last name of the patient.
    * 
    * @return A String representing the last name of the patient.
   */
  String getLastName();


  /**
   * Gets the date of birth of the patient.
   * 
   * @return A String representing the date of birth of the patient.
   */
  String getDateOfBirth();
  
  /**
   * Sets the approval status of a patient.
   * 
   * @param approvalStatus The approval status as determined by a clinical staff member.
   * @param member A clinical staff member is required to set the approval status of a patient.
   */
  void setApproval(ClinicalStaff member, boolean approvalStatus);
  
  /**
   * Gets the approval status for discharge of the patient.
   * 
   * @return a boolean that states whether or not the patient is approved for discharge.
   */
  boolean getApproval();
  
}
