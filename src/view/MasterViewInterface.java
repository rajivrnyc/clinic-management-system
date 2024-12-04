package view;

import javax.swing.JPanel;

/**
 * Interface for the master view class to handle different panel displays.
 */
public interface MasterViewInterface {

  /**
   * Changes JPanel based on button clicked.
   * 
   * @param newPanel the panel being switched to
   */
  void switchPanel(JPanel newPanel);
  
  /**
   *  Make the view visible to start the clinic system.
   */
  void makeVisible();

}
