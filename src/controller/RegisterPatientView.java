package controller;


import clinic.ClinicInterface3;
import clinic.PatientInterface;
import clinic.PatientInterface2;
import javax.swing.JOptionPane;
import view.MasterViewInterface;

/**
 * Command to Register patient into the view.
 */
public class RegisterPatientView implements ClinicCommand3 {
  private ClinicInterface3 model;
  private MasterViewInterface view;
  
  public RegisterPatientView(ClinicInterface3 model, MasterViewInterface view) {
    this.model = model;
    this.view = view;
  }
  
  @Override
  public void execute() {
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
    
    PatientInterface existingPatient = model.findPatient(patientFirstName.trim(), 
             patientlastName.trim());
    if (existingPatient != null) {
      JOptionPane.showMessageDialog(null, "Patient Found!", 
                "Patient Found", JOptionPane.PLAIN_MESSAGE);
    }

    String birthdate = null;
    
    if (existingPatient == null) {
      birthdate = JOptionPane.showInputDialog(null, "Enter the patient's birthdate "
                + "(dd/mm/yyyy):", "RegisterPatient",
                JOptionPane.PLAIN_MESSAGE);
      if (birthdate == null || birthdate.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Patient registration cancelled due to invalid input.",
            "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
    }

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
    if (existingPatient != null) {
      
    }
    model.addNewPatient(patientFirstName, patientlastName, birthdate);
    PatientInterface patient = model.getPatients().get(model.getPatients().size() - 1);
      
    PatientInterface2 patientI = (PatientInterface2) patient;
    patientI.addRecord(complaint, temp);
      
    view.refresh(model);
    JOptionPane.showMessageDialog(null, "Patient successfully added to clinic.");
  }
  
}
