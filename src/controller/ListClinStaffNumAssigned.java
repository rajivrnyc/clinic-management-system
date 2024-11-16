package controller;


import clinic.ClinicStaffAndPatientInfo;
import clinic.ClinicalStaffInterface2;
import clinic.Staff;
import clinic.StaffClass;
import java.io.IOException;
import java.util.Scanner;

/**
 * Command to display Clinical Staff members and the number of patients they have ever been 
 * assigned.
 */
public class ListClinStaffNumAssigned implements ClinicCommand2 {

  @Override
  public void execute(ClinicStaffAndPatientInfo model, Scanner scanner) throws IOException {
    System.out.println();
    System.out.println("List of Clincal Staff members of the number of patients "
        +  "they have ever been assigned:");
    StringBuilder output = new StringBuilder();
    for (Staff member : model.getEmployees()) {
      StaffClass mem = (StaffClass) member;
      if (mem.isClinicalStaff()) {
        ClinicalStaffInterface2 clin = (ClinicalStaffInterface2) mem;
        output.append(clin.getFirstName()).append(" ").append(clin.getLastName())
        .append(" - ").append(clin.getNumAssigned());
      }
    }
    System.out.println(output.toString());
    System.out.println();
  }

}
