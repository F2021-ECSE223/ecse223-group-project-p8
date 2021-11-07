package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.Assignment.AssignmentStatus;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;


public class AssignmentController {

    public static void initiateAssignmentProcess() throws InvalidInputException {

        List < Member > forShallow = Utility.climbSafe.getMembers();
        List < Member > unassignedMembers = new ArrayList < Member > ();
        for (Member temp: forShallow) {
            unassignedMembers.add(temp);
        }
        List < Assignment > assTemp = new ArrayList < Assignment > ();


        for (Guide currGuide: Utility.climbSafe.getGuides()) {

            boolean[] schedule = new boolean[Utility.climbSafe.getNrWeeks()];

            for (Member currentMember: unassignedMembers) {

                if (currentMember.getName().equals("del")) {
                    continue;
                };

                if (currentMember.getGuideRequired() == false) {
                    Assignment assignmentForMember = new Assignment(1, currentMember.getNrWeeks(), currentMember, Utility.climbSafe);
                    assignmentForMember.setGuide(currGuide);
                    Utility.climbSafe.addAssignment(assignmentForMember);
                    assTemp.add(assignmentForMember);
                    currentMember.setName("del");

                } else {

                    int memberStayWeeks = currentMember.getNrWeeks();
                    int startDate, endDate = 0;

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
                            assignmentForMember.setGuide(currGuide);
                            Utility.climbSafe.addAssignment(assignmentForMember);
                            assTemp.add(assignmentForMember);
                            currentMember.setName("del");
                            break;
                        }
                    }
                }
            }
        }
        for (Member currentMember: Utility.climbSafe.getMembers()) {
            if (currentMember.getAssignment() == null) {
                throw new InvalidInputException("Assignments could not be completed for all members");
            }
        }
    }

    public static void payTrip(String memberEmail, String authorizationCode) throws InvalidInputException {
        var error = "";
        Member member = (Member) Member.getWithEmail(memberEmail);
        
        if (member == null) {
            error = "Member with email address " + memberEmail + " does not exist ";
            throw new InvalidInputException(error);
        }
        Assignment assignment = member.getAssignment();
        if (authorizationCode.equals(null)) {
            error = "Invalid authorization code";
            throw new InvalidInputException(error);
        }
        if (assignment.getAssignmentStatus().equals(AssignmentStatus.Paid)) {
            error = "Trip has already been paid for";
            throw new InvalidInputException(error);
        }
        if (assignment.getAssignmentStatus().equals(AssignmentStatus.Started)) {
            error = "Trip has already been paid for";
            throw new InvalidInputException(error);
        }
        if (assignment.getAssignmentStatus().equals(AssignmentStatus.Cancelled)) {
            error = "Cannot pay for a trip which has been cancelled";
            throw new InvalidInputException(error);
        }
        if (assignment.getAssignmentStatus().equals(AssignmentStatus.Finished)) {
            error = "Cannot pay for a trip which has finished";
            throw new InvalidInputException(error);
        }
        if (assignment.getAssignmentStatus().equals(AssignmentStatus.Banned)) {
            error = "Cannot pay for the trip due to a ban";
            throw new InvalidInputException(error);
        }

        try {
            member.getAssignment().paidForTrip();
        } catch (RuntimeException e) {
            error = e.getMessage();
            throw new InvalidInputException(error);
        }
    }

    public static void cancelTrip(String memberEmail) throws InvalidInputException {
        var error = "";
        Member member = (Member) Member.getWithEmail(memberEmail);
        
        if (member == null) {
            error = "Member with email address " + memberEmail + " does not exist";
            throw new InvalidInputException(error);
        }
        Assignment assignment = member.getAssignment();
        if (assignment.getAssignmentStatus().equals(AssignmentStatus.Banned)) {
            error = "Cannot cancel the trip due to a ban";
            throw new InvalidInputException(error);
        }
        if (assignment.getAssignmentStatus().equals(AssignmentStatus.Finished)) {
            error = "Cannot cancel a trip which has finished";
            throw new InvalidInputException(error);
        }
        try {
            member.getAssignment().cancelTrip();
        } catch (RuntimeException e) {
            error = e.getMessage();
            throw new InvalidInputException(error);
        }
    }

    public static void finishTrip(String memberEmail) throws InvalidInputException {
        var error = "";
        Member member = (Member) Member.getWithEmail(memberEmail);
        if (member == null) {
            error = "Member with email address " + memberEmail + " does not exist";
            throw new InvalidInputException(error);
        }
        Assignment assignment = member.getAssignment();
        if (assignment.getAssignmentStatus().equals(AssignmentStatus.Assigned)) {
            error = "Cannot finish a trip which has not started";
            throw new InvalidInputException(error);
        }
        if (assignment.getAssignmentStatus().equals(AssignmentStatus.Paid)) {
            error = "Cannot finish a trip which has not started";
            throw new InvalidInputException(error);
        }
        if (assignment.getAssignmentStatus().equals(AssignmentStatus.Cancelled)) {
            error = "Cannot finish a trip which has been cancelled";
            throw new InvalidInputException(error);
        }
        if (assignment.getAssignmentStatus().equals(AssignmentStatus.Banned)) {
            error = "Cannot finish the trip due to a ban";
            throw new InvalidInputException(error);
        }
        try {
            member.getAssignment().finishTrip();
        } catch (RuntimeException e) {
            error = e.getMessage();
            throw new InvalidInputException(error);
        }
    }

    public static void startTrips(int weekNr) throws InvalidInputException {
        var error = "";
        List < Assignment > assignmentInSystem = Utility.climbSafe.getAssignments();
        for (Assignment a: assignmentInSystem) {
            Member member = a.getMember();
            if (member.getAssignment().getAssignmentStatus().equals(AssignmentStatus.Banned)) {
                error = "Cannot pay for the trip due to a ban";
                throw new InvalidInputException(error);
            }
            if (a.getAssignmentStatus().equals(AssignmentStatus.Cancelled)) {
                error = "Cannot start a trip which has been cancelled";
                throw new InvalidInputException(error);
            }
            if (a.getAssignmentStatus().equals(AssignmentStatus.Finished)) {
                error = "Cannot start a trip which has finished";
                throw new InvalidInputException(error);
            }

            try {
                a.startTrip();
            } catch (RuntimeException e) {
            	error += e.getMessage();
            	throw new InvalidInputException(error);
            }
        }
    }
}
