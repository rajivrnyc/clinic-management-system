package controller;

import clinic.ClinicInterface;
import clinic.PatientInterface;
import controller.ClinicCommand;
import java.io.IOException;
import java.util.Scanner;


/**
 * Register's a patient that exists in the clinic's system
 * into the database while recording a visit for their new
 * appearance.
 */
public class RegisterExistingPatient implements ClinicCommand {

  @Override
   public void execute(ClinicInterface model, Scanner scanner) throws IOException {
    scanner.nextLine();
    System.out.println("Enter patient's first name:");
    String firstName = scanner.nextLine().trim();
    
    System.out.println("Enter patient's last name:");
    String lastName = scanner.nextLine().trim();
    
    PatientInterface patient = model.findPatient(firstName, lastName);
    
    if (patient == null) {
      System.out.println("Patient not found. Please re-enter patient"
          + " name correctly.");
      return;
    }
    
    System.out.println("Patient found!");
    
    System.out.println("Enter chief complaint:");
    String complaint = scanner.nextLine().trim();
    
    System.out.println("Enter patient's body temperature:");
    double temp;
    while (true) {
      try {
        temp = Double.parseDouble(scanner.nextLine());
        break;
      } catch (NumberFormatException e) {
        System.out.println("Please enter a valid temperature");
      }
    }
    patient.addRecord(complaint, temp);
    System.out.println(patient);
  }
}
