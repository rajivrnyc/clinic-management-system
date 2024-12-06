package controller;

import clinic.ClinicStaffAndPatientInfo;
import view.Features;
import view.MasterViewInterface;

/**
 * Controller to support view implementation for the mvc.
 */
public class ClinicControllerGui extends ClinicController2 implements Features {
  private final MasterViewInterface view;
  
  public ClinicControllerGui(Readable in, Appendable out, ClinicStaffAndPatientInfo model, MasterViewInterface view) {
    super(in, out, model);
    this.view = view;
    
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
	// TODO Auto-generated method stub
	
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
