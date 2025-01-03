package controller;

import clinic.Clinic;
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
public class ClinicController implements ClinicControllerInterface {
  protected final Readable in;
  protected final Appendable out;
  protected final Map<Integer, Function<Scanner, ClinicCommand>> knownCommands;
  protected ClinicInterface model;
  
  /**
   * Controller used to access and demonstrate the function of the clinic model.
   * Readable is user input and out is the output by the controller. 
   * 
   * @param in input for the controller
   * @param out output for the controller
   * @param model the Clinic model that the controller is acting on
   */
  public ClinicController(Readable in, Appendable out, ClinicInterface model) {
    this.in = Objects.requireNonNull(in, "Input cannot be null.");
    this.out = Objects.requireNonNull(out, "Output cannot be null.");
    this.knownCommands = new HashMap<>();
    this.model = model;
   
    knownCommands.put(1, s -> new DisplayPatientCommand());
    knownCommands.put(2, s -> new DisplayRoom());
    knownCommands.put(3, s -> new DisplaySeatingChart());
    knownCommands.put(4, s -> new RegisterPatientCommand());
    knownCommands.put(5, s -> new RegisterNewClinStaff());
    knownCommands.put(6, s -> new RegisterExistingPatient());
    knownCommands.put(7, s -> new SendHomeCommand());
    knownCommands.put(8, s -> new AssignPatientRoom());
    knownCommands.put(9, s -> new AssignClinicalStaff());

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
