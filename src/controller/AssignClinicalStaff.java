package controller;

import clinic.ClinicInterface;
import clinic.ClinicalStaffInterface;
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
    ClinicalStaffInterface mostRecentClinStaff = (ClinicalStaffInterface) model
         .getEmployees().get(model.getEmployees().size() - 1);
    
    System.out.println("This is a patient: " + mostRecentPatient);
    System.out.println();
    System.out.println("This is a clinical staff member: " + mostRecentClinStaff);
    System.out.println();
    model.assignStaff(mostRecentPatient, mostRecentClinStaff);
    System.out.println(mostRecentPatient);
  }
}
