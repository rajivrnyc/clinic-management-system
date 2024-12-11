package test;

import clinic.ClinicInterface3;
import controller.ClinicCommand3;
import view.MasterViewInterface;

/**
 * Mock for clear records feature.
 */
public class MockClearRecords implements ClinicCommand3 {
  private ClinicInterface3 model;
  private MasterViewInterface view;
  
  /**
   * A constructor for the mock for the command to clear all records 
   * from the model which is reflected in the view.
   * 
   * @param model the mocked model of the clinic
   * @param view the mocked view of the clinic.
   */
  public MockClearRecords(ClinicInterface3 model, MasterViewInterface view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void execute() {
    model.reset();
    view.refresh(model);
    view.setMenuItems(false);
  }

}
