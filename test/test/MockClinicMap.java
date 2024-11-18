package test;

import clinic.ClinicStaffAndPatientInfo;
import controller.ClinicCommand2;
import java.io.IOException;
import java.util.Scanner;


/**
 * Command class to mock clinic map command.
 */
public class MockClinicMap implements ClinicCommand2 {
  StringBuilder sb;
  
  /**
   * A mock constructor to record user input for this command.
   * @param sb A StringBuilder where user input is stored.
   */
  public MockClinicMap(StringBuilder sb) {
    this.sb = sb;
  }
  
  @Override
  public void execute(ClinicStaffAndPatientInfo model, Scanner scanner) throws IOException {
  }
}
