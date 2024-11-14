package controller;

import java.io.IOException;
import java.util.Scanner;

import clinic.ClinicInterface;
import clinic.ClinicStaffAndPatientInfo;

/**
 * A controller command to display patients in the clinic that haven't been
 * to the clinic for more than one year.
 */
public class MoreThanYear implements ClinicCommand {

  @Override
  public void execute(ClinicInterface model, Scanner scanner) throws IOException {
    ClinicStaffAndPatientInfo list = (ClinicStaffAndPatientInfo) model;
    System.out.println("List of patients who haven't visited the clinic in more than a year:");
    System.out.println(list.listPatientVisitMoreThanYear());
  }

}
