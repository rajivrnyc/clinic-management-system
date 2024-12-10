package test;

import clinic.ClinicInterface3;
import controller.ClinicCommand3;
import view.MasterViewInterface;

/**
 * Mock for Exit Program.
 */
public class MockExitProgram implements ClinicCommand3 {
  private ClinicInterface3 model;
  private MasterViewInterface view;
 
  public MockExitProgram(ClinicInterface3 model, MasterViewInterface view) {
    this.model = model;
    this.view = view;
  }
    
  @Override
  public void execute() {
    System.out.println("System exited");
    
  }

}
