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
  
}
