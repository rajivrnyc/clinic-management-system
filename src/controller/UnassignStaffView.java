package controller;

import clinic.ClinicInterface3;
import clinic.ClinicalStaffInterface;
import clinic.PatientInterface;
import clinic.RoomInterface;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import view.ClinicLayoutPage;
import view.MasterViewInterface;

/**
 * Command class to unassign staff from patient for the view.
 */
public class UnassignStaffView implements UnassignStaffViewInterface {
  private ClinicInterface3 model;
  private MasterViewInterface view;
  private ClinicLayoutPage clinicLayoutPage;
  private PatientInterface selectedPatient;
  private ClinicalStaffInterface selectedPatientStaff;
  
  /**
   * Executes command to unassign staff from patient.
   * 
   * @param model the model of the clinic
   * @param view the view of the clinic
   */
  public UnassignStaffView(ClinicInterface3 model, MasterViewInterface view) {
    this.model = model;
    this.view = view;
    clinicLayoutPage = view.getLayoutPage();
  }
  
  @Override
  public void execute() {
    JOptionPane.showMessageDialog(null, "Please select a patient to unassign from");
    clinicLayoutPage.enablePatientSelectionUnassignStaff(this);
  }

  @Override
  public void processPatientUnassignStaff(PatientInterface patient) {
    clinicLayoutPage.disablePatientSelection();
    selectedPatient = patient;
    List<ClinicalStaffInterface> clin = selectedPatient.getAllocated();
    if (clin.isEmpty()) {
      JOptionPane.showMessageDialog(null, "This patient has no clinical staff members assigned");
      return;
    }
    String[] staffNames  = new String[clin.size()];
    for (int i = 0; i < clin.size(); i++) {
      if (clin.get(i).getStatus()) {
        StringBuilder sb = new StringBuilder();
        sb.append(clin.get(i).getFirstName()).append(" ").append(clin.get(i).getLastName());
        staffNames[i] = sb.toString();
      }
    }
    JComboBox<String> staffCombo = new JComboBox<>(staffNames);
    int result = JOptionPane.showConfirmDialog(null, staffCombo, "Select Clinical Staff",
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    String selectedStaffName = "";
    if (result == JOptionPane.OK_OPTION) {
      selectedStaffName = (String) staffCombo.getSelectedItem();
    }
    
    for (ClinicalStaffInterface c : clin) {
      StringBuilder sb = new StringBuilder();
      sb.append(c.getFirstName()).append(" ").append(c.getLastName());
      String fullName = sb.toString();
      if (fullName.equals(selectedStaffName)) {
        selectedPatientStaff = c;
      }
    }
    try {
      model.unassignClinStaff(selectedPatientStaff, selectedPatient);
      JOptionPane.showMessageDialog(null, "Clinical Staff Member Unassigned");
      view.refresh(model);
    } catch (IllegalArgumentException e) {
      StringBuilder sb = new StringBuilder();
      sb.append("Error: ").append(e.getMessage());
      JOptionPane.showMessageDialog(null, sb.toString());
    }
  }


}
