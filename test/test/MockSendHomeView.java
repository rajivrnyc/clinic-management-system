package test;

import clinic.ClinicInterface3;
import clinic.ClinicalStaffInterface2;
import clinic.PatientInterface;
import controller.SendHomeViewInterface;
import view.ClinicLayoutPage;
import view.MasterViewInterface;

/**
 * Mock class to test send home.
 */
public class MockSendHomeView implements SendHomeViewInterface {

  private ClinicInterface3 model;
  private MasterViewInterface view;
  
  /**
   * Executes command to send a patient home.
   * 
   * @param model the model of the clinic
   * @param view the view of the clinic
   */
  public MockSendHomeView(ClinicInterface3 model, MasterViewInterface view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void execute() {
    model.sendHome(null, null);
    view.refresh(model);
    model.getEmployees();
  }

  @Override
  public void processPatientSendHome(PatientInterface patient) {
  }

}
