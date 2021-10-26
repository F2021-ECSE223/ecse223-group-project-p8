package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;

import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;

public class ClimbSafeFeatureSet2Controller {
	
	/**
	 * TO DO
	 * Check import
	 * add javadoc
	 * ask methods
	 * check getNrWeeks
	 * ADD GUIDE AND HOTEL
	 * update equipment or add?
	 * fix errors
	 */
	private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

 
	public static void registerMember(String email, String password, String name,
     String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,    
     List<String> itemNames, List<Integer> itemQuantities) throws InvalidInputException {
		
		var error = "";
		
		
		//email already in use
		if (Utility.findMember(email)!=null) {
			error = "A member with this email already exists";
			throw new InvalidInputException(error);
		}
		
		//space in email
		if (email.contains(" ")) {
			error = "The email must not contain any spaces";
			throw new InvalidInputException(error);
		}
		
		//rest of email check
		
		//only 1 @ per email
		//need letter after @
		//need .
		//no space
		//cannot start with @
		//right format __@__.__

		//works
		if(Utility.emailInvalidSyntax(email) == true) {
			error="Invalid email";
			throw new InvalidInputException(error);
		}
		
		if(Utility.emailMissingSubstring2(email) == true) {
			error="Invalid email";
			throw new InvalidInputException(error);
		}
		
		if(Utility.missingEmailDeclaration2(email) == true) {
			error="Invalid email";
			throw new InvalidInputException(error);
		}


	   if(Utility.wrongEmailSyntax2(email) == true) {
		   error="Invalid email";
		   throw new InvalidInputException(error);
	   }

	   if(Utility.goodStart(email)==true) {
			error="Invalid email";
			throw new InvalidInputException(error);
	   }

		
		//empty password
		if (password.isEmpty()) {
			error = "The password cannot be empty";
			throw new InvalidInputException(error);
		}
		
		//empty name
		if (name.isEmpty()) {
			error = "The name cannot be empty";
			throw new InvalidInputException(error);
		}
		
		
		//empty emergency contact
		if (emergencyContact.isEmpty()) {
			error = "The emergency contact cannot be empty";
			throw new InvalidInputException(error);
		}
		
		//number of climbing weeks check
		if (nrWeeks<=0) {
			error = "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season";
			throw new InvalidInputException(error);
		}
		
		if (nrWeeks>climbSafe.getNrWeeks()) {
			error = "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season";
			throw new InvalidInputException(error);
		}
		
		//same email as admin
		if (email.equals("admin@nmc.nt")) {
			error = "The email entered is not allowed for members";
			throw new InvalidInputException(error);
		}

		//same email as a guide
		if (Utility.findGuide(email)!=null) {
			error = "A guide with this email already exists";
			throw new InvalidInputException(error);
		}
		
		//requested item not found
		if (!Utility.bookableItemsExists(climbSafe, itemNames)) {
			error = "Requested item not found";
			throw new InvalidInputException(error);
		}
		

		try {
			Member myMember;
			myMember=climbSafe.addMember(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired);
			//add items too!!
			Utility.addItemList(climbSafe, itemNames, itemQuantities, myMember);
			
		}catch (RuntimeException e) {
			error = e.getMessage();
			throw new InvalidInputException(error);
		}

	  }

 
	public static void updateMember(String email, String newPassword, String newName,
		String newEmergencyContact, int newNrWeeks, boolean newGuideRequired,
		boolean newHotelRequired, List<String> newItemNames, List<Integer> newItemQuantities) throws InvalidInputException {

			var error = "";

			//member not found
			if (Utility.findMember(email)==null) {
				error = "Member not found";
				throw new InvalidInputException(error);
			}

			if (newPassword.isEmpty()) {
				error = "The password cannot be empty";
				throw new InvalidInputException(error);
			}
			
			//empty name
			if (newName.isEmpty()) {
				error = "The name cannot be empty";
				throw new InvalidInputException(error);
			}

			//empty emergencE contact
			if (newEmergencyContact.isEmpty()) {
			error = "The emergence contact cannot be empty";
			throw new InvalidInputException(error);
			}

			//number of climbing weeks check
			if (newNrWeeks<=0) {
				error = "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season";
				throw new InvalidInputException(error);
			}
		
			if (newNrWeeks>climbSafe.getNrWeeks()) {
				error = "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season";
				throw new InvalidInputException(error);
			}

			//requested item not found
			if (!Utility.bookableItemsExists(climbSafe, newItemNames)) {
				error = "Requested item not found";
				throw new InvalidInputException(error);
			}

			try {
				Member existingMember = Utility.findMember(email);
				existingMember.setPassword(newPassword);
				existingMember.setName(newName);
				existingMember.setEmergencyContact(newEmergencyContact);
				existingMember.setNrWeeks(newNrWeeks);
				existingMember.setGuideRequired(newGuideRequired);
				existingMember.setHotelRequired(newHotelRequired);
				Utility.resetBookedItems(climbSafe, existingMember);
				Utility.addItemList(climbSafe, newItemNames, newItemQuantities, existingMember);
				
			}catch (RuntimeException e) {
				error = e.getMessage();
				throw new InvalidInputException(error);
			}

				


		
	}

}
