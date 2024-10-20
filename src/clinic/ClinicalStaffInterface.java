package clinic;

/**
 * An interface to differentiate ClinicalStaff members from Staff members.
 */
public interface ClinicalStaffInterface extends Staff {

  /**
   * Gets the appropriate prefix of the Clinical Staff member
   * given their job title. 
   * 
   * @return returns the title of the Clinical staff member.
   */
  String getTitle();
  
  /**
   * Gets the clinical staff member's Npi Level.
   * 
   * @return the clinical staff member's Npi level.
   */
  String getNpiLevel();

}
