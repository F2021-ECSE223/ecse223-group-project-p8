package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
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
	  List<TOAssignment> resultAssignment = new ArrayList<>();
	  
	  for(Assignment current : temp) {
		  
		  String aMemberEmail = current.getMember().getEmail();
		  String aMemberName = current.getMember().getName();
		  String aGuideEmail = current.getGuide().getEmail();
		  String aGuideName = current.getGuide().getName();
		  String aHotelName = current.getHotel().getName();
		  int aStartWeek = current.getStartWeek();
		  int aEndWeek = current.getEndWeek();
		  int TotalCostForGuide = (ClimbSafeApplication.getClimbSafe()).getPriceOfGuidePerWeek();
		  int TotalCostForEquipment = 5;
		  List<BookedItem> memberItems =  current.getMember().getBookedItems();
		  
		  for(BookedItem currentBooked : memberItems) {
		
			  BookableItem currentItem = currentBooked.getItem();
			  if (currentItem.getClass().equals("EquipmentBundle")) {
				  
			  }
		  }
		  
		// NOT DONE //  NOT DONE //  NOT DONE //  NOT DONE //  NOT DONE / temp1
		  resultAssignment.add(new TOAssignment(aMemberEmail,aMemberName, aGuideEmail, aGuideName, aHotelName, aStartWeek, aEndWeek, TotalCostForGuide, TotalCostForGuide));
		  
		 
	  }
	  
	  return resultAssignment;
  }
  
 

}
