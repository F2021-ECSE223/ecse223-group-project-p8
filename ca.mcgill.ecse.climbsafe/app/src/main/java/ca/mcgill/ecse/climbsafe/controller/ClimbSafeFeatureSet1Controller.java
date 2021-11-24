package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;

import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.persistence.ClimbsafePersistence;



public class ClimbSafeFeatureSet1Controller {
	
  /**
   * @author Joey
   * @param startDate is the starting date of the climbing season specified by the admin 
   * @param nrWeeks is the number of weeks in the climbing season
   * @param priceOfGuidePerWeek is the weekly price of hiring a guide
   * @throws InvalidInputException
   */
  public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek)
      throws InvalidInputException {
	  
	  var error = "";
	  if(nrWeeks <= 0){
		  error = "The number of climbing weeks must be greater than or equal to zero";
		  throw new InvalidInputException(error);
	  }
	  if(priceOfGuidePerWeek <= 0){
		  error = "The price of guide per week must be greater than or equal to zero";
		  throw new InvalidInputException(error);
	  }
	  
	  try {
		  Utility.climbSafe.setNrWeeks(nrWeeks);
		  Utility.climbSafe.setStartDate(startDate);
		  Utility.climbSafe.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
		  ClimbsafePersistence.save();
	  }catch (RuntimeException e){
		  error = e.getMessage();
		  throw new InvalidInputException(error);
	  }
  }
  
  /**
   * @author Joey
   * @param email is the string which identifies the member to be deleted since each member has a unique email
   */
  public static void deleteMember(String email) {
	  //find the member with specified email
	  Member member = Utility.findMember(email);
	  //if the member exists
	  if(member != null) {
		  //delete the member
		  member.delete();
		  ClimbsafePersistence.save();
	  }
  }

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
