package test;

import java.util.List;

import clinic.ClinicInterface;
import clinic.ClinicalStaffInterface;
import clinic.EducationLevel;
import clinic.PatientInterface;
import clinic.RoomInterface;
import clinic.Staff;

/**
 * A class to mock clinic.
 */
public class MockClinic implements ClinicInterface {
  private StringBuilder log;
  private final int unique;
  
  public MockClinic(StringBuilder log, int unique) {
    this.log = log;
    this.unique = unique;
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
    return null;
  }

  @Override
  public List<PatientInterface> getPatients() {
    return null;
  }

  @Override
  public void addNewPatient(String firstName, String lastName, String dateOfBirth) {
    log.append("Input:" + " " + firstName + " " + lastName + " " + dateOfBirth);
    
  }

  @Override
  public void addNewClinicalStaff(String jobTitle, String firstName, String lastName, 
      EducationLevel educationLevel,
      String npiLevel) {
  }

  @Override
  public RoomInterface getRoomFromNumber(int roomNumber) {
    return null;
  }

  @Override
  public void sendHome(PatientInterface patient, ClinicalStaffInterface member) {
  }

  @Override
  public void deactivateClinicalStaffClinic(ClinicalStaffInterface member) {
  }

  @Override
  public void assignPatient(PatientInterface patient, RoomInterface room) {
  }

  @Override
  public void assignStaff(PatientInterface patient, ClinicalStaffInterface member) {
  }

  @Override
  public PatientInterface findPatient(String firstName, String lastName) {
    return null;
  }

  @Override
  public String seatingChart() {
    return null;
  }

}
