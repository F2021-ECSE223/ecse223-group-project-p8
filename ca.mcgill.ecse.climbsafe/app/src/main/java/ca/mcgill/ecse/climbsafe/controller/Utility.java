package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;

public class Utility {
	
	public static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	
	/**
	 * Finds the corresponding member to the given name and returns it
	 * @author Joey
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
	
	/**
	 * Finds the corresponding Guide to the given name and returns it
	 * @author Joey
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
	
	/** @Aigiarn
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

	/** @Aigiarn
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
	
	/** 
	 * Finds whether a list contains 2 different kinds of equipment
	 * @author Joey
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
	
	
	/** 
	 * Finds whether the equipment present in the list is available in the system
	 * @author Joey
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
	
	/** 
	 * Finds whether the bundle is available in the system
	 * @author Joey
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
	
	/** 
	 * Finds whether the quantity string is a valid one
	 * @author Joey
	 * @param list of quantity
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
	
	
	/** 
	 * Finds an equipment has the same name as the new bundle name
	 * @author Joey
	 * @param newBundleName new name of bundle
	 * @param climbSafe instance of system
	 * 
	 * @return boolean
	 */
	public static boolean EquipmenttHasSameNameAsNewBundleName(ClimbSafe climbSafe,
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
	
	
	/**Method to find if there is an existing instance of equipment in the system.
	 * 
	 * @author Maya Selina
	 * 
	 * @param name equipment name entered
	 * @param climbsafe ClimbSafe system
	 * @return boolean indicating if the equipment entered was found in the system or not
	 */
	public static boolean equipmentExists(String name, ClimbSafe climbsafe) {
		List<Equipment> equipmentsInSystem = climbSafe.getEquipment();
		boolean found = false;
		for(int i=0;i<equipmentsInSystem.size();i++) {
			Equipment equipment = equipmentsInSystem.get(i);
			if(equipment != null) {
				String equipmentName = equipment.getName();
				if(equipmentName.equals(name)) {
					found=true;
					break;
				}
			}
		}
		return found;
	}
		
	/** Method to find whether two @ exist in the email declaration of the guide
	 * @author Selina
	 * @param email Email of guide 
	 * @return boolean value that indicates whether the email entered is functional
	 * 
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
	
	/** Method to check if there is a missing "com"
	 * @author Selina
	 * @param email Email of guide 
	 * @return boolean value that indicates whether the email entered is functional
	 */
	
	public static boolean emailMissingSubstring(String email) {
		boolean invalid=false;
		if(!email.contains("com")) {
			invalid=true;
		}
		return invalid;
		
	}
	
	/** Method to check if there is a missing ".ca"
	 * @author Selina 
	 * @param email Email of guide
	 * @return boolean value that indicates whether the email entered is functional
	 */
	
	public static boolean emailMissingSubstring2(String email) {
		boolean invalid=false;
		if(!email.contains(".ca")) {
			invalid=true;
		}
		return invalid;
		
	}
	/** Method to check if there is a missing "email"
	 * @author Selina
	 * @param email Email of guide 
	 * @return boolean value that indicates whether the email entered is functional
	 */
	public static boolean missingEmailDeclaration(String email) {
		boolean invalid=false;
		if(!email.contains("email")) {
			invalid=true;
		}
		return invalid;
	}
	
	/** Method to check if there is a missing "mail"
	 * @author Selina
	 * @param email Email of guide
	 * @return boolean value that indicates whether the email entered is functional
	 */
	public static boolean missingEmailDeclaration2(String email) {
		boolean invalid=false;
		if(!email.contains("mail")) {
			invalid=true;
		}
		return invalid;
	}
	
	/** Method to check if there is "@" is in front of "email"
	 * @author Selina
	 * @param email Email of guide
	 * @return boolean value that indicates whether the email entered is functional
	 */
	public static boolean wrongEmailSyntax(String email) {
		boolean invalid=false;
		int indexOfEmail=email.indexOf("email");
		int indexOfAt=email.indexOf("@");
		if(indexOfAt > indexOfEmail) {
			invalid=true;
		}
		return invalid;
	}
	
	/** Method to check if there is "@" is in front of "mail"
	 * @author Selina
	 * @param email Email of guide
	 * @return boolean value that indicates the email entered is functional
	 */
	public static boolean wrongEmailSyntax2(String email) {
		boolean invalid=false;
		int indexOfEmail=email.indexOf("mail");
		int indexOfAt=email.indexOf("@");
		if(indexOfAt > indexOfEmail) {
			invalid=true;
		}
		return invalid;
	}


	/**
	 * checks if there are characters before "@" in a string
	 * @author Ke
	 * @param email string to check
	 * @return true if there is no char, false otherwise
	 */
	public static boolean goodStart(String email) {
		boolean invalid=false;
		
		if(email.charAt(0)=='@') {
			invalid=true;
		}
		return invalid;
	}


	 /**
	  * checks existence of a list of bookableItems in system.
	  * @author Ke
	  * @param climbSafe instance of a system
	  * @param itemsToCheck list of bookableItems to check
	  * @return true if every bookableItems in list exists, false otherwise
	  */
	public static boolean bookableItemsExists( ClimbSafe climbSafe, List<String> itemsToCheck) {
		

		for (String item: itemsToCheck) {

		  if(bundleExistsInSystem(climbSafe,item)) {
			return true;
		  }

		  if(equipmentExists( item, climbSafe)) {
			return true;
		  }

		}
		return false;
  }

  /**
   * gets an EquipmentBundle by name in an instance of ClimbSafe
   * @author Ke Yan
   * @param climbSafe instance of system
   * @param budle name of the EquipmentBundle to search for
   * @return the EquipmentBundle if it is exists in climbSafe, otherwise null
   */

  public static EquipmentBundle getBundle( ClimbSafe climbSafe, String budle) {

	List<EquipmentBundle> equipmentBundleInSystem = climbSafe.getBundles();
	for(EquipmentBundle aBudle : equipmentBundleInSystem) {
		if(aBudle.getName().equals(budle)) {
			return aBudle;
		}
	}
	return null;
	  
  }

  /**
   * gets an equipment by name in an instance of ClimbSafe
   * @author Ke Yan
   * @param climbSafe instance of system
   * @param equipment name of the equipment to search for
   * @return the equipment if it is exists in climbSafe, otherwise null
   */

  public static Equipment getEquipment( ClimbSafe climbSafe, String equipment) {

	List<Equipment> equipmentInSystem = climbSafe.getEquipment();
	for (Equipment anEquipment : equipmentInSystem) {
		if (anEquipment.getName().equals(equipment)) {
			return anEquipment;
		}
	}
	return null;
	  
  }


  /**
   * adds a list of item and their respective quantities (same index) to a member's bookedItems in an instance of ClimbSafe
   * @author Ke Yan
   * @param climbSafe instance of system
   * @param itemsToAdd list of items to add
   * @param itemQuantities list of quantities for each item
   * @param member member to whome the items will be assigned to (in bookedItems)
   * @return true if the items are added successfully
   */
  public static boolean addItemList( ClimbSafe climbSafe, List<String> itemsToAdd, List<Integer> itemQuantities, Member member) {

	int index=0;
	BookableItem foundItem;

	for (String item: itemsToAdd) {

		//should always work since we checked?
		foundItem=getEquipment(climbSafe, item);
		if (foundItem==null) {
			foundItem= getBundle(climbSafe, item);
		}


		//member.addBookedItem(itemQuantities.get(index), climbSafe, foundItem);
		climbSafe.addBookedItem(itemQuantities.get(index), member, foundItem);
		index++;

	}

	return true;

  }

/**
 * method deletes all booked item of a member in an instance of ClimbSafe
 * @author Ke Yan
 * @param climbSafe instance of system
 * @param member member who's booked item will be reset
 * @return true if the reset is successful
 */
  public static boolean resetBookedItems( ClimbSafe climbSafe, Member member) {  
		
		List<BookedItem> ItemInMember = member.getBookedItems();
		
		int counter=ItemInMember.size();
	
	 for (int i=0; i<counter; i++) {
		  // climbSafe.removeBookedItem(bookedItem);
		  //member.removeBookedItem(bookedItem);
		  //bookedItem.delete();
		  ItemInMember.get(0).delete();
	 }
	
	 return true;
  }

  /**
   * deletes all bundle items in a bundle in an instance of ClimbSafe
   * @author Ke Yan
   * @param climbSafe instance of system
   * @param bundle  Bundle for which booked item will be reset
   * @return true if the reset is successful
   */
  public static boolean resetBundleItems( ClimbSafe climbSafe, EquipmentBundle bundle) {
		List<BundleItem> ItemInBundle = bundle.getBundleItems();
		int counter=ItemInBundle.size();
		for(int i = 0;i<counter;i++) {
			ItemInBundle.get(0).delete();
		  }
		return true;
		
	}


}
