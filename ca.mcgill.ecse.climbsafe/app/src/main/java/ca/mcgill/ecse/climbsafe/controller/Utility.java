package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;

public class Utility {
	
	public static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	
	/*
	 * @JoeyLiu
	 * @param email is the unique email of the member which is wanted
	 * @return the member with the specified email
	 */
	public static Member findMember(String email){
		Member foundMember = null;
		//iterate through the list of members in the application
		for(var member: climbSafe.getMembers()) {
			//if the email matches with the input email
			if(member.getEmail().equals(email)) {
				//we have found the member
				foundMember = member;
				break;
			}
		}
		return foundMember;
	}
	
	/*
	 * @JoeyLiu
	 * @param email is the unique email of the guide which is wanted
	 * @return the guide with the specified email
	 */
	public static Guide findGuide(String email){
		Guide foundGuide = null;
		//iterate through the list of guides in the application
		for(var guide: climbSafe.getGuides()) {
			//if the email matches with the input email
			if(guide.getEmail().equals(email)) {
				//we have found the guide
				foundGuide = guide;
				break;
			}
		}
		return foundGuide;
	}
	
	/* @Aigiarn
	 * Finds the corresponding equipment bundle to the given name and returns it
	 * 
	 * @param name Name of the equipment bundle
	 * 
	 * @return EquipmentBundle Corresponding equipment bundle
	 */
	public static EquipmentBundle findEquipmentBundle(String name) {
		EquipmentBundle foundEquipmentBundle = null;
		// iterate through all bundle until the old bundle is found
		for (var equipmentBundle : climbSafe.getBundles()) {
			if (equipmentBundle.getName().equals(name)) {
				foundEquipmentBundle = equipmentBundle;
				break;
			}
		}
		// return the desired bundle
		return foundEquipmentBundle;
	}

	/* @Aigiarn
	 * Finds the corresponding equipment to the given name and returns it
	 * 
	 * @param name Name of the equipment 
	 * 
	 * @return Equipment Corresponding equipment 
	 */
	public static Equipment findEquipment(String name) {
		Equipment foundEquipment = null;
		// iterate through all bundle until the old bundle is found
		for (var equipment : climbSafe.getEquipment()) {
			if (equipment.getName().equals(name)) {
				foundEquipment = equipment;
				break;
			}
		}
		// return the desired bundle
		return foundEquipment;
	}
	
	/* @Joey
	 * Finds whether a list contains 2 different kinds of equipment
	 * 
	 * @param equipmentList list of equipment 
	 * 
	 * @return boolean
	 */
	public static boolean listHas2DistinctEquipment(List<String> equipmentList) {
		boolean has2 = false;
		
		for(int i=0;i<equipmentList.size()-1;i++) {
			if(!(equipmentList.get(i).equals(equipmentList.get(i+1)))) {
				has2 = true;
				break;
			}
		}
		return has2;
	}
	
	
	/* @Joey
	 * Finds whether the equipment present in the list is available in the system
	 * 
	 * @param equipmentList list of equipment 
	 * @param climbSafe instance of system
	 * 
	 * @return boolean
	 */
	public static boolean equipmentIsNotInSystem(ClimbSafe climbSafe,List<String> newEquipmentList) {
		boolean isIn = true;
		List<Equipment> equipmentsInSystem = climbSafe.getEquipment();
		
		for(String x : newEquipmentList) {
			if(!(equipmentsInSystem.contains(Equipment.getWithName(x)))) {
				isIn = false;
				break;
			}
		}
		return isIn;
	}
	
	/* @Joey
	 * Finds whether the bundle is available in the system
	 * 
	 * @param bundleName name of bundle
	 * 
	 * @return boolean
	 */
	public static boolean bundleExistsInSystem(ClimbSafe climbSafe,String bundleName) {
		List<EquipmentBundle> equipmentBundleInSystem = climbSafe.getBundles();
		
		boolean valid = false;
		
		for(EquipmentBundle x : equipmentBundleInSystem) {
			if(x.getName().equals(bundleName)) {
				valid = true;
				break;
			}
		}
		return valid;
	}
	
	/* @Joey
	 * Finds whether the bundle is available in the system
	 * 
	 * @param bundleName name of bundle
	 * 
	 * @return boolean
	 */
	public static boolean quantityIsNotValid(List<Integer> newEquipmentQuantities) {
		boolean invalid = false;
		for(int i=0;i<newEquipmentQuantities.size();i++) {
			if(newEquipmentQuantities.get(i) <= 0) {
				invalid = true;
				break;
			}
		}
		return invalid;
	}
	
	
	/* @Joey
	 * Finds whether the bundle is available in the system
	 * 
	 * @param newBundleName new name of bundle
	 * @param climbSafe instance of system
	 * 
	 * @return boolean
	 */
	public static boolean bookableItemtHasSameNameAsNewBundleName(ClimbSafe climbSafe,
			String newBundleName) {
		//checks whether a bookable item has the same name as the new bundle name
		boolean invalid = false;
		List<Equipment> equipmentsInSystem = climbSafe.getEquipment();
		for(Equipment x : equipmentsInSystem) {
			if(x.getName().equals(newBundleName)){
				invalid = true;
				break;
			}
		}
		return invalid;
	}
	
	/*
	 * I CREATED AN INSTANCE OF THE CLIMB SAFE APP SO EVERY CONTROLLER CAN ACCESS IT FROM HERE
	 * (CALL for e.g Utility.climbSafe.setNrWeeks())
	 * ADD HELPER METHODS IN THIS CLASS IF U WANT TO
	 * I ADDED THE FINDGUIDE AND THE FINDMEMBER BECAUSE I NEEDED THEM IN THE CONTROLLER
	 * U CAN USE THEM IF U WANT TO
	 */
	
	/*
	 * @SelinaG 
	 * Idk if this works tbh
	 * This is supposed to find whether @ exists twice in an email
	 */
	
	public static boolean emailInvalidSyntax(String email) {
		boolean invalid=false;
		char c = '@';
		int count=0;
		
		for(int i=0; i<email.length();i++) {
			char a=email.charAt(i);
			if(c == a) {
				count++;
			}
		}
		
		if(count > 1) {
			invalid=true;
		}
		return invalid;
	}
	
	/**
	 * @Selina 
	 * Idk if this works either LOL
	 * 
	 */
	
	public static boolean emailMissingSubstring(String email) {
		boolean invalid=false;
		if(!email.contains("com")) {
			invalid=true;
		}
		return invalid;
		
	}
	
	/**
	 * @Selina
	 * This method finds whether string contains substring "email"
	 */
	public static boolean missingEmailDeclaration(String email) {
		boolean invalid=false;
		if(!email.contains("email")) {
			invalid=true;
		}
		return invalid;
	}

}
