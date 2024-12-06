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
   * Accepts callbacks from the controller to attach to components in the view.
   * 
   * @param f the set of feature callbacks as a Features object
   */
  void setFeatures(Features f);
  
  
  /**
   * Makes the current view visible.
   */
  void makeVisible();

}
