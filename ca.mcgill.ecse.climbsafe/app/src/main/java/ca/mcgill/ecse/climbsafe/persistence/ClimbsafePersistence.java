package ca.mcgill.ecse.climbsafe.persistence;


import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import java.sql.Date;

public class ClimbsafePersistence {

  private static String filename = "data.ClimbSafe";

  public static void setFilename(String filename) {
    ClimbsafePersistence.filename = filename;
  }

  public static void save() {
    PersistenceObjectStream.setFilename(filename);
    save(ClimbSafeApplication.getClimbSafe());
  }

  public static void save(ClimbSafe climbsafe) {
    PersistenceObjectStream.setFilename(filename);
    PersistenceObjectStream.serialize(climbsafe);
  }

  public static ClimbSafe load() {
    PersistenceObjectStream.setFilename(filename);
    var climbSafe = (ClimbSafe) PersistenceObjectStream.deserialize();
    if (climbSafe == null) {
      climbSafe = new ClimbSafe(new Date(0), 0, 0);
    } else {
      climbSafe.reinitialize();
    }
    return climbSafe;
  }

}
