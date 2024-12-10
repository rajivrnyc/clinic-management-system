package test;

import clinic.ClinicInterface3;
import clinic.PatientInterface;
import clinic.RoomInterface;
import controller.PatientToRoomView;
import view.ClinicLayoutPage;
import view.MasterViewInterface;

/**
 * Mock class for assigning a patient to a room.
 */
public class MockPatientToRoom implements PatientToRoomView {
  private ClinicInterface3 model;
  private MasterViewInterface view;

  
  /**
   * Executes command to transfer patient to room.
   * 
   * @param model the clinic model
   * @param view the clinic view
   */
  public MockPatientToRoom(ClinicInterface3 model, MasterViewInterface view) {
    this.model = model;
    this.view = view;
  }
  
  @Override
  public void execute() {
    model.assignPatient(null, null);
    view.refresh(model);
  }

  @Override
  public void processPatient(PatientInterface patient) {

  }

  @Override
  public void processRoom(RoomInterface room) {

  }

}
