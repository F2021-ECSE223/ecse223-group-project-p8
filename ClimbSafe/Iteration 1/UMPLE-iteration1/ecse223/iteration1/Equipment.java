/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ecse223.iteration1;
import java.util.*;

// line 39 "../../Iteration1.ump"
public class Equipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Equipment Attributes
  private int pricePerWeek;
  private double weight;

  //Equipment Associations
  private List<EquipmentQuantity> equipmentQuantities;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Equipment(int aPricePerWeek, double aWeight)
  {
    pricePerWeek = aPricePerWeek;
    weight = aWeight;
    equipmentQuantities = new ArrayList<EquipmentQuantity>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPricePerWeek(int aPricePerWeek)
  {
    boolean wasSet = false;
    pricePerWeek = aPricePerWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeight(double aWeight)
  {
    boolean wasSet = false;
    weight = aWeight;
    wasSet = true;
    return wasSet;
  }

  public int getPricePerWeek()
  {
    return pricePerWeek;
  }

  public double getWeight()
  {
    return weight;
  }
  /* Code from template association_GetMany */
  public EquipmentQuantity getEquipmentQuantity(int index)
  {
    EquipmentQuantity aEquipmentQuantity = equipmentQuantities.get(index);
    return aEquipmentQuantity;
  }

  public List<EquipmentQuantity> getEquipmentQuantities()
  {
    List<EquipmentQuantity> newEquipmentQuantities = Collections.unmodifiableList(equipmentQuantities);
    return newEquipmentQuantities;
  }

  public int numberOfEquipmentQuantities()
  {
    int number = equipmentQuantities.size();
    return number;
  }

  public boolean hasEquipmentQuantities()
  {
    boolean has = equipmentQuantities.size() > 0;
    return has;
  }

  public int indexOfEquipmentQuantity(EquipmentQuantity aEquipmentQuantity)
  {
    int index = equipmentQuantities.indexOf(aEquipmentQuantity);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipmentQuantities()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public EquipmentQuantity addEquipmentQuantity(int aCount, Bundle aEquipmentInBundle)
  {
    return new EquipmentQuantity(aCount, aEquipmentInBundle, this);
  }

  public boolean addEquipmentQuantity(EquipmentQuantity aEquipmentQuantity)
  {
    boolean wasAdded = false;
    if (equipmentQuantities.contains(aEquipmentQuantity)) { return false; }
    Equipment existingEquipment = aEquipmentQuantity.getEquipment();
    boolean isNewEquipment = existingEquipment != null && !this.equals(existingEquipment);
    if (isNewEquipment)
    {
      aEquipmentQuantity.setEquipment(this);
    }
    else
    {
      equipmentQuantities.add(aEquipmentQuantity);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipmentQuantity(EquipmentQuantity aEquipmentQuantity)
  {
    boolean wasRemoved = false;
    //Unable to remove aEquipmentQuantity, as it must always have a equipment
    if (!this.equals(aEquipmentQuantity.getEquipment()))
    {
      equipmentQuantities.remove(aEquipmentQuantity);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEquipmentQuantityAt(EquipmentQuantity aEquipmentQuantity, int index)
  {  
    boolean wasAdded = false;
    if(addEquipmentQuantity(aEquipmentQuantity))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipmentQuantities()) { index = numberOfEquipmentQuantities() - 1; }
      equipmentQuantities.remove(aEquipmentQuantity);
      equipmentQuantities.add(index, aEquipmentQuantity);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentQuantityAt(EquipmentQuantity aEquipmentQuantity, int index)
  {
    boolean wasAdded = false;
    if(equipmentQuantities.contains(aEquipmentQuantity))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipmentQuantities()) { index = numberOfEquipmentQuantities() - 1; }
      equipmentQuantities.remove(aEquipmentQuantity);
      equipmentQuantities.add(index, aEquipmentQuantity);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentQuantityAt(aEquipmentQuantity, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=equipmentQuantities.size(); i > 0; i--)
    {
      EquipmentQuantity aEquipmentQuantity = equipmentQuantities.get(i - 1);
      aEquipmentQuantity.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "pricePerWeek" + ":" + getPricePerWeek()+ "," +
            "weight" + ":" + getWeight()+ "]";
  }
}