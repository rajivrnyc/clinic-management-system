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
  private String clinicName;
  private int numRooms;
  private int numStaff;
  private int numPatients;
  private  RoomInterface primaryWaitingRoom;
  private List<RoomInterface> rooms;
  private List<Staff> employees;
  private List<PatientInterface> patients;

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

  
  /*
   * Helper method for the read loop for Room.
   */  
  private static void readRoom(BufferedReader br, int numRooms, 
        List<RoomInterface> roomList) throws IOException {
    for (int i = 0; i < numRooms; i++) {
      String roomText = br.readLine();
      Room room = Room.textRoom(roomText);
      roomList.add(room);
    }

  }
  
  /*
   * Helper method for the read loop for Staff.
   */
  private static void readStaff(BufferedReader br, int textNumStaff, 
        Clinic2 clinic) throws IOException {
    for (int i = 0; i < textNumStaff; i++) {
      String staffText = br.readLine();
      Staff staff = StaffFactoryHelper.constructStaff(staffText);
      clinic.employees.add(staff);
    }
  }
  
  /*
   * Helper method for the read loop for Patient.
   */
  private static void readPatients(BufferedReader br, 
       int textNumPatients, Clinic2 clinic) throws IOException {
    for (int i = 0; i < textNumPatients; i++) {
      String patientText = br.readLine();
      Patient patient = Patient.textPatient(patientText);
      int roomNum = patient.getRoomNumber();
      RoomInterface assignedRoom = clinic.getRoomFromNumber(roomNum);
      assignedRoom.placePatient(patient);
      clinic.patients.add(patient);
    }
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
          textClinic.getRooms().add(room);
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
