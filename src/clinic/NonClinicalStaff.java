package clinic;

/**
 * A class used to represent staff of the clinic that are 
 * non clinical staff members. This class inherits from the Staff
 * interface.
 */
public class NonClinicalStaff implements Staff {
  private String firstName;
  private String lastName;
  private EducationLevel educationLevel;
  private CprLevel cprLevel;
  
  
  /**
   * Constructor for the NonClinicalStaff class  which represents a staff
   * member that is considered non clinical.
   * 
   * @param firstName The first name of the non clinical staff member.
   * @param lastName The last name of the non clinical staff member.
   * @param educationLevel The education level of the non clinical staff member.
   * @param cprLevel The CPR level of the non clinical staff member.
   */
  public NonClinicalStaff(String firstName, String lastName, EducationLevel educationLevel, 
      CprLevel cprLevel) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.educationLevel = educationLevel;
    this.cprLevel = cprLevel;
  }

  @Override
  public String getFirstName() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getLastName() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public EducationLevel getEducationLevel() {
    // TODO Auto-generated method stub
    return null;
  }

}
