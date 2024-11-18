package controller;

import clinic.ClinicStaffAndPatientInfo;
import java.io.IOException;
import java.util.Scanner;


/**
 * Represents different commands that can be executed within the clinic.
 * Each command performs a unique operation within the clinic.
 */
public interface ClinicCommand2 {

  /**
   * Executes the command with the clinic model and the input scan.
   * @param model is the model on which the command runs
   * @param scanner is the scanner providing user input for running the command
   * @throws IOException when there is an I/O error caused by running this command
   */
  void execute(ClinicStaffAndPatientInfo model, Scanner scanner) throws IOException;
}
