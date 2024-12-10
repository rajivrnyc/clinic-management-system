package test;

import clinic.ClinicInterface3;
import controller.Features;
import javax.swing.JPanel;
import view.ClinicLayoutPage;
import view.MasterViewInterface;



/**
 * A mock for the view of the clinic.
 */
public class MockView implements MasterViewInterface {
  boolean refreshCalled = false;
  boolean setMenuItemsCalled = false;

  @Override
  public void switchPanel(JPanel newPanel) {
  }

  @Override
  public void setFeatures(Features f) {
    
  }

  @Override
  public void setMenuItems(boolean isClinicLoaded) {
    setMenuItemsCalled = true;
  }

  @Override
  public void makeVisible() {
    
  }

  @Override
  public void refresh(ClinicInterface3 model) {
    refreshCalled = true;
  }

  @Override
  public ClinicLayoutPage getLayoutPage() {
    return null;
  }

}
