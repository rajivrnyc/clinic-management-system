package view;

import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * The view for a clinic management system.
 */
public interface ClinicViewInterface {
  
  /**
   * Makes buttons to click on rooms for selection purposes visible.
   * 
   * @param onRoomSelected callback for when a room is selected by user.
   */
  void enableRoomSelection(ActionListener onRoomSelected);
  
  /**
   * Makes buttons to click on rooms for selection purposes invisible.
   */
  void disableRoomSelection();

}
