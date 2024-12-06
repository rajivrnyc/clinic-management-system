package controller;

import clinic.ClinicStaffAndPatientInfo;
import view.MasterViewInterface;

/**
 * Controller to support view implementation for the mvc.
 */
public class ClinicControllerGui extends ClinicController2{
  private final MasterViewInterface view;
  
  public ClinicControllerGui(Readable in, Appendable out, ClinicStaffAndPatientInfo model, MasterViewInterface view) {
    super(in, out, model);
    this.view = view;
    
  }

}
