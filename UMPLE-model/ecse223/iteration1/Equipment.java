/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ecse223.iteration1;

// line 14 "../../associationFile.ump"
// line 39 "../../iteration1.ump"
// line 103 "../../iteration1.ump"
// line 46 "../../associationFile.ump"
public class Equipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Equipment Attributes
  private int pricePerWeek;
  private double weight;

  //Equipment Associations
  private NMC nMC;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Equipment(int aPricePerWeek, double aWeight, NMC aNMC)
  {
    pricePerWeek = aPricePerWeek;
    weight = aWeight;
    boolean didAddNMC = setNMC(aNMC);
    if (!didAddNMC)
    {
      throw new RuntimeException("Unable to create equipment due to nMC. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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
  /* Code from template association_GetOne */
  public NMC getNMC()
  {
    return nMC;
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
      existingNMC.removeEquipment(this);
    }
    nMC.addEquipment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    NMC placeholderNMC = nMC;
    this.nMC = null;
    if(placeholderNMC != null)
    {
      placeholderNMC.removeEquipment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "pricePerWeek" + ":" + getPricePerWeek()+ "," +
            "weight" + ":" + getWeight()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "nMC = "+(getNMC()!=null?Integer.toHexString(System.identityHashCode(getNMC())):"null");
  }
}