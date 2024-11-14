package controller;

import clinic.ClinicInterface;
import clinic.ClinicStaffAndPatientInfo;
import java.io.IOException;
import java.util.Scanner;


/**
 * A controller command to display patients in the clinic that haven't been
 * to the clinic for more than one year.
 */
public class MoreThanYear implements ClinicCommand2 {

  @Override
  public void execute(ClinicStaffAndPatientInfo model, Scanner scanner) throws IOException {
    System.out.println("List of patients who haven't visited the clinic in more than a year:");
    System.out.println(model.listPatientVisitMoreThanYear());
  }

}
