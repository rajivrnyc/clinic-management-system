package clinic;

import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects; 

/**
 * A class to represent the workings of a clinic.
 * Clinics have a name, a number of rooms, a number of staff working there
 * and a number of patients that are being served at any given time.
 */
public class Clinic implements ClinicInterface {
  private String clinicName;
  private int numRooms;
  private int numStaff;
  private int numPatients;
  private  Room primaryWaitingRoom;
  private List<Room> rooms;
  private List<Staff> employees;
  private List<Patient> patients;
  
  
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
        int numPatients, Room primaryWaitingRoom) {
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
  public List<Room> getRooms() {
    return new ArrayList<>(this.rooms);
  }
  
  @Override
  public List<Staff> getEmployees() {
    return new ArrayList<>(this.employees);
  }
  
  @Override
  public List<Patient> getPatients() {
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
    ClinicalStaff newcStaff = new ClinicalStaff(jobTitle, firstName, lastName, 
        educationLevel, npiLevel);
    employees.add(newcStaff);
    this.numStaff += 1;
  }
  
  @Override
  public Room getRoomFromNumber(int roomNumber) {
    if (roomNumber < 0 || roomNumber > rooms.size()) {
      throw new IllegalArgumentException("Invalid room number. This room does not exist.");
    }
    return rooms.get(roomNumber);
  }
  
  @Override
  public void sendHome(Patient patient, ClinicalStaff member) {
    if (patient == null) {
      throw new IllegalArgumentException("This patient object is invalid.");
    }
    
    if (member == null) {
      throw new IllegalArgumentException("This clinical staff member is invalid.");
    }
    patient.setApproval(member, true);
    if (patient.getApproval()) {
      patient.deactivate();
    
      Room tempRoom = getRoomFromNumber(patient.getRoomNumber() - 1);
      if (tempRoom == null) {
        throw new IllegalArgumentException("This patient has an invalid room.");
      }
      tempRoom.removePatient(patient);
      this.numPatients--;
    }
  }
  
  @Override
  public void deactivateClinicalStaffClinic(ClinicalStaff member) {
    if (member == null) {
      throw new IllegalArgumentException("This ClinicalStaff object is invalid.");
    }
    if (employees.remove(member) == true) {
      member.deactivate();
    }
    for (Patient patient : patients) {
      patient.removeClinicalStaffMember(member);
    }
    this.numStaff--;
  }
  
  private int roomNumFromRoom(Room room) {
    int roomI = rooms.indexOf(room);
    return roomI;
  }
  
  @Override
  public void assignPatient(Patient patient, Room room) {
    if (patient == null || room == null) {
      throw new IllegalArgumentException("Invalid patient or room object.");
    }
    Room current = getRoomFromNumber(patient.getRoomNumber());
    if (current != null) {
      if (!current.isWaitingRoom()) {
        current.removePatient(patient);
      }
    }
    
    if (!room.isWaitingRoom() && room.isOccupied()) {
      throw new IllegalStateException("The room the patient is "
      + "trying to occupy is already occupied.");
    }
    room.placePatient(patient);
    patient.setRoomNumber(roomNumFromRoom(room));
  }
  
  @Override
  public void assignStaff(Patient patient, ClinicalStaff member) {
    if (patient == null || member == null) {
      throw new IllegalArgumentException("Patient or ClinicalStaff objects cannot be null.");
    }
    patient.getAllocated().add(member);
    
  }
  
  @Override
  public String seatingChart() {
    StringBuilder sb = new StringBuilder();
    for (Room room : rooms) {
      sb.append(room.getRoomName()).append(":\n");
     
      List<Patient> patientslocal = room.getResidents();
      if (patientslocal.isEmpty()) {
        sb.append(" - This room is empty");
      } else {
        for (Patient patient : patientslocal) {
          sb.append("  -").append(patient.getFirstName()).append(" ")
         .append(patient.getLastName()).append("\n");

          sb.append("   - Clinical Staff: ");
          if (patient.getAllocated().isEmpty()) {
            sb.append("None\n");
          } else {
            for (ClinicalStaff member : patient.getAllocated()) {
              sb.append(member.getFirstName()).append(" ").append(member.getLastName())
                .append(", ");
            }
            sb.setLength(sb.length() - 2);
            sb.append("\n");
          }
          VisitRecord recentVisit = patient.getMostRecentVisit();
          if (recentVisit != null) {
            sb.append("     - Most Recent Visit: ")
              .append(recentVisit.toString()).append("\n");
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
  
  /*
   * Helper method for the read loop for Room.
   */  
  private static void readRoom(BufferedReader br, int numRooms, 
        List<Room> roomList) throws IOException {
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
        Clinic clinic) throws IOException {
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
       int textNumPatients, Clinic clinic) throws IOException {
    for (int i = 0; i < textNumPatients; i++) {
      String patientText = br.readLine();
      Patient patient = Patient.textPatient(patientText);
      int roomNum = patient.getRoomNumber();
      Room assignedRoom = clinic.getRoomFromNumber(roomNum);
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
  public static Clinic readFile(String fileName) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      final String  textClinicName = br.readLine();
      
      int textNumRooms = Integer.parseInt(br.readLine());
      List<Room> tempRooms = new ArrayList<>();
      readRoom(br, textNumRooms, tempRooms);

      Room textPrimaryWaitingRoom = tempRooms.get(0);
      Clinic textClinic = new Clinic(textClinicName, textNumRooms, 0, 0, textPrimaryWaitingRoom);
      textClinic.rooms.addAll(tempRooms);
      
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
