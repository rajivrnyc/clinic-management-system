package controller;

import clinic.ClinicInterface3;
import java.io.IOException;
import view.MasterViewInterface;

/**
 * Command to exit the view.
 */
public class ExitProgramView implements ClinicCommand3 {

  @Override
  public void execute(ClinicInterface3 model, MasterViewInterface view) {
    System.exit(0);
  }

}
