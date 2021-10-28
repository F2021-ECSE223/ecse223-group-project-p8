package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;

import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;

public class ClimbSafeFeatureSet2Controller {
	private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

	/** 
	 * Adds a new member in the climbSafe system
	 * @author Ke Yan
	 * @param email Email of the new member
	 * @param password password of the new member
	 * @param name name of the new member
	 * @param emergencyContact phone number for the emergency contact of the new member
	 * @param nrWeeks nuber of weeks Weeks of climbing of the new member
	 * @param guideRequired whether or not the new member desires a guide
	 * @param hotelRequired whether or not the new member desires a hotel stay
	 * @param itemNames list of item the member desires to rent
	 * @param itemQuantities list of quantities associated to each item the member desires to rent
	 * @throws InvalidInputException for any invalid input
	 */
	public static void registerMember(String email, String password, String name,
     String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,    
     List<String> itemNames, List<Integer> itemQuantities) throws InvalidInputException {
		
		var error = "";
		
		//empty email
		if (email.isEmpty()) {
			error = "The email cannot be empty";
			throw new InvalidInputException(error);
		}

		//email already in use
		if (Utility.findMember(email)!=null) {
			error = "A member with this email already exists";
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
		
		//space in email
		if (email.contains(" ")) {
			error = "The email must not contain any spaces";
			throw new InvalidInputException(error);
		}

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

		
		//requested item not found
		if (!Utility.bookableItemsExists(climbSafe, itemNames)) {
			error = "Requested item not found";
			throw new InvalidInputException(error);
		}
		//empty check
		if (itemNames==null) {
			error = "Inputs canot be null";
			throw new InvalidInputException(error);
		}

		if (itemQuantities==null) {
			error = "Inputs canot be null";
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

	  /** 
	   * Updates an existing memeber in the climbSafe system
	   * @author Ke Yan
	   * @param email Email of the existing member we wish to update
	   * @param newPassword new password
	   * @param newName new name
	   * @param newEmergencyContact new emergency contact phone number
	   * @param newNrWeeks new number of weeks of climbing
	   * @param newGuideRequired new preference for guide (desired or not)
	   * @param newHotelRequired new preference for hotel stay (desired or not)
	   * @param newItemNames new list of items the member wishes to rent
	   * @param newItemQuantities new quantities associated to the list of items the member wishes to rent
	   * @throws InvalidInputException for any invalid input
	   */
	public static void updateMember(String email, String newPassword, String newName,
		String newEmergencyContact, int newNrWeeks, boolean newGuideRequired,
		boolean newHotelRequired, List<String> newItemNames, List<Integer> newItemQuantities) throws InvalidInputException {

			var error = "";

			//empty email
			if (email.isEmpty()) {
				error = "The email cannot be empty";
				throw new InvalidInputException(error);
			}

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
			error = "The emergency contact cannot be empty";
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

			//empty check
			if (newItemNames==null) {
				error = "Inputs canot be null";
				throw new InvalidInputException(error);
			}

			if (newItemQuantities==null) {
				error = "Inputs canot be null";
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
