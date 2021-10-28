package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;


public class ClimbSafeFeatureSet3Controller {
	 private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	 
	 /** This method registers a new guide in the climbSafe system
	  * @author Selina Gao
	  * @param email Email of Guide that gets entered and that is registered in the climbSafe system
	  * @param password Password of Guide that gets entered and that is registered in the climbSafe system
	  * @param name First name of Guide that is entered and registered in the climbSafe system
	  * @param emergencyContact Guide enters an emergency contact's phone number and this is registered in the climbSafe system
	  * @throws InvalidInputException for inputs when errors occur 
	  */

  public static void registerGuide(String email, String password, String name,
      String emergencyContact) throws InvalidInputException {
	  
	  var error="";
	  
	  if(email == null) {
		  error="The email entered is null";
		  throw new InvalidInputException(error);
	  }
	  
	  if(password == null) {
		  error="The password entered is null";
		  throw new InvalidInputException(error);
	  }
	  
	  if(name == null) {
		  error="The name entered is null";
		  throw new InvalidInputException(error);
	  }
	  
	  if(emergencyContact == null) {
		  error="The emergency contact is null";
		  throw new InvalidInputException(error);
	  }
	  
	  if(email.isEmpty() || email.isBlank()) {
		  error="Email cannot be empty";
		  throw new InvalidInputException(error);
	  }
	  
	  if(password.isEmpty() || password.isBlank()) {
		  error="Password cannot be empty";
		  throw new InvalidInputException(error);
	  }
	  
	  if(name.isEmpty() || name.isBlank()) {
		  error="Name cannot be empty";
		  throw new InvalidInputException(error);
	  }
	  
	  if(emergencyContact.isEmpty() || emergencyContact.isBlank()) {
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
		  error="Invalid email";
		  throw new InvalidInputException(error);
	  }
	  
	  if(Utility.missingEmailDeclaration(email) == true) {
		  error="Invalid email";
		  throw new InvalidInputException(error);
	  }
	  
	  if(!email.contains(name.toLowerCase())) {
		  error="Invalid email";
		  throw new InvalidInputException(error);
	  }
	 
	 if(Utility.wrongEmailSyntax(email) == true) {
		 error="Invalid email";
		 throw new InvalidInputException(error);
	 }
	 
	 try {
		climbSafe.addGuide(email, password, name, emergencyContact); 
	 }
	 catch (RuntimeException e){
		  error = e.getMessage();
		  throw new InvalidInputException(error);
	  }
  }
  
  /** This method updates the guide's relative information in the climbSafe system
   * @author Selina Gao
   * @param email Email of guide we wish to update information for
   * @param newPassword New password of guide that gets registered in the climbSafe system
   * @param newName New first name of guide that gets registered in the climbSafe system
   * @param newEmergencyContact New phone number for emergency contact of guide that gets registered in the climbSafe system
   * @throws InvalidInputException for inputs where errors occur
   */

  public static void updateGuide(String email, String newPassword, String newName,
      String newEmergencyContact) throws InvalidInputException {
	  
	  var error="";
	  
	  if(newPassword == null) {
		  error="The new password entered is null";
		  throw new InvalidInputException(error);
	  }
	  
	  if(newName == null) {
		  error="The new name entered is null";
		  throw new InvalidInputException(error);
	  }
	  
	  if(newEmergencyContact == null) {
		  error="The new emergency contact is null";
		  throw new InvalidInputException(error);
	  }
	  
	  if(newPassword.isEmpty() || newPassword.isBlank()) {
		  error="Password cannot be empty";
		  throw new InvalidInputException(error);
	  }
	  
	  if(newName.isEmpty() || newName.isBlank()) {
		  error="Name cannot be empty";
		  throw new InvalidInputException(error);
	  }
	  
	  if(newEmergencyContact.isEmpty() || newEmergencyContact.isBlank()) {
		  error="Emergency contact cannot be empty";
		  throw new InvalidInputException(error);
	  }
	  
	  try {
		  Guide existingGuide = Utility.findGuide(email);
		  existingGuide.setPassword(newPassword);
		  existingGuide.setName(newName);
		  existingGuide.setEmergencyContact(newEmergencyContact);
	  }
	  catch (RuntimeException e) {
		  error += e.getMessage();
		  throw new InvalidInputException(error);
	  }
	  
  }

}
