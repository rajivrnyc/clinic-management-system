package controller;

import java.io.IOException;

/**
 * Command to exit the view.
 */
public class ExitProgramView implements ClinicCommand3 {

  @Override
  public void execute() {
    System.exit(0);
  }

}
