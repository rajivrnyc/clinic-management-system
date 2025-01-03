package test;

import clinic.ClinicInterface3;
import controller.AssignStaffViewInterface;
import controller.ClearRecordsView;
import controller.ClinicCommand3;
import controller.ClinicControllerGui;
import controller.DisplayPatientInterface;
import controller.PatientToRoomView;
import controller.SendHomeView;
import controller.SendHomeViewInterface;
import controller.UnassignStaffViewInterface;
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
  public void registerNewPatient() {
    ClinicCommand3 register = new MockRegisterPatientView(this.model, this.view);
    register.execute();
  }
  
  @Override
  public void assignPatientToRoom() {
    PatientToRoomView assignToRoom = new MockPatientToRoom(this.model, this.view);
    assignToRoom.execute();
  }
  
  @Override
  public void assignStafftoPatient() {
    AssignStaffViewInterface assignStaff = new MockAssignStaffView(this.model, this.view);
    assignStaff.execute();
  }
  
  @Override
  public void displayPatientInfo() {
    DisplayPatientInterface displayPatient = new MockDisplayPatient(this.model, this.view);
    displayPatient.execute();
  }
  
  @Override
  public void unassignStaffFromPatient() {
    UnassignStaffViewInterface unassignStaff = new MockUnassignStaff(this.model, this.view);
    unassignStaff.execute();
  }
  
  @Override
  public void sendPatientHome() {
    SendHomeViewInterface sendHome = new MockSendHomeView(this.model, this.view);
    sendHome.execute();
  }

  @Override
  protected void showMessage(String message) {
  }
}