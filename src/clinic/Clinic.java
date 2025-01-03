package clinic;

import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects; 

/**
 * A class to represent the workings of a clinic.
 * Clinics have a name, a number of rooms, a number of staff working there
 * and a number of patients that are being served at any given time.
 */
public class Clinic implements ClinicInterface {
  protected String clinicName;
  protected int numRooms;
  protected int numStaff;
  protected int numPatients;
  protected  RoomInterface primaryWaitingRoom;
  protected List<RoomInterface> rooms;
  protected List<Staff> employees;
  protected List<PatientInterface> patients;
  
  
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
  public Clinic(String clinicName, int numRooms, int numStaff, 
        int numPatients, RoomInterface primaryWaitingRoom) {
    if (numRooms < 1 || numStaff < 0 || numPatients < 0) {
      throw new IllegalArgumentException("A clinic must have at least one room and "
        + "cannot have a negative number of patients or staff.");
    }
    
    if (clinicName == null || primaryWaitingRoom == null) {
      throw new IllegalArgumentException("A clinic must have a "
    + "name and primary waiting room must exist.");
    }
    this.clinicName = clinicName;
    this.numRooms = numRooms;
    this.numStaff = numStaff;
    this.numPatients = numPatients;
    this.primaryWaitingRoom = primaryWaitingRoom;
    this.rooms = new ArrayList<>();
    this.employees = new ArrayList<>();
    this.patients = new ArrayList<>();
    this.rooms.add(primaryWaitingRoom);
    
  }
  
  @Override
  public String getClinicName() {
    return this.clinicName;
  }
  
  @Override
  public int getNumRooms() {
    return this.numRooms;
  }
  
  @Override
  public int getNumPatients() {
    return this.numPatients;
  }
  
  @Override
  public int getNumStaff() {
    return this.numStaff;
  }
  
  @Override
  public List<RoomInterface> getRooms() {
    return new ArrayList<>(this.rooms);
  }
  
  @Override
  public List<Staff> getEmployees() {
    return new ArrayList<>(this.employees);
  }
  
  @Override
  public List<PatientInterface> getPatients() {
    return new ArrayList<>(this.patients);
  }
  
  @Override
  public void addNewPatient(String firstName, String lastName, String dateOfBirth) {
    if (firstName == null || lastName == null || dateOfBirth == null) {
      throw new IllegalArgumentException("Inputs cannot be null");
    }
    int waitingRoomNum = rooms.indexOf(primaryWaitingRoom) + 1;
    
    Patient newPatient = new Patient(waitingRoomNum, firstName, lastName, dateOfBirth);
    primaryWaitingRoom.placePatient(newPatient);
    this.patients.add(newPatient);
    this.numPatients += 1;
  }
  
  @Override
  public void addNewClinicalStaff(String jobTitle, String firstName, String lastName, 
      EducationLevel educationLevel, String npiLevel) {
    if (jobTitle == null || firstName == null || lastName == null 
            || educationLevel == null || npiLevel == null) {
      throw new IllegalArgumentException("Inputs cannot be null");
    }
    ClinicalStaffInterface newcStaff = new ClinicalStaff(jobTitle, firstName, lastName, 
        educationLevel, npiLevel);
    employees.add(newcStaff);
    this.numStaff += 1;
  }
  
  @Override
  public RoomInterface getRoomFromNumber(int roomNumber) {

    if (roomNumber < 0 || roomNumber > rooms.size()) {
      throw new IllegalArgumentException("Invalid room number. This room does not exist.");
    }
    return rooms.get(roomNumber - 1);
  }
  
  @Override
  public void sendHome(PatientInterface patient, ClinicalStaffInterface member) {
    if (patient == null) {
      throw new IllegalArgumentException("This patient object is invalid.");
    }
    
    if (member == null) {
      throw new IllegalArgumentException("This clinical staff member is invalid.");
    }
    
    RoomInterface tempRoom = getRoomFromNumber(patient.getRoomNumber());
    if (tempRoom == null) {
      throw new IllegalArgumentException("This patient has an invalid room.");
    }
    tempRoom.removePatient(patient);
    this.numPatients--;
  
    patient.setApproval(member, true);
    if (patient.getApproval()) {
      patient.deactivate();
      rooms.get(0).placePatient(patient);
    }

  }
  
  @Override
  public void deactivateClinicalStaffClinic(ClinicalStaffInterface member) {
    if (member == null) {
      throw new IllegalArgumentException("This ClinicalStaff object is invalid.");
    }
    if (employees.remove(member) == true) {
      member.deactivate();
    }
    for (PatientInterface patient : patients) {
      patient.removeClinicalStaffMember(member);
    }
    this.numStaff--;
  } 

  private int roomNumFromRoom(RoomInterface room) {
    int roomI = rooms.indexOf(room) + 1;
    return roomI;
  }
  
  @Override
  public void assignPatient(PatientInterface patient, RoomInterface room) {
    if (patient == null || room == null) {
      throw new IllegalArgumentException("Invalid patient or room object.");
    }
    RoomInterface current = getRoomFromNumber(patient.getRoomNumber());
    
    if (current != null && current.equals(room)) {
      throw new IllegalArgumentException("Patient is already in the target room.");
    }
    
    if (current != null) {
      current.removePatient(patient);
    }
    
    
    if (!room.isWaitingRoom() && room.isOccupied()) {
      throw new IllegalStateException("The room the patient is "
      + "trying to occupy is already occupied.");
    }
    room.placePatient(patient);
    patient.setRoomNumber(roomNumFromRoom(room));
  }
  
