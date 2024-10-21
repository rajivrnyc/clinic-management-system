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
public class MockAssignPatient implements ClinicCommand {
  StringBuilder sb;
  
  public MockAssignPatient(StringBuilder sb) {
    this.sb = sb;
  }

  @Override
  public void execute(ClinicInterface model, Scanner scanner) throws IOException {
    System.out.println("Sally is a new patient");
    model.addNewPatient("Sally", "Johnson", "01/02/1990");
    System.out.println();
    System.out.println(model.getRoomFromNumber(model.getPatients()
        .get(model.getPatients().size() - 1).getRoomNumber()));
    System.out.println();
    RoomInterface room1 = model.getRoomFromNumber(3);
    System.out.println(room1);
    System.out.println();
    System.out.println("Assigning Sally to room 3");
    
    System.out.println();
    
    PatientInterface mostRecentPatient = model.getPatients().get(model.getPatients().size() - 1);
    
    model.assignPatient(mostRecentPatient, room1);
    System.out.println(room1);
    
  }
}
