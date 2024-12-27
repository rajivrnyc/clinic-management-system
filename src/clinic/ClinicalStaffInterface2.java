package clinic;

import clinic.ClinicalStaffInterface;

/**
 * An interface to differentiate ClinicalStaff members from Staff members and to
 * add new functionality to clinical staff.
 */
public interface ClinicalStaffInterface2 extends ClinicalStaffInterface {
  
  /**
   * A method to increment the counter that shows the number of patients
   * that the ClinicalStaff member has ever been assigned to.
   */
  void incrementAssigned();
  
  /**
   * A method to get the number of patients ever assigned to a
   * certain clinical staff member.
   * 
   * @return an int that represents the number of patients ever
   *     asisgned to a certain clinical staff member.
   */
  int getNumAssigned();
}
 