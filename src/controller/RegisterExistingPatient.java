package controller;

import clinic.ClinicInterface;
import clinic.PatientInterface;
import clinic.PatientInterface2;
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
    boolean patientFound = false;
    String firstName = "";
    String lastName = "";
    
    while (!patientFound) {
      System.out.println("Enter patient's first name:");
      firstName = scanner.nextLine().trim();
      
      if ("q".equalsIgnoreCase(firstName)) {
        System.out.println("Quitting to Menu.");
        return;
      }
    
      System.out.println("Enter patient's last name:");
      lastName = scanner.nextLine().trim();
    
      if ("q".equalsIgnoreCase(lastName)) {
        System.out.println("Quitting to Menu.");
        return;
      }
      PatientInterface patient = model.findPatient(firstName, lastName);
      if (patient == null) {
        System.out.println("Patient not found. Please re-enter patient"
            + " name correctly.");
      } else {
        patientFound = true;
        System.out.println("Patient found!");
      }
    }
   
    final PatientInterface patient = model.findPatient(firstName, lastName); 
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
    PatientInterface2 patientI = (PatientInterface2) patient;
    patientI.addRecord(complaint, temp);
    System.out.println(patient);
  }
}
