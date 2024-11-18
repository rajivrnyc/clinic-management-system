package test;

import clinic.ClinicStaffAndPatientInfo;
import controller.ClinicCommand2;
import java.io.IOException;
import java.util.Scanner;


/**
 * Mock command class to test ListClinWithPatient.
 */
public class MockListClinWithPatient implements ClinicCommand2 {
  StringBuilder sb;

  /**
   * A mock constructor to record user input for this command.
   * @param sb A StringBuilder where user input is stored.
   */
  public MockListClinWithPatient(StringBuilder sb) {
    this.sb = sb;
  }

  @Override
  public void execute(ClinicStaffAndPatientInfo model, Scanner scanner) throws IOException {
  }
}
