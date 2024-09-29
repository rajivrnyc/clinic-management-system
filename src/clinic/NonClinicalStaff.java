package clinic;

/**
 * A class used to represent staff of the clinic that are 
 * non clinical staff members. This class inherits from the Staff
 * interface.
 */
public class NonClinicalStaff implements Staff {
  private String jobTitle;
  private String firstName;
  private String lastName;
  private EducationLevel educationLevel;
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
    if (firstName == null || lastName == null || educationLevel == null || cprLevel == null) {
      throw new IllegalArgumentException("The names, education level and cprLevel for "
        + "non clinical staff cannot be left null.");
    }

    this.jobTitle = jobTitle;
    this.firstName = firstName;
    this.lastName = lastName;
    this.educationLevel = educationLevel;
    this.cprLevel = cprLevel;
  }
  
  
  @Override
  public String getJobTitle() {
    return this.jobTitle;
  }

  @Override
  public String getFirstName() {
    return this.firstName;
  }

  @Override
  public String getLastName() {
    return this.lastName;
  }

  @Override
  public EducationLevel getEducationLevel() {
    return this.educationLevel;
  }

}
