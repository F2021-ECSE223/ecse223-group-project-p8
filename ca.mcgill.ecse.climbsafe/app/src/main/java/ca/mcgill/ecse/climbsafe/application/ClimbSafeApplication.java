package ca.mcgill.ecse.climbsafe.application;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;

import ca.mcgill.ecse.climbsafe.persistence.ClimbsafePersistence;
import ca.mcgill.ecse.climbsafe.javafx.fxml.ClimbsafeFxmlView;
import javafx.application.Application;


public class ClimbSafeApplication {
  private static ClimbSafe climbSafe;
  
  public enum UIMode { SWING, JAVAFX_PURE_JAVA, JAVAFX_FXML }

  public static final UIMode UI_MODE = UIMode.JAVAFX_FXML;

  public String getGreeting() {
    return "Hello World!";
  }

  public static void main(String[] args) {
    //System.out.println(new ClimbSafeApplication().getGreeting());
    Application.launch(ClimbsafeFxmlView.class, args);
  }

  public static ClimbSafe getClimbSafe() {
    if (climbSafe == null) {
      // these attributes are default, you should set them later with the setters
      climbSafe = new ClimbSafe(new Date(0), 0, 0);
    }
    
    return climbSafe;
  }
}
