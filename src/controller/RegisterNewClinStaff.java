package controller;

import clinic.ClinicInterface;
import clinic.ClinicalStaffInterface;
import clinic.EducationLevel;
import clinic.PatientInterface;
import controller.ClinicCommand;
import java.io.IOException;
import java.util.Scanner;

/**
 * Command to add a new ClinicalStaffMember through the controller.
 */
public class RegisterNewClinStaff implements ClinicCommand {

  @Override
  public void execute(ClinicInterface model, Scanner scanner) throws IOException {
    scanner.nextLine();    
    String jobTitle = "";
    String firstName = "";
    String lastName = "";
    
    while (jobTitle.isEmpty()) {
      System.out.println("Enter clinical staff member's job title:");
      jobTitle = scanner.nextLine().trim(); 
      if (jobTitle.isEmpty()) {
        System.out.println("Job title cannot be empty. Please enter again.");
      }
    }
    while (firstName.isEmpty()) {
      System.out.println("Enter clinical staff member's first name:");
      firstName = scanner.nextLine().trim(); 
      if (firstName.isEmpty()) {
        System.out.println("First name cannot be empty. Please enter again.");
      }
    }
      
    while (lastName.isEmpty()) {
      System.out.println("Enter clinical staff member's last name:");
      lastName = scanner.nextLine().trim(); 
      if (lastName.isEmpty()) {
        System.out.println("Last name cannot be empty. Please enter again.");
      }
    }
    
    String tempEdu = "";
    String npiLevel = "";
    EducationLevel eduLevel = null;
    
    while (eduLevel == null) {
      System.out.println("Enter clinical staff member's education level: ");
      tempEdu = scanner.nextLine().trim(); 
      try {
        eduLevel = EducationLevel.valueOf(tempEdu.toUpperCase()); 
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid education level. Please re-enter.");
      }
    }
    
    while (npiLevel.isEmpty()) {
      System.out.println("Enter clinical staff member's NPI level: ");
      npiLevel = scanner.nextLine().trim(); 
      if (npiLevel.isEmpty()) {
        System.out.println("NPI level cannot be empty. Please enter again.");
      }
    }
      
    model.addNewClinicalStaff(jobTitle, firstName, lastName, eduLevel, npiLevel);
      
    
    System.out.println();
    System.out.println(model.getEmployees().get(model.getEmployees().size() - 1));
  }
}
