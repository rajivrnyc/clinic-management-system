package clinic;

/**
 * Class that represents a clinical staff member that is working for the clinic.
 * This class inherits the staff class.
 */
public class ClinicalStaff implements ClinicalStaffInterface {
  private String jobTitle;
  private String firstName;
  private String lastName;
  private EducationLevel educationLevel;
  private String npiLevel;
  private boolean isActive;

  /**
   * A constructor for the ClinicalStaff class that represents a clinical staff member.
   * 
   * @param jobTitle The job title for the clinical staff member.
   * @param firstName The first name of the clinical staff member.
   * @param lastName The last name of the clinical staff member.
   * @param educationLevel The education level of the clinical staff member.
   * @param npiLevel The NPI level of the clinical staff member.
   */
  public ClinicalStaff(String jobTitle, String firstName, String lastName, EducationLevel 
      educationLevel, String npiLevel) {
    if (jobTitle == null || firstName == null 
        || lastName == null || educationLevel == null || npiLevel == null) {
      throw new IllegalArgumentException("Invalid input. First name, last "
        + "name and education level cannot be null.");
    }
    
    if (!npiLevel.matches("\\d{10}")) {
      throw new IllegalArgumentException("NPI levels cannot be negative and must have 10 digits.");
    }
    this.jobTitle = jobTitle;
    this.firstName = firstName;
    this.lastName = lastName;
    this.educationLevel = educationLevel;
    this.npiLevel = npiLevel;
    this.isActive = true;
  }
  
  @Override
  public String getJobTitle() {
    return this.jobTitle;
  }
  
  @Override
  public String getTitle() {
    if ("Physician".equalsIgnoreCase(this.jobTitle)) {
      return "Dr. ";
    } else if ("Nurse".equalsIgnoreCase(this.jobTitle)) {
      return "Nurse ";
    } else {
      return this.jobTitle;
    }
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
  
  @Override
  public String getNpiLevel() {
    return this.npiLevel;
  }
  
  @Override
  public void deactivate() {
    this.isActive = false;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Job Title: ").append(this.getJobTitle());
    sb.append("\nClinical Staff Name: ").append(this.getTitle()).append(this.getFirstName())
     .append(" ").append(this.getLastName());
    sb.append("\nEducation Level: ").append(this.getEducationLevel());
    sb.append("\nNPI Level: ").append(this.getNpiLevel());
    return sb.toString();
  }
}
