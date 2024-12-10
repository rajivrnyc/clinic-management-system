package test;

import clinic.ClinicInterface3;
import clinic.ClinicalStaffInterface2;
import clinic.PatientInterface;
import controller.AssignStaffViewInterface;
import view.ClinicLayoutPage;
import view.MasterViewInterface;

/**
 * Mock to test assignment of staff member to a patient.
 */
public class MockAssignStaffView implements AssignStaffViewInterface {

  private ClinicInterface3 model;
  private MasterViewInterface view;
  
  /**
   * Executes command to transfer patient to room.
   * 
   * @param model the clinic model
   * @param view the clinic view
   */
  public MockAssignStaffView(ClinicInterface3 model, MasterViewInterface view) {
    this.model = model;
    this.view = view;
  }
  
  @Override
  public void execute() {
    view.refresh(model);
    model.getEmployees();
  }

  @Override
  public void processPatientAssignStaff(PatientInterface patient) {

  }

}
