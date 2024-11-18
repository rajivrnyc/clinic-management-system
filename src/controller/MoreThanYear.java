package controller;

import clinic.ClinicInterface;
import clinic.ClinicStaffAndPatientInfo;
import clinic.PatientInterface2;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;


/**
 * A controller command to display patients in the clinic that haven't been
 * to the clinic for more than one year.
 */
public class MoreThanYear implements ClinicCommand2 {

  @Override
  public void execute(ClinicStaffAndPatientInfo model, Scanner scanner) throws IOException {
    System.out.println();
    PatientInterface2 aandi = model.findPatient("Aandi", "Acute");
    LocalDateTime dateTime = LocalDateTime.parse("2020-11-11T19:09:48.601523100");
    PatientInterface2 beth = model.findPatient("Beth", "Bunion");
    LocalDateTime dateTime2 = LocalDateTime.parse("2019-11-11T19:09:48.601523100");
    aandi.addOldRecord("Headache", 29.0, dateTime);
    beth.addOldRecord("Stomachache", 30.0, dateTime2);
    System.out.println("List of patients who haven't visited the clinic in more than a year:");
    System.out.println(model.listPatientVisitMoreThanYear());
    System.out.println();
  }

}
