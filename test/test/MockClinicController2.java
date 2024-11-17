package test;

import clinic.ClinicInterface;
import clinic.ClinicStaffAndPatientInfo;
import controller.ClinicCommand;
import controller.ClinicCommand2;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;


/**
 * Mock Controller to test newly implemented mock commands for the clinic.
 */
public class MockClinicController2 extends MockClinicController {
  protected ClinicStaffAndPatientInfo model;
  private final Map<Integer, Function<Scanner, ClinicCommand2>> knownCommands;

  /**
   * Mock of controller used to access and demonstrate the function of the
   * controller where readable in is used for input and out records the output.
   * 
   * @param in input for the controller
   * @param sb output for the controller
   * @param model the clinic passed to the controller.
   */
  public MockClinicController2(Readable in, StringBuilder sb, ClinicStaffAndPatientInfo model) {
    super(in, sb, model);
    this.model = model; 
    this.knownCommands = new HashMap<>();
    this.model = model;
    
    
    knownCommands.put(10, s -> new MockListClinWithPatient(sb));
    knownCommands.put(11, s -> new MockClinicMap(sb));
    knownCommands.put(12, s -> new MockDeactivateSelected(sb));
    knownCommands.put(13, s -> new MockMoreThanYear(sb));
    knownCommands.put(14, s -> new MockTwicePastYear(sb));
    knownCommands.put(15, s -> new MockUnassignClinicalStaff(sb));
    knownCommands.put(16, s -> new MockListClinNumAssigned(sb));
   }
  
  @Override
  public void go() {
    Objects.requireNonNull(model, "Clinic model cannot be null.");
    Scanner scan = new Scanner(this.in);
    boolean check = true;
    
    while (check) {
      String command = scan.next();
      sb.append(command + "\n");
      
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
    System.out.println("15: Unassign a ClinicalStaff member from the Clinic");
    System.out.println("16: List ClinicalStaff members and the number of patients that "
        + "they have ever been assigned");
    System.out.println("Enter 'q' to quit.");    
  }
}
