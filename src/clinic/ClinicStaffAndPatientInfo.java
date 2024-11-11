package clinic;

/**
 * An interface to represent additional information about a clinic being
 * displayed as it relates to staff and patient information.
 */
public interface ClinicStaffAndPatientInfo extends ClinicInterface {
  

  /*
   * A method to list clinical staff members and the patient that are
   * assigned to them.
   * 
   * @return a String that displays Clinical staff members and the 
   * patients that are assigned to them
   */
  String listClinWithPatient();
}
