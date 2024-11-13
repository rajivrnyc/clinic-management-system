package clinic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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
  void setApproval(ClinicalStaffInterface member, boolean approvalStatus);
  
  /**
   * Sets the room number that a patient is assigned to.
   * This is how we will handle changing of rooms.
   * 
   * @param roomNum a number that represents the room number the patient is currently in.
   */
  void setRoomNumber(int roomNum);
  
  /**
   * Gets the approval status for discharge of the patient.
   * 
   * @return a boolean that states whether or not the patient is approved for discharge.
   */
  boolean getApproval();
  
  /**
   * Finds the person who approved the patient's discharge.
   * 
   * @return the Clinical Staff member who approved the patient's discharge.
   */
  ClinicalStaffInterface getApprovedBy();

  /**
   * deactivates a patient by removing assigned staff members and setting status to inactive.
   */
  void deactivate();
  
  /**
   * Gets the list of allocated clinical staff members to a certain patient.
   * 
   * @return A list of clinical staff members assigned to a certain patient.
   */
  List<ClinicalStaffInterface> getAllocated();

  /**
    * Method to add a new visit to the patient's list of visits.
    * 
    * @param complaint The patient's chief complaint as a string.
    * @param temperature The patient's temperature reading in Celsius.
    */
  void addRecord(String complaint, double temperature);

  /**
    * removes a clinical staff member from allocated clinical staff members that are
    * caring for this patient object.
    * 
    * @param member is a clinical staff member at the clinic.
    * @throws IllegalArgumentException when member is invalid or does not exist.
    */
  void removeClinicalStaffMember(ClinicalStaffInterface member);
    
  /**
    * Method to return the visit record on the patient's
    * most recent visit to the clinic.
    * 
    * @return returns the patient's most recent visit record
    */
  Record getMostRecentVisit();
   
  /**
    * Checks if the patient is active.
    * @return The patient's status within the clinic.
    */
  boolean isActive();
    
  /**
    * Returns a copy of the patient's visit Record.
    * @return a list of the patient's visit record.
    */
  List<Record> getVisitRecord();
    
  /**
     * Adds a record of a past visit to the patient's information.
     * 
     * @param complaint The main complaint of the patient open visiting the clinic.
     * @param temperature The temperature of the patient recorded during admission to clinic.
     * @param date The date the patient arrived to the clinic.
     */
  void addOldRecord(String complaint, double temperature, LocalDateTime date);

}
