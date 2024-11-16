package clinic;

import java.util.Objects;

/**
 * A class used to represent staff of the clinic that are 
 * non clinical staff members. This class inherits from the Staff
 * interface.
 */
public class NonClinicalStaff extends StaffClass {
  private CprLevel cprLevel;
  
  
  /**
   * Constructor for the NonClinicalStaff class  which represents a staff
   * member that is considered non clinical.
   * 
   * @param jobTitle The job title of the non clinical staff member.
   * @param firstName The first name of the non clinical staff member.
   * @param lastName The last name of the non clinical staff member.
   * @param educationLevel The education level of the non clinical staff member.
   * @param cprLevel The CPR level of the non clinical staff member.
   */
  public NonClinicalStaff(String jobTitle, String firstName, 
        String lastName, EducationLevel educationLevel, 
      CprLevel cprLevel) {
    super(jobTitle, firstName, lastName, educationLevel);
    if (jobTitle == null || firstName == null || lastName == null 
           || educationLevel == null || cprLevel == null) {
      throw new IllegalArgumentException("The names, education level and cprLevel for "
        + "non clinical staff cannot be left null.");
    }

    this.cprLevel = cprLevel;
  }
  
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof NonClinicalStaff)) {
      return false;
    }
    NonClinicalStaff other = (NonClinicalStaff) obj;
    return this.jobTitle.equals(other.jobTitle)
            && this.firstName.equals(other.firstName)
            && this.lastName.equals(other.lastName)
           && this.educationLevel == other.educationLevel
           && this.cprLevel == other.cprLevel;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(jobTitle, firstName, lastName, educationLevel, cprLevel);
  }
  
}
