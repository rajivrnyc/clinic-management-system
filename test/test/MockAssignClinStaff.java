package test;

import clinic.ClinicInterface;
import clinic.ClinicalStaffInterface;
import clinic.PatientInterface;
import controller.ClinicCommand;
import java.io.IOException;
import java.util.Scanner;


/**
 * Command used to assign a clinical staff member to a patient.
 */
public class MockAssignClinStaff implements ClinicCommand {
  StringBuilder sb;
  
  /**
   * A mock constructor to record user input for this command.
   * @param sb A StringBuilder where user input is stored.
   */
  public MockAssignClinStaff(StringBuilder sb) {
    this.sb = sb;
  }

  @Override
  public void execute(ClinicInterface model, Scanner scanner) throws IOException {
    PatientInterface mostRecentPatient = model.getPatients().get(model.getPatients().size() - 1);
    System.out.println("This is a patient: " + mostRecentPatient);
    
    ClinicalStaffInterface mostRecentClinStaff = null;
    for (int i = model.getEmployees().size() - 1; i >= 0; i--) {
      if (model.getEmployees().get(i) instanceof ClinicalStaffInterface) {
        mostRecentClinStaff = (ClinicalStaffInterface) model.getEmployees().get(i);
        break;
      }
    }
    
    if (mostRecentClinStaff == null) {
      throw new IllegalStateException("No clinical staff members available.");
    }
    
    System.out.println();
    System.out.println("This is a clinical staff member: " + mostRecentClinStaff);
    System.out.println();
    model.assignStaff(mostRecentPatient, mostRecentClinStaff);
    System.out.println(mostRecentPatient);
  }
}
