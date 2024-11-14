package clinic;

/**
 * The ClinicController Interface represents a
 * controller class acts as an intermediary between the 
 * Clinic model and the user interface. It will take in user input
 * and process and conduct requests with different components of 
 * the Clinic's model and will facilitate displaying this information
 * to the user.
 */
public interface ClinicControllerInterface {

  /**
   * Method that gives control to the controller.
   * 
   * @param model the model to use with the controller.
   */
  void go(ClinicInterface model);
  
  
  /**
 * Displays the menu of available commands.
 */
  void displayMenu();
}
