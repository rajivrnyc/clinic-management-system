package clinic;

import clinic.PatientInterface;
import java.time.LocalDateTime;
import java.util.List;


/**
 * Represents a patient within the clinic.
 */
public interface PatientInterface2 extends PatientInterface {
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
