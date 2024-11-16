package controller;

import clinic.ClinicStaffAndPatientInfo;
import clinic.PatientInterface;
import clinic.RoomInterface;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;



/**
 * Creates a visual representation of the clinic through a command.
 */
public class ClinicMap implements ClinicCommand2 {

  @Override
  public void execute(ClinicStaffAndPatientInfo model, Scanner scanner) throws IOException {
    List<RoomInterface> rooms = model.getRooms();
    List<PatientInterface> patients = model.getPatients();
    
    int width = 800;
    int height = 600;
    
    BufferedImage map = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics g = map.getGraphics();
    
    g.setColor(Color.white);
    g.fillRect(0, 0, width, height);
    
    g.setFont(new Font("Arial", Font.PLAIN, 14));
    g.setColor(Color.black);
    
    int x = 20;
    int y = 20;
    int roomWidth = 200;
    int roomHeight = 100;
    int roomSpacing = 20;
    
    for (int i = 0; i < rooms.size(); i ++) {
      RoomInterface room = rooms.get(i);
      String roomName = room.getRoomName();
      
      
      
    }
  }
}
