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


  public MockListClinWithPatient(StringBuilder sb) {
    this.sb = sb;
  }

  @Override
  public void execute(ClinicStaffAndPatientInfo model, Scanner scanner) throws IOException {
    System.out.println(model.listClinWithPatient());
  }
}
