package controller;

import clinic.ClinicInterface;
import controller.ClinicCommand;
import java.io.IOException;
import java.util.Scanner;


/**
 * A command to send a patient home.
 */
public class SendHomeCommand implements ClinicCommand {

  @Override
  public void execute(ClinicInterface model, Scanner scanner) throws IOException {
    System.out.println(model.getRoomFromNumber(3));

  }

}
