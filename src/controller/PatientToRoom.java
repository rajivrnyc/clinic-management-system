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
  private PatientInterface selectedPatient;
  private RoomInterface room;
  
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
    clinicLayoutPage.enablePatientSelectionAssignRoom(this);
  }

  @Override
  public void processPatient(PatientInterface patient) {
    this.selectedPatient = patient;
    clinicLayoutPage.disablePatientSelection();
    JOptionPane.showMessageDialog(null, "Please select a Room");
    clinicLayoutPage.enableRoomSelectionAssignRoom(this);
  }

  @Override
  public void processRoom(RoomInterface room) {
    this.room = room;
    clinicLayoutPage.disableRoomSelection();
    try {
      model.assignPatient(selectedPatient, this.room);
      JOptionPane.showMessageDialog(null, "Patient moved!");
      setModel(model);
    } catch (IllegalArgumentException e) {
      StringBuilder sb = new StringBuilder();
      sb.append("Error: " + e.getMessage());
      JOptionPane.showMessageDialog(null, sb.toString());
    } catch (IllegalStateException e) {
      StringBuilder sb = new StringBuilder();
      sb.append("Error: ").append(e.getMessage());
      JOptionPane.showMessageDialog(null, sb.toString());
    }
  }
  
  private void setModel(ClinicInterface3 newModel) {
    this.model = newModel;
    view.refresh(newModel);
  }
}
