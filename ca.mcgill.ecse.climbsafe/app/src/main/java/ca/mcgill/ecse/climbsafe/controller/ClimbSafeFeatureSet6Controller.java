package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;
import java.util.Map;

public class ClimbSafeFeatureSet6Controller {

  public static void deleteEquipment(String name) throws InvalidInputException {
	  
	
	  BookableItem equipmentToDelete = (ClimbSafeApplication.getClimbSafe()).getWithName(name);
	 
	  if(equipmentToDelete!=null) {
		 
		  equipmentToDelete.delete();
	  }
  }

  // this method does not need to be implemented by a team with five team members
  public static void deleteEquipmentBundle(String name) {
	  BookableItem equipmentBundleToDelete = (ClimbSafeApplication.getClimbSafe()).getWithName(name);
		 
	  if(equipmentBundleToDelete!=null) {
		 
		  equipmentBundleToDelete.delete();
	  }
  }

  public static List<TOAssignment> getAssignments() {
	  
	  // NOT DONE //  NOT DONE //  NOT DONE //  NOT DONE //  NOT DONE
	  
	  List<Assignment> temp = (ClimbSafeApplication.getClimbSafe()).getAssignments();
	  List<TOAssignment> resultAssignment;
	  for(Assignment current : resultAssignment) {
		  String aMemberEmail = current.getMember().getEmail();
		  String aMemberName = current.getMember().getName();
		  String aGuideEmail = current.getGuide().getEmail();
		  String aGuideName = current.getGuide().getName();
		  String aHotelName = current.getHotel().getName();
		  int aStartWeek = current.getStartWeek();
		  int aEndWeek = current.getEndWeek();
		  int aTotalCostForGuide = (ClimbSafeApplication.getClimbSafe()).getPriceOfGuidePerWeek();
		  //int aTotalCostForEquipment = ;
	
		  //  NOT DONE //  NOT DONE //  NOT DONE //  NOT DONE //  NOT DONE //  NOT DONE //  NOT DONE
		  
		  
		  resultAssignment.add(new ToAssignment(aMemberEmail,aMemberName, aGuideEmail, aGuideName, aHotelName, aStartWeek, aEndWeek, aTotalCostForGuide, aTotalCostForEquipment));
		  
		  var name = equipment.get("name");
		  var weight = equipment.get("weight");
		  var pricePerWeek = equipment.get("pricePerWeek");
		  new Equipment(name, Integer.parseInt(weight), Integer.parseInt(pricePerWeek), climbSafe);
	  }
	  
	  
  }

}
