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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Utility {

	public static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

	/**
	 * Finds the corresponding member to the given name and returns it
	 * 
	 * @author Joey
	 * @param email is the unique email of the member which is wanted
	 * @return the member with the specified email
	 */
	public static Member findMember(String email) {
		Member foundMember = null;
		// iterate through the list of members in the application
		for (var member : climbSafe.getMembers()) {
			// if the email matches with the input email
			if (member.getEmail().equals(email)) {
				// we have found the member
				foundMember = member;
				break;
			}
		}
		return foundMember;
	}

	/**
	 * Finds the corresponding Guide to the given name and returns it
	 * 
	 * @author Joey
	 * @param email is the unique email of the guide which is wanted
	 * @return the guide with the specified email
	 */
	public static Guide findGuide(String email) {
		Guide foundGuide = null;
		// iterate through the list of guides in the application
		for (var guide : climbSafe.getGuides()) {
			// if the email matches with the input email
			if (guide.getEmail().equals(email)) {
				// we have found the guide
				foundGuide = guide;
				break;
			}
		}
		return foundGuide;
	}

	/**
	 * Finds the corresponding equipment bundle to the given name and returns it
	 * 
	 * @author @Aigiarn
	 * @param name Name of the equipment bundle
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

	/**
	 * Finds the corresponding equipment to the given name and returns it
	 * 
	 * @author Aigiarn
	 * @param name Name of the equipment
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
	 * 
	 * @author Joey
	 * @param equipmentList list of equipment
	 * @return boolean
	 */
	public static boolean listHas2DistinctEquipment(List<String> equipmentList) {
		boolean has2 = false;

		for (int i = 0; i < equipmentList.size() - 1; i++) {
			if (!(equipmentList.get(i).equals(equipmentList.get(i + 1)))) {
				has2 = true;
				break;
			}
		}
		return has2;
	}

	/**
	 * Finds whether the equipment present in the list is available in the system
	 * 
	 * @author Joey
	 * @param equipmentList list of equipment
	 * @param climbSafe     instance of system
	 * @return boolean
	 */
	public static boolean equipmentIsNotInSystem(ClimbSafe climbSafe, List<String> newEquipmentList) {
		boolean isIn = true;
		List<Equipment> equipmentsInSystem = climbSafe.getEquipment();

		for (String x : newEquipmentList) {
			if (!(equipmentsInSystem.contains(Equipment.getWithName(x)))) {
				isIn = false;
				break;
			}
		}
		return isIn;
	}

	/**
	 * Finds whether the bundle is available in the system
	 * 
	 * @author Joey
	 * @param bundleName name of bundle
	 * @return boolean
	 */
	public static boolean bundleExistsInSystem(ClimbSafe climbSafe, String bundleName) {
		List<EquipmentBundle> equipmentBundleInSystem = climbSafe.getBundles();

		boolean valid = false;

		for (EquipmentBundle x : equipmentBundleInSystem) {
			if (x.getName().equals(bundleName)) {
				valid = true;
				break;
			}
		}
		return valid;
	}

	/**
	 * Finds whether the quantity string is a valid one
	 * 
	 * @author Joey
	 * @param list of quantity
	 * @return boolean
	 */
	public static boolean quantityIsNotValid(List<Integer> newEquipmentQuantities) {
		boolean invalid = false;
		for (int i = 0; i < newEquipmentQuantities.size(); i++) {
			if (newEquipmentQuantities.get(i) <= 0) {
				invalid = true;
				break;
			}
		}
		return invalid;
	}

	/**
	 * Finds an equipment has the same name as the new bundle name
	 * 
	 * @author Joey
	 * @param newBundleName new name of bundle
	 * @param climbSafe     instance of system
	 * @return boolean
	 */
	public static boolean EquipmenttHasSameNameAsNewBundleName(ClimbSafe climbSafe, String newBundleName) {
		// checks whether a bookable item has the same name as the new bundle name
		boolean invalid = false;
		List<Equipment> equipmentsInSystem = climbSafe.getEquipment();
		for (Equipment x : equipmentsInSystem) {
			if (x.getName().equals(newBundleName)) {
				invalid = true;
				break;
			}
		}
		return invalid;
	}

	/**
	 * Method to find if there is an existing instance of equipment in the system.
	 * 
	 * @author Maya 
	 * @author Selina
	 * @param name      equipment name entered
	 * @param climbsafe ClimbSafe system
	 * @return boolean indicating if the equipment entered was found in the system
	 *         or not
	 */
	public static boolean equipmentExists(String name, ClimbSafe climbsafe) {
		List<Equipment> equipmentsInSystem = climbSafe.getEquipment();
		boolean found = false;
		for (int i = 0; i < equipmentsInSystem.size(); i++) {
			Equipment equipment = equipmentsInSystem.get(i);
			if (equipment != null) {
				String equipmentName = equipment.getName();
				if (equipmentName.equals(name)) {
					found = true;
					break;
				}
			}
		}
		return found;
	}

	/**
	 * Method to find whether two @ exist in the email declaration of the guide
	 * 
	 * @author Selina
	 * @param email Email of guide
	 * @return boolean value that indicates whether the email entered is functional
	 * 
	 */

	public static boolean emailInvalidSyntax(String email) {
		boolean invalid = false;
		char c = '@';
		int count = 0;

		for (int i = 0; i < email.length(); i++) {
			char a = email.charAt(i);
			if (c == a) {
				count++;
			}
		}

		if (count > 1) {
			invalid = true;
		}
		return invalid;
	}

	/**
	 * Method to check if there is a missing "com"
	 * 
	 * @author Selina
	 * @param email Email of guide
	 * @return boolean value that indicates whether the email entered is functional
	 */

	public static boolean emailMissingSubstring(String email) {
		boolean invalid = false;
		if (!email.contains("com")) {
			invalid = true;
		}
		return invalid;

	}

	/**
	 * Method to check if there is a missing ".ca"
	 * 
	 * @author Selina
	 * @param email Email of guide
	 * @return boolean value that indicates whether the email entered is functional
	 */

	public static boolean emailMissingSubstring2(String email) {
		boolean invalid = false;
		if (!email.contains(".ca")) {
			invalid = true;
		}
		return invalid;

	}

	/**
	 * Method to check if there is a missing "email"
	 * 
	 * @author Selina
	 * @param email Email of guide
	 * @return boolean value that indicates whether the email entered is functional
	 */
	public static boolean missingEmailDeclaration(String email) {
		boolean invalid = false;
		if (!email.contains("email")) {
			invalid = true;
		}
		return invalid;
	}

	/**
	 * Method to check if there is a missing "mail"
	 * 
	 * @author Selina
	 * @param email Email of guide
	 * @return boolean value that indicates whether the email entered is functional
	 */
	public static boolean missingEmailDeclaration2(String email) {
		boolean invalid = false;
		if (!email.contains("mail")) {
			invalid = true;
		}
		return invalid;
	}

	/**
	 * Method to check if there is "@" is in front of "email"
	 * 
	 * @author Selina
	 * @param email Email of guide
	 * @return boolean value that indicates whether the email entered is functional
	 */
	public static boolean wrongEmailSyntax(String email) {
		boolean invalid = false;
		int indexOfEmail = email.indexOf("email");
		int indexOfAt = email.indexOf("@");
		if (indexOfAt > indexOfEmail) {
			invalid = true;
		}
		return invalid;
	}

	/**
	 * Method to check if there is "@" is in front of "mail"
	 * 
	 * @author Selina
	 * @param email Email of guide
	 * @return boolean value that indicates the email entered is functional
	 */
	public static boolean wrongEmailSyntax2(String email) {
		boolean invalid = false;
		int indexOfEmail = email.indexOf("mail");
		int indexOfAt = email.indexOf("@");
		if (indexOfAt > indexOfEmail) {
			invalid = true;
		}
		return invalid;
	}

	/**
	 * checks if there are characters before "@" in a string
	 * 
	 * @author Ke
	 * @param email string to check
	 * @return true if there is no char, false otherwise
	 */
	public static boolean goodStart(String email) {
		boolean invalid = false;

		if (email.charAt(0) == '@') {
			invalid = true;
		}
		return invalid;
	}

	/**
	 * checks existence of a list of bookableItems in system.
	 * 
	 * @author Ke
	 * @param climbSafe    instance of a system
	 * @param itemsToCheck list of bookableItems to check
	 * @return true if every bookableItems in list exists, false otherwise
	 */
	public static boolean bookableItemsExists(ClimbSafe climbSafe, List<String> itemsToCheck) {

		for (String item : itemsToCheck) {

			if (bundleExistsInSystem(climbSafe, item)) {
				return true;
			}

			if (equipmentExists(item, climbSafe)) {
				return true;
			}

		}
		return false;
	}

	/**
	 * gets an EquipmentBundle by name in an instance of ClimbSafe
	 * 
	 * @author Ke Yan
	 * @param climbSafe instance of system
	 * @param budle     name of the EquipmentBundle to search for
	 * @return the EquipmentBundle if it is exists in climbSafe, otherwise null
	 */

	public static EquipmentBundle getBundle(ClimbSafe climbSafe, String budle) {

		List<EquipmentBundle> equipmentBundleInSystem = climbSafe.getBundles();
		for (EquipmentBundle aBudle : equipmentBundleInSystem) {
			if (aBudle.getName().equals(budle)) {
				return aBudle;
			}
		}
		return null;

	}

	/**
	 * gets an equipment by name in an instance of ClimbSafe
	 * 
	 * @author Maya Aji
	 * @param climbSafe instance of system
	 * @param equipment name of the equipment to search for
	 * @return the equipment if it is exists in climbSafe, otherwise null
	 */

	public static Equipment getEquipment(ClimbSafe climbSafe, String equipment) {

		List<Equipment> equipmentInSystem = climbSafe.getEquipment();
		for (Equipment anEquipment : equipmentInSystem) {
			if (anEquipment.getName().equals(equipment)) {
				return anEquipment;
			}
		}
		return null;

	}

	/**
	 * adds a list of item and their respective quantities (same index) to a
	 * member's bookedItems in an instance of ClimbSafe
	 * 
	 * @author Ke Yan
	 * @param climbSafe      instance of system
	 * @param itemsToAdd     list of items to add
	 * @param itemQuantities list of quantities for each item
	 * @param member         member to whome the items will be assigned to (in
	 *                       bookedItems)
	 * @return true if the items are added successfully
	 */
	public static boolean addItemList(ClimbSafe climbSafe, List<String> itemsToAdd, List<Integer> itemQuantities,
			Member member) {

		int index = 0;
		BookableItem foundItem;

		for (String item : itemsToAdd) {

			// should always work since we checked?
			foundItem = getEquipment(climbSafe, item);
			if (foundItem == null) {
				foundItem = getBundle(climbSafe, item);
			}

			// member.addBookedItem(itemQuantities.get(index), climbSafe, foundItem);
			climbSafe.addBookedItem(itemQuantities.get(index), member, foundItem);
			index++;

		}

		return true;

	}

	/**
	 * method deletes all booked item of a member in an instance of ClimbSafe
	 * 
	 * @author Ke Yan
	 * @param climbSafe instance of system
	 * @param member    member who's booked item will be reset
	 * @return true if the reset is successful
	 */
	public static boolean resetBookedItems(ClimbSafe climbSafe, Member member) {

		List<BookedItem> ItemInMember = member.getBookedItems();

		int counter = ItemInMember.size();

		for (int i = 0; i < counter; i++) {
			// climbSafe.removeBookedItem(bookedItem);
			// member.removeBookedItem(bookedItem);
			// bookedItem.delete();
			ItemInMember.get(0).delete();
		}

		return true;
	}

	/**
	 * deletes all bundle items in a bundle in an instance of ClimbSafe
	 * 
	 * @author Ke Yan
	 * @param climbSafe instance of system
	 * @param bundle    Bundle for which booked item will be reset
	 * @return true if the reset is successful
	 */
	public static boolean resetBundleItems(ClimbSafe climbSafe, EquipmentBundle bundle) {
		List<BundleItem> ItemInBundle = bundle.getBundleItems();
		int counter = ItemInBundle.size();
		for (int i = 0; i < counter; i++) {
			ItemInBundle.get(0).delete();
		}
		return true;

	}

	// ui

	/**
	 * check if member is in the system
	 * 
	 * @author Ke Yan
	 * @param email	member email address
	 * @return true if member is in system
	 */
	public static boolean memberInSystem(String email) {
		List<Member> members = ClimbSafeApplication.getClimbSafe().getMembers();
		for (Member e : members) {
			if (e.getEmail().equals(email))
				return true;
		}
		return false;
	}

	/**
	 * check if guide is in the system
	 * 
	 * @author Selena Gao
	 * @param email	guide email address
	 * @return true if guide is in system
	 */
	public static boolean guideInSystem(String email) {
		List<Guide> guides = ClimbSafeApplication.getClimbSafe().getGuides();
		for (Guide g : guides) {
			if (g.getEmail().equals(email))
				return true;
		}
		return false;
	}

	/**
	 * finds a member from its email
	 * 
	 * @author Ke Yan
	 * @param email	member email address
	 * @return Member
	 */
	public static Member findMemberEmail(String email) {
		return Utility.findMember(email);
	}

	/**
	 * gets a member's contact information
	 * 
	 * @author Ke Yan
	 * @param email	member email address
	 * @return String	member's contact information
	 */
	public static String getMemberContact(String email) {
		return Utility.findMember(email).getEmergencyContact();
	}

	/**
	 * gets a member's password
	 * 
	 * @author Ke Yan
	 * @param email	member email address
	 * @return String	member's password
	 */
	public static String getMemberPassword(String email) {
		return Utility.findMember(email).getPassword();
	}

	/**
	 * gets the number of weeks a member is climbing
	 * 
	 * @author Ke Yan
	 * @param email	member email address
	 * @return int	number of weeks a member
	 */
	public static int getMemberWeek(String email) {
		return Utility.findMember(email).getNrWeeks();
	}

	/**
	 * checks if the member is staying in a hotel
	 * 
	 * @author Ke Yan
	 * @param email	member email address
	 * @return true if member is staying in a hotel
	 */
	public static boolean getMemberHotel(String email) {
		return Utility.findMember(email).getHotelRequired();
	}

	/**
	 * checks if a guide is assigned to a member
	 * 
	 * @author Ke Yan
	 * @param email	member email address
	 * @return true if a guide is assigned to a member
	 */
	public static boolean getMemberGuide(String email) {
		return Utility.findMember(email).getGuideRequired();
	}

	/**
	 * gets the list of booked items of a member
	 * 
	 * @author Ke Yan
	 * @param email	member email address
	 * @return String list of booked items
	 */
	public static String getMemberItems(String email) {
		List<BookedItem> items = Utility.findMember(email).getBookedItems();
		String itemList = "";
		for (BookedItem e : items) {
			String pairing = "";
			pairing += e.getItem().getName();
			pairing += " x";
			pairing += e.getQuantity();
			pairing += ", ";
			itemList += pairing;
		}
		return itemList;
	}

	/**
	 * get a member's name from its email
	 * 
	 * @author Ke Yan
	 * @param email	member email address
	 * @return String member's name
	 */
	public static String getMemberName(String email) {
		return Utility.findMember(email).getName();
	}

	////////////////////////////////////////////////////////
	
	/**
	 * get a member's respective TOAssignment instance
	 * 
	 * @author Mihail Calitoiu
	 * @param email	member email address
	 * @return TOAssignment member's respective TOAssignment instance
	 */
	public static TOAssignment getTOAssignmentMemberEmail(String email) {
		List<TOAssignment> ass = ClimbSafeFeatureSet6Controller.getAssignments();

		for (TOAssignment e : ass) {
			if (e.getMemberEmail().equals(email))
				return e;
		}
		return null;
	}

	/**
	 * get a member's assignment guide
	 * 
	 * @author Mihail Calitoiu
	 * @param email	member email address
	 * @return String member's assignment guide in parenthesis
	 */
	public static String getMemberAssigmentGuide(String email) {
		String output = "";
		TOAssignment ass = getTOAssignmentMemberEmail(email);
		if (ass == null)
			return "Assignment not assigned yet";
		output += ass.getGuideName();
		output += " (";
		output += ass.getGuideEmail();
		output += ")";
		return output;
	}

	/**
	 * get a member's assignment weeks
	 * 
	 * @author Mihail Calitoiu
	 * @param email	member email address
	 * @return String member's assignment weeks
	 */
	public static String getMemberAssigmentWeeks(String email) {
		String output = "";
		TOAssignment ass = getTOAssignmentMemberEmail(email);
		if (ass == null)
			return "Assignment not assigned yet";
		output += Integer.toString(ass.getStartWeek());
		output += "-";
		output += Integer.toString(ass.getEndWeek());
		return output;
	}

	/**
	 * get a member's assignment guide cost
	 * 
	 * @author Mihail Calitoiu
	 * @param email	member email address
	 * @return String member's assignment guide cost
	 */
	public static String getMemberAssigmentGuideCost(String email) {
		String output = "";
		TOAssignment ass = getTOAssignmentMemberEmail(email);
		if (ass == null)
			return "Assignment not assigned yet";
		output += "$";
		output += Integer.toString(ass.getTotalCostForGuide());
		return output;
	}

	/**
	 * get a member's equipment cost in assignment
	 * 
	 * @author Mihail Calitoiu
	 * @param email	member email address
	 * @return String member's equipment cost
	 */
	public static String getMemberAssigmentEquipmentCost(String email) {
		String output = "";
		TOAssignment ass = getTOAssignmentMemberEmail(email);
		if (ass == null)
			return "Assignment not assigned yet";
		output += "$";
		output += Integer.toString(ass.getTotalCostForEquipment());
		return output;
	}

	/**
	 * get a member's assignment status
	 * 
	 * @author Mihail Calitoiu
	 * @param email	member email address
	 * @return String member's assignment status
	 */
	public static String getMemberAssigmentStatus(String email) {
		String output = "";
		TOAssignment ass = getTOAssignmentMemberEmail(email);
		if (ass == null)
			return "Assignment not assigned yet";
		output += ass.getStatus();
		return output;
	}

	////// JAVADOC TO EDIT
	
	/**
	 * get a member's name from its email
	 * 
	 * @author Ke Yan
	 * @param email	member email address
	 * @return String member's name
	 */
	public static String getMemberAssigmentACode(String email) {
		String output = "";
		TOAssignment ass = getTOAssignmentMemberEmail(email);
		if (ass == null)
			return "Assignment not assigned yet";
		output += ass.getAuthorizationCode();
		return output;
	}

	/**
	 * get a member's name from its email
	 * 
	 * @author Ke Yan
	 * @param email	member email address
	 * @return String member's name
	 */
	public static String getMemberAssigmentRefund(String email) {
		String output = "%";
		TOAssignment ass = getTOAssignmentMemberEmail(email);
		if (ass == null)
			return "Assignment not assigned yet";
		output += Integer.toString(ass.getRefundedPercentageAmount());
		return output;
	}

	/**
	 * get a member's name from its email
	 * 
	 * @author Ke Yan
	 * @param email	member email address
	 * @return String member's name
	 */
	public static ObservableList<String> getBundles() {
		List<EquipmentBundle> bundles = ClimbSafeApplication.getClimbSafe().getBundles();
		ObservableList<String> names = FXCollections.observableArrayList();
		for (EquipmentBundle e : bundles) {
			names.add(e.getName());
		}
		return names;
	}

	/**
	 * get a member's name from its email
	 * 
	 * @author Ke Yan
	 * @param email	member email address
	 * @return String member's name
	 */
	public static ObservableList<String> getEquipment() {
		List<Equipment> equipment = ClimbSafeApplication.getClimbSafe().getEquipment();
		ObservableList<String> names = FXCollections.observableArrayList();
		for (Equipment e : equipment) {
			names.add(e.getName());
		}
		return names;
	}

	/**
	 * get a member's name from its email
	 * 
	 * @author Ke Yan
	 * @param email	member email address
	 * @return String member's name
	 */
	public static ObservableList<Integer> getWeeks() {
		int length = ClimbSafeApplication.getClimbSafe().getNrWeeks();
		ObservableList<Integer> weeks = FXCollections.observableArrayList();
		for (int i = 1; i < length + 1; i++) {
			weeks.add(i);
		}
		return weeks;
	}

	/**
	 * get a member's name from its email
	 * 
	 * @author Ke Yan
	 * @param email	member email address
	 * @return String member's name
	 */
	public static String getGuideName(String email) {
		return findGuideInSystem(email).getName();
	}

	/**
	 * get a member's name from its email
	 * 
	 * @author Ke Yan
	 * @param email	member email address
	 * @return String member's name
	 */
	public static String getGuidePassword(String email) {
		return findGuideInSystem(email).getPassword();
	}

	/**
	 * get a member's name from its email
	 * 
	 * @author Ke Yan
	 * @param email	member email address
	 * @return String member's name
	 */
	public static String getGuideContact(String email) {
		return findGuideInSystem(email).getEmergencyContact();
	}

	/**
	 * get a member's name from its email
	 * 
	 * @author Ke Yan
	 * @param email	member email address
	 * @return String member's name
	 */
	public static TOEquipment getEquipmentFromName(String equipmentName) {
		List<Equipment> equipment = ClimbSafeApplication.getClimbSafe().getEquipment();
		for (Equipment e : equipment) {
			if (e.getName() == equipmentName) {
				return new TOEquipment(e.getName(), e.getWeight(), e.getPricePerWeek());
			}
		}
		return null;
	}

	/**
	 * get a member's name from its email
	 * 
	 * @author Ke Yan
	 * @param email	member email address
	 * @return String member's name
	 */
	public static Guide findGuideInSystem(String email) {
		return Utility.findGuide(email);
	}

	// TOEquipment
	/**
	 * get a member's name from its email
	 * 
	 * @author Ke Yan
	 * @param email	member email address
	 * @return String member's name
	 */
	public static TOEquipment getEquipmentTO(String equipmentName) {
		Equipment equipment = findEquipment(equipmentName);
		TOEquipment transfer = null;
		// if (equipment==null) return transfer;
		// else {
		int price = equipment.getPricePerWeek();
		int weight = equipment.getWeight();
		String name = equipment.getName();
		transfer = new TOEquipment(name, weight, price);
		return transfer;
		// }

	}

}
