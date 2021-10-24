package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;

public class ClimbSafeFeatureSet2Controller {
	
	/**
	 * TO DO
	 * Check import
	 * add javadoc
	 * ask methods
	 * check getNrWeeks
	 */
	private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

<<<<<<< HEAD
  public static void registerMember(String email, String password, String name,
      String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,
      List<String> itemNames, List<Integer> itemQuantities) throws InvalidInputException {
	  
	  var error = "";
	  
	  try {
		  climbSafe.addMember(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired);
		  //what to do with list?
	  } catch (RuntimeException e) {
		  //is this the right error?
		  //throw new InvalidInputException(e.getMessage());
		  error += e.getMessage();
=======
 
	public static void registerMember(String email, String password, String name,
     String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,    
     List<String> itemNames, List<Integer> itemQuantities) throws InvalidInputException {
		
		var error = "";
		
		//empty email SHOULD I CHECK?
		if (name.isEmpty()) {
			error = "The email cannot be empty";
			throw new InvalidInputException(error);
		}
		
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
		
		if (nrWeeks>=climbSafe.getNrWeeks()) {
			error = "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season";
			throw new InvalidInputException(error);
		}
		
		//same email as admin
		
		//same email as a guide
		
		//requested item not found
		
		try {
			
		}catch (RuntimeException e) {
			error = e.getMessage();
			throw new InvalidInputException(error);
		}
>>>>>>> branch 'main' of https://github.com/F2021-ECSE223/ecse223-group-project-p8
	  }

 
	public static void updateMember(String email, String newPassword, String newName,
			String newEmergencyContact, int newNrWeeks, boolean newGuideRequired,
			boolean newHotelRequired, List<String> newItemNames, List<Integer> newItemQuantities) throws InvalidInputException {
		
	}

}
