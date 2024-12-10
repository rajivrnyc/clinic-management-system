package test;

import clinic.ClinicInterface3;
import controller.ClearRecordsView;
import controller.ClinicCommand3;
import controller.ClinicControllerGui;
import view.MasterViewInterface;

/**
 * Class to help run tests of the mock view and model.
 */
public class TestClinicControllerGui extends ClinicControllerGui {
  private ClinicInterface3 model;
  private MasterViewInterface view;

  /**
   * Test controller to help facilitate testing features using
   * mock inputs.
   * 
   * @param in input stream reader for text controller
   * @param out appendable for text controller
   * @param model the mock model
   * @param view the mock view
   */
  public TestClinicControllerGui(Readable in, Appendable out, ClinicInterface3 model, 
        MasterViewInterface view) {
    super(in, out, model, view);
    this.model = model;
    this.view = view;
  }
  
  @Override
  public void clearAllRecords() {
    ClinicCommand3 clearCommand = new MockClearRecords(this.model, this.view);
    clearCommand.execute();
  }
  
  @Override
  public void exitProgram() {
    ClinicCommand3 exitCommand = new MockExitProgram(this.model, this.view);
    exitCommand.execute();
  }

  @Override
  protected void showMessage(String message) {
  }
}