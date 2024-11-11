package clinic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
  private List<ClinicalStaffInterface> allocated;
  private List<Record> visitInfo;
  private boolean approval;
  private boolean isActive;
  private ClinicalStaffInterface approvedBy;
  
  
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
  public boolean isActive() {
    return this.isActive;
  }
  
  @Override
  public void setApproval(ClinicalStaffInterface member, boolean approvalStatus) {
    if (member instanceof ClinicalStaffInterface) {
      this.approval = approvalStatus;
      if (approvalStatus) {
        this.approvedBy = member;
      } else {
        this.approvedBy = null;
      }
    }
  }
  
  @Override
  public ClinicalStaffInterface getApprovedBy() {
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
  public void removeClinicalStaffMember(ClinicalStaffInterface member) {
    if (member == null) {
      throw new IllegalArgumentException("This Clinical Staff Member is invalid or"
       + "does not exist.");
    }
    this.allocated.remove(member);
  }
  
  @Override
  public List<ClinicalStaffInterface> getAllocated() {
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
  public void addOldRecord(String complaint, double temperature, LocalDateTime date) {
    if (complaint == null) {
      throw new IllegalArgumentException("Complaint cannot be null");
    }    
    Record newRecord = new VisitRecord(complaint, temperature);
    this.visitInfo.add(newRecord);
  }
  
  @Override
  public List<Record> getVisitRecord() {
    return new ArrayList<>(this.visitInfo);
  }
  
  @Override
  public Record getMostRecentVisit() {
    if (visitInfo.isEmpty()) {
      return null;
    }
    Record recentRecord = visitInfo.get(visitInfo.size() - 1);
    return (VisitRecord) recentRecord;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Patient other = (Patient) obj;

    return roomNumber == other.roomNumber 
      && firstName.equals(other.firstName)
      && lastName.equals(other.lastName)
      && dateOfBirth.equals(other.dateOfBirth);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(roomNumber, firstName, lastName, dateOfBirth);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Patient Name: ").append(this.getFirstName()).append(" ").append(this.getLastName());
    sb.append("\nDate of Birth: ").append(dateOfBirth);
    sb.append("\nRoom Number: ").append(this.getRoomNumber());
    sb.append("\nAssigned Staff: ");
    if (allocated.isEmpty()) {
      sb.append("None");
    } else {
      for (ClinicalStaffInterface staff : allocated) {
        sb.append(staff.getFirstName()).append(" ").append(staff.getLastName()).append(", ");
      }
      sb.setLength(sb.length() - 2);
    }
    
    sb.append("\nVisit Info: ");
    if (visitInfo.isEmpty()) {
      sb.append("Patient has no visit records.");
    } else {
      for (Record records : visitInfo) {
        VisitRecord visit = (VisitRecord) records;
        sb.append("\n  Chief Complaint: ").append(visit.getChiefComplaint());
        sb.append("\n  Temperature: ").append(visit.getTemperature()).append(" °C");
        sb.append("\n  Visit Date: ").append(visit.getDate());
        sb.append("\n");
      }
    }
    
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