/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ecse223.iteration1;
import java.util.*;

// line 23 "../../associationFile.ump"
// line 44 "../../Iteration1.ump"
// line 60 "../../associationFile.ump"
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
  private NMC nMC;
  private List<EquipmentQuantity> equipmentQuantities;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Bundle(double aPercentageDiscount, int aCost, String aName, NMC aNMC)
  {
    percentageDiscount = aPercentageDiscount;
    cost = aCost;
    name = aName;
    boolean didAddNMC = setNMC(aNMC);
    if (!didAddNMC)
    {
      throw new RuntimeException("Unable to create bundle due to nMC. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    equipmentQuantities = new ArrayList<EquipmentQuantity>();
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

  /**
   * this is a derived attribute
   */
  public int getCost()
  {
    return cost;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetOne */
  public NMC getNMC()
  {
    return nMC;
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
      existingNMC.removeBundle(this);
    }
    nMC.addBundle(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipmentQuantities()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public EquipmentQuantity addEquipmentQuantity(int aCount, Equipment aEquipment)
  {
    return new EquipmentQuantity(aCount, this, aEquipment);
  }

  public boolean addEquipmentQuantity(EquipmentQuantity aEquipmentQuantity)
  {
    boolean wasAdded = false;
    if (equipmentQuantities.contains(aEquipmentQuantity)) { return false; }
    Bundle existingEquipmentInBundle = aEquipmentQuantity.getEquipmentInBundle();
    boolean isNewEquipmentInBundle = existingEquipmentInBundle != null && !this.equals(existingEquipmentInBundle);
    if (isNewEquipmentInBundle)
    {
      aEquipmentQuantity.setEquipmentInBundle(this);
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
    //Unable to remove aEquipmentQuantity, as it must always have a equipmentInBundle
    if (!this.equals(aEquipmentQuantity.getEquipmentInBundle()))
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
    NMC placeholderNMC = nMC;
    this.nMC = null;
    if(placeholderNMC != null)
    {
      placeholderNMC.removeBundle(this);
    }
    for(int i=equipmentQuantities.size(); i > 0; i--)
    {
      EquipmentQuantity aEquipmentQuantity = equipmentQuantities.get(i - 1);
      aEquipmentQuantity.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "percentageDiscount" + ":" + getPercentageDiscount()+ "," +
            "cost" + ":" + getCost()+ "," +
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "nMC = "+(getNMC()!=null?Integer.toHexString(System.identityHashCode(getNMC())):"null");
  }
}