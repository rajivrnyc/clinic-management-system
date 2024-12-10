package controller;

import clinic.ClinicInterface3;
import java.io.IOException;
import java.util.Scanner;
import view.MasterViewInterface;



/**
 * Represents different commands that can be executed within the clinic.
 * Each command performs a unique operation within the clinic.
 */
public interface ClinicCommand3 {

  /**
   * Executes the command with the clinic model and the input scan.
   * 
   * @param model the model of the clinic
   * @param view the view of the clinic
   */
  void execute(ClinicInterface3 model, MasterViewInterface view);
}