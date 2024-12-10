package test;

import clinic.ClinicInterface3;
import clinic.ClinicalStaffInterface;
import clinic.PatientInterface;
import controller.UnassignStaffViewInterface;
import view.ClinicLayoutPage;
import view.MasterViewInterface;

/**
 * Mock class to test unassign staff.
 */
public class MockUnassignStaff implements UnassignStaffViewInterface {
  private ClinicInterface3 model;
  private MasterViewInterface view;
  
  /**
   * Executes command to unassign staff from patient.
   * 
   * @param model the model of the clinic
   * @param view the view of the clinic
   */
  public MockUnassignStaff(ClinicInterface3 model, MasterViewInterface view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void processPatientUnassignStaff(PatientInterface patient) {
  }

  @Override
  public void execute() {
    model.unassignClinStaff(null, null);
    view.refresh(model);
  }

}
