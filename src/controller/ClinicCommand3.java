package controller;

import clinic.ClinicInterface3;
import java.io.IOException;
import java.util.Scanner;



/**
 * Represents different commands that can be executed within the clinic.
 * Each command performs a unique operation within the clinic.
 */
public interface ClinicCommand3 {

  /**
   * Executes the command with the clinic model and the input scan.
   * 
   */
  void execute();
}