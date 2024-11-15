package clinic;

/**
 * Abstract class for staff objects in the clinic.
 */
public abstract class StaffClass implements Staff {
  private String jobTitle;
  private String firstName;
  private String lastName;
  private EducationLevel educationLevel;
  private boolean isActive;

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

}
