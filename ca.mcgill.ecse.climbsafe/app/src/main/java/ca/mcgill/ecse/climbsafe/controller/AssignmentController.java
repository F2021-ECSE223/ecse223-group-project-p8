package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;
import java.util.List;

import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.Assignment.AssignmentStatus;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.Member.MemberStatus;

public class AssignmentController {
	
	 public static void initiateAssignmentProcess() throws InvalidInputException {
		
		 
		 /*
		  
			Starting with the first guide registered in the application, go through each member starting with the first
			member registered in the application. 
			
			If the member requested a guide and the guide is available for the number of weeks requested by the member, assign the guide
			to the member at the earliest weeks for which the guide is available.
			
			Then continue with the next member until the guide does not have any availability anymore, at which point the process moves
			on to the next guide (and again starts with the first member that has not yet been assigned).
			
			If a member did not request a guide, the member is always
			assigned to the earliest weeks in the climbing season.
			
		  */
	 
		  
		  
		  List<Member> unassignedMembers = Utility.climbSafe.getMembers();
		  
		  for (Guide currGuide: Utility.climbSafe.getGuides()) {
			  boolean[] schedule = new boolean[Utility.climbSafe.getNrWeeks()];
			  
		
			  
			//  1      2     3    4     5     6    7     8
			 // false true false false false false true true 
			  
			  for (Member currentMember: unassignedMembers) {
				  
				  if (currentMember == null) { continue; };
				  
				  if (currentMember.getGuideRequired() == false) {
					  Assignment assignmentForMember = new Assignment(1, 1 + currentMember.getNrWeeks(), currentMember, Utility.climbSafe);
					  Utility.climbSafe.addAssignment(assignmentForMember);
					  continue;
				  }			
				  
				  int memberStayWeeks = currentMember.getNrWeeks();
				  int startDate, endDate = 0;
				  
				  for (int a = 0; a < (Utility.climbSafe.getNrWeeks()-memberStayWeeks); a++) {
					  
					  boolean isRoom = true;
					  for (int b = a; b<memberStayWeeks; b++) {
						  if (schedule[b] == false) {isRoom = false; break;}

					  }
					  if (isRoom == true) {
						  startDate = a;
						  endDate = a + memberStayWeeks;
						  Assignment assignmentForMember = new Assignment(startDate, endDate, currentMember, Utility.climbSafe);
						  if (Utility.climbSafe.addAssignment(assignmentForMember) == false) {System.out.println("error / cant add assignment");}
						  //currentMember.getAssignment().setGuide(currGuide);
						  currentMember = null;
						  break;
					  }  
				  }

			  }
		  }
	 }
	 
	 public static void payTrip(String memberEmail, String authorizationCode) throws InvalidInputException {
		 var error = "";
		 Member member = (Member) Member.getWithEmail(memberEmail);
		 Assignment assignment = member.getAssignment();
		 if(member.equals(null)) {
			 error = "Member with email address " + memberEmail +" does not exist ";
			 throw new InvalidInputException(error);
		 }
		 if(authorizationCode.equals(null)) {
			 error = "Invalid authorization code";
			 throw new InvalidInputException(error);
		 }
		 if(assignment.getAssignmentStatus().equals(AssignmentStatus.Paid)) {
			 error = "Trip has already been paid for";
			 throw new InvalidInputException(error);
		 }
		 if(assignment.getAssignmentStatus().equals(AssignmentStatus.Started)) {
			 error = "Trip has already been paid for";
			 throw new InvalidInputException(error);
		 }
		 if(assignment.getAssignmentStatus().equals(AssignmentStatus.Cancelled)) {
			 error = "Cannot pay for a trip which has been cancelled";
			 throw new InvalidInputException(error);
		 }
		 if(assignment.getAssignmentStatus().equals(AssignmentStatus.Finished)) {
			 error = "Cannot pay for a trip which has finished";
			 throw new InvalidInputException(error);
		 }
		 if(member.getMemberStatus().equals(MemberStatus.Banned)) {
			 error = "Cannot pay for the trip due to a ban";
			 throw new InvalidInputException(error);
		 }
		 
		 try {
			 member.getAssignment().paidForTrip();
		 }catch(RuntimeException e) {
			 error = e.getMessage();
			 throw new InvalidInputException(error);
		 }
	 }
	 
	 public static void cancelTrip(String memberEmail) throws InvalidInputException {
		 var error = "";
		 Member member = (Member) Member.getWithEmail(memberEmail);
		 Assignment assignment = member.getAssignment();
		 if(member.equals(null)) {
			 error = "Member with email address " + memberEmail +" does not exist ";
			 throw new InvalidInputException(error);
		 }
		 if(member.getMemberStatus().equals(MemberStatus.Banned)) {
			 error = "Cannot cancel the trip due to a ban";
			 throw new InvalidInputException(error);
		 }
		 if(assignment.getAssignmentStatus().equals(AssignmentStatus.Finished)) {
			 error = "Cannot pay for a trip which has finished";
			 throw new InvalidInputException(error);
		 }
		 try {
			 member.getAssignment().cancelTrip();
		 }catch(RuntimeException e) {
			 error = e.getMessage();
			 throw new InvalidInputException(error);
		 }
	 }
	 
	 public static void finishTrip(String memberEmail) throws InvalidInputException {
		 var error = "";
		 Member member = (Member) Member.getWithEmail(memberEmail);
		 Assignment assignment = member.getAssignment();
		 if(assignment.getAssignmentStatus().equals(AssignmentStatus.Assigned)) {
			 error = "Cannot finish a trip which has not started";
			 throw new InvalidInputException(error);
		 }
		 if(assignment.getAssignmentStatus().equals(AssignmentStatus.Paid)) {
			 error = "Cannot finish a trip which has not started";
			 throw new InvalidInputException(error);
		 }
		 if(assignment.getAssignmentStatus().equals(AssignmentStatus.Cancelled)) {
			 error = "Cannot finish a trip which has been cancelled";
			 throw new InvalidInputException(error);
		 }
		 if(member.getMemberStatus().equals(MemberStatus.Banned)) {
			 error = "Cannot finish the trip due to a ban";
			 throw new InvalidInputException(error);
		 }
		 if(member.equals(null)) {
			 error = "Member with email address " + memberEmail +" does not exist ";
			 throw new InvalidInputException(error);
		 }
		 try {
			 member.getAssignment().finishTrip();
		 }catch(RuntimeException e) {
			 error = e.getMessage();
			 throw new InvalidInputException(error);
		 }
	 }
	 
	 public static void startTrips(int weekNr) throws InvalidInputException {
		 var error = "";
		 List<Assignment> assignmentInSystem = Utility.climbSafe.getAssignments();
		 for(Assignment a : assignmentInSystem) {
			 Member member = a.getMember();
		 	if(member.getMemberStatus().equals(MemberStatus.Banned)) {
				error = "Cannot pay for the trip due to a ban";
			 	throw new InvalidInputException(error);
		 	}
		 	if(a.getAssignmentStatus().equals(AssignmentStatus.Cancelled)) {
				error = "Cannot start a trip which has been cancelled";
			 	throw new InvalidInputException(error);
		 	}
		 	if(a.getAssignmentStatus().equals(AssignmentStatus.Finished)) {
				error = "Cannot start a trip which has finished";
			 	throw new InvalidInputException(error);
		 	}
		 	
		 	try {
		 		a.startTrip();
		 	}catch(RuntimeException e){
		 		
		 	}
		 }
	 }
}
