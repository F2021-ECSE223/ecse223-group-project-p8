/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ecse223.iteration1;
import java.util.*;

// line 26 "../../associationFile.ump"
// line 29 "../../Iteration1.ump"
// line 96 "../../Iteration1.ump"
// line 65 "../../associationFile.ump"
public class MountainGuide extends Registrant
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MountainGuide Attributes
  private int weeklyCost;

  //MountainGuide Associations
  private List<Assignment> assignments;
  private NMC nMC;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MountainGuide(String aName, int aEmergencyContact, int aWeeklyCost, NMC aNMC)
  {
    super(aName, aEmergencyContact);
    weeklyCost = aWeeklyCost;
    assignments = new ArrayList<Assignment>();
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
  public Assignment getAssignment(int index)
  {
    Assignment aAssignment = assignments.get(index);
    return aAssignment;
  }

  public List<Assignment> getAssignments()
  {
    List<Assignment> newAssignments = Collections.unmodifiableList(assignments);
    return newAssignments;
  }

  public int numberOfAssignments()
  {
    int number = assignments.size();
    return number;
  }

  public boolean hasAssignments()
  {
    boolean has = assignments.size() > 0;
    return has;
  }

  public int indexOfAssignment(Assignment aAssignment)
  {
    int index = assignments.indexOf(aAssignment);
    return index;
  }
  /* Code from template association_GetOne */
  public NMC getNMC()
  {
    return nMC;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addAssignment(Assignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    MountainGuide existingGuideHired = aAssignment.getGuideHired();
    if (existingGuideHired == null)
    {
      aAssignment.setGuideHired(this);
    }
    else if (!this.equals(existingGuideHired))
    {
      existingGuideHired.removeAssignment(aAssignment);
      addAssignment(aAssignment);
    }
    else
    {
      assignments.add(aAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAssignment(Assignment aAssignment)
  {
    boolean wasRemoved = false;
    if (assignments.contains(aAssignment))
    {
      assignments.remove(aAssignment);
      aAssignment.setGuideHired(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssignmentAt(Assignment aAssignment, int index)
  {  
    boolean wasAdded = false;
    if(addAssignment(aAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignments()) { index = numberOfAssignments() - 1; }
      assignments.remove(aAssignment);
      assignments.add(index, aAssignment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssignmentAt(Assignment aAssignment, int index)
  {
    boolean wasAdded = false;
    if(assignments.contains(aAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignments()) { index = numberOfAssignments() - 1; }
      assignments.remove(aAssignment);
      assignments.add(index, aAssignment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssignmentAt(aAssignment, index);
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
    while( !assignments.isEmpty() )
    {
      assignments.get(0).setGuideHired(null);
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