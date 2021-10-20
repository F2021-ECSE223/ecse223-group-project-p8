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
	  catch (RuntimeException e){
		  
		  error += e.getMessage();
		  
		  throw new InvalidInputException(error); 
	  }
  }

  public static void updateGuide(String email, String newPassword, String newName,
      String newEmergencyContact) throws InvalidInputException {}

}
