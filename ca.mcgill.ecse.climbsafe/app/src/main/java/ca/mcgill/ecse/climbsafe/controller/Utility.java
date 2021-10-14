package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;

public class Utility {
	
	public static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	
	/*
	 * @JoeyLiu
	 * @param email is the unique email of the member which is wanted
	 * @return the member with the specified email
	 */
	public static Member findMember(String email){
		Member foundMember = null;
		//iterate through the list of members in the application
		for(var member: climbSafe.getMembers()) {
			//if the email matches with the input email
			if(member.getEmail().equals(email)) {
				//we have found the member
				foundMember = member;
				break;
			}
		}
		return foundMember;
	}
	
	/*
	 * @JoeyLiu
	 * @param email is the unique email of the guide which is wanted
	 * @return the guide with the specified email
	 */
	public static Guide findGuide(String email){
		Guide foundGuide = null;
		//iterate through the list of guides in the application
		for(var guide: climbSafe.getGuides()) {
			//if the email matches with the input email
			if(guide.getEmail().equals(email)) {
				//we have found the guide
				foundGuide = guide;
				break;
			}
		}
		return foundGuide;
	}
	
	
	
	/*
	 * I CREATED AN INSTANCE OF THE CLIMB SAFE APP SO EVERY CONTROLLER CAN ACCESS IT FROM HERE
	 * (CALL for e.g Utility.climbSafe.setNrWeeks())
	 * ADD HELPER METHODS IN THIS CLASS IF U WANT TO
	 * I ADDED THE FINDGUIDE AND THE FINDMEMBER BECAUSE I NEEDED THEM IN THE CONTROLLER
	 * U CAN USE THEM IF U WANT TO
	 */

}
