/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ecse223.iteration1;
import java.util.*;

// line 3 "../../iteration1.ump"
// line 68 "../../iteration1.ump"
public abstract class User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByEmailAddress = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String emailAddress;
  private String password;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aEmailAddress, String aPassword)
  {
    password = aPassword;
    if (!setEmailAddress(aEmailAddress))
    {
      throw new RuntimeException("Cannot create due to duplicate emailAddress. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmailAddress(String aEmailAddress)
  {
    boolean wasSet = false;
    String anOldEmailAddress = getEmailAddress();
    if (anOldEmailAddress != null && anOldEmailAddress.equals(aEmailAddress)) {
      return true;
    }
    if (hasWithEmailAddress(aEmailAddress)) {
      return wasSet;
    }
    emailAddress = aEmailAddress;
    wasSet = true;
    if (anOldEmailAddress != null) {
      usersByEmailAddress.remove(anOldEmailAddress);
    }
    usersByEmailAddress.put(aEmailAddress, this);
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithEmailAddress(String aEmailAddress)
  {
    return usersByEmailAddress.get(aEmailAddress);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithEmailAddress(String aEmailAddress)
  {
    return getWithEmailAddress(aEmailAddress) != null;
  }

  public String getPassword()
  {
    return password;
  }

  public void delete()
  {
    usersByEmailAddress.remove(getEmailAddress());
  }


  public String toString()
  {
    return super.toString() + "["+
            "emailAddress" + ":" + getEmailAddress()+ "," +
            "password" + ":" + getPassword()+ "]";
  }
}