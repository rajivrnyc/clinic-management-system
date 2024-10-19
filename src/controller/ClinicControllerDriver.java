package controller;

import java.io.FileReader;
import java.io.IOException;

/**
 *  A driver class to take in the user input for the clinic controller.
 */
public class ClinicControllerDriver {

  /**
   * takes in arguments for the main method.
   * 
   * @param args arguments that are passed in to the main
   *     method.
   */
  public static void main(String[] args) {
    try {
      FileReader input = new FileReader("res/sample_input.txt");
      Appendable output = System.out;
      Clinic model = new
    } catch (IOException e) {
      System.out.println("Error loading input file: " + e.getMessage());
    }
  }
}
