package clinic;

import java.util.Objects;

/**
 * Class that represents a clinical staff member that is working for the clinic.
 * This class inherits the staff class.
 */
public class ClinicalStaff extends StaffClass implements ClinicalStaffInterface2 {
  private String npiLevel;
  private int countAssigned;


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
    super(jobTitle, firstName, lastName, educationLevel);
    if (jobTitle == null || firstName == null 
        || lastName == null || educationLevel == null || npiLevel == null) {
      throw new IllegalArgumentException("Invalid input. First name, last "
        + "name and education level cannot be null.");
    }
    
    if (!npiLevel.matches("\\d{10}")) {
      throw new IllegalArgumentException("NPI levels cannot be negative and must have 10 digits.");
    }
    
    this.npiLevel = npiLevel;
  }
  
  @Override
  public String getTitle() {
    if ("Physician".equalsIgnoreCase(this.jobTitle)) {
      return "Dr. ";
    } else if ("Nurse".equalsIgnoreCase(this.jobTitle)) {
      return "Nurse ";
    } else {
      return this.jobTitle + " ";
    }
  }
  
  @Override
  public String getNpiLevel() {
    return this.npiLevel;
  }
  
  @Override
  public boolean getStatus() {
    return this.isActive;
  }
  
  @Override
  public boolean isClinicalStaff() {
    return true;
  }
  
  @Override
  public void incrementAssigned() {
    countAssigned++;
  }
  
  @Override
  public int getNumAssigned() {
    return countAssigned;
  }

  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    ClinicalStaff other = (ClinicalStaff) obj;
    return this.jobTitle.equals(other.jobTitle)
            && this.firstName.equals(other.firstName)
            && this.lastName.equals(other.lastName)
            && this.educationLevel == other.educationLevel
            && this.npiLevel.equals(other.npiLevel);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(jobTitle, firstName, lastName, educationLevel, npiLevel);
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
