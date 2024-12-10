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
