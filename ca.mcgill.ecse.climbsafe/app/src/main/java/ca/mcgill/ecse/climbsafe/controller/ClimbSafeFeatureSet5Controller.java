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
	 * @author Aigiarn Cheuk
	 * @param name Name of the equipment bundle
	 * @param discount Equipment bundle discount
	 * @param equipmentNames List of all the names of the equipment in the bundle
	 * @param equipmentQuantities List of all the quantities of the equipment in the bundle
	 */
	public static void addEquipmentBundle(String name, int discount, List<String> equipmentNames,
			List<Integer> equipmentQuantities) throws InvalidInputException {
		var error = "";

		// check name
		if (name.length() == 0) {
			error = "Equipment bundle name cannot be empty";
			throw new InvalidInputException(error);
		}
		if (Utility.EquipmenttHasSameNameAsNewBundleName(climbSafe, name)) {
			error = "A bookable item called " + name + " already exists";
			throw new InvalidInputException(error);
		}

		// check discount
		if (discount < 0) {
			error = "Discount must be at least 0";
			throw new InvalidInputException(error);
		}
		if (discount > 100) {
			error = "Discount must be no more than 100";
			throw new InvalidInputException(error);
		}

		// check equipments in bundle
		if (equipmentNames == null) {
			error = "List of equipments cannot be null";
			throw new InvalidInputException(error);
		}
		
		if (equipmentNames.size() <= 1 || !Utility.listHas2DistinctEquipment(equipmentNames)) {
			error = "Equipment bundle must contain at least two distinct types of equipment";
			throw new InvalidInputException(error);
		}

		List<Equipment> equipmentInSystem = climbSafe.getEquipment();
		List<String> storesNames = new ArrayList<String>();
		for (Equipment temp : equipmentInSystem) {
			storesNames.add(temp.getName());
		}

		String missingEquipment = null;
		for (String x : equipmentNames) {
			if (!storesNames.contains(x)) {
				missingEquipment = x;
				break;
			}
		}

		if (missingEquipment != null) {
			error = "Equipment " + missingEquipment + " does not exist";
			throw new InvalidInputException(error);
		}

		// check quantity of equipment
		if (equipmentQuantities == null) {
			error = "List of equipment quantities cannot be null";
			throw new InvalidInputException(error);
		}
		
		if (equipmentNames.size() != equipmentQuantities.size()) {
			error = "List of equipments must have the same length as list of equipment quantities";
			throw new InvalidInputException(error);
		}
		
		if (Utility.quantityIsNotValid(equipmentQuantities)) {
			error = "Each bundle item must have quantity greater than or equal to 1";
			throw new InvalidInputException(error);
		}
		
		if(Utility.bundleExistsInSystem(climbSafe, name)) {
			error = "A bookable item called " + name + " already exists";
			throw new InvalidInputException(error);
		}
		
		try {
			// addBundle() creates a new equipment bundle and adds it to ClimbSafe
			EquipmentBundle equipmentBundle = climbSafe.addBundle(name, discount);
			for (int i = 0; i < equipmentNames.size(); i++) {
				int equipmentQuantity = equipmentQuantities.get(i);
				String equipmentName = equipmentNames.get(i);
				Equipment equipment = Utility.findEquipment(equipmentName);
				climbSafe.addBundleItem(equipmentQuantity, equipmentBundle, equipment);
			}
		} catch (RuntimeException e) {
			error = e.getMessage();
			throw new InvalidInputException(error);
		}
	}
	
	/*
	 * Updates an equipment bundle using its old name, and changes all the bundle's attributes
	 * 
	 * @author Aigiarn Cheuk
	 * @param oldName Old bundle's name
	 * @param newName Bundle's new name
	 * @param newDiscount Bundle's new discount
	 * @param newEquipmentNames Bundle's new list of all the names of the equipment in the bundle
	 * @param newEquipmentQuantities Bundle's new list of all the quantities of the equipment in the bundle
	 */
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
		if(Utility.EquipmenttHasSameNameAsNewBundleName(climbSafe, newName)){
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
		if (newEquipmentNames == null) {
			error = "List of new equipments cannot be null";
			throw new InvalidInputException(error);
		}
		
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
		if (newEquipmentQuantities == null) {
			error = "List of new equipment quantities cannot be null";
			throw new InvalidInputException(error);
		}
		
		if (newEquipmentNames.size() != newEquipmentQuantities.size()) {
			error = "List of new equipments must have the same length as list of new equipment quantities";
			throw new InvalidInputException(error);
		}
		
		if(Utility.quantityIsNotValid(newEquipmentQuantities)) {
			error = "Each bundle item must have quantity greater than or equal to 1";
			throw new InvalidInputException(error);
		}
		
		if(!(newName.equals(oldName)) && Utility.bundleExistsInSystem(climbSafe, newName)) {
			error = "A bookable item called " + newName + " already exists";
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
			// for(int i = 0;i<=equipmentBundle.getBundleItems().size();i++) {
			// 	equipmentBundle.getBundleItem(0).delete();
			// }
			Utility.resetBundleItems(climbSafe, equipmentBundle);
			
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
