package ca.mcgill.ecse.climbsafe.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Member;

//@Mihail Calitoiu

public class ClimbSafeFeatureSet6Controller {

    public static void deleteEquipment(String name) throws InvalidInputException {

        Equipment equipment = null;
        List < Equipment > equipmentList = (ClimbSafeApplication.getClimbSafe()).getEquipment();

        for (Equipment temp: equipmentList) {
            if (temp.getName().equals(name)) {
                equipment = temp;
                break;
            }
        }

        if (equipment != null) {
            equipment.delete();
        }
    }

    public static void deleteEquipmentBundle(String name) {
        
    	EquipmentBundle equipmentBundle = null;
        List < EquipmentBundle > equipmentBundleList = (ClimbSafeApplication.getClimbSafe()).getBundles();

        for (EquipmentBundle temp: equipmentBundleList) {
        	if (temp.getName().equals(name)) {
        		equipmentBundle = temp;
                break; 
            }
        }

        if (equipmentBundle != null) {
            equipmentBundle.delete();
        }
        
    }

    public static List < TOAssignment > getAssignments() {


        List < Assignment > temp = (ClimbSafeApplication.getClimbSafe()).getAssignments();
        List < TOAssignment > assignment2ToAssignment = new ArrayList <>();

        for (Assignment current: temp) {
        	String aMemberEmail, aMemberName, aGuideEmail, aGuideName, aHotelName;
        	aMemberEmail = aMemberName = aGuideEmail = aGuideName = aHotelName = null;
        	int aStartWeek, aEndWeek, stayedWeeks, TotalCostForGuide, TotalCostForEquipment;
        	aStartWeek = aEndWeek = stayedWeeks = TotalCostForGuide = TotalCostForEquipment = 0;
	    	aStartWeek = current.getStartWeek();
	        aEndWeek = current.getEndWeek();
        	stayedWeeks = (aEndWeek - aStartWeek)+1;
        	aMemberEmail = current.getMember().getEmail();
            aMemberName = current.getMember().getName();
            Boolean hasGuide = current.getMember().isGuideRequired();
            Boolean hasHotel = current.getMember().isHotelRequired();
            if (hasGuide) {
			  aGuideEmail = current.getGuide().getEmail();
			  aGuideName = current.getGuide().getName();
			  TotalCostForGuide = (ClimbSafeApplication.getClimbSafe()).getPriceOfGuidePerWeek() * stayedWeeks ;
            }
            if (hasHotel) {
            	aHotelName = current.getHotel().getName();
            }
            

            List < BookedItem > membersItems = current.getMember().getBookedItems();

            for (BookedItem currentBooked: membersItems) {

                if (currentBooked.getItem() instanceof EquipmentBundle) {
                	System.out.println("Type: "  + currentBooked.getItem().getClass()+ "!");
                	EquipmentBundle specificItem = (EquipmentBundle) currentBooked.getItem();
                    List < BundleItem > storedEquipment = specificItem.getBundleItems();
                    int costForBundle = 0;
                    
                    for (BundleItem currentEqi: storedEquipment) {
                        
                    	int price = currentEqi.getEquipment().getPricePerWeek() * stayedWeeks* currentBooked.getQuantity();
                    	costForBundle += price;
                    	
                    }
                    if (hasGuide) {
                    	costForBundle = costForBundle * ((100 - specificItem.getDiscount()) / 100);
                    } 
                   
                    TotalCostForEquipment += costForBundle;

                } else if (currentBooked.getItem() instanceof Equipment) {
                	
                    Equipment specificItem = (Equipment) currentBooked.getItem();
                    TotalCostForEquipment += specificItem.getPricePerWeek() * stayedWeeks * currentBooked.getQuantity();
                    
                }
            }
	        
            assignment2ToAssignment.add(new TOAssignment(aMemberEmail, aMemberName, aGuideEmail, aGuideName, aHotelName, aStartWeek, aEndWeek, TotalCostForGuide,  TotalCostForEquipment));
	        
          

        }

        return assignment2ToAssignment;
    }



}
