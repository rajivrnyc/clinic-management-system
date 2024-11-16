package controller;

import clinic.ClinicStaffAndPatientInfo;
import java.io.IOException;
import java.util.Scanner;

/**
 * A command for the controller to list all patients who
 * have made more than two visits to the clinic in the past year.
 */
public class TwicePastYear implements ClinicCommand2 {

  @Override
  public void execute(ClinicStaffAndPatientInfo model, Scanner scanner) throws IOException {
    System.out.println();
    System.out.println("List of patients who have visited the clinic twice in the past year:");
    System.out.println(model.listVisitTwiceOneYear());
    System.out.println();
  }

}
