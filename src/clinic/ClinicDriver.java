package clinic;

import controller.ClinicController;
import controller.ClinicController2;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;




/**
 * A Driver class to test the functionality of the Clinic model.
 */
public class ClinicDriver {
  RoomInterface tempRoom;

  /**
 * Takes command line argument that we will use to from clinic objects.
 * @param args Takes an argument from the command line.
 */
  public static void main(String[] args) {
    
    try {
      Clinic2 clinic = Clinic2.readFile(new FileReader("res/clinicfile.txt"));
      System.out.println("Loaded File.");
    
    
      System.out.println("Clinic Name: " + clinic.getClinicName());
      System.out.println("Number of Rooms: " + clinic.getNumRooms());
      System.out.println("Number of Staff: " + clinic.getNumStaff());
      System.out.println("Number of Patients: " + clinic.getNumPatients());
      System.out.println();

      Readable input;
      if (args.length > 1) {
        input = new FileReader(args[1]);
      } else {
        input = new InputStreamReader(System.in);
      }
      Appendable output = System.out; 
      ClinicControllerInterface controller = new ClinicController2(input, output, clinic);
      controller.go();
    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
      return;
    }
    
  }

}
