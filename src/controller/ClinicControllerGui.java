package controller;


import clinic.Clinic2;
import clinic.Clinic3;
import clinic.ClinicInterface3;
import clinic.ClinicStaffAndPatientInfo;
import clinic.ClinicalStaffInterface;
import clinic.ClinicalStaffInterface2;
import clinic.PatientInterface;
import clinic.PatientInterface2;
import clinic.RoomInterface;
import clinic.Staff;
import clinic.StaffClass;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import view.ClinicLayoutPage;
import view.MasterViewInterface;

/**
 * Controller to support view implementation for the mvc.
 */
public class ClinicControllerGui extends ClinicController2 implements Features {
  private  ClinicInterface3 model;
  private final MasterViewInterface view;
  private ClinicLayoutPage clinicLayoutPage;
  private PatientInterface selectedPatient;
  private RoomInterface room;
  private ClinicalStaffInterface selectedPatientStaff;
  private ClinicalStaffInterface2 selectedStaff;
  
  
  /**
   * Creates a gui controller for view implementation where it takes model and
   * view and updates them based on user input.
   * 
   * @param in user input in the form of text
   * @param out is System output
   * @param model the model of the clinic
   * @param view the view of the clinic
   */
  public ClinicControllerGui(Readable in, Appendable out, ClinicInterface3 model, 
         MasterViewInterface view) {
    super(in, out, model);
    this.model = model;
    this.view = view;
    view.setFeatures(this);
    
    
  }

  @Override
  public void loadClinicFile() {
    try {
      FileReader fileReader = new FileReader("res/clinicfile.txt");
      model = Clinic3.readFile(fileReader);
      view.refresh(model);
      view.setMenuItems(true);
      JOptionPane.showMessageDialog(null, "Clinic loaded successfully.");
       
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
  }

  @Override
  public void clearAllRecords() {
    ClinicCommand3 clearCommand = new ClearRecordsView(this.model, this.view);
    clearCommand.execute();

  }

  @Override
  public void exitProgram() {
    ClinicCommand3 exit = new ExitProgramView(this.model, this.view);
    exit.execute();
  }

  @Override
  public void registerNewPatient() {
    ClinicCommand3 register = new RegisterPatientView(this.model, this.view);
    register.execute();
  }
  
  @Override
  public void assignPatientToRoom() {
    PatientToRoomView toRoom = new PatientToRoom(this.model, this.view);
    toRoom.execute();
  }
  
  @Override
  public void assignStafftoPatient() {
    AssignStaffViewInterface assignStaff = new AssignStaffView(this.model, this.view);
    assignStaff.execute();
  }

  @Override
  public void displayPatientInfo() {
    DisplayPatientInterface assignStaff = new DisplayPatientView(this.model, this.view);
  }

  @Override
  public void unassignStaffFromPatient() {
    clinicLayoutPage = view.getLayoutPage();
    JOptionPane.showMessageDialog(null, "Please select a patient to unassign from");
    clinicLayoutPage.enablePatientSelectionUnassignStaff(this);
  }
  

  @Override
  public void sendPatientHome() {
    clinicLayoutPage = view.getLayoutPage();
    JOptionPane.showMessageDialog(null, "Please select a patient to send home.");
    clinicLayoutPage.enablePatientSelectionSendHome(this);
  }
  
  @Override
  public void go() {
    view.makeVisible();
  }
  
  private void setModel(ClinicInterface3 newModel) {
    this.model = newModel;
    view.refresh(newModel);
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
      setModel(model);
    } catch (IllegalArgumentException e) {
      StringBuilder sb = new StringBuilder();
      sb.append("Error: ").append(e.getMessage());
      JOptionPane.showMessageDialog(null, sb.toString());
    }
  }
  
  @Override
  public void processPatientSendHome(PatientInterface patient) {
    clinicLayoutPage.disablePatientSelection();
    selectedPatient = patient;
    JOptionPane.showMessageDialog(null, "Please select the approving staff member.");
    
    List<ClinicalStaffInterface2> clin = getClinStaff();
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
    for (ClinicalStaffInterface2 c : clin) {
      StringBuilder sb = new StringBuilder();
      sb.append(c.getFirstName()).append(" ").append(c.getLastName());
      String fullName = sb.toString();
      if (fullName.equals(selectedStaffName)) {
        selectedStaff = c;
      }
    }
    try {
      model.sendHome(selectedPatient, selectedStaff);
      JOptionPane.showMessageDialog(null, "Patient sent home successfully.");
      setModel(model);
    } catch (IllegalArgumentException e) {
      StringBuilder sb = new StringBuilder();
      sb.append("Error: ").append(e.getMessage());
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
