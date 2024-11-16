package controller;

import clinic.ClinicInterface;
import clinic.ClinicStaffAndPatientInfo;
import clinic.ClinicalStaffInterface;
import clinic.Staff;
import clinic.StaffClass;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


/**
 * Command for controller to deactivate a selected staff member.
 */
public class DeactivateSelectedStaff implements ClinicCommand2 {

  @Override
  public void execute(ClinicStaffAndPatientInfo model, Scanner scanner) throws IOException {
    System.out.println("List of Staff Members:");
    List<Staff> staff = model.getEmployees();
    if (staff.isEmpty()) {
      System.out.println("Staff list is empty");
      return;
    }
    
    for (int i = 0; i < staff.size(); i++) {
      int index = i + 1;
      Staff member = staff.get(i);
      StringBuilder sb = new StringBuilder();
      sb.append(index).append(": ").append(member.getFirstName())
      .append(" ").append(member.getLastName());
      System.out.println(sb.toString());
    }
    
    int staffIndex = -1;
    scanner.nextLine();
    boolean running = true;
    while (running) {
      System.out.println();
      System.out.print("Select staff member to display: ");
      
      try {
        String nextInt = scanner.nextLine();
        if ("q".equalsIgnoreCase(nextInt)) {
          System.out.println("Quitting to Menu.");
          return;
        }
        staffIndex = Integer.parseInt(nextInt) - 1;
        if (staffIndex < 0 || staffIndex >= staff.size()) {
          throw new IllegalArgumentException("Invalid staff number.");
        }
        running = false;
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid staff number");
        scanner.nextLine();
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid input. Please enter a valid number.");
        scanner.nextLine();
      }
    }
    System.out.println();
    StaffClass temp = (StaffClass) staff.get(staffIndex);
    if (temp.isClinicalStaff()) {
      ClinicalStaffInterface tempClin = (ClinicalStaffInterface) temp;
      model.deactivateClinicalStaffClinic(tempClin);
      System.out.println("Clinical Staff Member deactivated.");
    } else {
      temp.deactivate();
      System.out.println("Non Clinical Staff Member deactivated.");
    }
    System.out.println();
  }

}
