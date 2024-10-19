package controller;

import clinic.Clinic;
import clinic.ClinicInterface;
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
public class ClinicController {
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
  public ClinicController(Readable in, Appendable out) {
    this.in = Objects.requireNonNull(in, "Input cannot be null.");
    this.out = Objects.requireNonNull(out, "Output cannot be null.");
    this.knownCommands = new HashMap<>();
    
    knownCommands.put(1, s -> new DisplayPatientCommand());
  }
  
  /**
   * Method that gives control to the controller.
   * 
   * @param model the model to use with the controller.
   */
  public void go(ClinicInterface model) {
    
    Objects.requireNonNull(model, "Clinic model cannot be null.");
    Scanner scan = new Scanner(this.in);
    boolean check = true;
    
    while (check) {
      String command = scan.next();
      
      if ("q".equalsIgnoreCase(command)) {
        scan.close();
        return;
      }
      Function<Scanner, ClinicCommand> cmdFunction = knownCommands.getOrDefault(command, null);
      if (cmdFunction == null) {
        throw new UnsupportedOperationException(command + " not suppported");
      }
    
    
      try {
        ClinicCommand cmd = cmdFunction.apply(scan);
        cmd.execute(model, scan);
      } catch (IOException e) {
        System.out.println("Error: Unable to load clinic file");
      }
    }
  }
}
