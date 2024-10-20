package controller;

import clinic.ClinicInterface;
import clinic.PatientInterface;
import clinic.Record;
import clinic.VisitRecord;
import controller.ClinicCommand;
import java.io.IOException;
import java.util.Scanner;


/**
 * A command for the controller to register a patient into the clinic model.
 */
public class RegisterPatientCommand implements ClinicCommand {

  @Override
  public void execute(ClinicInterface model, Scanner scanner) throws IOException {
    System.out.println("Enter patient's first name:");
    String firstName = scanner.nextLine();
    System.out.println("Enter patient's last name:");
    String lastName = scanner.nextLine();
    System.out.println("Enter patient birth date (MM/DD/YYYY):");
    String bthDate = scanner.nextLine();
    
    model.addNewPatient(firstName, lastName, bthDate);
    System.out.println("Patient added");
    
    System.out.println("Enter chief complaint: ");
    String complaint = scanner.nextLine();
    
    System.out.println("Enter body temp in Celsius");
    double temperature;
    while (true) {
      try {
        temperature = Double.parseDouble(scanner.nextLine());
        break;
      } catch (NumberFormatException e) {
        System.out.println("Please enter a valid temperature reading.");
      }
    }
    PatientInterface patient = model.getPatients().get(model.getPatients().size() - 1);
    
    Record visitRecord = new VisitRecord(complaint, temperature);
  }

}
