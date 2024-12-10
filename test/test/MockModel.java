package test;

import clinic.ClinicInterface3;
import clinic.ClinicalStaffInterface;
import clinic.EducationLevel;
import clinic.PatientInterface;
import clinic.PatientInterface2;
import clinic.RoomInterface;
import clinic.Staff;
import java.util.List;

/**
 * Mocks the model for testing the controller.
 */
public class MockModel implements ClinicInterface3 {
  private StringBuilder out;
  
  public MockModel(StringBuilder out) {
    this.out = out;
  }
  
  @Override
  public String listClinWithPatient() {
    return null;
  }

  @Override
  public void deactivateStaff(Staff member) {
  }

  @Override
  public String listPatientVisitMoreThanYear() {
    return null;
  }

  @Override
  public void unassignClinStaff(ClinicalStaffInterface member, PatientInterface patient) {
    out.append("Clin staff unassigned");
  }

  @Override
  public String listVisitTwiceOneYear() {
    return null;
  }

  @Override
  public String getClinicName() {
    return null;
  }

  @Override
  public int getNumRooms() {
    return 0;
  }

  @Override
  public int getNumStaff() {
    return 0;
  }

  @Override
  public int getNumPatients() {
    return 0;
  }

  @Override
  public List<RoomInterface> getRooms() {
    return null;
  }

  @Override
  public List<Staff> getEmployees() {
    out.append("Got Employees.");
    return null;
  }

  @Override
  public List<PatientInterface> getPatients() {
    out.append("got patients");
    return null;
  }

  @Override
  public void addNewPatient(String firstName, String lastName, String dateOfBirth) {
    out.append(firstName).append(" ").append(lastName).append(" ").append(dateOfBirth);
  }

  @Override
  public void addNewClinicalStaff(String jobTitle, String firstName, String lastName, 
           EducationLevel educationLevel, String npiLevel) {

  }

  @Override
  public RoomInterface getRoomFromNumber(int roomNumber) {
    return null;
  }

  @Override
  public void sendHome(PatientInterface patient, ClinicalStaffInterface member) {
    out.append("Patient sent home.");
  }

  @Override
  public void deactivateClinicalStaffClinic(ClinicalStaffInterface member) {

  }

  @Override
  public void assignPatient(PatientInterface patient, RoomInterface room) {
    out.append("assigned patient");
  }

  @Override
  public void assignStaff(PatientInterface patient, ClinicalStaffInterface member) {

  }

  @Override
  public PatientInterface2 findPatient(String firstName, String lastName) {
    out.append(firstName).append(" ").append(lastName);
    return null;
    
  }

  @Override
  public String seatingChart() {
    return null;
  }

  @Override
  public void reset() {
    out.append("Reset Called.");
  }

}
