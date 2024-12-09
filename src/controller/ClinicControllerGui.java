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
import view.Features;
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
      ClinicInterface3 newModel = Clinic3.readFile(fileReader);
      this.setModel(newModel);
      view.setMenuItems(true);
      JOptionPane.showMessageDialog(null, "Clinic loaded successfully.");
      
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
  }

  @Override
  public void clearAllRecords() {
    model.reset();
    this.setModel(model);
    view.setMenuItems(false);
    JOptionPane.showMessageDialog(null, "Clinic records have been cleared.");
    
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void registerNewPatient() {
    String patientFirstName = JOptionPane.showInputDialog(null, "Enter the patient's first name: ",
        "Register Patient", JOptionPane.PLAIN_MESSAGE);
    if (patientFirstName == null || patientFirstName.trim().isEmpty()) {
      JOptionPane.showMessageDialog(null, "Patient registration canceled or invalid input.", 
          "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    String patientlastName = JOptionPane.showInputDialog(null, "Enter the patient's last name: ",
            "Register Patient", JOptionPane.PLAIN_MESSAGE);
    if (patientlastName == null || patientlastName.trim().isEmpty()) {
      JOptionPane.showMessageDialog(null, "Patient registration canceled or invalid input.", 
            "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    
    final String birthdate = JOptionPane.showInputDialog(null, "Enter the patient's birthdate "
        + "(dd/mm/yy):", "RegisterPatient",
        JOptionPane.PLAIN_MESSAGE);
    
    String complaint = JOptionPane.showInputDialog(null, "Enter the patient's chief complaint :",
        "Register Patient", JOptionPane.PLAIN_MESSAGE);
    
    if (complaint == null || complaint.trim().isEmpty()) {
      JOptionPane.showMessageDialog(null, "Patient registration canceled or invalid input.", 
              "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    
    String temperature = JOptionPane.showInputDialog(null, "Enter the patient's temperature :",
            "Register Patient", JOptionPane.PLAIN_MESSAGE);
        
    if (temperature == null || temperature.trim().isEmpty()) {
      JOptionPane.showMessageDialog(null, "Patient registration canceled or invalid input.", 
                  "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    
    double temp;
    
    try {
      temp = Double.parseDouble(temperature.trim());

      if (temp < 0) {
        throw new NumberFormatException("Temperature cannot be negative.");
      }

    } catch (NumberFormatException e) {

      JOptionPane.showMessageDialog(null, "Invalid input for temperature. "
          + "Please enter a valid positive number.",
                "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    
    model.addNewPatient(patientFirstName, patientlastName, birthdate);
    PatientInterface patient = model.getPatients().get(model.getPatients().size() - 1);
    
    PatientInterface2 patientI = (PatientInterface2) patient;
    patientI.addRecord(complaint, temp);
    
    this.setModel(model);
    JOptionPane.showMessageDialog(null, "Patient successfully added to clinic.");
    
  }
  
  @Override
  public void assignPatientToRoom() {
    clinicLayoutPage = view.getLayoutPage();
    JOptionPane.showMessageDialog(null, "Please select a patient.");
    clinicLayoutPage.enablePatientSelectionAssignRoom(this);
  }
  
  @Override
  public void assignStafftoPatient() {
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
  public void displayPatientInfo() {
    clinicLayoutPage = view.getLayoutPage();
    JOptionPane.showMessageDialog(null, "Please select a patient to display.");
    clinicLayoutPage.enablePatientSelectionDisplayPatient(this);
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
  
  @Override
  public void processPatientAssignStaff(PatientInterface patient) {
    clinicLayoutPage.disablePatientSelection();
    selectedPatient = patient;
    try {
      model.assignStaff(selectedPatient, selectedStaff);
      JOptionPane.showMessageDialog(null, "Staff assigned!");
      setModel(model);
    } catch (IllegalArgumentException e) {
      StringBuilder sb = new StringBuilder();
      sb.append("Error: " + e.getMessage());
      JOptionPane.showMessageDialog(null, sb.toString());
    }
  }
  
  @Override
  public void processPatientDisplayPatient(PatientInterface patient) {
    clinicLayoutPage.disablePatientSelection();
    selectedPatient = patient;
    JOptionPane.showMessageDialog(null, patient.toString());
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
