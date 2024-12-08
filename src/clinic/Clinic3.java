package clinic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent a clinic.
 * Clinics have a name, a number of rooms, a number of staff working there
 * and a number of patients that are being served at any given time.
 */
public class Clinic3 extends Clinic2 implements ClinicInterface3 {

  /**
   * A constructor for the clinic class with a clinic name a set number of rooms
   * number of staff and number of patients in the clinic.
   * 
   * @param clinicName The name of the clinic.
   * @param numRooms The number of rooms in the clinic.
   * @param numStaff The number of staff working in the clinic.
   * @param numPatients The number of patients being treated at the clinic.
   * @param primaryWaitingRoom The primary waiting room at the clinic.
   */
  public Clinic3(String clinicName, int numRooms, int numStaff, int numPatients, 
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
  public void reset() {
    clinicName = null;
    numRooms = 0;
    numStaff = 0;
    numPatients = 0;
    primaryWaitingRoom = null;
    rooms.clear();
    employees.clear();
    patients.clear();
  }
  
  
  
  
  /**
   * Method to create a Clinic object from a text file.
   * 
   * @param fileName A file that contains information about the clinic.
   * @return A clinic object with the information contained in the text file.
   * @throws IOException If an error occurs during file reading such as 
   *         an invalid file, a nonexistent file etc.
   */
  public static Clinic3 readFile(FileReader fileName) throws IOException {
    try (BufferedReader br = new BufferedReader(fileName)) {
      final String textClinicName = br.readLine();
      int textNumRooms = Integer.parseInt(br.readLine());
      List<RoomInterface> tempRooms = new ArrayList<>();
      readRoom(br, textNumRooms, tempRooms);
      RoomInterface textPrimaryWaitingRoom = tempRooms.get(0);
      Clinic3 textClinic = new Clinic3(textClinicName, textNumRooms, 0, 0, textPrimaryWaitingRoom);
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
