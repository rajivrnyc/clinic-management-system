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
  
  
  /**
   * Registers a new clinical staff member as an employee for the clinic.
   * 
   * @param jobTitle The job title for the clinical staff member.
   * @param firstName The first name of the clinical staff member.
   * @param lastName The last name of the clinical staff member.
   * @param educationLevel The education level of the clinical staff member.
   * @param npiLevel The NPI level of the clinical staff member.
   */
  void addNewClinicalStaff(String jobTitle, String firstName, String lastName, EducationLevel 
      educationLevel, String npiLevel);
  
  /**
   * Finds a room object based on the room number.
   * 
   * @param roomNumber The room number of the room
   * @return a room object associated with that room number.
   */
  Room getRoomFromNumber(int roomNumber);
  
  
  /**
   * If a patient is approved for discharge, they are sent home.
   * 
   * @param patient A patient who should be approved for discharge.
   * @throws IllegalArgumentException if patient parameter is null.
   * @throws IllegalArgumentException if room number is invalid.
   */
  void sendHome(Patient patient);
}
