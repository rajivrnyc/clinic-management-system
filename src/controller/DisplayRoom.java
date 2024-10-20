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
    RoomInterface roomTest1 = model.getRoomFromNumber(1);
    System.out.println(roomTest1);
  }
}
