package controller;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import clinic.Clinic;
import clinic.ClinicalStaff;
import clinic.EducationLevel;
import clinic.Patient;
import clinic.PatientInterface;
import clinic.Staff;

/**
 * The ClinicController class acts as an intermediary between the 
 * Clinic model and the user interface. It will take in user input
 * and process and conduct requests with different components of 
 * the Clinic's model and will facilitate displaying this information
 * to the user.
 */
public class ClinicController {
  private final Readable in;
  private final Appendable out;
  private Patient sally;
  private Staff john;
  private Staff steve;
  
  public ClinicController(Readable in, Appendable out) {
    this.in = Objects.requireNonNull(in, "Input cannot be null.");
    this.out = Objects.requireNonNull(out, "Output cannot be null.");
    sally = new Patient(1, "Sally", "Johnson", "01/02/1990");
    john = new ClinicalStaff("Doctor", "John", "Smith", EducationLevel.MASTERS, "0123456789");
    steve = new ClinicalStaff("Nurse", "Steve", "Wilson", EducationLevel.MASTERS, "0123456789");
  }
  
  /**
   * Method that gives control to the controller.
   * 
   * @param model the model to use with the controller.
   */
  public void go(Clinic model) {
    
    Objects.requireNonNull(model, "Clinic model cannot be null.");
    Scanner scan = new Scanner(this.in);
    boolean check = true;
    
    while (check) {
      String command = scan.next();
      
      switch(command) {
      case "1":
        displayPatient(model, scan);
        break;
      case "2":
        displayRoom(model, scan);
      case "3":
        displayAllRooms(model, scan);
      case "4":
        registerNewPatient(model);
      case "5":
        registerNewClinStaff(model);
      case "6":
        registerExistingPatient(model);
      case "7":
        sendPatientHome(model);
      case "8":
        assignToRoom(model);
      case "9":
        assignStaffToPatient(model);
      case "q":
        scan.close();
    	  return;
      default:
        throw new UnsupportedOperationException(command + " not suppported");
      }
    }
  }
  
  private void displayPatient(Clinic model, Scanner scan) throws IOException {
    model.addNewPatient("Sally", "Johnson", "01/02/1990");
    PatientInterface sallyJ = model.getPatients().get(model.getPatients().size() - 1);
    model.addNewClinicalStaff("Doctor", "John", "Smith", EducationLevel.MASTERS, "0123456789");
    model.addNewClinicalStaff("Nurse", "Steve", "Wilson", EducationLevel.MASTERS, "0123456789");
    
  }
 
}
