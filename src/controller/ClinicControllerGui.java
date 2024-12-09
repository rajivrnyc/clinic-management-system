package controller;


import clinic.Clinic2;
import clinic.Clinic3;
import clinic.ClinicInterface3;
import clinic.ClinicStaffAndPatientInfo;
import clinic.PatientInterface;
import clinic.PatientInterface2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import view.Features;
import view.MasterViewInterface;

/**
 * Controller to support view implementation for the mvc.
 */
public class ClinicControllerGui extends ClinicController2 implements Features {
  private  ClinicInterface3 model;
  private final MasterViewInterface view;
  
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
    // TODO Auto-generated method stub
  }

  @Override
  public void assignStafftoPatient() {
    // TODO Auto-generated method stub
  }

  @Override
  public void displayPatientInfo() {
    // TODO Auto-generated method stub
  }

  @Override
  public void unassignStaffFromPatient() {
    // TODO Auto-generated method stub
  }

  @Override
  public void sendPatientHome() {
    // TODO Auto-generated method stub
  }
  
  @Override
  public void go() {
    view.makeVisible();
  }
  
  private void setModel(ClinicInterface3 newModel) {
    this.model = newModel;
    view.refresh(newModel);
  }
  



}
