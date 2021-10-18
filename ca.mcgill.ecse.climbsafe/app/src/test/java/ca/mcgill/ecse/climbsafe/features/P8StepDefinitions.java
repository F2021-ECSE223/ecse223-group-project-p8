package ca.mcgill.ecse.climbsafe.features;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet5Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

public class P8StepDefinitions {

	private ClimbSafe climbSafe;
	private String error;

	// @Joey
	@Given("the following ClimbSafe system exists: \\(p8)")
	public void the_following_climb_safe_system_exists_p8(io.cucumber.datatable.DataTable dataTable) {
		
		List<Map<String, String>> climbSafe1 = dataTable.asMaps(String.class, String.class);
		var date = climbSafe1.get(0).get("startDate");
		var weeks = climbSafe1.get(0).get("nrWeeks");
		var price = climbSafe1.get(0).get("priceOfGuidePerWeek");
		error = "";
		climbSafe = ClimbSafeApplication.getClimbSafe();
		climbSafe.setStartDate(java.sql.Date.valueOf(date));
		climbSafe.setNrWeeks(Integer.parseInt(weeks));
		climbSafe.setPriceOfGuidePerWeek(Integer.parseInt(price));
	}

	// @Joey
	@Given("the following equipment exists in the system: \\(p8)")
	public void the_following_equipment_exists_in_the_system_p8(io.cucumber.datatable.DataTable dataTable) {
	
		List<Map<String, String>> equipmentInfo = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> equipment : equipmentInfo){
			var name = equipment.get("name");
			var weight = equipment.get("weight");
			var pricePerWeek = equipment.get("pricePerWeek");
			new Equipment(name, Integer.parseInt(weight), Integer.parseInt(pricePerWeek), climbSafe);
		}
	}

	// @Aigiarn
	@Given("the following equipment bundles exist in the system: \\(p8)")
	public void the_following_equipment_bundles_exist_in_the_system_p8(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.

		List<Map<String, String>> equipmentBundleInfo = dataTable.asMaps(String.class, String.class);
		System.out.println(equipmentBundleInfo.toString());
		
		for (Map<String, String> equipmentBundle : equipmentBundleInfo){
			var name = equipmentBundle.get("name");
			var discount = equipmentBundle.get("discount");
			List<String> equipmentsInBundle = new ArrayList<String>(Arrays.asList(equipmentBundle.get("items").split(",")));
			List<String> equipmentQuantity = new ArrayList<String>(Arrays.asList(equipmentBundle.get("quantities").split(",")));
			EquipmentBundle equipmentBundle1 = new EquipmentBundle(name, Integer.parseInt(discount), climbSafe);
			
			for (int i = 0; i < equipmentsInBundle.size(); i++) {
				new BundleItem(Integer.parseInt(equipmentQuantity.get(i)), climbSafe, equipmentBundle1,(Equipment) BookableItem.getWithName(equipmentsInBundle.get(i)));
			}
		}
	}

	// @Maya
	@When("the administrator attempts to update the equipment bundle {string} to have name {string}, discount {string}, items {string}, and quantities {string} \\(p8)")
	public void the_administrator_attempts_to_update_the_equipment_bundle_to_have_name_discount_items_and_quantities_p8(String string, String string2, String string3, String string4, String string5) {
		List<String> newEquipmentNames = new ArrayList<String>(Arrays.asList(string4.split(",")));
		List<String> newEquipmentQuantities = new ArrayList<String>(Arrays.asList(string5.split(",")));
		List<Integer> newEquipmentQuantInt = new ArrayList<Integer>();
		
		for (String s : newEquipmentQuantities){
			newEquipmentQuantInt.add(Integer.valueOf(s));
		}
		
		try {
			ClimbSafeFeatureSet5Controller.updateEquipmentBundle(string, string2, Integer.parseInt(string3), newEquipmentNames, newEquipmentQuantInt);
		} catch (InvalidInputException e){
			error += e.getMessage();
		}
	}

	// @Maya
	@Then("the number of equipment bundles in the system shall be {string} \\(p8)")
	public void the_number_of_equipment_bundles_in_the_system_shall_be_p8(String string) {
		if (climbSafe.hasBundles()){
			assertEquals(Integer.parseInt(string), climbSafe.numberOfBundles());
		}
	}

	// @Aigiarn, Joey, Ke
	@Then("the equipment bundle {string} shall contain the items {string} with quantities {string} \\(p8)")
	public void the_equipment_bundle_shall_contain_the_items_with_quantities_p8(String bundleName, String itemNames,String quantityStrings) {

		boolean found=false;
		List<String> itemNamesCleaned = new ArrayList<String>(Arrays.asList(itemNames.split(",")));
		List<String> quantityStringsCleaned = new ArrayList<String>(Arrays.asList(quantityStrings.split(",")));
		EquipmentBundle equipmentBundle;
		Equipment currentItem;
		
		
		List<EquipmentBundle> equipmentBundleList = climbSafe.getBundles();
		
		for (EquipmentBundle temp : equipmentBundleList) {
			if (temp.getName().equals(bundleName)) {
				equipmentBundle = temp;	
		}
		
		assertNotNull(equipmentBundle);
		
		for (int i=0; i<itemNamesCleaned.size(); i++) {
			int quantity = Integer.parseInt(quantityStringsCleaned.get(i));
			
			for (BundleItem temp2 : equipmentBundle.getBundleItems()) {
				if (temp2.getEquipment().getName().equals(itemNamesCleaned.get(i))) {
					currentItem=temp2.getEquipment();
				}
			}
			
			assertNotNull(currentItem);
			
			assertEquals(quantity, equipmentBundle.getQuantity());
			
		}
	}	
		
	}

	// @Ke
	@Then("the equipment bundle {string} shall have a discount of {string} \\(p8)")
	public void the_equipment_bundle_shall_have_a_discount_of_p8(String bundleName, String discountString) {
		
		int discount = Integer.parseInt(discountString);
		EquipmentBundle equipmentBundle;
		
		List<EquipmentBundle> equipmentBundleList = climbSafe.getBundles();
		
		for (EquipmentBundle temp : equipmentBundleList) {
			if (temp.getName.equals(bundleName)) {
				equipmentBundle = temp;	
			}
		}
		
		assertNotNull(equipmentBundle);
		
		assertEquals(discount, equipmentBundle.getDiscount());
	}

	// @Mihail
	@Then("the equipment bundle {string} shall not exist in the system \\(p8)")
	public void the_equipment_bundle_shall_not_exist_in_the_system_p8(String string) {
		//assertNull(EquipmentBundle.getWithName(string));
		
		List<EquipmentBundle> equipmentBundleList = climbSafe.getBundles();
		EquipmentBundle equipmentBundle;
		
		for (EquipmentBundle temp : equipmentBundleList) {
			if (temp.getName.equals(bundleName)) {
				equipmentBundle= temp;
			}
		}
		
		assertNull(equipmentBundle);
		
	}

	// @Ke
	@Then("the number of pieces of equipment in the system shall be {string} \\(p8)")
	public void the_number_of_pieces_of_equipment_in_the_system_shall_be_p8(String totalEquipment) {
		assertEquals(Integer.parseInt(totalEquipment), climbSafe.numberOfEquipment());
	}

	// @Selina
	@Then("the error {string} shall be raised \\(p8)")
	public void the_error_shall_be_raised_p8(String string) {
		assertTrue(error.contains(string));
	}

	@After
	public void tearDown() {
		
		climbSafe.delete();
		
	}
}
