package controller;


import clinic.Clinic2;
import clinic.Clinic3;
import clinic.ClinicInterface3;
import clinic.ClinicStaffAndPatientInfo;
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
    this.view = view;
    view.setFeatures(this);
    
  }

  @Override
  public void loadClinicFile() {
    try {
      FileReader fileReader = new FileReader("res/clinicfile.txt");
      ClinicInterface3 newModel = Clinic3.readFile(fileReader);
      this.setModel(newModel);
      
      JOptionPane.showMessageDialog(null, "Clinic loaded successfully.");
      
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
  }

  @Override
  public void clearAllRecords() {
    
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void registerNewPatient() {
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
