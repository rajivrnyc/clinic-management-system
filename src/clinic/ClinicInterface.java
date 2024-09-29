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
   * @throws IllegalArgumentException if the room number is invalid.
   */
  Room getRoomFromNumber(int roomNumber);
  
  
  /**
   * If a patient is approved for discharge, they are sent home.
   * 
   * @param patient A patient who should be approved for discharge.
   * @throws IllegalArgumentException if patient parameter is null.
   */
  void sendHome(Patient patient);
  
  /**
   * Deactivates a clinical staff member and removes any patient assignments.
   *  
   * @param member a clinical staff member at the clinic.
   * @throws IllegalArgumentException if the member parameter is null.
   */
  void deactivateClinicalStaffClinic(ClinicalStaff member);
  
  /**
   * Method to assign a patient to a room.
   * 
   * @param patient A person to be assigned to a room attending the clinic.
   * @param room   A room that the patient will reside in.
   * @throws IllegalArgumentException if either input is null.
   * @throws IllegalStateException if the room the patient is trying to enter is full.
   */
  void assignPatient(Patient patient, Room room);
  
  /**
   * Method to assign a clinical staff member to a patient.
   * 
   * @param patient A person who will be assigned a clinical staff member in the clinic.
   * @param member A clinical staff member who will be assigned to a patient.
   * @throws IllegalArgumentException if 
   */
  void assignStaff(Patient patient, ClinicalStaff member);
}
