package view;

import clinic.ClinicStaffAndPatientInfo;
import clinic.PatientInterface;
import clinic.Room;
import clinic.RoomInterface;
import java.awt.BorderLayout;
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
    JLabel clinicNameLabel = new JLabel(clinic.getClinicName(), SwingConstants.CENTER);
    clinicNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
    this.add(clinicNameLabel, BorderLayout.NORTH);
    
    JPanel roomsPanel = new JPanel();
    roomsPanel.setLayout(new BoxLayout(roomsPanel, BoxLayout.Y_AXIS));
    
    List<RoomInterface> rooms = clinic.getRooms();
    
    for (RoomInterface room : rooms) {
      JPanel roomPanel = new JPanel();
      roomPanel.setLayout(new BorderLayout());
      roomPanel.setBorder(BorderFactory.createTitledBorder(room.getRoomName()));
      
      List<PatientInterface> patients = room.getResidents();
      
      DefaultListModel<String> patientListModel = new DefaultListModel<>();
      for (PatientInterface patient : patients) {
        patientListModel.addElement(patient.getFirstName() + " " 
            + patient.getLastName());
      }
      JList<String> patientList = new JList<>(patientListModel);
      
      roomPanel.add(new JScrollPane(patientList), BorderLayout.CENTER);
      roomsPanel.add(roomPanel);
    }
    this.add(new JScrollPane(roomsPanel), BorderLayout.CENTER);
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
