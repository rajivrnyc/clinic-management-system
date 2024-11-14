package controller;

import clinic.ClinicInterface;
import clinic.ClinicalStaffInterface;
import clinic.Staff;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


/**
 * Command for controller to deactivate a selected staff member.
 */
public class DeactivateSelectedStaff implements ClinicCommand {

  @Override
  public void execute(ClinicInterface model, Scanner scanner) throws IOException {
    System.out.println("List of Clinical Staff Members:");
    List<Staff> staff = model.getEmployees();
    if (staff.isEmpty()) {
      System.out.println("Staff list is empty");
      return;
    }
  }

}