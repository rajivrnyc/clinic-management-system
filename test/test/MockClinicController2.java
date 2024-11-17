package test;

import clinic.ClinicInterface;
import clinic.ClinicStaffAndPatientInfo;
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
    
    knownCommands.put(10, s -> new MockPatientDisplay());
    knownCommands.put(11, s -> new MockDisplayRoom());
    knownCommands.put(12, s -> new MockSeatingChart());
    knownCommands.put(13, s -> new MockRegisterPatient());
    knownCommands.put(14, s -> new MockNewClinStaff());
    knownCommands.put(15, s -> new MockExistingPatient());
    knownCommands.put(16, s -> new MockSendHome());
   }
  
  
}
