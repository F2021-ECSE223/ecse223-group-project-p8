/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.model;
import java.io.Serializable;

// line 107 "../../../../../ClimbSafePersistence.ump"
// line 1 "../../../../../ClimbSafeStates.ump"
// line 93 "../../../../../ClimbSafe.ump"
public class Assignment implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private String authorizationCode;
  private int refund;
  private String error;
  private int startWeek;
  private int endWeek;

  //Assignment State Machines
  public enum AssignmentStatus { Assigned, Paid, Started, Cancelled, Finished, Banned }
  private AssignmentStatus assignmentStatus;

  //Assignment Associations
  private Member member;
  private Guide guide;
  private Hotel hotel;
  private ClimbSafe climbSafe;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Assignment(int aStartWeek, int aEndWeek, Member aMember, ClimbSafe aClimbSafe)
  {
    authorizationCode = null;
    error = null;
    startWeek = aStartWeek;
    endWeek = aEndWeek;
    boolean didAddMember = setMember(aMember);
    if (!didAddMember)
    {
      throw new RuntimeException("Unable to create assignment due to member. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create assignment due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    setAssignmentStatus(AssignmentStatus.Assigned);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAuthorizationCode(String aAuthorizationCode)
  {
    boolean wasSet = false;
    authorizationCode = aAuthorizationCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setRefund(int aRefund)
  {
    boolean wasSet = false;
    refund = aRefund;
    wasSet = true;
    return wasSet;
  }

  public boolean setError(String aError)
  {
    boolean wasSet = false;
    error = aError;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartWeek(int aStartWeek)
  {
    boolean wasSet = false;
    startWeek = aStartWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndWeek(int aEndWeek)
  {
    boolean wasSet = false;
    endWeek = aEndWeek;
    wasSet = true;
    return wasSet;
  }

  public String getAuthorizationCode()
  {
    return authorizationCode;
  }

  public int getRefund()
  {
    return refund;
  }

  public String getError()
  {
    return error;
  }

  public int getStartWeek()
  {
    return startWeek;
  }

  public int getEndWeek()
  {
    return endWeek;
  }

  public String getAssignmentStatusFullName()
  {
    String answer = assignmentStatus.toString();
    return answer;
  }

  public AssignmentStatus getAssignmentStatus()
  {
    return assignmentStatus;
  }

  public boolean paidForTrip()
  {
    boolean wasEventProcessed = false;
    
    AssignmentStatus aAssignmentStatus = assignmentStatus;
    switch (aAssignmentStatus)
    {
      case Assigned:
        if (isValid())
        {
          setAssignmentStatus(AssignmentStatus.Paid);
          wasEventProcessed = true;
          break;
        }
        break;
      case Paid:
        // line 17 "../../../../../ClimbSafeStates.ump"
        this.error = "Trip has already been paid for";
        setAssignmentStatus(AssignmentStatus.Paid);
        wasEventProcessed = true;
        break;
      case Started:
        // line 23 "../../../../../ClimbSafeStates.ump"
        this.error = "Trip has already been paid for";
        setAssignmentStatus(AssignmentStatus.Started);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 28 "../../../../../ClimbSafeStates.ump"
        this.error = "Cannot pay for a trip which has been cancelled";
        setAssignmentStatus(AssignmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 34 "../../../../../ClimbSafeStates.ump"
        this.error = "Cannot pay for a trip which has finished";
        setAssignmentStatus(AssignmentStatus.Finished);
        wasEventProcessed = true;
        break;
      case Banned:
        // line 39 "../../../../../ClimbSafeStates.ump"
        this.error = "Cannot pay for the trip due to a ban";
        setAssignmentStatus(AssignmentStatus.Banned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancelTrip()
  {
    boolean wasEventProcessed = false;
    
    AssignmentStatus aAssignmentStatus = assignmentStatus;
    switch (aAssignmentStatus)
    {
      case Assigned:
        // line 9 "../../../../../ClimbSafeStates.ump"
        modRefund();
        setAssignmentStatus(AssignmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 15 "../../../../../ClimbSafeStates.ump"
        modRefund();
        setAssignmentStatus(AssignmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Started:
        // line 22 "../../../../../ClimbSafeStates.ump"
        modRefund();
        setAssignmentStatus(AssignmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 33 "../../../../../ClimbSafeStates.ump"
        this.error = "Cannot cancel a trip which has finished";
        setAssignmentStatus(AssignmentStatus.Finished);
        wasEventProcessed = true;
        break;
      case Banned:
        // line 40 "../../../../../ClimbSafeStates.ump"
        this.error = "Cannot cancel the trip due to a ban";
        setAssignmentStatus(AssignmentStatus.Banned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean startTrip()
  {
    boolean wasEventProcessed = false;
    
    AssignmentStatus aAssignmentStatus = assignmentStatus;
    switch (aAssignmentStatus)
    {
      case Assigned:
        setAssignmentStatus(AssignmentStatus.Banned);
        wasEventProcessed = true;
        break;
      case Paid:
        setAssignmentStatus(AssignmentStatus.Started);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 26 "../../../../../ClimbSafeStates.ump"
        this.error = "Cannot start a trip which has been cancelled";
        setAssignmentStatus(AssignmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 32 "../../../../../ClimbSafeStates.ump"
        this.error = "Cannot start a trip which has finished";
        setAssignmentStatus(AssignmentStatus.Finished);
        wasEventProcessed = true;
        break;
      case Banned:
        // line 37 "../../../../../ClimbSafeStates.ump"
        this.error = "Cannot start the trip due to a ban";
        setAssignmentStatus(AssignmentStatus.Banned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean finishTrip()
  {
    boolean wasEventProcessed = false;
    
    AssignmentStatus aAssignmentStatus = assignmentStatus;
    switch (aAssignmentStatus)
    {
      case Assigned:
        // line 11 "../../../../../ClimbSafeStates.ump"
        this.error = "Cannot finish a trip which has not started";
        setAssignmentStatus(AssignmentStatus.Assigned);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 16 "../../../../../ClimbSafeStates.ump"
        this.error = "Cannot finish a trip which has not started";
        setAssignmentStatus(AssignmentStatus.Paid);
        wasEventProcessed = true;
        break;
      case Started:
        setAssignmentStatus(AssignmentStatus.Finished);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 27 "../../../../../ClimbSafeStates.ump"
        this.error = "Cannot finish a trip which has been cancelled";
        setAssignmentStatus(AssignmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Banned:
        // line 38 "../../../../../ClimbSafeStates.ump"
        this.error = "Cannot finish the trip due to a ban";
        setAssignmentStatus(AssignmentStatus.Banned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setAssignmentStatus(AssignmentStatus aAssignmentStatus)
  {
    assignmentStatus = aAssignmentStatus;
  }
  /* Code from template association_GetOne */
  public Member getMember()
  {
    return member;
  }
  /* Code from template association_GetOne */
  public Guide getGuide()
  {
    return guide;
  }

  public boolean hasGuide()
  {
    boolean has = guide != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Hotel getHotel()
  {
    return hotel;
  }

  public boolean hasHotel()
  {
    boolean has = hotel != null;
    return has;
  }
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setMember(Member aNewMember)
  {
    boolean wasSet = false;
    if (aNewMember == null)
    {
      //Unable to setMember to null, as assignment must always be associated to a member
      return wasSet;
    }
    
    Assignment existingAssignment = aNewMember.getAssignment();
    if (existingAssignment != null && !equals(existingAssignment))
    {
      //Unable to setMember, the current member already has a assignment, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Member anOldMember = member;
    member = aNewMember;
    member.setAssignment(this);

    if (anOldMember != null)
    {
      anOldMember.setAssignment(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setGuide(Guide aGuide)
  {
    boolean wasSet = false;
    Guide existingGuide = guide;
    guide = aGuide;
    if (existingGuide != null && !existingGuide.equals(aGuide))
    {
      existingGuide.removeAssignment(this);
    }
    if (aGuide != null)
    {
      aGuide.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setHotel(Hotel aHotel)
  {
    boolean wasSet = false;
    Hotel existingHotel = hotel;
    hotel = aHotel;
    if (existingHotel != null && !existingHotel.equals(aHotel))
    {
      existingHotel.removeAssignment(this);
    }
    if (aHotel != null)
    {
      aHotel.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setClimbSafe(ClimbSafe aClimbSafe)
  {
    boolean wasSet = false;
    if (aClimbSafe == null)
    {
      return wasSet;
    }

    ClimbSafe existingClimbSafe = climbSafe;
    climbSafe = aClimbSafe;
    if (existingClimbSafe != null && !existingClimbSafe.equals(aClimbSafe))
    {
      existingClimbSafe.removeAssignment(this);
    }
    climbSafe.addAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Member existingMember = member;
    member = null;
    if (existingMember != null)
    {
      existingMember.setAssignment(null);
    }
    if (guide != null)
    {
      Guide placeholderGuide = guide;
      this.guide = null;
      placeholderGuide.removeAssignment(this);
    }
    if (hotel != null)
    {
      Hotel placeholderHotel = hotel;
      this.hotel = null;
      placeholderHotel.removeAssignment(this);
    }
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeAssignment(this);
    }
  }


  /**
   * 
   * @author Joey
   * authorization Code is valid if it is not empty
   */
  // line 49 "../../../../../ClimbSafeStates.ump"
   public boolean isValid(){
    if(this.authorizationCode == "") {
  		this.error = "Invalid authorization code";
    	return false;
    }
    return true;
  }


  /**
   * 
   * @author Joey
   * this method modifies the refund percentage depending on the status of the assignment
   */
  // line 61 "../../../../../ClimbSafeStates.ump"
   public void modRefund(){
    if(this.assignmentStatus.equals(AssignmentStatus.Paid)) {
		  this.refund = 50;
	  }
	  if(this.assignmentStatus.equals(AssignmentStatus.Started)) {
		  this.refund = 10;
	  }
	  if(this.assignmentStatus.equals(AssignmentStatus.Finished)) {
		  this.refund = 0;
	  }
  }


  /**
   * 
   * @author Joey
   * sets the assignment status testing purposes
   */
  // line 77 "../../../../../ClimbSafeStates.ump"
   public void changeAssignmentStatus(AssignmentStatus aAssignmentStatus){
    this.assignmentStatus = aAssignmentStatus;
  }


  public String toString()
  {
    return super.toString() + "["+
            "authorizationCode" + ":" + getAuthorizationCode()+ "," +
            "refund" + ":" + getRefund()+ "," +
            "error" + ":" + getError()+ "," +
            "startWeek" + ":" + getStartWeek()+ "," +
            "endWeek" + ":" + getEndWeek()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guide = "+(getGuide()!=null?Integer.toHexString(System.identityHashCode(getGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "hotel = "+(getHotel()!=null?Integer.toHexString(System.identityHashCode(getHotel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 110 "../../../../../ClimbSafePersistence.ump"
  private static final long serialVersionUID = 11L ;

  
}