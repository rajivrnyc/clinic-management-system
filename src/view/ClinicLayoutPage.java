package view;

import clinic.ClinicStaffAndPatientInfo;
import clinic.PatientInterface;
import clinic.Room;
import clinic.RoomInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;


/**
 * Panel for the Clinic display.
 */
public class ClinicLayoutPage extends JPanel implements ClinicViewInterface {
  private static final long serialVersionUID = 1L;
  
  /**
   * Creates the layout  of clinic.
   * @param clinic the clinic model.
   */
  public ClinicLayoutPage(ClinicStaffAndPatientInfo clinic) {
    this.setLayout(new BorderLayout());
    JLabel clinicNameLabel = new JLabel(clinic.getClinicName(), SwingConstants.CENTER);
    clinicNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
    clinicNameLabel.setPreferredSize(new Dimension(800, 50));
    this.add(clinicNameLabel, BorderLayout.NORTH);
    
    JPanel roomPanelContainer = new JPanel();
    roomPanelContainer.setLayout(new GridLayout(0, 3, 10, 10));
    roomPanelContainer.setPreferredSize(new Dimension(800, 600));
    
    List<RoomInterface> rooms = clinic.getRooms();
    
    for (RoomInterface room : rooms) {
      JPanel roomPanel = new JPanel();
      roomPanel.setLayout(new BorderLayout());
      roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      
      JLabel roomLabel = new JLabel(room.getRoomName(), SwingConstants.CENTER);
      roomLabel.setFont(new Font("Arial", Font.BOLD, 20));
      roomPanel.add(roomLabel, BorderLayout.NORTH);
      roomPanel.setPreferredSize(new Dimension(250, 150));
      
      List<PatientInterface> patients = room.getResidents();
      
      JPanel patientPanel = new JPanel();
      patientPanel.setLayout(new BoxLayout(patientPanel, BoxLayout.Y_AXIS));
      for (PatientInterface patient : patients) {
        JLabel patientLabel = new JLabel(patient.getFirstName() + " "
            + patient.getLastName());
        patientLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        patientPanel.add(patientLabel);
      }
      JScrollPane patientScroll = new JScrollPane(patientPanel);
      
      roomPanel.add(patientScroll, BorderLayout.CENTER);
      roomPanelContainer.add(roomPanel);
    }
    this.add(roomPanelContainer, BorderLayout.CENTER);
  }
  
  @Override
  public void makeVisible() {
    // TODO Auto-generated method stub

  }

  @Override
  public void addActionListener(ActionListener listener) {
    // TODO Auto-generated method stub

  }

  @Override
  public void showMessage(String message) {
    // TODO Auto-generated method stub

  }

  @Override
  public void clear() {
    // TODO Auto-generated method stub

  }

}
