package view;

import clinic.ClinicInterface3;
import clinic.ClinicStaffAndPatientInfo;
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
   * Enables features if a clinic is loaded and 
   * disables them if not.
   * @param isClinicLoaded a boolean to check if a clinic is loaded to the model.
   */
  void setMenuItems(boolean isClinicLoaded);
  
  
  /**
   * Makes the current view visible.
   */
  void makeVisible();
  
  /**
   * Update view.
   * 
   * @param model the new model that the view should update with.
   */
  void refresh(ClinicInterface3 model);
  

}
