package clinic;

/**
 * Abstract class for staff objects in the clinic.
 */
public abstract class StaffClass implements Staff {
  protected String jobTitle;
  protected String firstName;
  protected String lastName;
  protected EducationLevel educationLevel;
  protected boolean isActive;

  /**
   * Constructor for staff objects.
   * 
   * @param jobTitle The job title of the Staff member
   * @param firstName the first name of the Staff member
   * @param lastName the last name of the staff member
   * @param educationLevel the education level of the staff member
   */
  public StaffClass(String jobTitle, String firstName, String lastName,
      EducationLevel educationLevel) {
    this.jobTitle = jobTitle;
    this.firstName = firstName;
    this.lastName = lastName;
    this.educationLevel = educationLevel;
    this.isActive = true;
      
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

  @Override
  public void deactivate() {
    this.isActive = false;
  }
  
  /**
   *  Method to check if the current object is a Clinical Staff object.
   * @return true or false depending on whether or not the object is clin staff.
   */
  public boolean isClinicalStaff() {
    return false;
  }

}
