package controller;

import clinic.ClinicStaffAndPatientInfo;
import java.io.IOException;
import java.util.Scanner;


/**
 * Command used to list clinical staff members who have a patient in the clinic.
 */
public class ListClinStaffWithPatient implements ClinicCommand2 {

  @Override
  public void execute(ClinicStaffAndPatientInfo model, Scanner scanner) throws IOException {
    System.out.println(model.listClinWithPatient());
  }
}
