package test;

import clinic.ClinicStaffAndPatientInfo;
import controller.ClinicCommand2;
import java.io.IOException;
import java.util.Scanner;



/**
 * Mock command class for ListClinNumAssigned.
 */
public class MockListClinNumAssigned implements ClinicCommand2 {
  StringBuilder sb;
  
  /**
   * A mock constructor to record user input for this command.
   * @param sb A StringBuilder where user input is stored.
   */
  public MockListClinNumAssigned(StringBuilder sb) {
    this.sb = sb;
  }

  @Override
  public void execute(ClinicStaffAndPatientInfo model, Scanner scanner) throws IOException {
  }

}
