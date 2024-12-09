package controller;

import clinic.ClinicInterface3;
import clinic.PatientInterface;
import clinic.RoomInterface;
import javax.swing.JOptionPane;
import view.ClinicLayoutPage;
import view.MasterViewInterface;

/**
 * Executes assigning of a patient to a room.
 */
public class PatientToRoom implements PatientToRoomView {
  private ClinicInterface3 model;
  private MasterViewInterface view;
  private ClinicLayoutPage clinicLayoutPage;
  
  /**
   * Executes command to transfer patient to room.
   * 
   * @param model the clinic model
   * @param view the clinic view
   */
  public PatientToRoom(ClinicInterface3 model, MasterViewInterface view) {
    this.model = model;
    this.view = view;
    clinicLayoutPage = view.getLayoutPage();
  }
  
  @Override
  public void execute() {
    JOptionPane.showMessageDialog(null, "Please select a patient.");
    
  }

  @Override
  public void processPatient(PatientInterface patient) {
    // TODO Auto-generated method stub

  }

  @Override
  public void processRoom(RoomInterface room) {
    // TODO Auto-generated method stub

  }

}
