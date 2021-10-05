/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ecse223.iteration1;
import java.sql.Date;
import java.util.*;

// line 3 "../../associationFile.ump"
// line 38 "../../associationFile.ump"
public class NMC
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //NMC Attributes
  private Date startDate;
  private Date endDate;

  //NMC Associations
  private List<User> users;
  private List<Hotel> hotels;
  private List<Equipment> equipment;
  private List<ClimbingWeek> climbingWeeks;
  private List<MountainGuide> mountainGuides;
  private List<Bundle> bundles;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public NMC(Date aStartDate, Date aEndDate)
  {
    startDate = aStartDate;
    endDate = aEndDate;
    users = new ArrayList<User>();
    hotels = new ArrayList<Hotel>();
    equipment = new ArrayList<Equipment>();
    climbingWeeks = new ArrayList<ClimbingWeek>();
    mountainGuides = new ArrayList<MountainGuide>();
    bundles = new ArrayList<Bundle>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(Date aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  /* Code from template association_GetMany */
  public Hotel getHotel(int index)
  {
    Hotel aHotel = hotels.get(index);
    return aHotel;
  }

  public List<Hotel> getHotels()
  {
    List<Hotel> newHotels = Collections.unmodifiableList(hotels);
    return newHotels;
  }

  public int numberOfHotels()
  {
    int number = hotels.size();
    return number;
  }

  public boolean hasHotels()
  {
    boolean has = hotels.size() > 0;
    return has;
  }

  public int indexOfHotel(Hotel aHotel)
  {
    int index = hotels.indexOf(aHotel);
    return index;
  }
  /* Code from template association_GetMany */
  public Equipment getEquipment(int index)
  {
    Equipment aEquipment = equipment.get(index);
    return aEquipment;
  }

  public List<Equipment> getEquipment()
  {
    List<Equipment> newEquipment = Collections.unmodifiableList(equipment);
    return newEquipment;
  }

  public int numberOfEquipment()
  {
    int number = equipment.size();
    return number;
  }

  public boolean hasEquipment()
  {
    boolean has = equipment.size() > 0;
    return has;
  }

  public int indexOfEquipment(Equipment aEquipment)
  {
    int index = equipment.indexOf(aEquipment);
    return index;
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
  /* Code from template association_GetMany */
  public MountainGuide getMountainGuide(int index)
  {
    MountainGuide aMountainGuide = mountainGuides.get(index);
    return aMountainGuide;
  }

  public List<MountainGuide> getMountainGuides()
  {
    List<MountainGuide> newMountainGuides = Collections.unmodifiableList(mountainGuides);
    return newMountainGuides;
  }

  public int numberOfMountainGuides()
  {
    int number = mountainGuides.size();
    return number;
  }

  public boolean hasMountainGuides()
  {
    boolean has = mountainGuides.size() > 0;
    return has;
  }

  public int indexOfMountainGuide(MountainGuide aMountainGuide)
  {
    int index = mountainGuides.indexOf(aMountainGuide);
    return index;
  }
  /* Code from template association_GetMany */
  public Bundle getBundle(int index)
  {
    Bundle aBundle = bundles.get(index);
    return aBundle;
  }

  public List<Bundle> getBundles()
  {
    List<Bundle> newBundles = Collections.unmodifiableList(bundles);
    return newBundles;
  }

  public int numberOfBundles()
  {
    int number = bundles.size();
    return number;
  }

  public boolean hasBundles()
  {
    boolean has = bundles.size() > 0;
    return has;
  }

  public int indexOfBundle(Bundle aBundle)
  {
    int index = bundles.indexOf(aBundle);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public User addUser()
  {
    return new User(this);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    NMC existingNMC = aUser.getNMC();
    boolean isNewNMC = existingNMC != null && !this.equals(existingNMC);
    if (isNewNMC)
    {
      aUser.setNMC(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a nMC
    if (!this.equals(aUser.getNMC()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHotels()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Hotel addHotel()
  {
    return new Hotel(this);
  }

  public boolean addHotel(Hotel aHotel)
  {
    boolean wasAdded = false;
    if (hotels.contains(aHotel)) { return false; }
    NMC existingNMC = aHotel.getNMC();
    boolean isNewNMC = existingNMC != null && !this.equals(existingNMC);
    if (isNewNMC)
    {
      aHotel.setNMC(this);
    }
    else
    {
      hotels.add(aHotel);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHotel(Hotel aHotel)
  {
    boolean wasRemoved = false;
    //Unable to remove aHotel, as it must always have a nMC
    if (!this.equals(aHotel.getNMC()))
    {
      hotels.remove(aHotel);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHotelAt(Hotel aHotel, int index)
  {  
    boolean wasAdded = false;
    if(addHotel(aHotel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotels()) { index = numberOfHotels() - 1; }
      hotels.remove(aHotel);
      hotels.add(index, aHotel);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHotelAt(Hotel aHotel, int index)
  {
    boolean wasAdded = false;
    if(hotels.contains(aHotel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotels()) { index = numberOfHotels() - 1; }
      hotels.remove(aHotel);
      hotels.add(index, aHotel);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHotelAt(aHotel, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipment()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Equipment addEquipment()
  {
    return new Equipment(this);
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipment.contains(aEquipment)) { return false; }
    NMC existingNMC = aEquipment.getNMC();
    boolean isNewNMC = existingNMC != null && !this.equals(existingNMC);
    if (isNewNMC)
    {
      aEquipment.setNMC(this);
    }
    else
    {
      equipment.add(aEquipment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    //Unable to remove aEquipment, as it must always have a nMC
    if (!this.equals(aEquipment.getNMC()))
    {
      equipment.remove(aEquipment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEquipmentAt(Equipment aEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addEquipment(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Equipment aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipment.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfClimbingWeeksValid()
  {
    boolean isValid = numberOfClimbingWeeks() >= minimumNumberOfClimbingWeeks();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfClimbingWeeks()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public ClimbingWeek addClimbingWeek()
  {
    ClimbingWeek aNewClimbingWeek = new ClimbingWeek(this);
    return aNewClimbingWeek;
  }

  public boolean addClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasAdded = false;
    if (climbingWeeks.contains(aClimbingWeek)) { return false; }
    NMC existingNMC = aClimbingWeek.getNMC();
    boolean isNewNMC = existingNMC != null && !this.equals(existingNMC);

    if (isNewNMC && existingNMC.numberOfClimbingWeeks() <= minimumNumberOfClimbingWeeks())
    {
      return wasAdded;
    }
    if (isNewNMC)
    {
      aClimbingWeek.setNMC(this);
    }
    else
    {
      climbingWeeks.add(aClimbingWeek);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasRemoved = false;
    //Unable to remove aClimbingWeek, as it must always have a nMC
    if (this.equals(aClimbingWeek.getNMC()))
    {
      return wasRemoved;
    }

    //nMC already at minimum (1)
    if (numberOfClimbingWeeks() <= minimumNumberOfClimbingWeeks())
    {
      return wasRemoved;
    }

    climbingWeeks.remove(aClimbingWeek);
    wasRemoved = true;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMountainGuides()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MountainGuide addMountainGuide()
  {
    return new MountainGuide(this);
  }

  public boolean addMountainGuide(MountainGuide aMountainGuide)
  {
    boolean wasAdded = false;
    if (mountainGuides.contains(aMountainGuide)) { return false; }
    NMC existingNMC = aMountainGuide.getNMC();
    boolean isNewNMC = existingNMC != null && !this.equals(existingNMC);
    if (isNewNMC)
    {
      aMountainGuide.setNMC(this);
    }
    else
    {
      mountainGuides.add(aMountainGuide);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMountainGuide(MountainGuide aMountainGuide)
  {
    boolean wasRemoved = false;
    //Unable to remove aMountainGuide, as it must always have a nMC
    if (!this.equals(aMountainGuide.getNMC()))
    {
      mountainGuides.remove(aMountainGuide);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMountainGuideAt(MountainGuide aMountainGuide, int index)
  {  
    boolean wasAdded = false;
    if(addMountainGuide(aMountainGuide))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMountainGuides()) { index = numberOfMountainGuides() - 1; }
      mountainGuides.remove(aMountainGuide);
      mountainGuides.add(index, aMountainGuide);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMountainGuideAt(MountainGuide aMountainGuide, int index)
  {
    boolean wasAdded = false;
    if(mountainGuides.contains(aMountainGuide))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMountainGuides()) { index = numberOfMountainGuides() - 1; }
      mountainGuides.remove(aMountainGuide);
      mountainGuides.add(index, aMountainGuide);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMountainGuideAt(aMountainGuide, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBundles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Bundle addBundle()
  {
    return new Bundle(this);
  }

  public boolean addBundle(Bundle aBundle)
  {
    boolean wasAdded = false;
    if (bundles.contains(aBundle)) { return false; }
    NMC existingNMC = aBundle.getNMC();
    boolean isNewNMC = existingNMC != null && !this.equals(existingNMC);
    if (isNewNMC)
    {
      aBundle.setNMC(this);
    }
    else
    {
      bundles.add(aBundle);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBundle(Bundle aBundle)
  {
    boolean wasRemoved = false;
    //Unable to remove aBundle, as it must always have a nMC
    if (!this.equals(aBundle.getNMC()))
    {
      bundles.remove(aBundle);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBundleAt(Bundle aBundle, int index)
  {  
    boolean wasAdded = false;
    if(addBundle(aBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundles()) { index = numberOfBundles() - 1; }
      bundles.remove(aBundle);
      bundles.add(index, aBundle);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBundleAt(Bundle aBundle, int index)
  {
    boolean wasAdded = false;
    if(bundles.contains(aBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundles()) { index = numberOfBundles() - 1; }
      bundles.remove(aBundle);
      bundles.add(index, aBundle);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBundleAt(aBundle, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    while (hotels.size() > 0)
    {
      Hotel aHotel = hotels.get(hotels.size() - 1);
      aHotel.delete();
      hotels.remove(aHotel);
    }
    
    while (equipment.size() > 0)
    {
      Equipment aEquipment = equipment.get(equipment.size() - 1);
      aEquipment.delete();
      equipment.remove(aEquipment);
    }
    
    while (climbingWeeks.size() > 0)
    {
      ClimbingWeek aClimbingWeek = climbingWeeks.get(climbingWeeks.size() - 1);
      aClimbingWeek.delete();
      climbingWeeks.remove(aClimbingWeek);
    }
    
    while (mountainGuides.size() > 0)
    {
      MountainGuide aMountainGuide = mountainGuides.get(mountainGuides.size() - 1);
      aMountainGuide.delete();
      mountainGuides.remove(aMountainGuide);
    }
    
    while (bundles.size() > 0)
    {
      Bundle aBundle = bundles.get(bundles.size() - 1);
      aBundle.delete();
      bundles.remove(aBundle);
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}