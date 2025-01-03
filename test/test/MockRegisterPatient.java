package test;

import clinic.ClinicInterface;
import clinic.PatientInterface;
import clinic.PatientInterface2;
import clinic.Record;
import clinic.VisitRecord;
import controller.ClinicCommand;
import java.io.IOException;
import java.util.Scanner;


/**
 * A command for the controller to register a patient into the clinic model.
 */
public class MockRegisterPatient implements ClinicCommand {
  StringBuilder sb;

  /**
   * A mock constructor to record user input for this command.
   * @param sb A StringBuilder where user input is stored.
   */
  public MockRegisterPatient(StringBuilder sb) {
    this.sb = sb;
  }

  @Override
  public void execute(ClinicInterface model, Scanner scanner) throws IOException {
    scanner.nextLine();
    
    String firstName = "";
    String lastName = "";
    while (firstName.isEmpty()) {
      System.out.println("Enter patient's first name:");
      firstName = scanner.nextLine().trim(); 
      
      if (firstName.isEmpty()) {
        System.out.println("First name cannot be empty. Please enter again.");
        sb.append("\n");
      }
    }
    sb.append(firstName + "\n");
    
    while (lastName.isEmpty()) {
      System.out.println("Enter patient's last name:");
      lastName = scanner.nextLine().trim(); 
      
      if (lastName.isEmpty()) {
        System.out.println("Last name cannot be empty. Please enter again.");
        sb.append("\n");
      }
    }
    sb.append(lastName + "\n");
    
    System.out.println("Enter patient birth date (MM/DD/YYYY):");
    String bthDate = scanner.nextLine();
    sb.append(bthDate + "\n");
    
    model.addNewPatient(firstName, lastName, bthDate);
    System.out.println("Patient added");
    
    System.out.println("Enter chief complaint: ");
    String complaint = scanner.nextLine();
    sb.append(complaint + "\n");
    
    System.out.println("Enter body temp in Celsius");
    double temperature;
    
    while (true) {
      try {
        String tempText = scanner.nextLine();
        sb.append(tempText + "\n");
        temperature = Double.parseDouble(tempText);
        break;
      } catch (NumberFormatException e) {
        System.out.println("Please enter a valid temperature reading.");
      }
    }
    PatientInterface patient = model.getPatients().get(model.getPatients().size() - 1);
    
    PatientInterface2 patientI = (PatientInterface2) patient;
    
    patientI.addRecord(complaint, temperature);
    int roomNum = patient.getRoomNumber();
    System.out.println(patient);
  }

}