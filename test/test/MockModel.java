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

  @Override
  public String listClinWithPatient() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deactivateStaff(Staff member) {
    // TODO Auto-generated method stub
  }

  @Override
  public String listPatientVisitMoreThanYear() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void unassignClinStaff(ClinicalStaffInterface member, PatientInterface patient) {
    // TODO Auto-generated method stub

  }

  @Override
  public String listVisitTwiceOneYear() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getClinicName() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getNumRooms() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getNumStaff() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getNumPatients() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public List<RoomInterface> getRooms() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Staff> getEmployees() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<PatientInterface> getPatients() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addNewPatient(String firstName, String lastName, String dateOfBirth) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addNewClinicalStaff(String jobTitle, String firstName, String lastName, 
           EducationLevel educationLevel, String npiLevel) {
    // TODO Auto-generated method stub

  }

  @Override
  public RoomInterface getRoomFromNumber(int roomNumber) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void sendHome(PatientInterface patient, ClinicalStaffInterface member) {
    // TODO Auto-generated method stub

  }

  @Override
  public void deactivateClinicalStaffClinic(ClinicalStaffInterface member) {
    // TODO Auto-generated method stub

  }

  @Override
  public void assignPatient(PatientInterface patient, RoomInterface room) {
    // TODO Auto-generated method stub

  }

  @Override
  public void assignStaff(PatientInterface patient, ClinicalStaffInterface member) {
    // TODO Auto-generated method stub

  }

  @Override
  public PatientInterface2 findPatient(String firstName, String lastName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String seatingChart() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void reset() {
    // TODO Auto-generated method stub

  }

}
