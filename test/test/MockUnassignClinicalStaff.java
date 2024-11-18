package test;

import clinic.ClinicStaffAndPatientInfo;
import clinic.ClinicalStaffInterface;
import clinic.PatientInterface;
import controller.ClinicCommand2;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


/**
 * Mock class for Unassign Clinical Staff command.
 */
public class MockUnassignClinicalStaff implements ClinicCommand2 {
  StringBuilder sb;
  
  /**
   * A mock constructor to record user input for this command.
   * @param sb A StringBuilder where user input is stored.
   */
  public MockUnassignClinicalStaff(StringBuilder sb) {
    this.sb = sb;
  }
  
  @Override
  public void execute(ClinicStaffAndPatientInfo model, Scanner scanner) throws IOException {
    System.out.println("List of Patients:");
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
          sb.append(nextInt + "\n");
          System.out.println("Quitting to Menu.");
          return;
        }
        sb.append(nextInt + "\n");
        patientIndex = Integer.parseInt(nextInt) - 1;
        if (patientIndex < 0 || patientIndex >= patients.size()) {
          throw new IllegalArgumentException("Invalid staff number.");
        }
        running = false;
        
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a patient number.");
        String error = scanner.nextLine();
        sb.append(error + "\n");
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid input. Please enter a valid number.");
        String error = scanner.nextLine();
        sb.append(error + "\n");
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
            sb.append(nextInt + "\n");
            System.out.println("Quitting to Menu.");
            return;
          }
          sb.append(nextInt + "\n");
          staffIndex = Integer.parseInt(nextInt) - 1;
          if (staffIndex < 0 || staffIndex >= tempStaff.size()) {
            throw new IllegalArgumentException("Invalid staff number.");
          }
          runningStaff = false;
        } catch (NumberFormatException e) {
          System.out.println("Invalid input. Please enter a valid staff number");
          String error = scanner.nextLine();
          sb.append(error + "\n");
        } catch (IllegalArgumentException e) {
          System.out.println("Invalid input. Please enter a valid number.");
          String error = scanner.nextLine();
          sb.append(error + "\n");
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
