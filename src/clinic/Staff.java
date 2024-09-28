package clinic;

/**
 * A staff member working for the clinic that can either be
 * categorized as Clinical Staff or Non Clinical Staff.
 * Each staff member has a first name, a last name and an education level.
 */
public interface Staff {
  
  /**
   * Gets the first name of a staff member.
   * 
   * @return the staff member's first name.
   */
  String getFirstName();
  
  /**
   * Gets the last name of a staff member.
   * 
   * @return the staff member's last name.
   */
  String getLastName();
  
  /**
   * Gets the education level of a staff member.
   * 
   * @return the education level of the staff member.
   */
  EducationLevel getEducationLevel();

}
