package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

import ca.mcgill.ecse.btms.controller.InvalidInputException;
// new imports
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;

public class ClimbSafeFeatureSet5Controller {

	private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

	/*
	 * Creates a new equipment bundle with the given name and discount and adds it
	 * to the ClimbSafe system
	 * 
	 * @param name Name of the equipment bundle
	 * 
	 * @param discount Equipment bundle discount
	 * 
	 * @param equipmentNames List of all the names of the equipment in the bundle
	 * 
	 * @param equipmentQuantities List of all the quantities of the equipment in the
	 * bundle
	 */
	public static void addEquipmentBundle(String name, int discount, List<String> equipmentNames,
			List<Integer> equipmentQuantities) throws InvalidInputException {
		var error = "";
		try {
			// addBundle() creates a new equipment bundle and adds it to ClimbSafe
			EquipmentBundle equipmentBundle = climbSafe.addBundle(name, discount);
			// add items with the corresponding quantity to bundle
			if (equipmentNames.size() != equipmentQuantities.size()) {
				error = "The list of equipment names must have the same number of elements as the list of equipment quantities. ";
			} else {
				for (int i = 0; i < equipmentNames.size(); i++) {
					int equipmentQuantity = equipmentQuantities.get(i);
					String equipmentName = equipmentNames.get(i);
					Equipment equipment = findEquipment(equipmentName);
					climbSafe.addBundleItem(equipmentQuantity, equipmentBundle, equipment);
				}
			}
			// not sure about the exception
		} catch (RuntimeException e) {
			error = e.getMessage();
			if (error.startsWith("Cannot create due to duplicate name.")) {
				error = "A bundle with this name already exists. Please use a different name.";
			}
			throw new InvalidInputException(error);
		}
	}

	/*
	 * Given the bundles old name, this method replaces the old bundle with the
	 * updated bundle.
	 * 
	 * @param oldName Previous name of the equipment bundle
	 * 
	 * @param newName Updated name of the equipment bundle
	 * 
	 * @param discount Equipment bundle discount
	 * 
	 * @param equipmentNames List of all the names of the equipment in the bundle
	 * 
	 * @param equipmentQuantities List of all the quantities of the equipment in the
	 * bundle
	 */
	public static void updateEquipmentBundle(String oldName, String newName, int newDiscount,
			List<String> newEquipmentNames, List<Integer> newEquipmentQuantities) throws InvalidInputException {
		var error = "";
		try {
			// find the old bundle and remove it
			EquipmentBundle oldEquipmentBundle = findEquipmentBundle(oldName);
			climbSafe.removeBundle(oldEquipmentBundle);
			// addBundle() creates a new equipment bundle and adds it to ClimbSafe
			EquipmentBundle equipmentBundle = climbSafe.addBundle(newName, newDiscount);
			// add items with the corresponding quantity to bundle
			if (newEquipmentNames.size() != newEquipmentQuantities.size()) {
				error = "The list of equipment names must have the same number of elements as the list of equipment quantities. ";
			} else {
				for (int i = 0; i < newEquipmentNames.size(); i++) {
					int equipmentQuantity = newEquipmentQuantities.get(i);
					String equipmentName = newEquipmentNames.get(i);
					Equipment equipment = findEquipment(equipmentName);
					climbSafe.addBundleItem(equipmentQuantity, equipmentBundle, equipment);
				}
			}
			// not sure about the exception
		} catch (RuntimeException e) {
			error = e.getMessage();
			if (error.startsWith("Cannot create due to duplicate name.")) {
				error = "A bundle with this name already exists. Please use a different name.";
			}
			throw new InvalidInputException(error);
		}
	}

	/*
	 * Finds the corresponding equipment bundle to the given name and returns it
	 * 
	 * @param name Name of the equipment bundle
	 * 
	 * @return EquipmentBundle Corresponding equipment bundle
	 */
	private static EquipmentBundle findEquipmentBundle(String name) {
		EquipmentBundle foundEquipmentBundle = null;
		// iterate through all bundle until the old bundle is found
		for (var equipmentBundle : climbSafe.getBundles()) {
			if (equipmentBundle.getName() == name) {
				foundEquipmentBundle = equipmentBundle;
				break;
			}
		}
		// return the desired bundle
		return foundEquipmentBundle;
	}

	/*
	 * Finds the corresponding equipment to the given name and returns it
	 * 
	 * @param name Name of the equipment 
	 * 
	 * @return EquipmentBundle Corresponding equipment 
	 */
	private static Equipment findEquipment(String name) {
		Equipment foundEquipment = null;
		// iterate through all bundle until the old bundle is found
		for (var equipment : climbSafe.getEquipment()) {
			if (equipment.getName() == name) {
				foundEquipment = equipment;
				break;
			}
		}
		// return the desired bundle
		return foundEquipment;
	}
}
