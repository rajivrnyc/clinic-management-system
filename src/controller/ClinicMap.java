package controller;

import clinic.ClinicStaffAndPatientInfo;
import clinic.PatientInterface;
import clinic.RoomInterface;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;



/**
 * Creates a visual representation of the clinic through a command.
 */
public class ClinicMap implements ClinicCommand2 {

  @Override
  public void execute(ClinicStaffAndPatientInfo model, Scanner scanner) throws IOException {
    System.out.println("Creating map of Clinic");
    System.out.println();

    
    int width = 800;
    int height = 600;
    
    BufferedImage map = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics g = map.getGraphics();
    
    g.setColor(Color.white);
    g.fillRect(0, 0, width, height);
    
    g.setFont(new Font("Arial", Font.PLAIN, 14));
    g.setColor(Color.black);
    
    List<RoomInterface> rooms = model.getRooms();
    List<PatientInterface> patients = model.getPatients();
    
    int x = 20;
    int y = 20;
    int roomWidth = 200;
    int roomHeight = 100;
    int space = 20;
    
    for (int i = 0; i < rooms.size(); i++) {
      RoomInterface room = rooms.get(i);
      String roomName = room.getRoomName();
      
      g.drawString(roomName, x + 10, y + 20);
      
      g.drawRect(x, y + 20, roomWidth, roomHeight);
      int textLoc = y + 50;
      
      for (PatientInterface patient : patients) {
        if (patient.getRoomNumber() - 1 == i) {
          StringBuffer sb = new StringBuffer();
          sb.append(patient.getFirstName()).append(" ").append(patient.getLastName());
          g.drawString(sb.toString(), x + 10, textLoc);
          textLoc += 20;
        }
      }
      
      x += roomWidth + space;
      if (x + roomWidth > width) {
        x = 20;
        y += roomHeight + space + 20;
      }
      
    }
    
    File image = new File("res/clinic_map.png");
    
    ImageIO.write(map, "png", image);
    
    System.out.println("Clinic map has been saved in the 'res' folder.");
    System.out.println();
  }
}
