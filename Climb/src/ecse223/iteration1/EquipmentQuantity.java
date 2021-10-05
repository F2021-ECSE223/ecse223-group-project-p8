/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ecse223.iteration1;

// line 65 "../../Iteration1.ump"
public class EquipmentQuantity
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //EquipmentQuantity Attributes
  private int count;

  //EquipmentQuantity Associations
  private Bundle equipmentInBundle;
  private Equipment equipment;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetEquipmentInBundle;
  private boolean canSetEquipment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public EquipmentQuantity(int aCount, Bundle aEquipmentInBundle, Equipment aEquipment)
  {
    cachedHashCode = -1;
    canSetEquipmentInBundle = true;
    canSetEquipment = true;
    count = aCount;
    boolean didAddEquipmentInBundle = setEquipmentInBundle(aEquipmentInBundle);
    if (!didAddEquipmentInBundle)
    {
      throw new RuntimeException("Unable to create equipmentQuantity due to equipmentInBundle. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddEquipment = setEquipment(aEquipment);
    if (!didAddEquipment)
    {
      throw new RuntimeException("Unable to create equipmentQuantity due to equipment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCount(int aCount)
  {
    boolean wasSet = false;
    count = aCount;
    wasSet = true;
    return wasSet;
  }

  public int getCount()
  {
    return count;
  }
  /* Code from template association_GetOne */
  public Bundle getEquipmentInBundle()
  {
    return equipmentInBundle;
  }
  /* Code from template association_GetOne */
  public Equipment getEquipment()
  {
    return equipment;
  }
  /* Code from template association_SetOneToManyAssociationClass */
  public boolean setEquipmentInBundle(Bundle aEquipmentInBundle)
  {
    boolean wasSet = false;
    if (!canSetEquipmentInBundle) { return false; }
    if (aEquipmentInBundle == null)
    {
      return wasSet;
    }

    Bundle existingEquipmentInBundle = equipmentInBundle;
    equipmentInBundle = aEquipmentInBundle;
    if (existingEquipmentInBundle != null && !existingEquipmentInBundle.equals(aEquipmentInBundle))
    {
      existingEquipmentInBundle.removeEquipmentQuantity(this);
    }
    if (!equipmentInBundle.addEquipmentQuantity(this))
    {
      equipmentInBundle = existingEquipmentInBundle;
      wasSet = false;
    }
    else
    {
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOneToManyAssociationClass */
  public boolean setEquipment(Equipment aEquipment)
  {
    boolean wasSet = false;
    if (!canSetEquipment) { return false; }
    if (aEquipment == null)
    {
      return wasSet;
    }

    Equipment existingEquipment = equipment;
    equipment = aEquipment;
    if (existingEquipment != null && !existingEquipment.equals(aEquipment))
    {
      existingEquipment.removeEquipmentQuantity(this);
    }
    if (!equipment.addEquipmentQuantity(this))
    {
      equipment = existingEquipment;
      wasSet = false;
    }
    else
    {
      wasSet = true;
    }
    return wasSet;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    EquipmentQuantity compareTo = (EquipmentQuantity)obj;
  
    if (getEquipmentInBundle() == null && compareTo.getEquipmentInBundle() != null)
    {
      return false;
    }
    else if (getEquipmentInBundle() != null && !getEquipmentInBundle().equals(compareTo.getEquipmentInBundle()))
    {
      return false;
    }

    if (getEquipment() == null && compareTo.getEquipment() != null)
    {
      return false;
    }
    else if (getEquipment() != null && !getEquipment().equals(compareTo.getEquipment()))
    {
      return false;
    }

    return true;
  }

  public int hashCode()
  {
    if (cachedHashCode != -1)
    {
      return cachedHashCode;
    }
    cachedHashCode = 17;
    if (getEquipmentInBundle() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getEquipmentInBundle().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }
    if (getEquipment() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getEquipment().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetEquipmentInBundle = false;
    canSetEquipment = false;
    return cachedHashCode;
  }

  public void delete()
  {
    Bundle placeholderEquipmentInBundle = equipmentInBundle;
    this.equipmentInBundle = null;
    if(placeholderEquipmentInBundle != null)
    {
      placeholderEquipmentInBundle.removeEquipmentQuantity(this);
    }
    Equipment placeholderEquipment = equipment;
    this.equipment = null;
    if(placeholderEquipment != null)
    {
      placeholderEquipment.removeEquipmentQuantity(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "count" + ":" + getCount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "equipmentInBundle = "+(getEquipmentInBundle()!=null?Integer.toHexString(System.identityHashCode(getEquipmentInBundle())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "equipment = "+(getEquipment()!=null?Integer.toHexString(System.identityHashCode(getEquipment())):"null");
  }
}