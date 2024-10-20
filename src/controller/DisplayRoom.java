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
 * Command used to display the contents of a room within a clinic model.
 */
public class DisplayRoom implements ClinicCommand {

  @Override
  public void execute(ClinicInterface model, Scanner scanner) throws IOException {
    model.addNewPatient("Sally", "Johnson", "01/02/1990");
    model.addNewPatient("Joe", "Johnson", "01/02/1991");
    PatientInterface sallyJ = model.getPatients().get(model.getPatients().size() - 2);
    PatientInterface joeJ = model.getPatients().get(model.getPatients().size() - 1);
    model.addNewClinicalStaff("Doctor", "John", "Smith", EducationLevel.MASTERS, "0123456789");
    ClinicalStaffInterface john = (ClinicalStaffInterface) model.getEmployees().get(0);
    model.assignStaff(sallyJ, john);
    RoomInterface roomTest = model.getRoomFromNumber(2);
    RoomInterface roomTest1 = model.getRoomFromNumber(1);
    model.assignPatient(sallyJ, roomTest);
    model.assignPatient(joeJ, roomTest);
    System.out.println(roomTest);
  }
}
