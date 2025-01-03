package test;

import clinic.Clinic;
import clinic.ClinicInterface;
import controller.ClinicCommand;
import controller.ClinicControllerInterface;
import java.io.BufferedReader;
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
public class MockClinicController implements ClinicControllerInterface {
  protected final Readable in;
  protected final StringBuilder sb;
  protected final Map<Integer, Function<Scanner, ClinicCommand>> knownCommands;
  protected ClinicInterface model;
  
  /**
   * Controller used to access and demonstrate the function of the clinic model.
   * Readable is user input and out is the output by the controller. 
   * 
   * @param in input for the controller
   * @param sb output for the controller
   * @param model the clinic passed to the controller.
   */
  public MockClinicController(Readable in, StringBuilder sb, ClinicInterface model) {
    
    
    this.in = Objects.requireNonNull(in, "Input cannot be null.");
    this.sb = Objects.requireNonNull(sb, "Output cannot be null.");
    this.knownCommands = new HashMap<>();
    this.model = model;
    
    
    knownCommands.put(1, s -> new MockPatientDisplay(sb));
    knownCommands.put(2, s -> new MockDisplayRoom(sb));
    knownCommands.put(3, s -> new MockSeatingChart(sb));
    knownCommands.put(4, s -> new MockRegisterPatient(sb));
    knownCommands.put(5, s -> new MockNewClinStaff(sb));
    knownCommands.put(6, s -> new MockExistingPatient(sb));
    knownCommands.put(7, s -> new MockSendHome(sb));
    knownCommands.put(8, s -> new MockAssignPatient(sb));
    knownCommands.put(9, s -> new MockAssignClinStaff(sb));
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
        Function<Scanner, ClinicCommand> cmdFunction = knownCommands.get(cmdNumber);
        if (cmdFunction == null) {
          throw new UnsupportedOperationException(command + " not suppported");
        }      
        ClinicCommand cmd = cmdFunction.apply(scan);
        cmd.execute(model, scan);
      } catch (NumberFormatException e) {
        System.out.println("Please enter a number that corresponds with a valid command.");
      } catch (IOException e) {
        System.out.println("Error: Unable to load clinic file");
      
      }
    }
  }

  @Override
  public void displayMenu() {
    System.out.println("Menu:");
    System.out.println("1: Display Selected Patient");
    System.out.println("2: Display Selected Room");
    System.out.println("3: Display Seating Chart");
    System.out.println("4: Register New Patient");
    System.out.println("5: Register New Clinical Staff");
    System.out.println("6: Register Existing Patient");
    System.out.println("7: Send Patient Home");
    System.out.println("8: Assign Patient to Room");
    System.out.println("9: Assign Clinical Staff");
        
  }
}
