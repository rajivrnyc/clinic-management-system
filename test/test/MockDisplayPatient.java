package test;

import clinic.ClinicInterface3;
import clinic.PatientInterface;
import clinic.RoomInterface;
import controller.DisplayPatientInterface;
import view.ClinicLayoutPage;
import view.MasterViewInterface;

/**
 * Mock class to help test display patient info.
 */
public class MockDisplayPatient implements DisplayPatientInterface {
  private ClinicInterface3 model;
  private MasterViewInterface view;
  
  /**
   * Executes command to display patient.
   * 
   * @param model the model of the clinic
   * @param view the view of the clinic
   */
  public MockDisplayPatient(ClinicInterface3 model, MasterViewInterface view) {
    this.model = model;
    this.view = view;
  }
  
  @Override
  public void execute() {
    
  }
  
  @Override
  public void processPatientDisplayPatient(PatientInterface patient) {
  }

}
