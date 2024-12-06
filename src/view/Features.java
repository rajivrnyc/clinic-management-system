package view;

/**
 * The interface represents the features that this program offers.
 */
public interface Features {
  
  /**
   * Loads a clinic text file into memory and updates the view.
   * 
   */
  void loadClinicFile();
  
  /**
   * Clears all the records in the memory.
   */
  void clearAllRecords();
  
  /**
   * Exits program.
   */
  void exitProgram();
  
  /**
   * Register's a new patient into the clinic and records their visit.
   */
  void registerNewPatient();
  
  /**
   * Assigns a clinical staff member to a patient.
   */
  void assignStafftoPatient();
  
  /**
   * Displays a patient's info.
   */
  void displayPatientInfo();
  
  
  /**
   * Unassigns a staff member from a patient.
   */
  void unassignStaffFromPatient();
  
  /**
   * Sends a patient home from the clinic.
   */
  void sendPatientHome();
  
  
}
