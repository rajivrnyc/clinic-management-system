package test;

import clinic.ClinicStaffAndPatientInfo;
import controller.ClinicCommand2;
import java.io.IOException;
import java.util.Scanner;

/**
 * A command class to mock MoreThanYear.
 */
public class MockMoreThanYear implements ClinicCommand2 {
  StringBuilder sb;
  
  public MockMoreThanYear(StringBuilder sb) {
    this.sb = sb;
  }
  
  @Override
  public void execute(ClinicStaffAndPatientInfo model, Scanner scanner) throws IOException {
    
  }

}
