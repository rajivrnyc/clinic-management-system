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
    model.addNewPatient("Joe", "Johnson", "01/02/1991");
    model.addNewPatient("Jack", "Johnson", "01/02/1992");
    PatientInterface sallyJ = model.getPatients().get(model.getPatients().size() - 3);
    PatientInterface joeJ = model.getPatients().get(model.getPatients().size() - 2);
    PatientInterface jackJ = model.getPatients().get(model.getPatients().size() - 1);
    model.addNewClinicalStaff("Doctor", "John", "Smith", EducationLevel.MASTERS, "0123456789");
    ClinicalStaffInterface john = (ClinicalStaffInterface) model.getEmployees().get(0);
    model.assignStaff(sallyJ, john);
    RoomInterface roomTest = model.getRoomFromNumber(2);
    RoomInterface roomTest2 = model.getRoomFromNumber(3);
    model.assignPatient(sallyJ, roomTest);
    model.assignPatient(joeJ, roomTest);
    model.assignPatient(jackJ, roomTest2);
    System.out.println(model.seatingChart());
  }
}
