/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ecse223.iteration1;
import java.util.*;

// line 26 "../../associationFile.ump"
// line 29 "../../iteration1.ump"
// line 90 "../../iteration1.ump"
// line 66 "../../associationFile.ump"
public class MountainGuide extends Registrant
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MountainGuide Attributes
  private int weeklyCost;

  //MountainGuide Associations
  private List<Assignment> guideHired;
  private NMC nMC;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MountainGuide(String aName, int aEmergencyContact, int aWeeklyCost, NMC aNMC)
  {
    super(aName, aEmergencyContact);
    weeklyCost = aWeeklyCost;
    guideHired = new ArrayList<Assignment>();
    boolean didAddNMC = setNMC(aNMC);
    if (!didAddNMC)
    {
      throw new RuntimeException("Unable to create mountainGuide due to nMC. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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
  /* Code from template association_GetOne */
  public NMC getNMC()
  {
    return nMC;
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
  /* Code from template association_SetOneToMany */
  public boolean setNMC(NMC aNMC)
  {
    boolean wasSet = false;
    if (aNMC == null)
    {
      return wasSet;
    }

    NMC existingNMC = nMC;
    nMC = aNMC;
    if (existingNMC != null && !existingNMC.equals(aNMC))
    {
      existingNMC.removeMountainGuide(this);
    }
    nMC.addMountainGuide(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while( !guideHired.isEmpty() )
    {
      guideHired.get(0).setMountainGuide(null);
    }
    NMC placeholderNMC = nMC;
    this.nMC = null;
    if(placeholderNMC != null)
    {
      placeholderNMC.removeMountainGuide(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "weeklyCost" + ":" + getWeeklyCost()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "nMC = "+(getNMC()!=null?Integer.toHexString(System.identityHashCode(getNMC())):"null");
  }
}