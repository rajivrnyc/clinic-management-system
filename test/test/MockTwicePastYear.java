package test;


import clinic.ClinicStaffAndPatientInfo;
import controller.ClinicCommand2;
import java.io.IOException;
import java.util.Scanner;


/**
 * A mock of the TwicePastYear command class.
 */
public class MockTwicePastYear implements ClinicCommand2 {
  StringBuilder sb;
  
  /**
   * A mock constructor to record user input for this command.
   * @param sb A StringBuilder where user input is stored.
   */
  public MockTwicePastYear(StringBuilder sb) {
    this.sb = sb;
  }
  
  @Override
  public void execute(ClinicStaffAndPatientInfo model, Scanner scanner) throws IOException {
  }

}
