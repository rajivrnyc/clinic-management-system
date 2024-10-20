package controller;

import clinic.ClinicInterface;
import clinic.Patient;
import clinic.PatientInterface;
import clinic.Room;
import clinic.RoomInterface;
import controller.ClinicCommand;
import java.io.IOException;
import java.util.Scanner;

/**
 * Command for controller to assign a patient to a room.
 */
public class AssignPatientRoom implements ClinicCommand {

  @Override
  public void execute(ClinicInterface model, Scanner scanner) throws IOException {
    System.out.println("Sally is a new patient");
    RoomInterface room1 = model.getRoomFromNumber(1);
    
    System.out.println();
    System.out.println(room1);
    System.out.println();
    System.out.println("Assigning Sally to room 1");
    
    System.out.println();
    model.addNewPatient("Sally", "Johnson", "01/02/1990");
    PatientInterface mostRecentPatient = model.getPatients().get(model.getPatients().size() - 1);
    
    model.assignPatient(mostRecentPatient, room1);
    System.out.println(room1);
    
  }
}
