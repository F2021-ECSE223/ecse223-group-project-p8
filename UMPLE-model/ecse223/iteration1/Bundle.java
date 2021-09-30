/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ecse223.iteration1;
import java.util.*;

// line 44 "../../iteration1.ump"
// line 108 "../../iteration1.ump"
public class Bundle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Bundle Attributes
  private double percentageDiscount;
  private int cost;
  private String name;

  //Bundle Associations
  private List<Equipment> equipmentInBundle;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Bundle(double aPercentageDiscount, int aCost, String aName, Equipment... allEquipmentInBundle)
  {
    percentageDiscount = aPercentageDiscount;
    cost = aCost;
    name = aName;
    equipmentInBundle = new ArrayList<Equipment>();
    boolean didAddEquipmentInBundle = setEquipmentInBundle(allEquipmentInBundle);
    if (!didAddEquipmentInBundle)
    {
      throw new RuntimeException("Unable to create Bundle, must have at least 2 equipmentInBundle. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPercentageDiscount(double aPercentageDiscount)
  {
    boolean wasSet = false;
    percentageDiscount = aPercentageDiscount;
    wasSet = true;
    return wasSet;
  }

  public boolean setCost(int aCost)
  {
    boolean wasSet = false;
    cost = aCost;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public double getPercentageDiscount()
  {
    return percentageDiscount;
  }

  public int getCost()
  {
    return cost;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetMany */
  public Equipment getEquipmentInBundle(int index)
  {
    Equipment aEquipmentInBundle = equipmentInBundle.get(index);
    return aEquipmentInBundle;
  }

  public List<Equipment> getEquipmentInBundle()
  {
    List<Equipment> newEquipmentInBundle = Collections.unmodifiableList(equipmentInBundle);
    return newEquipmentInBundle;
  }

  public int numberOfEquipmentInBundle()
  {
    int number = equipmentInBundle.size();
    return number;
  }

  public boolean hasEquipmentInBundle()
  {
    boolean has = equipmentInBundle.size() > 0;
    return has;
  }

  public int indexOfEquipmentInBundle(Equipment aEquipmentInBundle)
  {
    int index = equipmentInBundle.indexOf(aEquipmentInBundle);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipmentInBundle()
  {
    return 2;
  }
  /* Code from template association_AddUnidirectionalMStar */
  public boolean addEquipmentInBundle(Equipment aEquipmentInBundle)
  {
    boolean wasAdded = false;
    if (equipmentInBundle.contains(aEquipmentInBundle)) { return false; }
    equipmentInBundle.add(aEquipmentInBundle);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipmentInBundle(Equipment aEquipmentInBundle)
  {
    boolean wasRemoved = false;
    if (!equipmentInBundle.contains(aEquipmentInBundle))
    {
      return wasRemoved;
    }

    if (numberOfEquipmentInBundle() <= minimumNumberOfEquipmentInBundle())
    {
      return wasRemoved;
    }

    equipmentInBundle.remove(aEquipmentInBundle);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_SetUnidirectionalMStar */
  public boolean setEquipmentInBundle(Equipment... newEquipmentInBundle)
  {
    boolean wasSet = false;
    ArrayList<Equipment> verifiedEquipmentInBundle = new ArrayList<Equipment>();
    for (Equipment aEquipmentInBundle : newEquipmentInBundle)
    {
      if (verifiedEquipmentInBundle.contains(aEquipmentInBundle))
      {
        continue;
      }
      verifiedEquipmentInBundle.add(aEquipmentInBundle);
    }

    if (verifiedEquipmentInBundle.size() != newEquipmentInBundle.length || verifiedEquipmentInBundle.size() < minimumNumberOfEquipmentInBundle())
    {
      return wasSet;
    }

    equipmentInBundle.clear();
    equipmentInBundle.addAll(verifiedEquipmentInBundle);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEquipmentInBundleAt(Equipment aEquipmentInBundle, int index)
  {  
    boolean wasAdded = false;
    if(addEquipmentInBundle(aEquipmentInBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipmentInBundle()) { index = numberOfEquipmentInBundle() - 1; }
      equipmentInBundle.remove(aEquipmentInBundle);
      equipmentInBundle.add(index, aEquipmentInBundle);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentInBundleAt(Equipment aEquipmentInBundle, int index)
  {
    boolean wasAdded = false;
    if(equipmentInBundle.contains(aEquipmentInBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipmentInBundle()) { index = numberOfEquipmentInBundle() - 1; }
      equipmentInBundle.remove(aEquipmentInBundle);
      equipmentInBundle.add(index, aEquipmentInBundle);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentInBundleAt(aEquipmentInBundle, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    equipmentInBundle.clear();
  }


  public String toString()
  {
    return super.toString() + "["+
            "percentageDiscount" + ":" + getPercentageDiscount()+ "," +
            "cost" + ":" + getCost()+ "," +
            "name" + ":" + getName()+ "]";
  }
}