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
import ca.mcgill.ecse.climbsafe.persistence.ClimbsafePersistence;

public class ClimbSafeFeatureSet6Controller {

    /**
     *  deleteEquipment uses the name parameter to delete the corresponding equipment with the same name
     *  
     * @author Mihail Calitoiu
     * @param  name is the equipment's name that needs to be deleted
     * @throws InvalidInputException for inputs when errors occur
     */
    public static void deleteEquipment(String name) throws InvalidInputException {
        Equipment equipment = null;
        List < Equipment > equipmentList = (ClimbSafeApplication.getClimbSafe()).getEquipment();
        List < EquipmentBundle > equipmentBundles = (ClimbSafeApplication.getClimbSafe()).getBundles();		
		for(EquipmentBundle currentBundle : equipmentBundles) {
            List < BundleItem > storedEquipment = currentBundle.getBundleItems();
            for (BundleItem itemInBundle: storedEquipment) {
                if (itemInBundle.getEquipment().getName().equals(name)){
                	throw new InvalidInputException("The piece of equipment is in a bundle and cannot be deleted");
                }
            }
		}
        for (Equipment temp: equipmentList) {
            if (temp.getName().equals(name)) {equipment = temp; break;}
        }
        if (equipment != null) {equipment.delete(); ClimbsafePersistence.save();}
    }

    /**
     *  deleteEquipmentBundle uses the name parameter to delete the corresponding equipment bundle with the same name
     *
     * @author Mihail Calitoiu
     * @param  name is the equipment bundle's name that needs to be deleted
     */
    public static void deleteEquipmentBundle(String name) {
        EquipmentBundle equipmentBundle = null;
        List < EquipmentBundle > equipmentBundleList = (ClimbSafeApplication.getClimbSafe()).getBundles();
        for (EquipmentBundle temp: equipmentBundleList) {
            if (temp.getName().equals(name)) {equipmentBundle = temp; break;}
        }
        if (equipmentBundle != null) {equipmentBundle.delete(); ClimbsafePersistence.save();}
    }
    
    /**
     *  getAssignments gets the assignment object list inside ClimbSafe and converts the list into a ToAssignment object list
     * 
     * @author Mihail Calitoiu
     * @return a TOAssignment list containing the converted assignments
     */
    public static List < TOAssignment > getAssignments() {
        List < Assignment > assignmentInClimb = (ClimbSafeApplication.getClimbSafe()).getAssignments();
        List < TOAssignment > assignment2ToAssignment = new ArrayList < > ();
        for (Assignment currentAssignment: assignmentInClimb) {
            String aMemberEmail, aMemberName, aGuideEmail, aGuideName, aHotelName, aStatus, authorizationCode;
            aMemberEmail = aMemberName = aGuideEmail = aGuideName = aHotelName = aStatus = authorizationCode = null;
            int aStartWeek, aEndWeek, stayedWeeks, TotalCostForGuide, TotalCostForEquipment, refundedPercentageAmount;
            aStartWeek = aEndWeek = stayedWeeks = TotalCostForGuide = TotalCostForEquipment = refundedPercentageAmount = 0;
            aStartWeek = currentAssignment.getStartWeek();
            aEndWeek = currentAssignment.getEndWeek();
            stayedWeeks = (aEndWeek - aStartWeek) + 1;
            aMemberEmail = currentAssignment.getMember().getEmail();
            aMemberName = currentAssignment.getMember().getName();
            Boolean hasGuide = currentAssignment.getMember().isGuideRequired();
            Boolean hasHotel = currentAssignment.getMember().isHotelRequired();
            aStatus = currentAssignment.getAssignmentStatusFullName();
            authorizationCode = currentAssignment.getAuthorizationCode();
            refundedPercentageAmount = currentAssignment.getRefund();
            if (hasGuide) {
                aGuideEmail = currentAssignment.getGuide().getEmail();
                aGuideName = currentAssignment.getGuide().getName();
                TotalCostForGuide = (ClimbSafeApplication.getClimbSafe()).getPriceOfGuidePerWeek() * stayedWeeks;
            }
            if (hasHotel) {aHotelName = currentAssignment.getHotel().getName();}
            List < BookedItem > membersItems = currentAssignment.getMember().getBookedItems();
            for (BookedItem currentBooked: membersItems) {
                int quant = currentBooked.getQuantity();
                if (currentBooked.getItem() instanceof EquipmentBundle) {
                    EquipmentBundle currentBundle = (EquipmentBundle) currentBooked.getItem();
                    List < BundleItem > storedEquipment = currentBundle.getBundleItems();
                    int costForBundle = 0;
                    for (BundleItem itemInBundle: storedEquipment) {
                        costForBundle += itemInBundle.getEquipment().getPricePerWeek() * stayedWeeks * itemInBundle.getQuantity();
                    }
                    if (hasGuide) {
                        float discount = (float) currentBundle.getDiscount() / 100;
                        costForBundle = (int)(costForBundle - costForBundle * discount);
                    }
                    TotalCostForEquipment += quant * costForBundle;
                } else {
                    Equipment equipment = (Equipment) currentBooked.getItem();
                    TotalCostForEquipment += equipment.getPricePerWeek() * stayedWeeks * quant;
                }
            }
            assignment2ToAssignment.add(new TOAssignment(aMemberEmail, aMemberName,
            		aGuideEmail, aGuideName, aHotelName, aStartWeek, aEndWeek, TotalCostForGuide,
            		TotalCostForEquipment,aStatus,authorizationCode,refundedPercentageAmount));
            ClimbsafePersistence.save();
        }
        return assignment2ToAssignment;
    }

}
