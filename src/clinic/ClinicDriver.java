package clinic;

import java.io.IOException;

/**
 * A Driver class to test the functionality of the Clinic model.
 */
public class ClinicDriver {

  /**
 * Takes command line argument that we will use to from clinic objects.
 * @param args Takes an argument from the command line.
 */
  public static void main(String[] args) {
    String filePath = args[0];
    Clinic clinic = null;
    
    try {
      clinic = Clinic.readFile(filePath);
      System.out.println("Loaded File.");
    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
      return;
    }
    
    System.out.println("Clinic Name: " + clinic.getClinicName());
    System.out.println("Number of Rooms: " + clinic.getNumRooms());
    System.out.println("Number of Staff: " + clinic.getNumStaff());
    System.out.println("Number of Patients: " + clinic.getNumPatients());
    System.out.println();
    System.out.println("Requirement 1: Adding a New Patient");
    clinic.addNewPatient("Sally", "Johnson", "01/02/1990");
    System.out.println("Patient was added");
    System.out.println("New Number of Patients: " + clinic.getNumPatients());
    System.out.println();
    System.out.println("Requirement 2: Adding a New Clinical Staff Member");
    final ClinicalStaff john = new ClinicalStaff("Doctor", "John", "Smith", 
         EducationLevel.MASTERS, "1234567890");
    final ClinicalStaff jack = new ClinicalStaff("Doctor", "Jack", "Smith", 
            EducationLevel.MASTERS, "1234567890");
    clinic.addNewClinicalStaff("Doctor", "John", "Smith", EducationLevel.MASTERS, "1234567890");
    System.out.println("Clinical Staff Member was add");
    System.out.println("New Number of Clinical Staff Members: " + clinic.getNumStaff());
    System.out.println();
    System.out.println("Requirement 3: Sending Someone Home");
    PatientInterface sally = clinic.getPatients().get(clinic.getPatients().size() - 1);
    clinic.assignStaff(sally, john);
    clinic.sendHome(sally, john);
    System.out.println(sally.getFirstName() + " " + sally.getLastName() + " has been sent home.");
    System.out.println("Current Number of Patients: " + clinic.getNumPatients());
    System.out.println();
    System.out.println("Requirement 4: Deactivate a Clinical Staff Member");
    clinic.deactivateClinicalStaffClinic(john);
    System.out.println("A clinical staff member has been deactivated.");
    System.out.println("Current Number of Staff: " + clinic.getNumStaff());
    System.out.println();
    System.out.println("Requirement 5: Assign a person to a room");
    RoomInterface roomAssignment = clinic.getRooms().get(3);
    System.out.println("Sally's Current Room Number: " + sally.getRoomNumber());
    clinic.assignPatient(sally, roomAssignment);
    System.out.println("Sally has been moved to " + roomAssignment.getRoomName());
    System.out.println("Sally's New Room Number: " + sally.getRoomNumber());
    System.out.println();
    System.out.println("Requirement 6: Assign a Clinical Staff Member to a patient.");
    Staff ben = clinic.getEmployees().get(1);
    ClinicalStaff member = (ClinicalStaff) ben;
    clinic.assignStaff(sally, member);
    System.out.println("Succesfully assigned Sally a new staff member.");
    System.out.println("Sally's assigned staff: " + sally.getAllocated().get(0).getFirstName() 
         + " " + sally.getAllocated().get(0).getLastName());
    System.out.println();
    System.out.println("Requirement 7: Print information about a specific room as a String");
    RoomInterface infoR = clinic.getRoomFromNumber(sally.getRoomNumber());
    RoomInterface infoS = clinic.getRoomFromNumber(1);
    sally.addRecord("Headache", 39);
    System.out.println("Printing info about the room Sally is in:\n");
    System.out.println(infoR.toString());
    System.out.println();
    System.out.println("Requirement 8: Display a seating chart");
    System.out.println(clinic.seatingChart());
    System.out.println("Sally Info:");
    System.out.println(sally);
    
    
  }

}
