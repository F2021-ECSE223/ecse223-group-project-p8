package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
import java.util.List;

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
					Equipment equipment = Utility.findEquipment(equipmentName);
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
	
	public static void updateEquipmentBundle(String oldName, String newName, int newDiscount,
			List<String> newEquipmentNames, List<Integer> newEquipmentQuantities) throws InvalidInputException {
		var error = "";
		
		//check old name
		if(!Utility.bundleExistsInSystem(climbSafe, oldName)) {
			error = "Equipment bundle " + oldName + " does not exist";
			throw new InvalidInputException(error);
		}
		if(oldName.length()==0){
			error = "Equipment bundle name cannot be empty";
			throw new InvalidInputException(error);
		}
		
		//check new name
		if(newName.length()==0) {
			error = "Equipment bundle name cannot be empty";
			throw new InvalidInputException(error);
		}
		if(Utility.bookableItemtHasSameNameAsNewBundleName(climbSafe, newName)){
			error = "A bookable item called " + newName + " already exists";
			throw new InvalidInputException(error);
		}
		
		//check discount
		if(newDiscount < 0) {
			error = "Discount must be at least 0";
			throw new InvalidInputException(error);
		}
		if (newDiscount > 100) {
			error = "Discount must be no more than 100";
			throw new InvalidInputException(error);
		}
		
		//check equipments in bundle
		if(newEquipmentNames.size() <= 1 || !Utility.listHas2DistinctEquipment(newEquipmentNames)) {
			error = "Equipment bundle must contain at least two distinct types of equipment";	
			throw new InvalidInputException(error);
		}
		
		List<Equipment> equipmentInSystem = climbSafe.getEquipment();
		List<String> storesNames = new ArrayList<String>();
		for (Equipment temp23: equipmentInSystem) {
			storesNames.add(temp23.getName());
		}
		
		String missingEquipment = null;
		for(String x : newEquipmentNames) {
			if(!storesNames.contains(x)){
				missingEquipment = x;
				break;
			}
		}
		
		if(missingEquipment != null) {
			error = "Equipment " + missingEquipment + " does not exist";  
			throw new InvalidInputException(error);
		}
		
		
		//check quantity of equipment
		if(Utility.quantityIsNotValid(newEquipmentQuantities)) {
			error = "Each bundle item must have quantity greater than or equal to 1";
			throw new InvalidInputException(error);
		}
		
		if(!(newName.equals(oldName)) && Utility.bundleExistsInSystem(climbSafe, newName)) {
			error = "A bookable item called large bundle already exists";
			throw new InvalidInputException(error);
		}
		
		try {			
			//find equipment bundle
			EquipmentBundle equipmentBundle = Utility.findEquipmentBundle(oldName);
			//set new name
			equipmentBundle.setName(newName);
			//set new discount
			equipmentBundle.setDiscount(newDiscount);
			//remove all items in the bundle
			for(int i = 0;i<equipmentBundle.getBundleItems().size();i++) {
				equipmentBundle.removeBundleItem(equipmentBundle.getBundleItem(i));
			}
			
			//add new items in bundle with corresponding quantity
			for(int i = 0;i<newEquipmentNames.size();i++) {
				equipmentBundle.addBundleItem(newEquipmentQuantities.get(i), climbSafe,
						(Equipment) Equipment.getWithName(newEquipmentNames.get(i)));				
			}			
			
		}catch (RuntimeException e){
			error = e.getMessage();
			throw new InvalidInputException(error);
		}
	}
}
