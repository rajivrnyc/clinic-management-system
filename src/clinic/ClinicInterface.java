package clinic;

/**
 * Creates a model of a Clinic.
 */
public interface ClinicInterface {


  /**
   * Gets the name of the clinic.
   * 
   * @return a String that tells the name of the clinic.
   */
  String getClinicName();
  
  
  /**
   * Gets the number of rooms in the clinic.
   * 
   * @return an integer that tells the number of rooms in the clinic.
   */
  int getNumRooms();
  
  /**
   * Gets the number of staff members in the clinic.
   * 
   * @return an integer that tells the number of staff members in the clinic.
   */
  int getNumStaff();
  
  /**
   * Gets the number of patients in the clinic.
   * 
   * @return an integer that tells the number of patients in the clinic.
   */
  int getNumPatients();
  
  
  /**
 *  Adds a new patient to the Clinic.
 *  
 * @param firstName The first name of the new patient.
 * @param lastName The last name of the new patient
 * @param dateOfBirth The date of birth of the new patient.
 */
  void addNewPatient(String firstName, String lastName, String dateOfBirth);
}
