package controller;





import clinic.Clinic;
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
  public void execute(Clinic model, Scanner scanner) throws IOException {
    model.addNewPatient("Sally", "Johnson", "01/02/1990");
    PatientInterface sallyJ = model.getPatients().get(model.getPatients().size() - 1);
    model.addNewClinicalStaff("Doctor", "John", "Smith", EducationLevel.MASTERS, "0123456789");
    model.addNewClinicalStaff("Nurse", "Steve", "Wilson", EducationLevel.MASTERS, "0123456789");
    model.assignStaff(sallyJ, model.getEmployees().get(0));
  }

}
