package clinic;

/**
 * Class that represents a clinical staff member that is working for the clinic.
 * This class inherits the staff class.
 */
public class ClinicalStaff implements Staff {
  private String firstName;
  private String lastName;
  private EducationLevel educationLevel;
  private String npiLevel;
	
  /**
   * A constructor for the ClinicalStaff class that represents a clinical staff member.
   * 
   * @param firstName The first name of the clinical staff member.
   * @param lastName The last name of the clinical staff member.
   * @param educationLevel The education level of the clinical staff member.
   * @param npiLevel The NPI level of the clinical staff member.
   */
  public ClinicalStaff(String firstName, String lastName, EducationLevel 
      educationLevel, String npiLevel) {
    if (firstName == null || lastName == null || educationLevel == null || npiLevel == null) {
      throw new IllegalArgumentException("Invalid input. First name, last "
        + "name and education level cannot be null.");
    }
    
    if (!npiLevel.matches("\\d{10")) {
      throw new IllegalArgumentException("NPI levels cannot be negative and must have 10 digits.");
    }
    this.firstName = firstName;
    this.lastName = lastName;
    this.educationLevel = educationLevel;
    this.npiLevel = npiLevel;
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
