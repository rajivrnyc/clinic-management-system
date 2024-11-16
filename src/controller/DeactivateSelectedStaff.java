package controller;

import clinic.ClinicInterface;
import clinic.ClinicStaffAndPatientInfo;
import clinic.ClinicalStaffInterface;
import clinic.Staff;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


/**
 * Command for controller to deactivate a selected staff member.
 */
public class DeactivateSelectedStaff implements ClinicCommand2 {

  @Override
  public void execute(ClinicStaffAndPatientInfo model, Scanner scanner) throws IOException {
    System.out.println("List of Clinical Staff Members:");
    List<Staff> staff = model.getEmployees();
    if (staff.isEmpty()) {
      System.out.println("Staff list is empty");
      return;
    }
    
    for (int i = 0; i < staff.size(); i++) {
      
    }
  }

}
