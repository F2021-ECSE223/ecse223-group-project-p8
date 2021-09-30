/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ecse223.iteration1;
import java.util.*;

// line 29 "../../iteration1.ump"
// line 90 "../../iteration1.ump"
public class MountainGuide extends Registrant
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MountainGuide Attributes
  private int weeklyCost;

  //MountainGuide Associations
  private List<Assignment> guideHired;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MountainGuide(String aEmailAddress, String aPassword, String aName, int aEmergencyContact, int aWeeklyCost)
  {
    super(aEmailAddress, aPassword, aName, aEmergencyContact);
    weeklyCost = aWeeklyCost;
    guideHired = new ArrayList<Assignment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWeeklyCost(int aWeeklyCost)
  {
    boolean wasSet = false;
    weeklyCost = aWeeklyCost;
    wasSet = true;
    return wasSet;
  }

  public int getWeeklyCost()
  {
    return weeklyCost;
  }
  /* Code from template association_GetMany */
  public Assignment getGuideHired(int index)
  {
    Assignment aGuideHired = guideHired.get(index);
    return aGuideHired;
  }

  public List<Assignment> getGuideHired()
  {
    List<Assignment> newGuideHired = Collections.unmodifiableList(guideHired);
    return newGuideHired;
  }

  public int numberOfGuideHired()
  {
    int number = guideHired.size();
    return number;
  }

  public boolean hasGuideHired()
  {
    boolean has = guideHired.size() > 0;
    return has;
  }

  public int indexOfGuideHired(Assignment aGuideHired)
  {
    int index = guideHired.indexOf(aGuideHired);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGuideHired()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addGuideHired(Assignment aGuideHired)
  {
    boolean wasAdded = false;
    if (guideHired.contains(aGuideHired)) { return false; }
    MountainGuide existingMountainGuide = aGuideHired.getMountainGuide();
    if (existingMountainGuide == null)
    {
      aGuideHired.setMountainGuide(this);
    }
    else if (!this.equals(existingMountainGuide))
    {
      existingMountainGuide.removeGuideHired(aGuideHired);
      addGuideHired(aGuideHired);
    }
    else
    {
      guideHired.add(aGuideHired);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGuideHired(Assignment aGuideHired)
  {
    boolean wasRemoved = false;
    if (guideHired.contains(aGuideHired))
    {
      guideHired.remove(aGuideHired);
      aGuideHired.setMountainGuide(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGuideHiredAt(Assignment aGuideHired, int index)
  {  
    boolean wasAdded = false;
    if(addGuideHired(aGuideHired))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGuideHired()) { index = numberOfGuideHired() - 1; }
      guideHired.remove(aGuideHired);
      guideHired.add(index, aGuideHired);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGuideHiredAt(Assignment aGuideHired, int index)
  {
    boolean wasAdded = false;
    if(guideHired.contains(aGuideHired))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGuideHired()) { index = numberOfGuideHired() - 1; }
      guideHired.remove(aGuideHired);
      guideHired.add(index, aGuideHired);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGuideHiredAt(aGuideHired, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while( !guideHired.isEmpty() )
    {
      guideHired.get(0).setMountainGuide(null);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "weeklyCost" + ":" + getWeeklyCost()+ "]";
  }
}