package controller;


import clinic.ClinicInterface3;
import clinic.ClinicalStaffInterface;
import clinic.ClinicalStaffInterface2;
import clinic.PatientInterface;
import clinic.RoomInterface;
import clinic.Staff;
import clinic.StaffClass;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import view.ClinicLayoutPage;
import view.MasterViewInterface;

/**
 * Command in view to assign staff to a patient.
 */
public class AssignStaffView implements AssignStaffViewInterface {
  private ClinicInterface3 model;
  private MasterViewInterface view;
  private ClinicLayoutPage clinicLayoutPage;
  private PatientInterface selectedPatient;
  private ClinicalStaffInterface2 selectedStaff;
  
  /**
   * Executes command to transfer patient to room.
   * 
   * @param model the clinic model
   * @param view the clinic view
   */
  public AssignStaffView(ClinicInterface3 model, MasterViewInterface view) {
    this.model = model;
    this.view = view;
    clinicLayoutPage = view.getLayoutPage();
  }

  @Override
  public void execute() {
    List<ClinicalStaffInterface2> clin = getClinStaff();
    String[] staffNames = new String[clin.size()];
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
    for (ClinicalStaffInterface2 c : clin) {
      StringBuilder sb = new StringBuilder();
      sb.append(c.getFirstName()).append(" ").append(c.getLastName());
      String fullName = sb.toString();
      if (fullName.equals(selectedStaffName)) {
        selectedStaff = c;
      }
    }
    this.clinicLayoutPage = view.getLayoutPage();
    JOptionPane.showMessageDialog(null, "Please select a patient.");
    clinicLayoutPage.enablePatientSelectionAssignStaff(this);
  }

  @Override
  public void processPatientAssignStaff(PatientInterface patient) {
    clinicLayoutPage.disablePatientSelection();
    selectedPatient = patient;
    try {
      model.assignStaff(selectedPatient, selectedStaff);
      JOptionPane.showMessageDialog(null, "Staff assigned!");
      view.refresh(model);
    } catch (IllegalArgumentException e) {
      StringBuilder sb = new StringBuilder();
      sb.append("Error: " + e.getMessage());
      JOptionPane.showMessageDialog(null, sb.toString());
    }
  }
  
  private List<ClinicalStaffInterface2> getClinStaff() {
    List<ClinicalStaffInterface2> clin = new ArrayList<>();
    List<Staff> staff = model.getEmployees();
    List<StaffClass> stfClass = new ArrayList<>();
    for (Staff emp : staff) {
      stfClass.add((StaffClass) emp);
    }
    for (StaffClass stf : stfClass) {
      if (stf.isClinicalStaff()) {
        clin.add((ClinicalStaffInterface2) stf);
      }
    }
    return clin;
  }

}
