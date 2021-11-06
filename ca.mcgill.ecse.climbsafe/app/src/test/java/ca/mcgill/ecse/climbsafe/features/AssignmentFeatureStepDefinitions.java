package ca.mcgill.ecse.climbsafe.features;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class AssignmentFeatureStepDefinitions {

private ClimbSafe climbSafe;
private String error;

  @Given("the following ClimbSafe system exists:")
  public void the_following_climb_safe_system_exists(io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
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

  @Given("the following pieces of equipment exist in the system:")
  public void the_following_pieces_of_equipment_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.

    List<Map<String, String>> equipmentInfo = dataTable.asMaps(String.class, String.class);

    for (Map<String, String> equipment : equipmentInfo) {
      var name = equipment.get("name");
      var weight = equipment.get("weight");
      var pricePerWeek = equipment.get("pricePerWeek");
      new Equipment(name, Integer.parseInt(weight), Integer.parseInt(pricePerWeek), climbSafe);
    }
  }

  @Given("the following equipment bundles exist in the system:")
  public void the_following_equipment_bundles_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    List<Map<String, String>> equipmentBundleInfo = dataTable.asMaps(String.class, String.class);

    for (Map<String, String> equipmentBundle : equipmentBundleInfo) {
      var name = equipmentBundle.get("name");
      var discount = equipmentBundle.get("discount");
      List<String> equipmentsInBundle = Arrays.asList(equipmentBundle.get("items").split(","));
      List<String> equipmentQuantity = Arrays.asList(equipmentBundle.get("quantity").split(","));
      EquipmentBundle equipmentBundle1 =
          new EquipmentBundle(name, Integer.parseInt(discount), climbSafe);

      for (int i = 0; i < equipmentsInBundle.size(); i++) {
        new BundleItem(Integer.parseInt(equipmentQuantity.get(i)), climbSafe, equipmentBundle1,
            (Equipment) BookableItem.getWithName(equipmentsInBundle.get(i)));
      }
    }
  }

  @Given("the following guides exist in the system:")
  public void the_following_guides_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    List<Map<String, String>> existingGuides = dataTable.asMaps();

    for (var guides : existingGuides) {
      var email = guides.get("email");
      var password = guides.get("password");
      var name = guides.get("name");
      var emergencyContact = guides.get("emergencyContact");
      new Guide(email, password, name, emergencyContact, climbSafe);
    }
  }

  @Given("the following members exist in the system:")
  public void the_following_members_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    List<Map<String, String>> members = dataTable.asMaps();
    for (var member : members) {
      
      List<String> bookedItems = Arrays.asList(member.get("bookedItems").split(","));
      List<String> bookedQuantities = Arrays.asList(member.get("quantity").split(","));

      var newMember = new Member(member.get("email"), member.get("password"), member.get("name"),
          member.get("emergencyContact"),Integer.parseInt(member.get("nrWeeks")),
          Boolean.parseBoolean(member.get("guideRequired")),
          Boolean.parseBoolean(member.get("hotelRequired")), climbSafe);
      
      
      for (int i = 0; i < bookedItems.size(); i++) {
        new BookedItem(Integer.parseInt(bookedQuantities.get(i)), climbSafe, newMember,
            BookableItem.getWithName(bookedItems.get(i)));
      }
    }
  }

  @When("the administrator attempts to initiate the assignment process")
  public void the_administrator_attempts_to_initiate_the_assignment_process() {
    // Write code here that turns the phrase above into concrete actions
	  
		try {
			AssignmentController.initiateAssignmentProcess();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		  
	  
  }

  @Then("the following assignments shall exist in the system:")
  public void the_following_assignments_shall_exist_in_the_system(
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

  @Then("the assignment for {string} shall be marked as {string}")
  public void the_assignment_for_shall_be_marked_as(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the number of assignments in the system shall be {string}")
  public void the_number_of_assignments_in_the_system_shall_be(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Given("the following equipment exists in the system:")
  public void the_following_equipment_exists_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
	  List<Map<String, String>> equipmentInfo = dataTable.asMaps(String.class, String.class);

	    for (Map<String, String> equipment : equipmentInfo) {
	      var name = equipment.get("name");
	      var weight = equipment.get("weight");
	      var pricePerWeek = equipment.get("pricePerWeek");
	      new Equipment(name, Integer.parseInt(weight), Integer.parseInt(pricePerWeek), climbSafe);
	    }
  }

  @Given("the following assignments exist in the system:")
  public void the_following_assignments_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
	  List<Map<String, String>> assignmentInfo = dataTable.asMaps(String.class, String.class);

	    for (Map<String, String> assignment : assignmentInfo) {
	      var memberEmail = assignment.get("memberEmail");
	      var guideEmail = assignment.get("guideEmail");
	      var startWeek = assignment.get("startWeek");
	      var endWeek = assignment.get("endWeek");
	      Assignment myAssignment = new Assignment(Integer.parseInt(startWeek), Integer.parseInt(endWeek), 
	    		  (Member) Member.getWithEmail(memberEmail), climbSafe);
	      myAssignment.setGuide((Guide)Guide.getWithEmail(guideEmail));
	      
	    }
  }

  @When("the administrator attempts to confirm payment for {string} using authorization code {string}")
  public void the_administrator_attempts_to_confirm_payment_for_using_authorization_code(
      String email, String code) {
    // Write code here that turns the phrase above into concrete actions
	  try {
		  AssignmentController.confirmPayment(email,code);
	  }catch(InvalidInputException e){
		  
	  }
  }

  @Then("the assignment for {string} shall record the authorization code {string}")
  public void the_assignment_for_shall_record_the_authorization_code(String string,
      String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the member account with the email {string} does not exist")
  public void the_member_account_with_the_email_does_not_exist(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("there are {string} members in the system")
  public void there_are_members_in_the_system(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the error {string} shall be raised")
  public void the_error_shall_be_raised(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the administrator attempts to cancel the trip for {string}")
  public void the_administrator_attempts_to_cancel_the_trip_for(String email) {
    // Write code here that turns the phrase above into concrete actions
	  
	  try {
		  AssignmentController.cancelTrip(email);
	  }catch(InvalidInputException e){
		  
	  }
  }

  @Given("the member with {string} has paid for their trip")
  public void the_member_with_has_paid_for_their_trip(String memberEmail) {
    // Write code here that turns the phrase above into concrete actions
	  Member member = (Member) Member.getWithEmail(memberEmail);
	  //member.hasPaidTrip
    
  }

  @Then("the member with email address {string} shall receive a refund of {string} percent")
  public void the_member_with_email_address_shall_receive_a_refund_of_percent(String string,
      String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Given("the member with {string} has started their trip")
  public void the_member_with_has_started_their_trip(String memberEmail) {
    // Write code here that turns the phrase above into concrete actions
    Member member = (Member) Member.getWithEmail(memberEmail);
  }

  @When("the administrator attempts to finish the trip for the member with email {string}")
  public void the_administrator_attempts_to_finish_the_trip_for_the_member_with_email(
      String email) {
    // Write code here that turns the phrase above into concrete actions
	  try {
		  AssignmentController.finishTrip(email);
	  }catch(InvalidInputException e){
		  
	  }
  }

  @Given("the member with {string} is banned")
  public void the_member_with_is_banned(String memberEmail) {
    // Write code here that turns the phrase above into concrete actions
	  Member member = (Member) Member.getWithEmail(memberEmail);
  }

  @Then("the member with email {string} shall be {string}")
  public void the_member_with_email_shall_be(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the administrator attempts to start the trips for week {string}")
  public void the_administrator_attempts_to_start_the_trips_for_week(String weekNr) {
    // Write code here that turns the phrase above into concrete actions
	  try {
		  AssignmentController.startTrip(Integer.parseInt(weekNr));
	  }catch(InvalidInputException e){
		  
	  }
  }

  @Given("the member with {string} has cancelled their trip")
  public void the_member_with_has_cancelled_their_trip(String memberEmail) {
    // Write code here that turns the phrase above into concrete actions
	  Member member = (Member) Member.getWithEmail(memberEmail);
  }

  @Given("the member with {string} has finished their trip")
  public void the_member_with_has_finished_their_trip(String memberEmail) {
    // Write code here that turns the phrase above into concrete actions
    Member member = (Member) Member.getWithEmail(memberEmail);
  }

  @Then("the member with email {string} shall be banned")
  public void the_member_with_email_shall_be_banned(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
}
