package clinic;

import controller.ClinicController;
import controller.ClinicController2;
import controller.ClinicControllerGui;
import controller.ClinicControllerInterface;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import view.AboutPage;
import view.MasterView;
import view.MasterViewInterface;

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
    if (args.length == 1) {
      try {
        String clinicFile = args[0];
        ClinicStaffAndPatientInfo clinic = Clinic2.readFile(new FileReader(clinicFile));
        System.out.println("Loaded File.");
        System.out.println("Clinic Name: " + clinic.getClinicName());
        System.out.println("Number of Rooms: " + clinic.getNumRooms());
        System.out.println("Number of Staff: " + clinic.getNumStaff());
        System.out.println("Number of Patients: " + clinic.getNumPatients());
        System.out.println();
   

        Readable input = new InputStreamReader(System.in);
        Appendable output = System.out; 
        ClinicControllerInterface controller = new ClinicController2(input, output, clinic);
        controller.go();
      } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
        return;
      }
  
    } else {
      try {
        Appendable output = System.out;
        Readable input = new InputStreamReader(System.in);
        ClinicInterface3 model = Clinic3.readFile(new FileReader("res/clinicfile.txt"));
        MasterViewInterface view = new MasterView();
        ClinicControllerInterface controller = new ClinicControllerGui(input, output, model, view);
        controller.go();
        
      } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
        return;
      }
    }

  }
}
