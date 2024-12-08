package clinic;

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
    clinicName = "";
    numRooms = 0;
    numStaff = 0;
    numPatients = 0;
    primaryWaitingRoom = null;
    rooms.clear();
    employees.clear();
    patients.clear();
  }

}
