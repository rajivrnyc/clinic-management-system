package controller;

import clinic.ClinicStaffAndPatientInfo;
import java.io.IOException;
import java.util.Scanner;


/**
 * Represents different commands that can be executed within the clinic.
 * Each command performs a unique operation within the clinic.
 */
public interface ClinicCommand2 {

  void execute(ClinicStaffAndPatientInfo model, Scanner scanner) throws IOException;
}
