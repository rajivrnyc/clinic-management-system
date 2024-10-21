package controller;

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
public class DisplayRoom implements ClinicCommand {

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
    while (running) {
      System.out.print("Select room to display: ");
      scanner.nextLine();
      System.out.println();
      try {
        roomIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (roomIndex < 0 || roomIndex >= rooms.size()) {
          throw new IllegalArgumentException("Invalid room number.");
        }
        running = false; 
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please hit enter, then enter a valid room number.");
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
    System.out.println();
    System.out.println(rooms.get(roomIndex).toString());
  }
}
