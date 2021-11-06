package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;
import java.util.List;

import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;

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
	 
		  
		  var climbInstance = Utility.climbSafe;
		  List<Member> unassignedMembers = climbInstance.getMembers();
		  
		  for (Guide currGuide: climbInstance.getGuides()) {
			  Boolean[] schedule = new Boolean[climbInstance.getNrWeeks()];
			  for (Member currentMember: unassignedMembers) {
				  if (currentMember == null) {continue;};
				  if (currentMember.getGuideRequired() == false) {
					  Assignment assignmentForMember = new Assignment(1, currentMember.getNrWeeks(), currentMember, climbInstance);
					  if (climbInstance.addAssignment(assignmentForMember) == false) {System.out.println("error / cant add assignment");}
				  }			
				  
				  int memberStayWeeks = currentMember.getNrWeeks();
				  int startDate, endDate = 0;
				  for (int a = 0; a < (climbInstance.getNrWeeks()-memberStayWeeks); a++) {
					  
					  boolean isRoom = true;
					  for (int b = a; b<memberStayWeeks; b++) {
						  if (schedule[b] == false) {isRoom = false;}

					  }
					  if (isRoom == true) {
						  startDate = a;
						  endDate = a + memberStayWeeks;
						  Assignment assignmentForMember = new Assignment(startDate, endDate, currentMember, climbInstance);
						  if (climbInstance.addAssignment(assignmentForMember) == false) {System.out.println("error / cant add assignment");}
						  currentMember.getAssignment().setGuide(currGuide);
						  currentMember = null;
						  break;
					  }  
				  }

			  }
		  }
	 }
	 
	 public static void confirmPayment(String email, String authorizationCode) throws InvalidInputException {
		 
	 }
	 
	 public static void cancelTrip(String email) throws InvalidInputException {
		 
	 }
	 
	 public static void finishTrip(String email) throws InvalidInputException {
		 
	 }
	 
	 public static void startTrip(int weekNr) throws InvalidInputException {
		 
	 }
}
