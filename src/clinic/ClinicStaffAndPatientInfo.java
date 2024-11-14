package clinic;

import java.util.List;

/**
 * An interface to represent additional information about a clinic being
 * displayed as it relates to staff and patient information.
 */
public interface ClinicStaffAndPatientInfo extends ClinicInterface {
  

  /**
   * A method to list clinical staff members and the patient that are
   * assigned to them.
   * 
   * @return a String that displays Clinical staff members and the 
   *     patients that are assigned to them
   */
  String listClinWithPatient();
  
  /**
   * A method to deactivate a staff member from the Clinic.
   * @param member A staff member at the clinic.
   */
  void deactivateStaff(Staff member);
  
  
  /**
   * A method to list patients that have not visited the clinic
   * in over a year.
   * 
   * @return A string representation of patients who have not 
   *     visited the clinic in over a year.
   */
  String listPatientVisitMoreThanYear();
  
  /**
   * A method to unassign a clinical staff member from
   * a patient.
   * 
   * @param member a Clinical Staff member in the clinic
   * @param patient a Patient in the clinic.
   */
  void unassignClinStaff(ClinicalStaffInterface member, PatientInterface patient);
  
  /**
   * A method to list patients who have visited the clinic
   * at least twice in the past year.
   * 
   * @return A string representation of the patients who have
   *     visited the clinic at least twice in the past year.
   */
  String listVisitTwiceOneYear();
  
  /**
   * Gets a copy of the ArrayList ClinicalStaff and returns it.
   * @return a copy of ArrayList ClinicalStaff.
   */
  List<PatientInterface> getClinicalStaff();
}
