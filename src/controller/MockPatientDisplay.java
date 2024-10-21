package controller;

import clinic.Clinic;
import clinic.ClinicInterface;
import clinic.ClinicalStaff;
import clinic.ClinicalStaffInterface;
import clinic.EducationLevel;
import clinic.PatientInterface;
import controller.ClinicCommand;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Executes displaying of a patient object.
 */
public class MockPatientDisplay implements ClinicCommand {
  private final StringBuilder sb;

  public MockPatientDisplay(StringBuilder sb) {
    this.sb = sb;
  }
  
  @Override
  public void execute(ClinicInterface model, Scanner scanner) throws IOException {
    System.out.println("List of Patients:");
    List<PatientInterface> patients = model.getPatients();
    
    if (patients.isEmpty()) {
      System.out.println("Patient List is empty");
      return;
    }
    for (int i = 0; i < patients.size(); i++) {
      System.out.println((i + 1) + ": " + (patients.get(i)).getFirstName() + " " 
          + (patients.get(i)).getLastName());
    }
    scanner.nextLine();
    int patientIndex = -1;
    boolean running = true;
    while (running) {
      System.out.println();
      System.out.print("Select patient to display: ");
      
      try {
        String nextInt = scanner.nextLine();
        if ("q".equalsIgnoreCase(nextInt)) {
          System.out.println("Qutting to Menu.");
          return; 
        }
        sb.append(nextInt + "\n");
        patientIndex = Integer.parseInt(nextInt) - 1;
        if (patientIndex < 0 || patientIndex >= patients.size()) {
          throw new IllegalArgumentException("Invalid patient number.");
        }
        running = false;
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid patient number.");
        scanner.nextLine();
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid input. Please enter a valid number.");
        scanner.nextLine();
      }
    }
    System.out.println();
    System.out.println(patients.get(patientIndex));  
  }
}