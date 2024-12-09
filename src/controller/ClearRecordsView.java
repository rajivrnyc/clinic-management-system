package controller;

import clinic.ClinicInterface3;
import java.io.IOException;
import javax.swing.JOptionPane;
import view.MasterViewInterface;


/**
 * command to clear all records from the clinic.
 */
public class ClearRecordsView implements ClinicCommand3 {
  private ClinicInterface3 model;
  private MasterViewInterface view;
  
  public ClearRecordsView(ClinicInterface3 model, MasterViewInterface view) {
    this.model = model;
    this.view = view;
  }
  
  @Override
  public void execute() throws IOException {
    model.reset();
    this.setModel(model);
    view.setMenuItems(false);
    JOptionPane.showMessageDialog(null, "Clinic records have been cleared.");
  }
  
  private void setModel(ClinicInterface3 newModel) {
    this.model = newModel;
    view.refresh(newModel);
  }
}