  @Override
  public void assignStaff(PatientInterface patient, ClinicalStaffInterface member) {
    if (patient == null || member == null) {
      throw new IllegalArgumentException("Patient or ClinicalStaff objects cannot be null.");
    }
    if (!member.getStatus()) {
      throw new IllegalArgumentException("Clinical Staff member must be active "
           + "to be assigned to a patient.");
    }
    
    if (patient.getAllocated().contains(member)) {
      throw new IllegalArgumentException("The Clinical Staff member is already "
          + "assigned to this patient.");
    }
    
    ClinicalStaffInterface clinicalstaff = member;
    patient.getAllocated().add(clinicalstaff);
    if (clinicalstaff instanceof ClinicalStaffInterface2) {
      ((ClinicalStaffInterface2) clinicalstaff).incrementAssigned();
    }
    
  }
  
  @Override
  public PatientInterface2 findPatient(String firstName, String lastName) {
    if (firstName == null || lastName == null) {
      throw new IllegalArgumentException("Please enter a valid first and last name");
    }
    for (PatientInterface patient : patients) {
      if (patient.getFirstName().equalsIgnoreCase(firstName) 
          && patient.getLastName().equalsIgnoreCase(lastName)) {
        PatientInterface2 patientI = (PatientInterface2) patient;
        return patientI;
      }
    }
    return null;
  }
  
  @Override
  public String seatingChart() {
    StringBuilder sb = new StringBuilder();
    for (RoomInterface room : rooms) {
      sb.append(room.getRoomName()).append(":\n");
     
      List<PatientInterface> patientslocal = room.getResidents();
      if (patientslocal.isEmpty()) {
        sb.append(" - This room is empty");
      } else {
        for (PatientInterface patient : patientslocal) {
          PatientInterface2 patientI = (PatientInterface2) patient;
          if (patientI.isActive()) {
            sb.append("  -").append(patientI.getFirstName()).append(" ")
         .append(patientI.getLastName()).append("\n");

            sb.append("   - Clinical Staff: ");
            if (patientI.getAllocated().isEmpty()) {
              sb.append("None\n");
            } else {
              for (ClinicalStaffInterface member : patientI.getAllocated()) {
                sb.append(member.getTitle()).append(member.getFirstName())
              .append(" ").append(member.getLastName())
                .append(", ");
              }
              sb.setLength(sb.length() - 2);
              sb.append("\n");
            }
            Record recentVisit = patientI.getMostRecentVisit();
            if (recentVisit != null) {
              sb.append("     - Most Recent Visit: ")
              .append(recentVisit.toString()).append("\n");
            }
          }
        }
      }
      sb.append("-------------------------\n");
    }
    return sb.toString();
  }
  
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Clinic clinic = (Clinic) obj;
    return (numRooms == clinic.numRooms 
        && numStaff == clinic.numStaff 
        && numPatients == clinic.numPatients 
        && clinicName.equals(clinic.clinicName) 
        && primaryWaitingRoom.equals(clinic.primaryWaitingRoom));
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(clinicName, numRooms, numStaff, numPatients, primaryWaitingRoom);
  }
  
  /**
   * Helper method for the read loop for Room.
   */  
  protected static void readRoom(BufferedReader br, int numRooms, 
        List<RoomInterface> roomList) throws IOException {
    for (int i = 0; i < numRooms; i++) {
      String roomText = br.readLine();
      RoomInterface room = Room.textRoom(roomText);
      roomList.add(room);
    }

  }
  
  /**
   * Helper method for the read loop for Staff.
   */
  protected static void readStaff(BufferedReader br, int textNumStaff, 
        Clinic clinic) throws IOException {
    for (int i = 0; i < textNumStaff; i++) {
      String staffText = br.readLine();
      Staff staff = StaffFactoryHelper.constructStaff(staffText);
      clinic.employees.add(staff);
    }
  }
  
  /**
   * Helper method for the read loop for Patient.
   */
  protected static void readPatients(BufferedReader br, 
       int textNumPatients, Clinic clinic) throws IOException {
    for (int i = 0; i < textNumPatients; i++) {
      String patientText = br.readLine();
      PatientInterface2 patient = Patient.textPatient(patientText);
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
  public static Clinic readFile(FileReader fileName) throws IOException {
    try (BufferedReader br = new BufferedReader(fileName)) {
      final String  textClinicName = br.readLine();
      
      int textNumRooms = Integer.parseInt(br.readLine());
      List<RoomInterface> tempRooms = new ArrayList<>();
      readRoom(br, textNumRooms, tempRooms);

      RoomInterface textPrimaryWaitingRoom = tempRooms.get(0);
      
      Clinic textClinic = new Clinic(textClinicName, textNumRooms, 0, 0, textPrimaryWaitingRoom);
      
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
    } catch (NumberFormatException  e) {
      throw new IllegalArgumentException("Number format is not valid.", e);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Insufficient information to complete task.", e);
    }
    
  }
}
