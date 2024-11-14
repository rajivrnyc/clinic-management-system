package controller;

import clinic.ClinicInterface;
import clinic.ClinicStaffAndPatientInfo;
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
      
    }
  }
}
