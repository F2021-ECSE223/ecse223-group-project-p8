/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ecse223.iteration1;
import java.util.*;

// line 20 "../../iteration1.ump"
// line 83 "../../iteration1.ump"
public class Member extends Registrant
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Member Attributes
  private boolean hireGuide;
  private int numberOfWeeks;
  private boolean hotelStay;

  //Member Associations
  private List<Equipment> equipmentWanted;
  private List<Bundle> bundlesWanted;
  private List<Assignment> assignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Member(String aName, int aEmergencyContact, boolean aHireGuide, int aNumberOfWeeks, boolean aHotelStay)
  {
    super(aName, aEmergencyContact);
    hireGuide = aHireGuide;
    numberOfWeeks = aNumberOfWeeks;
    hotelStay = aHotelStay;
    equipmentWanted = new ArrayList<Equipment>();
    bundlesWanted = new ArrayList<Bundle>();
    assignments = new ArrayList<Assignment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setHireGuide(boolean aHireGuide)
  {
    boolean wasSet = false;
    hireGuide = aHireGuide;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfWeeks(int aNumberOfWeeks)
  {
    boolean wasSet = false;
    numberOfWeeks = aNumberOfWeeks;
    wasSet = true;
    return wasSet;
  }

  public boolean setHotelStay(boolean aHotelStay)
  {
    boolean wasSet = false;
    hotelStay = aHotelStay;
    wasSet = true;
    return wasSet;
  }

  public boolean getHireGuide()
  {
    return hireGuide;
  }

  public int getNumberOfWeeks()
  {
    return numberOfWeeks;
  }

  public boolean getHotelStay()
  {
    return hotelStay;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isHireGuide()
  {
    return hireGuide;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isHotelStay()
  {
    return hotelStay;
  }
  /* Code from template association_GetMany */
  public Equipment getEquipmentWanted(int index)
  {
    Equipment aEquipmentWanted = equipmentWanted.get(index);
    return aEquipmentWanted;
  }

  public List<Equipment> getEquipmentWanted()
  {
    List<Equipment> newEquipmentWanted = Collections.unmodifiableList(equipmentWanted);
    return newEquipmentWanted;
  }

  public int numberOfEquipmentWanted()
  {
    int number = equipmentWanted.size();
    return number;
  }

  public boolean hasEquipmentWanted()
  {
    boolean has = equipmentWanted.size() > 0;
    return has;
  }

  public int indexOfEquipmentWanted(Equipment aEquipmentWanted)
  {
    int index = equipmentWanted.indexOf(aEquipmentWanted);
    return index;
  }
  /* Code from template association_GetMany */
  public Bundle getBundlesWanted(int index)
  {
    Bundle aBundlesWanted = bundlesWanted.get(index);
    return aBundlesWanted;
  }

  public List<Bundle> getBundlesWanted()
  {
    List<Bundle> newBundlesWanted = Collections.unmodifiableList(bundlesWanted);
    return newBundlesWanted;
  }

  public int numberOfBundlesWanted()
  {
    int number = bundlesWanted.size();
    return number;
  }

  public boolean hasBundlesWanted()
  {
    boolean has = bundlesWanted.size() > 0;
    return has;
  }

  public int indexOfBundlesWanted(Bundle aBundlesWanted)
  {
    int index = bundlesWanted.indexOf(aBundlesWanted);
    return index;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipmentWanted()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addEquipmentWanted(Equipment aEquipmentWanted)
  {
    boolean wasAdded = false;
    if (equipmentWanted.contains(aEquipmentWanted)) { return false; }
    equipmentWanted.add(aEquipmentWanted);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipmentWanted(Equipment aEquipmentWanted)
  {
    boolean wasRemoved = false;
    if (equipmentWanted.contains(aEquipmentWanted))
    {
      equipmentWanted.remove(aEquipmentWanted);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEquipmentWantedAt(Equipment aEquipmentWanted, int index)
  {  
    boolean wasAdded = false;
    if(addEquipmentWanted(aEquipmentWanted))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipmentWanted()) { index = numberOfEquipmentWanted() - 1; }
      equipmentWanted.remove(aEquipmentWanted);
      equipmentWanted.add(index, aEquipmentWanted);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentWantedAt(Equipment aEquipmentWanted, int index)
  {
    boolean wasAdded = false;
    if(equipmentWanted.contains(aEquipmentWanted))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipmentWanted()) { index = numberOfEquipmentWanted() - 1; }
      equipmentWanted.remove(aEquipmentWanted);
      equipmentWanted.add(index, aEquipmentWanted);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentWantedAt(aEquipmentWanted, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBundlesWanted()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addBundlesWanted(Bundle aBundlesWanted)
  {
    boolean wasAdded = false;
    if (bundlesWanted.contains(aBundlesWanted)) { return false; }
    bundlesWanted.add(aBundlesWanted);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBundlesWanted(Bundle aBundlesWanted)
  {
    boolean wasRemoved = false;
    if (bundlesWanted.contains(aBundlesWanted))
    {
      bundlesWanted.remove(aBundlesWanted);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBundlesWantedAt(Bundle aBundlesWanted, int index)
  {  
    boolean wasAdded = false;
    if(addBundlesWanted(aBundlesWanted))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundlesWanted()) { index = numberOfBundlesWanted() - 1; }
      bundlesWanted.remove(aBundlesWanted);
      bundlesWanted.add(index, aBundlesWanted);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBundlesWantedAt(Bundle aBundlesWanted, int index)
  {
    boolean wasAdded = false;
    if(bundlesWanted.contains(aBundlesWanted))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundlesWanted()) { index = numberOfBundlesWanted() - 1; }
      bundlesWanted.remove(aBundlesWanted);
      bundlesWanted.add(index, aBundlesWanted);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBundlesWantedAt(aBundlesWanted, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Assignment addAssignment(int aTotalCost)
  {
    return new Assignment(aTotalCost, this);
  }

  public boolean addAssignment(Assignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    Member existingMember = aAssignment.getMember();
    boolean isNewMember = existingMember != null && !this.equals(existingMember);
    if (isNewMember)
    {
      aAssignment.setMember(this);
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
    //Unable to remove aAssignment, as it must always have a member
    if (!this.equals(aAssignment.getMember()))
    {
      assignments.remove(aAssignment);
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

  public void delete()
  {
    equipmentWanted.clear();
    bundlesWanted.clear();
    for(int i=assignments.size(); i > 0; i--)
    {
      Assignment aAssignment = assignments.get(i - 1);
      aAssignment.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "hireGuide" + ":" + getHireGuide()+ "," +
            "numberOfWeeks" + ":" + getNumberOfWeeks()+ "," +
            "hotelStay" + ":" + getHotelStay()+ "]";
  }
}