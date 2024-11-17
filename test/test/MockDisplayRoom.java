package test;

import clinic.ClinicInterface;
import clinic.ClinicalStaffInterface;
import clinic.EducationLevel;
import clinic.PatientInterface;
import clinic.RoomInterface;
import controller.ClinicCommand;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


/**
 * Command used to display the contents of a room within a clinic model.
 */
public class MockDisplayRoom implements ClinicCommand {
  private final StringBuilder sb;
  
  public MockDisplayRoom(StringBuilder sb) {
    this.sb = sb;
  }

  @Override
  public void execute(ClinicInterface model, Scanner scanner) throws IOException {
    List<RoomInterface> rooms = model.getRooms();
    
    System.out.println("List of Rooms:");
    for (int i = 0; i < rooms.size(); i++) {
      System.out.println((i + 1) + ": " + rooms.get(i).getRoomName());
    }
    System.out.println();
    int roomIndex = -1;
    boolean running = true;
    scanner.nextLine();
    while (running) {
      System.out.println("Select room to display: ");
      System.out.println();
      try {
        String nextInt = scanner.nextLine();
        if ("q".equalsIgnoreCase(nextInt)) {
          sb.append(nextInt + "\n");
          System.out.println("Qutting to Menu.");
          return; 
        }
        sb.append(nextInt + "\n");
        roomIndex = Integer.parseInt(nextInt) - 1;
        if (roomIndex < 0 || roomIndex >= rooms.size()) {
          throw new IllegalArgumentException("Invalid room number. Hit enter to confirm.");
        }
        running = false; 
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please hit enter, then enter a valid room number.");
        String error = scanner.nextLine();
        sb.append(error + "\n");
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
        String error = scanner.nextLine();
        sb.append(error + "\n");
      }
    }
    System.out.println();
    System.out.println(rooms.get(roomIndex).toString());
    System.out.println();
  }
}
