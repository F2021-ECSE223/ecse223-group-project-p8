package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;

public class ClimbSafeFeatureSet3Controller {
	 private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	 
	 
	 // idk how to do javadoc 
	 /**
	  * 
	  * @param email Email of Guide that gets entered and that is registered in the climbSafe system
	  * @param password Password of Guide that gets entered and that is registered in the climbSafe system
	  * @param name First name of Guide that is entered and registered in the climbSafe system
	  * @param emergencyContact Guide enters an emergency contact's phone number and this is registered in the climbSafe system
	  * @throws InvalidInputException for inputs when errors occur 
	  */

  public static void registerGuide(String email, String password, String name,
      String emergencyContact) throws InvalidInputException {
	 
	  var error = "";
	  
	  if(email.isEmpty()) {
		  error="Email cannot be empty";
		  throw new InvalidInputException(error);
	  }
	  
	  if(password.isEmpty()) {
		  error="Password cannot be empty";
		  throw new InvalidInputException(error);
	  }
	  
	  if(name.isEmpty()) {
		  error="Name cannot be empty";
		  throw new InvalidInputException(error);
	  }
	  
	  if(emergencyContact.isEmpty()) {
		  error="Emergency contact cannot be empty";
		  throw new InvalidInputException(error);
	  }
	  
	  String adminEmail="admin@nmc.nt";
	  if(email.equals(adminEmail)) {
		  error="Email cannot be admin@nmc.nt";
		  throw new InvalidInputException(error);
	  }
	 
	  Guide existingGuide = Utility.findGuide(email);
	  if(existingGuide != null) {
		  error="Email already linked to a guide account";
		  throw new InvalidInputException(error);
	  }
	  
	  Member existingMember = Utility.findMember(email);
	  if(existingMember != null) {
		  error="Email already linked to a member account";
		  throw new InvalidInputException(error);
	  }
	  
	  if(email.contains(" ")) {
		  error="Email must not contain any spaces";
		  throw new InvalidInputException(error);
	  }
	  
	  if(Utility.emailInvalidSyntax(email) == true) {
		  error="Invalid email";
		  throw new InvalidInputException(error);
	  }
	  
	  if(Utility.emailMissingSubstring(email) == true) {
		  error="Invalid Email";
		  throw new InvalidInputException(error);
	  }
	  
	  if(Utility.missingEmailDeclaration(email) == true) {
		  error="Invalid Email";
		  throw new InvalidInputException(error);
	  }
	  
	  
	  
	  
	  
	  
	  
	  try {
		  climbSafe.addGuide(email, password, name, emergencyContact); 
	  }
	  catch (RuntimeException e){
		  error += e.getMessage();
		  throw new InvalidInputException(error); 
	  }
  }

  public static void updateGuide(String email, String newPassword, String newName,
      String newEmergencyContact) throws InvalidInputException {}

}
