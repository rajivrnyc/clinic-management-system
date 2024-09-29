package clinic;

/**
 * Helper class to create the correct Staff object based on the 5th input into
 * the constructor.
 */
public class StaffFactoryHelper {

  /**
   * Constructor for a Staff object that correctly identifies and makes 
   * either a clinical staff or non clinical staff object.
   * 
   * @param staffText A text block that is interpreted and created into a Staff object.
   * @return returns a Staff object of either clinical staff or non clinical staff.
   */
  public static Staff constructStaff(String staffText) {
    String[] staffSplit = staffText.split(" ");
    String jobTitle = staffSplit[0];
    String firstName = staffSplit[1];
    String lastName = staffSplit[2];
    EducationLevel educationLevel = EducationLevel.valueOf(staffSplit[3].toUpperCase());
    String unknown = staffSplit[4];

    if (npiChecker(unknown)) {
      return constructClinicalStaff(jobTitle, firstName, lastName, educationLevel, unknown);
    } else if (cprChecker(unknown)) {
      CprLevel cprLevel = CprLevel.valueOf(unknown);
      return constructNonClinicalStaff(jobTitle, firstName, lastName, educationLevel, cprLevel);
    } else {
      throw new IllegalArgumentException("Input for this constructor is invalid");
    }
  }

  private static boolean npiChecker(String fifth) {
    if (fifth.matches("\\d{10}")) {
      return true;
    }
    return false;
  }
    
  private static boolean cprChecker(String fifth) {
    if ("A".equals(fifth) || "B".equals(fifth) || "C".equals(fifth) || "BLS".equals(fifth)) {
      return true;
    }
    return false;
  }
  
  private static Staff constructClinicalStaff(String jobTitle, String firstName, 
      String lastName, EducationLevel educationLevel, String npiLevel) {
    return new ClinicalStaff(jobTitle, firstName, lastName, educationLevel, npiLevel);
  }
    
  private static Staff constructNonClinicalStaff(String jobTitle, String firstName, 
      String lastName, EducationLevel educationLevel, CprLevel cprLevel) {
    return new NonClinicalStaff(jobTitle, firstName, lastName, educationLevel, cprLevel);
  }
}
