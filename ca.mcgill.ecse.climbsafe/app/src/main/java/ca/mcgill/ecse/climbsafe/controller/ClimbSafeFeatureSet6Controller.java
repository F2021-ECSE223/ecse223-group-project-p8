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
        List < TOAssignment > resultAssignment = new ArrayList < > ();

        for (Assignment current: temp) {

            String aMemberEmail = current.getMember().getEmail();
            String aMemberName = current.getMember().getName();
            String aGuideEmail = current.getGuide().getEmail();
            String aGuideName = current.getGuide().getName();
            String aHotelName = current.getHotel().getName();
            int aStartWeek = current.getStartWeek();
            int aEndWeek = current.getEndWeek();
            int stayedWeeks = aEndWeek - aStartWeek;
            int TotalCostForGuide = (ClimbSafeApplication.getClimbSafe()).getPriceOfGuidePerWeek() * stayedWeeks;
            int TotalCostForEquipment = 0;

            List < BookedItem > membersItems = current.getMember().getBookedItems();

            for (BookedItem currentBooked: membersItems) {

                if (currentBooked.getItem().getClass().equals("EquipmentBundle")) {
                    EquipmentBundle specificItem = (EquipmentBundle) currentBooked.getItem();
                    List < BundleItem > storedEquipment = specificItem.getBundleItems();
                    int costForBundle = 0;
                    for (BundleItem currentEqi: storedEquipment) {
                        int price = currentEqi.getEquipment().getPricePerWeek() * stayedWeeks;
                        costForBundle += price;
                    }
                    costForBundle = costForBundle * ((100 - specificItem.getDiscount()) / 100);
                    TotalCostForEquipment += costForBundle;

                } else if (currentBooked.getItem().getClass().equals("Equipment")) {
                    Equipment specificItem = (Equipment) currentBooked.getItem();
                    TotalCostForEquipment += specificItem.getPricePerWeek() * stayedWeeks * currentBooked.getQuantity();
                }
            }

            resultAssignment.add(new TOAssignment(aMemberEmail, aMemberName, aGuideEmail, aGuideName, aHotelName, aStartWeek, aEndWeek, TotalCostForGuide, TotalCostForGuide));


        }

        return resultAssignment;
    }



}