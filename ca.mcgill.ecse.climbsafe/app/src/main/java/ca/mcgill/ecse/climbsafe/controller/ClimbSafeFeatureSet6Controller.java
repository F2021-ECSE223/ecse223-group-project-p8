package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;

public class ClimbSafeFeatureSet6Controller {

  public static void deleteEquipment(String name) throws InvalidInputException {
	 
	Equipment equipment = null;
	List<Equipment> equipmentList = (ClimbSafeApplication.getClimbSafe()).getEquipment();
	
	for (Equipment temp : equipmentList) {
		if (temp.getName().equals(name)) {
			equipment = temp;	
			break;
		}
	}
	
	  if(equipment!=null) {
		 
		  equipment.delete();
	  }
  }

  // this method  does not need to be implemented by a team with five team members
  public static void deleteEquipmentBundle(String name) {
	  EquipmentBundle equipmentBundle = null;
		List<EquipmentBundle> equipmentBundleList = (ClimbSafeApplication.getClimbSafe()).getBundles();
		
		for (EquipmentBundle temp : equipmentBundleList) {
			if (temp.getName().equals(name)) {
				equipmentBundle = temp;	
				break;
			}
		}
		
		  if(equipmentBundle!=null) {
			 
			  equipmentBundle.delete();
		  }
  }

  public static List<TOAssignment> getAssignments() {
	  
	  // NOT DONE //  NOT DONE //  NOT DONE //  NOT DONE //  NOT DONE
	  
	  List<Assignment> temp = (ClimbSafeApplication.getClimbSafe()).getAssignments();
	  List<TOAssignment> resultAssignment;
	  for(TOAssignment current : resultAssignment) {
		  String aMemberEmail = current.getEmail();
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
