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
public class UnassignClinicalStaff implements ClinicCommand {

  @Override
  public void execute(ClinicInterface model, Scanner scanner) throws IOException {
    ClinicStaffAndPatientInfo tempModel = (ClinicStaffAndPatientInfo) model;
    System.out.println("Select a Patient:");
    List<PatientInterface> patients = tempModel.getPatients();
    
    if (patients.isEmpty()) {
      System.out.println("Patient List is empty");
      return;
    }
    
    for (int i = 0; i < patients.size(); i++) {
      
    }
  }
}
