package test;

import clinic.ClinicInterface3;
import controller.ClinicCommand3;
import view.MasterViewInterface;

/**
 * Mock for register patient to as its implemented in the view.
 */
public class MockRegisterPatientView implements ClinicCommand3 {
  private ClinicInterface3 model;
  private MasterViewInterface view;
  
  /**
   * A mock of the command to register a patient into the model which is then reflected
   * in the view.
   * 
   * @param model the mocked model of the clinic
   * @param view the mocked view of the clinic
   */
  public MockRegisterPatientView(ClinicInterface3 model, MasterViewInterface view) {
    this.model = model;
    this.view = view;
  }
  
  @Override
  public void execute() {
    String firstName = "First";
    String lastName = "Last";
    String dob = "dob";
    model.findPatient(firstName, lastName);
    model.addNewPatient(firstName, lastName, dob);
    model.getPatients();
    view.refresh(model);
  }
}
