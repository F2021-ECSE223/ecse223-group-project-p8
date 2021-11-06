/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 1 "ClimbSafeStates.ump"
public class Assignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment State Machines
  public enum AssignmentStatus { Assigned, Paid, Started, Cancelled, Finished, Banned }
  private AssignmentStatus assignmentStatus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Assignment()
  {
    setAssignmentStatus(AssignmentStatus.Assigned);
  }

  //------------------------
  // INTERFACE
  //------------------------

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
        setAssignmentStatus(AssignmentStatus.Paid);
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
        setAssignmentStatus(AssignmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Paid:
        setAssignmentStatus(AssignmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Started:
        setAssignmentStatus(AssignmentStatus.Cancelled);
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
      case Started:
        setAssignmentStatus(AssignmentStatus.Finished);
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

  public void delete()
  {}


  /**
   * in which state do we include the ban is it in the assigned state when we cancel the trip? Or should we add a new state?
   */
  // line 24 "ClimbSafeStates.ump"
   private int getRefund(){
    int refund = 100;
	  if(this.assignmentStatus.equals(AssignmentStatus.Paid)) {
		  refund = 50;
	  }
	  if(this.assignmentStatus.equals(AssignmentStatus.Started)) {
		  refund = 10;
	  }
	  if(this.assignmentStatus.equals(AssignmentStatus.Finished)) {
		  refund = 0;
	  }
	  
	  return refund;
  }

}