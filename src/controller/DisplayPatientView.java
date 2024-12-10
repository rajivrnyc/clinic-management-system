package controller;

import clinic.ClinicInterface3;
import clinic.PatientInterface;
import clinic.RoomInterface;
import javax.swing.JOptionPane;
import view.ClinicLayoutPage;
import view.MasterViewInterface;


/**
 * Display patient info command in view.
 */
public class DisplayPatientView implements DisplayPatientInterface {
  private ClinicInterface3 model;
  private MasterViewInterface view;
  private ClinicLayoutPage clinicLayoutPage;
  private PatientInterface selectedPatient;
  private RoomInterface room;
  
  /**
   * Executes command to display patient.
   * 
   * @param model the model of the clinic
   * @param view the view of the clinic
   */
  public DisplayPatientView(ClinicInterface3 model, MasterViewInterface view) {
    this.model = model;
    this.view = view;
    clinicLayoutPage = view.getLayoutPage();
  }
  
  @Override
  public void execute() {
    JOptionPane.showMessageDialog(null, "Please select a patient to display.");
    clinicLayoutPage.enablePatientSelectionDisplayPatient(this);
  }

  @Override
  public void processPatientDisplayPatient(PatientInterface patient) {
    clinicLayoutPage.disablePatientSelection();
    selectedPatient = patient;
    JOptionPane.showMessageDialog(null, patient.toString());
  }

}
