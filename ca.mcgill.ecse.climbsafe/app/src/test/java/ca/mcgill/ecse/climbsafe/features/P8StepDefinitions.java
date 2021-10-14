package ca.mcgill.ecse.climbsafe.features;

import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class P8StepDefinitions {
	
	private ClimbSafe climbSafe;
	
  //@Joey 
  @Given("the following ClimbSafe system exists: \\(p8)")
  public void the_following_climb_safe_system_exists_p8(io.cucumber.datatable.DataTable dataTable) {
	  // Write code here that turns the phrase above into concrete actions
	  // For automatic transformation, change DataTable to one of
	  // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	  // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	  // Double, Byte, Short, Long, BigInteger or BigDecimal.
	  //
	  // For other transformations you can register a DataTableType.
	  List<Map<String,String>> climbSafe1 = dataTable.asMaps(String.class,String.class);
	  var date = climbSafe1.get(0).get("startDate");
	  var weeks = climbSafe1.get(0).get("nrWeeks");
	  var price = climbSafe1.get(0).get("priceOfGuidesPerWeek");
	  
	  climbSafe = ClimbSafeApplication.getClimbSafe();
	  climbSafe.setStartDate(java.sql.Date.valueOf(date));
	  climbSafe.setNrWeeks(Integer.parseInt(weeks));
	  climbSafe.setPriceOfGuidePerWeek(Integer.parseInt(price));
	  throw new io.cucumber.java.PendingException();
  }

  //@Joey
  @Given("the following equipment exists in the system: \\(p8)")
  public void the_following_equipment_exists_in_the_system_p8(
      io.cucumber.datatable.DataTable dataTable) {
	  // Write code here that turns the phrase above into concrete actions
	  // For automatic transformation, change DataTable to one of
	  // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	  // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	  // Double, Byte, Short, Long, BigInteger or BigDecimal.
	  //
	  // For other transformations you can register a DataTableType.
	  
	  List<Map<String,String>> equipmentInfo = dataTable.asMaps(String.class, String.class); 
	  
	  for(Map<String,String> equipment : equipmentInfo) {
		  var name = equipment.get("name");
		  var weight = equipment.get("weight");
		  var pricePerWeek = equipment.get("pricePerWeek");
		  new Equipment(name, Integer.parseInt(weight), Integer.parseInt(pricePerWeek), climbSafe);
	  }
	  
	  throw new io.cucumber.java.PendingException();
  }

  //@Aigiarn
  @Given("the following equipment bundles exist in the system: \\(p8)")
  public void the_following_equipment_bundles_exist_in_the_system_p8(
      io.cucumber.datatable.DataTable dataTable) {
	  // Write code here that turns the phrase above into concrete actions
	  // For automatic transformation, change DataTable to one of
	  // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	  // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	  // Double, Byte, Short, Long, BigInteger or BigDecimal.
	  //
	  // For other transformations you can register a DataTableType.
	  throw new io.cucumber.java.PendingException();
  }

  @When("the administrator attempts to update the equipment bundle {string} to have name {string}, discount {string}, items {string}, and quantities {string} \\(p8)")
  public void the_administrator_attempts_to_update_the_equipment_bundle_to_have_name_discount_items_and_quantities_p8(
      String string, String string2, String string3, String string4, String string5) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the number of equipment bundles in the system shall be {string} \\(p8)")
  public void the_number_of_equipment_bundles_in_the_system_shall_be_p8(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the equipment bundle {string} shall contain the items {string} with quantities {string} \\(p8)")
  public void the_equipment_bundle_shall_contain_the_items_with_quantities_p8(String string,
      String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  //@Ke
  @Then("the equipment bundle {string} shall have a discount of {string} \\(p8)")
  public void the_equipment_bundle_shall_have_a_discount_of_p8(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  //@Aigiarn
  //@Mihail
  @Then("the equipment bundle {string} shall not exist in the system \\(p8)")
  public void the_equipment_bundle_shall_not_exist_in_the_system_p8(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  //@Ke
  @Then("the number of pieces of equipment in the system shall be {string} \\(p8)")
  public void the_number_of_pieces_of_equipment_in_the_system_shall_be_p8(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the error {string} shall be raised \\(p8)")
  public void the_error_shall_be_raised_p8(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
}
