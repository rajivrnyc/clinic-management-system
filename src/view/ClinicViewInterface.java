package view;

import java.awt.event.ActionListener;

/**
 * The view for a clinic management system.
 */
public interface ClinicViewInterface {

  /**
   * Makes the view visible to the user.
   */
  void makeVisible();
  
  
  /**
   * Adds a listener for inputs from the user.
   * 
   * @param listener the action listener
   */
  void addCommandListener(ActionListener listener);
  
  
  /**
   *  An action to quit the program.
   * 
   * @param listener a listener for the quit button
   */
  void addQuitListener(ActionListener listener);
  
  
  /**
   * Displays a message in the view display area.
   * 
   * @param message the message being displayed
   */
  void showMessage(String message);
  
  
  /**
   * Clears the view of displayed messages.
   */
  void clear();
  
  
  /**
   * Closes the view and resets.
   */
  void exit();


}
