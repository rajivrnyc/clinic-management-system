package controller;

import clinic.ClinicInterface;
import clinic.ClinicStaffAndPatientInfo;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;


/**
 * The ClinicController class acts as an intermediary between the 
 * Clinic model and the user interface. It will take in user input
 * and process and conduct requests with different components of 
 * the Clinic's model and will facilitate displaying this information
 * to the user.
 */
public class ClinicController2 extends ClinicController {
  protected ClinicStaffAndPatientInfo model;  
  private final Map<Integer, Function<Scanner, ClinicCommand2>> knownCommands;

  /**
   * Controller used to access and demonstrate the function of the clinic model.
   * Readable is user input and out is the output by the controller. 
   * 
   * @param in input for the controller
   * @param out output for the controller
   * @param model the Clinic model that the controller is acting on
   */
  public ClinicController2(Readable in, Appendable out, ClinicStaffAndPatientInfo model) {
    super(in, out, model);
    this.model = model;
    this.knownCommands = new HashMap<>();
    
    knownCommands.put(10, s -> new ListClinStaffWithPatient());
    knownCommands.put(11, s -> new ClinicMap());
    knownCommands.put(12, s -> new DeactivateSelectedStaff());
    knownCommands.put(13, s -> new MoreThanYear());
    knownCommands.put(14, s -> new TwicePastYear());
    knownCommands.put(15, s -> new UnassignClinicalStaff());
    knownCommands.put(16, s -> new ListClinStaffNumAssigned());
  }
  
  @Override
  public void go() {
    Objects.requireNonNull(model, "Clinic model cannot be null.");
    Scanner scan = new Scanner(this.in);
    boolean check = true;
    
    while (check) {
      displayMenu();
      String command = scan.next();
      
      if ("q".equalsIgnoreCase(command)) {
        System.out.println("Quitting program.");
        scan.close();
        return;
      }
      try {
        int cmdNumber = Integer.parseInt(command);
        Function<Scanner, ClinicCommand2> cmdFunction = this.knownCommands.get(cmdNumber);
        if (cmdFunction != null) {
          ClinicCommand2 cmd2 = cmdFunction.apply(scan);
          cmd2.execute(model, scan);
          continue;
        }
        Function<Scanner, ClinicCommand> parentCmd = super.knownCommands.get(cmdNumber);
        if (parentCmd != null) {
          ClinicCommand cmd = parentCmd.apply(scan);
          cmd.execute(model, scan);
        } else {
          throw new UnsupportedOperationException(command + "not supported");
        }
        
        
      } catch (NumberFormatException e) {
        System.out.println("Please enter a number that corresponds with a valid command.");
      } catch (IOException e) {
        System.out.println("Error: Unable to load clinic file");
      
      }
    }
  }

  @Override
  public void displayMenu() {
    super.displayMenu();
    System.out.println("10: List Clinical Staff Members with Patients Assigned");
    System.out.println("11: Display Map of Clinic");
    System.out.println("12: Deactivate a Selected Staff Member");
    System.out.println("13: Display Patients Who Haven't Visited the Clinic in More Than a year");
    System.out.println("14: Display patients who visited the clinic at "
        + "least twice in the past year");
    System.out.println("15: Unassign a ClinicalStaff member from a Patient");
    System.out.println("16: List ClinicalStaff members and the number of patients that "
        + "they have ever been assigned");
    System.out.println("Enter 'q' to quit.");    
  }
}
