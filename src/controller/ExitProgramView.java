package controller;

import clinic.ClinicInterface3;
import java.io.IOException;
import view.MasterViewInterface;

/**
 * Command to exit the view.
 */
public class ExitProgramView implements ClinicCommand3 {
  private ClinicInterface3 model;
  private MasterViewInterface view;
  
  /**
   * A constructor for the command to exit the program.
   * 
   * @param model the model of the clinic
   * @param view the view of the clinic
   */
  public ExitProgramView(ClinicInterface3 model, MasterViewInterface view) {
    this.model = model;
    this.view = view;
  }
  
  @Override
  public void execute() {
    System.exit(0);
  }

}
