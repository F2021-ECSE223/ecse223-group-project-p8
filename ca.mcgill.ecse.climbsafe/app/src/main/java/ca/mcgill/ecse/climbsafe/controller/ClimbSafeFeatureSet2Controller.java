package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

public class ClimbSafeFeatureSet2Controller {
	
	//check if it should it be static
	//use this as...
	private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

  public static void registerMember(String email, String password, String name,
      String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,
      List<String> itemNames, List<Integer> itemQuantities) throws InvalidInputException {
	  
	  var error = "";
	  
	  try {
		  climbSafe.addMember(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired);
		  //what to do with list?
	  } catch (InvalidInputException e) {
		  //is this the right error? do I throw?
		  //throw new InvalidInputException(e.getMessage());
		  error += e.getMessage();
	  }
	  
	  try {
		  
	  } catch () {
		  
	  }
  }

  public static void updateMember(String email, String newPassword, String newName,
      String newEmergencyContact, int newNrWeeks, boolean newGuideRequired,
      boolean newHotelRequired, List<String> newItemNames, List<Integer> newItemQuantities)
      throws InvalidInputException {}

}
