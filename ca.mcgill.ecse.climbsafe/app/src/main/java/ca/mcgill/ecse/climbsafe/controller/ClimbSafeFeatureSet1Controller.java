package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;

public class ClimbSafeFeatureSet1Controller {

  public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek)
      throws InvalidInputException {}

  public static void deleteMember(String email) {}

  public static void deleteGuide(String email) {}

  // this method needs to be implemented only by teams with seven team members
  public static void deleteHotel(String name) {}


  /**
   * @author Joey
   * @param email is the string which identifies the guide to be deleted since each guide has a unique email
   */
  public static void deleteGuide(String email) {
	  //find the guide with specified email
	  Guide guide = Utility.findGuide(email);
	  //if the guide exists
	  if(guide != null) {
		  //delete the guide
		  guide.delete();
		  ClimbsafePersistence.save();
	  }
	  
  }
  
  public static void deleteHotel(String name) {
	  
  }

}
