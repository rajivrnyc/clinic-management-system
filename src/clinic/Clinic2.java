package clinic;

import java.time.LocalDate;
import java.util.List;

/**
 * A class to represent the workings of a clinic.
 * Clinics have a name, a number of rooms, a number of staff working there
 * and a number of patients that are being served at any given time.
 */
public class Clinic2 extends Clinic implements ClinicStaffAndPatientInfo {
  private String clinicName;
  private int numRooms;
  private int numStaff;
  private int numPatients;
  private  RoomInterface primaryWaitingRoom;
  private List<RoomInterface> rooms;
  private List<Staff> employees;
  private List<PatientInterface> patients;

  public Clinic2(String clinicName, int numRooms, int numStaff, int numPatients, 
        RoomInterface primaryWaitingRoom) {
    super(clinicName, numRooms, numStaff, numPatients, primaryWaitingRoom);
  }
  
  @Override
  public void deactivateStaff(Staff member) {
    if (member == null) {
      throw new IllegalArgumentException("This Staff object is invalid.");
    }
    if (employees.remove(member) == true) {
      member.deactivate();
    }
    this.numStaff--;
  }
  
  @Override
  public void unassignClinStaff(ClinicalStaffInterface member, PatientInterface patient) {
    if (member == null || patient == null) {
      throw new IllegalArgumentException("This ClinicalStaff or Patient object is invalid");
    }
    patient.removeClinicalStaffMember(member);
  }
  
  @Override
  public String listClinWithPatient() {
    StringBuilder output = new StringBuilder();
    for (PatientInterface patient : patients) {
      if (patient.isActive()) {
        for (ClinicalStaffInterface staff : patient.getAllocated()) {
          output.append("Clinical Staff: ").append(staff.getFirstName())
          .append(" ").append(staff.getLastName()).append("\n");
          
          for (PatientInterface assigned : patients) {
            if (assigned.isActive() && assigned.getAllocated().contains(staff)) {
              output.append("  Assigned Patient: ").append(assigned.getFirstName())
              .append(" ").append(assigned.getLastName()).append("\n");
            }
          }
        }
      }
    }
    return output.toString().trim();
  }
  
  @Override
  public String listPatientVisitMoreThanYear() {
    LocalDate today = LocalDate.now();
    LocalDate oneYearAgo = today.minusDays(365);
    StringBuilder listPatient = new StringBuilder();
    for (PatientInterface patient : patients) {
      if (!patient.getVisitRecord().isEmpty()) {
        Record recent = patient.getMostRecentVisit();
        if (recent.getDateArrival().toLocalDate().isBefore(oneYearAgo)) {
          listPatient.append(patient.getFirstName()).append(" ").append(patient.getLastName())
            .append("\n");
        }
      }
    }
    if (listPatient.length() == 0) {
      listPatient.append("No patient hasn't visted in more than a year.");
    }
    return listPatient.toString().trim();
  }
  

  @Override 
  public String listVisitTwiceOneYear() { 
    LocalDate today = LocalDate.now(); 
    LocalDate oneYearAgo = today.minusDays(365); 
    StringBuilder visitors = new StringBuilder(); 
    return "";
  }

}
