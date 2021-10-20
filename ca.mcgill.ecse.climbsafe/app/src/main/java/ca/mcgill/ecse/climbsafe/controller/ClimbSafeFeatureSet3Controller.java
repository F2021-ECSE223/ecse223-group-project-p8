package ca.mcgill.ecse.climbsafe.controller;

public class ClimbSafeFeatureSet3Controller {

  public static void registerGuide(String email, String password, String name,
      String emergencyContact, int priceOfGuidePerWeek) 
    		  throws InvalidInputException {
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  
	  var error = "";
	  try {
		  climbSafe.addGuide(email, password, name, emergencyContact, priceOfGuidePerWeek);
	  }
	  catch (InvalidInputException){
		  
		  error += e.getMessage();
	  }
  }

  public static void updateGuide(String email, String newPassword, String newName,
      String newEmergencyContact) throws InvalidInputException {}

}
