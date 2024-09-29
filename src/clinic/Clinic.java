package clinic;

import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List; 

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
  private final Room primaryWaitingRoom;
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
    
    if (clinicName == null) {
      throw new IllegalArgumentException("A clinic must have a name.");
    }
    this.clinicName = clinicName;
    this.numRooms = numRooms;
    this.numStaff = numStaff;
    this.numPatients = numPatients;
    this.primaryWaitingRoom = primaryWaitingRoom;
    this.rooms = new ArrayList<>();
    this.employees = new ArrayList<>();
    this.patients = new ArrayList<>();
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
  public void addNewPatient(String firstName, String lastName, String dateOfBirth) {
    int waitingRoomNum = rooms.indexOf(primaryWaitingRoom) + 1;
    Patient newPatient = new Patient(waitingRoomNum, firstName, lastName, dateOfBirth);
    primaryWaitingRoom.placePatient(newPatient);
  }
  
  @Override
  public void addNewClinicalStaff(String jobTitle, String firstName, String lastName, 
      EducationLevel educationLevel, String npiLevel) {
    ClinicalStaff newcStaff = new ClinicalStaff(jobTitle, firstName, lastName, 
        educationLevel, npiLevel);
    employees.add(newcStaff);
  }
  
  @Override
  public Room getRoomFromNumber(int roomNumber) {
    if (roomNumber < 1 || roomNumber > rooms.size()) {
      throw new IllegalArgumentException("Invalid room number. This room does not exist.");
    }
    return rooms.get(roomNumber - 1);
  }
  
  @Override
  public void sendHome(Patient patient) {
    if (patient == null) {
      throw new IllegalArgumentException("This patient object is invalid.");
    }
    if (patient.getApproval()) {
      patient.deactivate();
    
      Room tempRoom = getRoomFromNumber(patient.getRoomNumber());
      if (tempRoom == null) {
        throw new IllegalArgumentException("This patient has an invalid room.");
      }
      tempRoom.removePatient(patient);
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
  }
  
  private int roomNumFromRoom(Room room) {
    int roomI = rooms.indexOf(room) + 1;
    return roomI;
  }
  
  @Override
  public void assignPatient(Patient patient, Room room) {
    if (patient == null || room == null) {
      throw new IllegalArgumentException("Invalid patient or room object.");
    }
    Room current = getRoomFromNumber(patient.getRoomNumber());
    if (current != null) {
      current.removePatient(patient);
    }
    
    if (!room.isWaitingRoom() && !room.isOccupied()) {
      throw new IllegalStateException("The room the patient is "
      + "trying to occupy is alreaday occupied.");
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
     
      List<Patient> patients = room.getResidents();
    }
    return sb.toString();
  }
  
  /*
   * Helper method for the read loop for Room.
   */  
  private static List<Room> readRoom(BufferedReader br, int numRooms) throws IOException {
    List<Room> tempRooms = new ArrayList<>();
    String textWaitingRoom = br.readLine();
    Room textPrimaryWaitingRoom = Room.textRoom(textWaitingRoom);
    tempRooms.add(textPrimaryWaitingRoom);
    for (int i = 1; i < numRooms; i++) {
      String textRoomInfo = br.readLine();
      Room tempRoom = Room.textRoom(textRoomInfo);
      tempRooms.add(tempRoom);
    }
    return tempRooms;
  }
  
  /*
   * Helper method for the read loop for Staff.
   */
  private static List<Staff> readStaff(BufferedReader br, int textNumStaff) throws IOException {
    List<Staff> textStaffs = new ArrayList<>();
    for (int i = 0; i < textNumStaff; i++) {
      String textStaffInfo = br.readLine();
      Staff tempStaff = StaffFactoryHelper.constructStaff(textStaffInfo);
      textStaffs.add(tempStaff);
    }
    return textStaffs;
  }
  
  /*
   * Helper method for the read loop for Patient.
   */
  private static List<Patient> readPatients(BufferedReader br, 
       int textNumPatients) throws IOException {
    List<Patient> textPatients = new ArrayList<>();
    for (int i = 0; i < textNumPatients; i++) {
      String textPatientInfo = br.readLine();
      Patient tempPatient = Patient.textPatient(textPatientInfo);
      textPatients.add(tempPatient);
    }
    return textPatients;
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
      List<Room> textRooms = readRoom(br, textNumRooms);
      Room textPrimaryWaitingRoom = textRooms.get(0);

      int textNumStaff = Integer.parseInt(br.readLine());
      List<Staff> textStaffs = readStaff(br, textNumStaff);

      int textNumPatients = Integer.parseInt(br.readLine());
      List<Patient> textPatients = readPatients(br, textNumPatients);
      

      Clinic textClinic = new Clinic(textClinicName, textNumRooms, textNumStaff,
          textNumPatients, textPrimaryWaitingRoom);
      textClinic.rooms = textRooms;
      textClinic.employees = textStaffs;
      textClinic.patients = textPatients;

      return textClinic;
    } catch (NumberFormatException  e) {
      throw new IllegalArgumentException("Number format is not valid.", e);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Insufficient information to complete task.", e);
    }
    
  }
}
