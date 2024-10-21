package test;

import clinic.ClinicInterface;
import clinic.ClinicalStaffInterface;
import clinic.PatientInterface;
import controller.ClinicCommand;
import java.io.IOException;
import java.util.Scanner;


/**
 * A command to send a patient home.
 */
public class MockSendHome implements ClinicCommand {
  StringBuilder sb;
  
  public MockSendHome(StringBuilder sb) {
    this.sb = sb;
  }

  @Override
  public void execute(ClinicInterface model, Scanner scanner) throws IOException {
    System.out.println(model.getRoomFromNumber(3));
    System.out.println();
    System.out.println(model.seatingChart());
    System.out.println();
    System.out.println("Clive Cardiac is in Room 3.");
    System.out.println("Sending Clive Cardiac home...");
    PatientInterface clive = model.getPatients().get(2);
    
    ClinicalStaffInterface amy = (ClinicalStaffInterface) model.getEmployees().get(0);
    model.sendHome(clive, amy);
    System.out.println("Clive has been sent home!");
    System.out.println();
    System.out.println(model.getRoomFromNumber(3));
    
    System.out.println();
    System.out.println(model.seatingChart());
  }

}
