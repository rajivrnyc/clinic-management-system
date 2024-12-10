package view;

import clinic.ClinicInterface3;
import clinic.PatientInterface;
import clinic.RoomInterface;

/**
 * Display patient info command in view.
 */
public class DisplayPatientView implements DisplayPatientInterface {
  private ClinicInterface3 model;
  private MasterViewInterface view;
  private ClinicLayoutPage clinicLayoutPage;
  private PatientInterface selectedPatient;
  private RoomInterface room;
  
  public DisplayPatientView(ClinicInterface3 model, MasterViewInterface view) {
    this.model = model;
    this.view = view;
    clinicLayoutPage = view.getLayoutPage();
  }
  
  @Override
  public void execute() {
    // TODO Auto-generated method stub
  }

	@Override
	public void processPatientDisplayPatient(PatientInterface patient) {
		// TODO Auto-generated method stub

	}

}
