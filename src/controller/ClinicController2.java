package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

import clinic.ClinicInterface;
import clinic.ClinicStaffAndPatientInfo;

/**
 * The ClinicController class acts as an intermediary between the 
 * Clinic model and the user interface. It will take in user input
 * and process and conduct requests with different components of 
 * the Clinic's model and will facilitate displaying this information
 * to the user.
 */
public class ClinicController2 extends ClinicController {
  private final Readable in;
  private final Appendable out;
  private final Map<Integer, Function<Scanner, ClinicCommand>> knownCommands;

  /**
   * Controller used to access and demonstrate the function of the clinic model.
   * Readable is user input and out is the output by the controller. 
   * 
   * @param in input for the controller
   * @param out output for the controller
   */
  public ClinicController2(Readable in, Appendable out) {
    super(in, out);
    this.in = Objects.requireNonNull(in, "Input cannot be null.");
    this.out = Objects.requireNonNull(out, "Output cannot be null.");
    this.knownCommands = new HashMap<>();
    
    knownCommands.put(10, s -> new ListClinStaffWithPatient());
    knownCommands.put(11, s -> new ClinicMap());
    knownCommands.put(12, s -> new DeactivateSelectedStaff());
    knownCommands.put(13, s -> new MoreThanYear());
    knownCommands.put(14, s -> new UnassignClinicalStaff());
  }
  
  @Override
  public void go(ClinicStaffAndPatientInfo model) {
	    
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
        Function<Scanner, ClinicCommand2> cmdFunction = knownCommands.get(cmdNumber);
        if (cmdFunction == null) {
          throw new UnsupportedOperationException(command + " not suppported");
        }      
        ClinicCommand2 cmd = cmdFunction.apply(scan);
        cmd.execute(model, scan);
      } catch (NumberFormatException e) {
        System.out.println("Please enter a number that corresponds with a valid command.");
      } catch (IOException e) {
        System.out.println("Error: Unable to load clinic file");
      
      }
    }
  }
	  
  /**
 * Displays the menu of available commands.
 */
  private void displayMenu() {
    super.displayMenu();
    System.out.println("10: List Clinical Staff Members with Patients Assigned");
    System.out.println("11: Display Map of Clinic");
    System.out.println("12: Deactivate a Selected Staff Member");
    System.out.println("13: Display Patients Who Haven't Visited the Clinic in More Than a Year");
    System.out.println("14: Unassign a ClinicalStaff Member from the Clinic");
    System.out.println("Enter 'q' to quit.");    
  }
}
