/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ecse223.iteration1;
import java.util.*;

// line 59 "../../Iteration1.ump"
// line 116 "../../Iteration1.ump"
public class Assignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private int totalCost;

  //Assignment Associations
  private Member member;
  private MountainGuide guideHired;
  private List<ClimbingWeek> climbingWeeks;
  private Hotel assignedHotel;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Assignment(int aTotalCost, Member aMember)
  {
    totalCost = aTotalCost;
    boolean didAddMember = setMember(aMember);
    if (!didAddMember)
    {
      throw new RuntimeException("Unable to create assignment due to member. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    climbingWeeks = new ArrayList<ClimbingWeek>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTotalCost(int aTotalCost)
  {
    boolean wasSet = false;
    totalCost = aTotalCost;
    wasSet = true;
    return wasSet;
  }

  /**
   * this is a derived attribute for the total cost of a member
   */
  public int getTotalCost()
  {
    return totalCost;
  }
  /* Code from template association_GetOne */
  public Member getMember()
  {
    return member;
  }
  /* Code from template association_GetOne */
  public MountainGuide getGuideHired()
  {
    return guideHired;
  }

  public boolean hasGuideHired()
  {
    boolean has = guideHired != null;
    return has;
  }
  /* Code from template association_GetMany */
  public ClimbingWeek getClimbingWeek(int index)
  {
    ClimbingWeek aClimbingWeek = climbingWeeks.get(index);
    return aClimbingWeek;
  }

  public List<ClimbingWeek> getClimbingWeeks()
  {
    List<ClimbingWeek> newClimbingWeeks = Collections.unmodifiableList(climbingWeeks);
    return newClimbingWeeks;
  }

  public int numberOfClimbingWeeks()
  {
    int number = climbingWeeks.size();
    return number;
  }

  public boolean hasClimbingWeeks()
  {
    boolean has = climbingWeeks.size() > 0;
    return has;
  }

  public int indexOfClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    int index = climbingWeeks.indexOf(aClimbingWeek);
    return index;
  }
  /* Code from template association_GetOne */
  public Hotel getAssignedHotel()
  {
    return assignedHotel;
  }

  public boolean hasAssignedHotel()
  {
    boolean has = assignedHotel != null;
    return has;
  }
  /* Code from template association_SetOneToMany */
  public boolean setMember(Member aMember)
  {
    boolean wasSet = false;
    if (aMember == null)
    {
      return wasSet;
    }

    Member existingMember = member;
    member = aMember;
    if (existingMember != null && !existingMember.equals(aMember))
    {
      existingMember.removeAssignment(this);
    }
    member.addAssignment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setGuideHired(MountainGuide aGuideHired)
  {
    boolean wasSet = false;
    MountainGuide existingGuideHired = guideHired;
    guideHired = aGuideHired;
    if (existingGuideHired != null && !existingGuideHired.equals(aGuideHired))
    {
      existingGuideHired.removeAssignment(this);
    }
    if (aGuideHired != null)
    {
      aGuideHired.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfClimbingWeeks()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasAdded = false;
    if (climbingWeeks.contains(aClimbingWeek)) { return false; }
    climbingWeeks.add(aClimbingWeek);
    if (aClimbingWeek.indexOfAssignment(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aClimbingWeek.addAssignment(this);
      if (!wasAdded)
      {
        climbingWeeks.remove(aClimbingWeek);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasRemoved = false;
    if (!climbingWeeks.contains(aClimbingWeek))
    {
      return wasRemoved;
    }

    int oldIndex = climbingWeeks.indexOf(aClimbingWeek);
    climbingWeeks.remove(oldIndex);
    if (aClimbingWeek.indexOfAssignment(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aClimbingWeek.removeAssignment(this);
      if (!wasRemoved)
      {
        climbingWeeks.add(oldIndex,aClimbingWeek);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addClimbingWeekAt(ClimbingWeek aClimbingWeek, int index)
  {  
    boolean wasAdded = false;
    if(addClimbingWeek(aClimbingWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClimbingWeeks()) { index = numberOfClimbingWeeks() - 1; }
      climbingWeeks.remove(aClimbingWeek);
      climbingWeeks.add(index, aClimbingWeek);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveClimbingWeekAt(ClimbingWeek aClimbingWeek, int index)
  {
    boolean wasAdded = false;
    if(climbingWeeks.contains(aClimbingWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClimbingWeeks()) { index = numberOfClimbingWeeks() - 1; }
      climbingWeeks.remove(aClimbingWeek);
      climbingWeeks.add(index, aClimbingWeek);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addClimbingWeekAt(aClimbingWeek, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setAssignedHotel(Hotel aAssignedHotel)
  {
    boolean wasSet = false;
    Hotel existingAssignedHotel = assignedHotel;
    assignedHotel = aAssignedHotel;
    if (existingAssignedHotel != null && !existingAssignedHotel.equals(aAssignedHotel))
    {
      existingAssignedHotel.removeAssignment(this);
    }
    if (aAssignedHotel != null)
    {
      aAssignedHotel.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Member placeholderMember = member;
    this.member = null;
    if(placeholderMember != null)
    {
      placeholderMember.removeAssignment(this);
    }
    if (guideHired != null)
    {
      MountainGuide placeholderGuideHired = guideHired;
      this.guideHired = null;
      placeholderGuideHired.removeAssignment(this);
    }
    ArrayList<ClimbingWeek> copyOfClimbingWeeks = new ArrayList<ClimbingWeek>(climbingWeeks);
    climbingWeeks.clear();
    for(ClimbingWeek aClimbingWeek : copyOfClimbingWeeks)
    {
      aClimbingWeek.removeAssignment(this);
    }
    if (assignedHotel != null)
    {
      Hotel placeholderAssignedHotel = assignedHotel;
      this.assignedHotel = null;
      placeholderAssignedHotel.removeAssignment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "totalCost" + ":" + getTotalCost()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guideHired = "+(getGuideHired()!=null?Integer.toHexString(System.identityHashCode(getGuideHired())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assignedHotel = "+(getAssignedHotel()!=null?Integer.toHexString(System.identityHashCode(getAssignedHotel())):"null");
  }
}