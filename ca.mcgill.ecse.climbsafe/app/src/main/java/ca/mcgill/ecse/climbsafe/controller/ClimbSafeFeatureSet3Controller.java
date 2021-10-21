package ca.mcgill.ecse.climbsafe.controller;

public class ClimbSafeFeatureSet3Controller {
	 private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

  public static void registerGuide(String email, String password, String name,
      String emergencyContact) throws InvalidInputException {
	 
	  
	  var error = "";
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
