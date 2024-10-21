package controller;

import clinic.ClinicInterface;
import java.io.IOException;
import java.util.Scanner;



/**
 * Mocking display of patient in clinic.
 */
public class MockPatientDisplay implements ClinicCommand {
  private StringBuilder log;
  
  public MockPatientDisplay() {
  }

  @Override
  public void execute(ClinicInterface model, Scanner scanner) throws IOException {
    log.append(scanner.nextLine());
    log.append(scanner.nextLine());
  }
}
