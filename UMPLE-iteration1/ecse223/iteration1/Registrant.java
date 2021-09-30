/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ecse223.iteration1;
import java.util.*;

// line 6 "../../Iteration1.ump"
// line 11 "../../Iteration1.ump"
// line 84 "../../Iteration1.ump"
public abstract class Registrant extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Registrant Attributes
  private String name;
  private int emergencyContact;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Registrant(String aEmailAddress, String aPassword, String aName, int aEmergencyContact)
  {
    super(aEmailAddress, aPassword);
    name = aName;
    emergencyContact = aEmergencyContact;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmergencyContact(int aEmergencyContact)
  {
    boolean wasSet = false;
    emergencyContact = aEmergencyContact;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getEmergencyContact()
  {
    return emergencyContact;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "emergencyContact" + ":" + getEmergencyContact()+ "]";
  }
}