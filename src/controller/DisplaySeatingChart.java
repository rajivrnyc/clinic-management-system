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
    model.addNewClinicalStaff("Physician", "John", "Smith", EducationLevel.MASTERS, "0123456789");
    PatientInterface sally = model.getPatients().get(model.getPatients().size() - 1);
    model.assignStaff(sally, model.getEmployees().get(0));
    System.out.println(model.seatingChart());
  }
}
