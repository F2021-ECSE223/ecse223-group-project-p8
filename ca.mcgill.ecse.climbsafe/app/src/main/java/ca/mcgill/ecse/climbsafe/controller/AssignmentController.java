package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.javafx.fxml.ClimbsafeFxmlView;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.Assignment.AssignmentStatus;
import ca.mcgill.ecse.climbsafe.persistence.ClimbsafePersistence;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;



public class AssignmentController {

    /** Initiates the assignment instances by assigning assignments to members 
     * @author Ke Yan
     * @author Selina Gao
     * @author Mihail Calitoiu
     * @throws InvalidInputException for any invalid input
     */
    public static void initiateAssignmentProcess() throws InvalidInputException {

        List < Member > forShallow = Utility.climbSafe.getMembers();
        List < Member > unassignedMembers = new ArrayList < Member > ();

        // shallow copy of climbSafe's member array (we don't want to edit the original array)
        for (Member temp: forShallow) {
            unassignedMembers.add(temp);
        }

        for (Guide currentGuide: Utility.climbSafe.getGuides()) {
            // boolean list stores the availability per week of a specific guide (for a week, false -> available, true -> unavailable)
            boolean[] schedule = new boolean[Utility.climbSafe.getNrWeeks()];
            // visit each unassigned member
            for (Member currentMember: unassignedMembers) {
                // already assigned, go to next member
                if (currentMember.getAssignment() != null) {
                    continue;
                };
                // no guide required
                if (currentMember.getGuideRequired() == false) {
                    Assignment assignmentForMember = new Assignment(1, currentMember.getNrWeeks(), currentMember, Utility.climbSafe);
                    assignmentForMember.setGuide(currentGuide);
                    Utility.climbSafe.addAssignment(assignmentForMember);
                    ClimbsafePersistence.save();
                    
                    // guide is required
                } else {
                    int memberStayWeeks = currentMember.getNrWeeks();
                    int startDate, endDate = 0;
                    // code below finds earliest available consecutive weeks (number being the member's stayed weeks)
                    for (int a = 0; a < (Utility.climbSafe.getNrWeeks() - memberStayWeeks + 1); a++) {
                        boolean isRoom = true;
                        for (int b = a; b < memberStayWeeks + a; b++) {
                            if (schedule[b] == true) {
                                isRoom = false;
                                break;
                            }
                        }
                        if (isRoom) {
                            startDate = a + 1;
                            endDate = a + memberStayWeeks;
                            for (int z = a; z < memberStayWeeks + a; z++) {
                                schedule[z] = true;
                            }
                            Assignment assignmentForMember = new Assignment(startDate, endDate, currentMember, Utility.climbSafe);
                            assignmentForMember.setGuide(currentGuide);
                            Utility.climbSafe.addAssignment(assignmentForMember);
                            ClimbsafePersistence.save();
                            break;
                        }
                    }
                }
            }
        }
        // visit each member in climbSafe's member array and if a member's assignment is null, throw error
        for (Member currentMember: Utility.climbSafe.getMembers()) {
            if (currentMember.getAssignment() == null) {
                throw new InvalidInputException("Assignments could not be completed for all members");
            }
        }
    }

    /** Pays for the trip by checking whether authorizationCode is valid
     * @author Maya Ajji
     * @author Joey Liu
     * @param memberEmail The email of the member
     * @param authorizationCode The authorization code
     * @throws InvalidInputException for any invalid input
     */
    public static void payTrip(String memberEmail, String authorizationCode) throws InvalidInputException {
    	
    	if(authorizationCode == null) {
    		throw new InvalidInputException("Authorization code cannot be null");
    	}

        if (Member.getWithEmail(memberEmail) == null) {
            throw new InvalidInputException("Member with email address " + memberEmail + " does not exist");
        }


        Member member = (Member) Member.getWithEmail(memberEmail);
        Assignment assignment = member.getAssignment();
        var error = "";


        try {
            assignment.setAuthorizationCode(authorizationCode);
            assignment.paidForTrip();
            error = assignment.getError();
            if (error != null) {
                throw new InvalidInputException(error);
            }
            ClimbsafePersistence.save();
        } catch (RuntimeException e) {
            error = e.getMessage();
            throw new InvalidInputException(error);
        }
    }

    /** Cancels trip
     * @author Maya Ajji
     * @author Mihail Calitoiu
     * @param memberEmail The email of the member
     * @throws InvalidInputException for any invalid input
     */
    public static void cancelTrip(String memberEmail) throws InvalidInputException {

        Member member = (Member) Member.getWithEmail(memberEmail);

        if (member == null) {
            throw new InvalidInputException("Member with email address " + memberEmail + " does not exist ");
        }
        Assignment assignment = member.getAssignment();
        var error = "";


        try {
            member.getAssignment().cancelTrip();
            error = assignment.getError();
            if (error != null) {
                throw new InvalidInputException(error);
            }
            ClimbsafePersistence.save();
        } catch (RuntimeException e) {
            error = e.getMessage();
            throw new InvalidInputException(error);
        }
    }

    /** Finishes trip
     * @author Selina Gao
     * @author Aigiarn Cheuk
     * @author Joey Liu 
     * @param memberEmail The email of the member
     * @throws InvalidInputException for any invalid input
     */
    public static void finishTrip(String memberEmail) throws InvalidInputException {
        Member member = (Member) Member.getWithEmail(memberEmail);
        if (member == null) {
            throw new InvalidInputException("Member with email address " + memberEmail + " does not exist ");
        }

        Assignment assignment = member.getAssignment();
        var error = "";

        try {
            member.getAssignment().finishTrip();
            error = assignment.getError();
            if (error != null) {
                throw new InvalidInputException(error);
            }
            ClimbsafePersistence.save();
        } catch (RuntimeException e) {
            error = e.getMessage();
            throw new InvalidInputException(error);
        }
    }

    /** Starts the trip
     * @author Aigiarn Cheuk
     * @author Ke Yan
     * @param weekNr The number of weeks
     * @throws InvalidInputException for any invalid input
     */
    public static void startTrips(int weekNr) throws InvalidInputException {
    	
    	if(weekNr <= 0 ) {
    		throw new InvalidInputException("Week number cannot be 0 or negative");
    	}
    	
        List < Assignment > assignmentInSystem = Utility.climbSafe.getAssignments();
        boolean hasError = false;
        var error = "";
        for (Assignment a : assignmentInSystem) {
            if (weekNr == a.getStartWeek()) {
                
                try {
                	a.startTrip();
                	error = a.getError();
                	if(error != null) {
                		hasError = true;
                	}
                    ClimbsafePersistence.save();
                } catch (RuntimeException e) {
                    error = e.getMessage();
                    throw new InvalidInputException(error);
                }
            }
        }
        if(hasError)throw new InvalidInputException(error);
    }
}
