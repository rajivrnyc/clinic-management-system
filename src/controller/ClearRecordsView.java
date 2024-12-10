package controller;

import clinic.ClinicInterface3;
import java.io.IOException;
import javax.swing.JOptionPane;
import view.MasterViewInterface;


/**
 * command to clear all records from the clinic.
 */
public class ClearRecordsView implements ClinicCommand3 {

  @Override
  public void execute(ClinicInterface3 model, MasterViewInterface view) {
    model.reset();
    view.refresh(model);
    view.setMenuItems(false);
    JOptionPane.showMessageDialog(null, "Clinic records have been cleared.");
  }
  
}
