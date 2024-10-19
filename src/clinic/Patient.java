package clinic;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent a single patient within the clinic.
 * Each patient has a first name, last name, date of birth, and 
 * can have a list of staff members assigned to their care.
 */
public class Patient implements PatientInterface {

  private int roomNumber;
  private String firstName;
  private String lastName;
  private String dateOfBirth;
  private List<ClinicalStaff> allocated;
  private List<Record> visitInfo;
  private boolean approval;
  private boolean isActive;
  private ClinicalStaff approvedBy;
  
  
  /**
   * A constructor for the patient class for a patient attending the clinic.
   * 
   * @param roomNumber The number for the room the patient is currently in.
   * @param firstName The patient's first name.
   * @param lastName The patient's last name.
   * @param dateOfBirth The patient's date of birth.
   * 
   */
  public Patient(int roomNumber, String firstName, String lastName, String dateOfBirth) {
    if (roomNumber < 0) {
      throw new IllegalArgumentException("The patient's room number cannot be less than 0.");
    }
    if (firstName == null || lastName == null || dateOfBirth == null) {
      throw new IllegalArgumentException("First name, last name and date of birth cannot be null.");
    }
    this.roomNumber = roomNumber;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.approval = false;
    this.allocated = new ArrayList<>();
    this.visitInfo = new ArrayList<>();
    this.isActive = true;
  }
  
  @Override
  public int getRoomNumber() {
    return this.roomNumber;
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
  public String getDateOfBirth() {
    return this.dateOfBirth;
  }
  
  @Override
  public void setRoomNumber(int roomNum) {
    this.roomNumber = roomNum;
  }
  
  @Override
  public void setApproval(ClinicalStaff member, boolean approvalStatus) {
    if (member instanceof ClinicalStaff) {
      this.approval = approvalStatus;
      if (approvalStatus) {
        this.approvedBy = member;
      } else {
        this.approvedBy = null;
      }
    }
  }
  
  @Override
  public ClinicalStaff getApprovedBy() {
    return approvedBy;
  }
  
  @Override
  public boolean getApproval() {
    return this.approval;
  }
  
  @Override
  public void deactivate() {
    this.isActive = false;
    allocated.clear();
    this.roomNumber = 1;
  }
  
  @Override
  public void removeClinicalStaffMember(ClinicalStaff member) {
    if (member == null) {
      throw new IllegalArgumentException("This Clinical Staff Member is invalid or"
       + "does not exist.");
    }
    this.allocated.remove(member);
  }
  
  @Override
  public List<ClinicalStaff> getAllocated() {
    return this.allocated;
  }
  
  @Override
  public void addRecord(String complaint, double temperature) {
    if (complaint == null) {
      throw new IllegalArgumentException("Complaint cannot be null");
    }
    
    Record newRecord = new VisitRecord(complaint, temperature);
    this.visitInfo.add(newRecord);
  }
  
  @Override
  public VisitRecord getMostRecentVisit() {
    if (visitInfo.isEmpty()) {
      return null;
    }
    Record recentRecord = visitInfo.get(visitInfo.size() - 1);
    return (VisitRecord) recentRecord;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("PatientName: ").append(this.getFirstName()).append(this.getLastName());
    
    return sb.toString();
  }
  
  /**
   * Method used to interpret the Patient portion of a text file passed in
   * to the model.
   * @param patientText A text block with information describing a patient in the clinic.
   * @return A patient object based on the information inputed into the method.
   */
  public static Patient textPatient(String patientText) {
    String[] splitPatient = patientText.split("\\s+");
    int tempRoomNumber = Integer.parseInt(splitPatient[0]);
    String tempFirstName = splitPatient[1];
    String tempLastName = splitPatient[2];
    String tempDateOfBirth = splitPatient[3];
    return new Patient(tempRoomNumber, tempFirstName, tempLastName, tempDateOfBirth);
  }
}