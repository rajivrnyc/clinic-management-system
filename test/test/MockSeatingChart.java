package test;

import clinic.ClinicInterface;
import clinic.ClinicalStaffInterface;
import clinic.EducationLevel;
import clinic.PatientInterface;
import clinic.RoomInterface;
import controller.ClinicCommand;
import java.io.IOException;
import java.util.Scanner;


/**
 * Displays seating chart of the clinic model.
 */
public class MockSeatingChart implements ClinicCommand { 
  StringBuilder sb;

  public MockSeatingChart(StringBuilder sb) {
    this.sb = sb;
  }

  @Override
  public void execute(ClinicInterface model, Scanner scanner) throws IOException {

    System.out.println(model.seatingChart());
  }
}
