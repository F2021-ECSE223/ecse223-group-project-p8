/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ecse223.iteration1;

// line 39 "../../iteration1.ump"
// line 103 "../../iteration1.ump"
public class Equipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Equipment Attributes
  private int pricePerWeek;
  private double weight;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Equipment(int aPricePerWeek, double aWeight)
  {
    pricePerWeek = aPricePerWeek;
    weight = aWeight;
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

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "pricePerWeek" + ":" + getPricePerWeek()+ "," +
            "weight" + ":" + getWeight()+ "]";
  }
}