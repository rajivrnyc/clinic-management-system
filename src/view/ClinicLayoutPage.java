package view;

import clinic.ClinicInterface3;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
  private final Map<String, JButton> roomButtons;
  private final Map<String, JButton> roomPatients;
  
  /**
   * Creates the layout  of clinic.
   * @param clinic the clinic model.
   */
  public ClinicLayoutPage(ClinicInterface3 clinic) {
    this.setLayout(new BorderLayout());
    roomButtons = new HashMap<>();
    roomPatients = new HashMap<>();
    
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
      
      JButton roomButton = new JButton(room.getRoomName());
      roomButton.setFont(new Font("Arial", Font.BOLD, 20));
      roomButton.setForeground(Color.BLACK);
      roomButton.setEnabled(false);
      roomPanel.add(roomButton, BorderLayout.NORTH);
      roomButtons.put(room.getRoomName(), roomButton);
      
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
    JScrollPane scrollableRoomPanelContainer = new JScrollPane(roomPanelContainer);
    this.add(scrollableRoomPanelContainer, BorderLayout.CENTER);
  }
  
  @Override 
  public void enableRoomSelection(ActionListener onRoomSelected) {
    for (Map.Entry<String, JButton> entry : roomButtons.entrySet()) {
      JButton button = entry.getValue();
      button.setEnabled(true);
      button.addActionListener(onRoomSelected);
    }
  }

  @Override
  public void disableRoomSelection() {
    for (JButton button : roomButtons.values()) {
      button.setEnabled(false);
      for (ActionListener listener : button.getActionListeners()) {
        button.removeActionListener(listener);
      }
    }
  }
  

}
