package ca.mcgill.ecse.climbsafe.controller;

public class ClimbSafeFeatureSet3Controller {

  public static void registerGuide(String email, String password, String name,
      String emergencyContact, int priceOfGuidePerWeek) 
    		  throws InvalidInputException {
	  ClimbSafe climbsafe = ClimbSafeApplication.getClimbSafe();
	  
	  var error = "";
	  try {
		  
	  }
  }

  public static void updateGuide(String email, String newPassword, String newName,
      String newEmergencyContact) throws InvalidInputException {}

}
