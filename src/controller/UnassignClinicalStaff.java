package controller;

import clinic.ClinicInterface;
import clinic.ClinicStaffAndPatientInfo;
import clinic.ClinicalStaffInterface;
import clinic.PatientInterface;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;



/**
 * Controller command to unassign a clinical Staff member from a patient.
 */
public class UnassignClinicalStaff implements ClinicCommand2 {

  @Override
  public void execute(ClinicStaffAndPatientInfo model, Scanner scanner) throws IOException {
    System.out.println("Select a Patient:");
    List<PatientInterface> patients = model.getPatients();
    
    if (patients.isEmpty()) {
      System.out.println("Patient List is empty");
      return;
    }
    
    for (int i = 0; i < patients.size(); i++) {
      int index = i + 1;
      PatientInterface patient = patients.get(i);
      StringBuilder sb = new StringBuilder();
      sb.append(index).append(": ").append(patient.getFirstName())
      .append(" ").append(patient.getLastName());
      System.out.println(sb.toString());
    }
    
    scanner.nextLine();
    int patientIndex = -1;
    boolean running = true;
    while (running) {
      System.out.println();
      System.out.println("Select patient to unassign from: ");
      
      try {
        String nextInt = scanner.nextLine();
        if ("q".equalsIgnoreCase(nextInt)) {
          System.out.println("Quitting to Menu.");
          return;
        }
        
        patientIndex = Integer.parseInt(nextInt) - 1;
        if (patientIndex < 0 || patientIndex >= patients.size()) {
          throw new IllegalArgumentException("Invalid staff number.");
        }
        running = false;
        
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a patient number.");
        scanner.nextLine();
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid input. Please enter a valid number.");
        scanner.nextLine();
      }
    }
    System.out.println();
    PatientInterface temp = patients.get(patientIndex);
    List<ClinicalStaffInterface> tempStaff = temp.getAllocated();
    if (tempStaff.isEmpty()) {
      System.out.println("This Patient has no assigned staff members");
      System.out.println();
      return;
    } else {
      for (int i = 0; i < tempStaff.size(); i++) {
        int index = i + 1;
        ClinicalStaffInterface clin = tempStaff.get(i);
        StringBuilder sb = new StringBuilder();
        sb.append(index).append(": ").append(clin.getFirstName())
        .append(" ").append(clin.getLastName());
        System.out.println(sb.toString());
      }
      
      int staffIndex = -1;

      boolean runningStaff = true;
      while (runningStaff) {
        System.out.println();
        System.out.print("Select staff member to unassign: ");
          
        try {
          String nextInt = scanner.next();
          if ("q".equalsIgnoreCase(nextInt)) {
            System.out.println("Quitting to Menu.");
            return;
          }
          staffIndex = Integer.parseInt(nextInt) - 1;
          if (staffIndex < 0 || staffIndex >= tempStaff.size()) {
            throw new IllegalArgumentException("Invalid staff number.");
          }
          runningStaff = false;
        } catch (NumberFormatException e) {
          System.out.println("Invalid input. Please enter a valid staff number");
          scanner.nextLine();
        } catch (IllegalArgumentException e) {
          System.out.println("Invalid input. Please enter a valid number.");
          scanner.nextLine();
        }
      }
      System.out.println();
      ClinicalStaffInterface doctor = tempStaff.get(staffIndex);
      model.unassignClinStaff(doctor, temp);
      System.out.println("Clinical Staff member has been unassinged");
      System.out.println();
    }
  }
}
