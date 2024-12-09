package controller;

import clinic.Clinic3;
import clinic.ClinicInterface3;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import view.MasterViewInterface;

/**
 * Command for Loading a file into the view.
 */
public class LoadFileView implements ClinicCommand3 {
  private ClinicInterface3 model;
  private MasterViewInterface view;

  public LoadFileView(ClinicInterface3 model, MasterViewInterface view) {
    this.model = model;
    this.view = view;
  }
  
  @Override
  public void execute() {
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
  
  private void setModel(ClinicInterface3 newModel) {
    this.model = newModel;
    view.refresh(newModel);
  }
}