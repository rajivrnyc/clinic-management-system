package clinic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent the workings of a clinic.
 * Clinics have a name, a number of rooms, a number of staff working there
 * and a number of patients that are being served at any given time.
 */
public class Clinic2 extends Clinic implements ClinicStaffAndPatientInfo {

  /**
   * A constructor for the clinic class with a clinic name, a set number of rooms
   * number of staff and the number of patients at the clinic.
   * 
   * @param clinicName The name of the clinic.
   * @param numRooms The number of rooms in the clinic.
   * @param numStaff The number of staff working in the clinic.
   * @param numPatients The number of patients being treated at the clinic.
   * @param primaryWaitingRoom The primary waiting room at the clinic.
   */
  public Clinic2(String clinicName, int numRooms, int numStaff, int numPatients, 
        RoomInterface primaryWaitingRoom) {
    super(clinicName, numRooms, numStaff, numPatients, primaryWaitingRoom);
    if (numRooms < 1 || numStaff < 0 || numPatients < 0) {
      throw new IllegalArgumentException("A clinic must have at least one room and "
          + "cannot have a negative number of patients or staff.");
    }
      
    if (clinicName == null || primaryWaitingRoom == null) {
      throw new IllegalArgumentException("A clinic must have a "
      + "name and primary waiting room must exist.");
    }
    this.rooms = new ArrayList<>();
    this.employees = new ArrayList<>();
    this.patients = new ArrayList<>();
    this.rooms.add(primaryWaitingRoom);
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
      PatientInterface2 patientI = (PatientInterface2) patient;
      if (patientI.isActive()) {
        for (ClinicalStaffInterface staff : patient.getAllocated()) {
          output.append("Clinical Staff: ").append(staff.getFirstName())
          .append(" ").append(staff.getLastName()).append("\n");
          
          for (PatientInterface assigned : patients) {
            PatientInterface2 assignedI = (PatientInterface2) assigned;
            if (assignedI.isActive() && assigned.getAllocated().contains(staff)) {
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
      PatientInterface2 patientI = (PatientInterface2) patient;
      if (!patientI.getVisitRecord().isEmpty()) {
        Record recent = patientI.getMostRecentVisit();
        if (recent.getDateArrival().toLocalDate().isBefore(oneYearAgo)) {
          listPatient.append(patient.toString().trim())
              .append("\n ------------------------------- \n");
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
    LocalDate oneYearago = today.minusDays(365);
    StringBuilder listPatient = new StringBuilder();
    for (PatientInterface patient : patients) {
      PatientInterface2 patientI = (PatientInterface2) patient;
      int visitCount = 0;
      for (Record record : patientI.getVisitRecord()) {
        if (!record.getDateArrival().toLocalDate().isBefore(oneYearago)) {
          visitCount++;
        }
        if (visitCount >= 2) {
          listPatient.append(patient.toString().trim())
              .append("\n ------------------------------- \n");
          break;
        }
      }
    }
    if (listPatient.length() == 0) {
      listPatient.append("No patient has visited 2 or more times in the past year.");
    }
    return listPatient.toString().trim();
  }


  
  
  /**
   * Method to create a Clinic object from a text file.
   * 
   * @param fileName A file that contains information about the clinic.
   * @return A clinic object with the information contained in the text file.
   * @throws IOException If an error occurs during file reading such as 
   *         an invalid file, a nonexistent file etc.
   */
  public static Clinic2 readFile(FileReader fileName) throws IOException {
    try (BufferedReader br = new BufferedReader(fileName)) {
      final String textClinicName = br.readLine();
      int textNumRooms = Integer.parseInt(br.readLine());
      List<RoomInterface> tempRooms = new ArrayList<>();
      readRoom(br, textNumRooms, tempRooms);
      RoomInterface textPrimaryWaitingRoom = tempRooms.get(0);
      Clinic2 textClinic = new Clinic2(textClinicName, textNumRooms, 0, 0, textPrimaryWaitingRoom);
      for (RoomInterface room : tempRooms) {
        if (!room.equals(textPrimaryWaitingRoom)) {
          textClinic.rooms.add(room);
        }
      }

      int textNumStaff = Integer.parseInt(br.readLine());
      readStaff(br, textNumStaff, textClinic);

      int textNumPatients = Integer.parseInt(br.readLine());
      readPatients(br, textNumPatients, textClinic);
      textClinic.numStaff = textNumStaff;
      textClinic.numPatients = textNumPatients;

      return textClinic;
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Number format is not valid.", e);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Insufficient information to complete task.", e);
    }
  }

}
