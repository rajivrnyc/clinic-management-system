package controller;

import clinic.Clinic;
import clinic.ClinicInterface;
import clinic.ClinicalStaff;
import clinic.ClinicalStaffInterface;
import clinic.EducationLevel;
import clinic.PatientInterface;
import controller.ClinicCommand;
import java.io.IOException;
import java.util.Scanner;

/**
 * Executes displaying of a patient object.
 */
public class DisplayPatientCommand implements ClinicCommand {
	
  @Override
  public void execute(ClinicInterface model, Scanner scanner) throws IOException {
    model.addNewPatient("Sally", "Johnson", "01/02/1990");
    PatientInterface sallyJ = model.getPatients().get(model.getPatients().size() - 1);
    model.addNewClinicalStaff("Doctor", "John", "Smith", EducationLevel.MASTERS, "0123456789");
    model.addNewClinicalStaff("Nurse", "Steve", "Wilson", EducationLevel.MASTERS, "0123456789");
    ClinicalStaffInterface john = (ClinicalStaffInterface) model.getEmployees().get(0);
    ClinicalStaffInterface steve = (ClinicalStaffInterface) model.getEmployees().get(1);
    model.assignStaff(sallyJ, john);
    model.assignStaff(sallyJ, steve);
    System.out.println(sallyJ);
  }

}
