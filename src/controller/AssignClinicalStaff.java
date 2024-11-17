package controller;

import clinic.ClinicInterface;
import clinic.ClinicalStaffInterface;
import clinic.ClinicalStaffInterface2;
import clinic.PatientInterface;
import controller.ClinicCommand;
import java.io.IOException;
import java.util.Scanner;


/**
 * Command used to assign a clinical staff member to a patient.
 */
public class AssignClinicalStaff implements ClinicCommand {

  @Override
  public void execute(ClinicInterface model, Scanner scanner) throws IOException {
    PatientInterface mostRecentPatient = model.getPatients().get(model.getPatients().size() - 1);
    System.out.println("This is a patient: \n" + mostRecentPatient);
    
    ClinicalStaffInterface2 mostRecentClinStaff = null;
    for (int i = model.getEmployees().size() - 1; i >= 0; i--) {
      if (model.getEmployees().get(i) instanceof ClinicalStaffInterface2) {
        mostRecentClinStaff = (ClinicalStaffInterface2) model.getEmployees().get(i);
        break;
      }
    }
    
    if (mostRecentClinStaff == null) {
      throw new IllegalStateException("No clinical staff members available.");
    }
    
    System.out.println();
    System.out.println("This is a clinical staff member: \n" + mostRecentClinStaff);
    System.out.println();
    try {
      model.assignStaff(mostRecentPatient, mostRecentClinStaff);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      System.out.println();
      return;
    }
    System.out.println(mostRecentPatient);
    System.out.println();
  }
}
