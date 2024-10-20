package controller;

import clinic.ClinicInterface;
import clinic.ClinicalStaffInterface;
import clinic.EducationLevel;
import clinic.PatientInterface;
import clinic.RoomInterface;
import controller.ClinicCommand;
import java.io.IOException;
import java.util.Scanner;


/**
 * Displays seating chart of the clinic model.
 */
public class DisplaySeatingChart implements ClinicCommand {

  @Override
  public void execute(ClinicInterface model, Scanner scanner) throws IOException {

    model.addNewPatient("Sally", "Johnson", "01/02/1990");
    PatientInterface sally = model.getPatients().get(model.getPatients().size() - 1);
    System.out.println(model.seatingChart());
  }
}
