package controller;

import clinic.ClinicStaffAndPatientInfo;
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
  public ClinicControllerGui(Readable in, Appendable out, ClinicStaffAndPatientInfo model, 
         MasterViewInterface view) {
    super(in, out, model);
    this.view = view;
    view.setFeatures(this);
    
  }

  @Override
  public void loadClinicFile() {
    // TODO Auto-generated method stub
  }

  @Override
  public void clearAllRecords() {
    // TODO Auto-generated method stub
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

}
