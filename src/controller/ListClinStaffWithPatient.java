package controller;

import clinic.ClinicInterface;
import clinic.ClinicStaffAndPatientInfo;
import java.io.IOException;
import java.util.Scanner;


/**
 * Command used to list clinical staff members who have a patient in the clinic.
 */
public class ListClinStaffWithPatient implements ClinicCommand {

  @Override
  public void execute(ClinicInterface model, Scanner scanner) throws IOException {
    ClinicStaffAndPatientInfo list = (ClinicStaffAndPatientInfo) model;
    System.out.println("List of Clinical Staff Members with an Assigned Patient:");
    System.out.println(list.listClinWithPatient());
  }
}
