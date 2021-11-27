package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.controller.TOAssignment;
import ca.mcgill.ecse.climbsafe.controller.Utility;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet4Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet5Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet7Controller;
import ca.mcgill.ecse.climbsafe.javafx.fxml.ClimbsafeFxmlView;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewUtils {

  /** Calls the controller and shows an error, if applicable. */
  public static boolean callController(Executable executable) {
    try {
      executable.execute();
      ClimbsafeFxmlView.getInstance().refresh();
      return true;
    } catch (InvalidInputException e) {
      showError(e.getMessage());
    } catch (Throwable t) {
      t.printStackTrace();
    }
    return false;
  }

  /** Calls the controller and returns true on success. This method is included for readability. */
  public static boolean successful(Executable controllerCall) {
    return callController(controllerCall);
  }

  /**
   * Creates a popup window.
   *
   * @param title: title of the popup window
   * @param message: message to display
   */
  public static void makePopupWindow(String title, String message) {
    Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    VBox dialogPane = new VBox();

    // create UI elements
    Text text = new Text(message);
    Button okButton = new Button("OK");
    okButton.setOnAction(a -> dialog.close());

    // display the popup window
    int innerPadding = 10; // inner padding/spacing
    int outerPadding = 100; // outer padding
    dialogPane.setSpacing(innerPadding);
    dialogPane.setAlignment(Pos.CENTER);
    dialogPane.setPadding(new Insets(innerPadding, innerPadding, innerPadding, innerPadding));
    dialogPane.getChildren().addAll(text, okButton);
    Scene dialogScene = new Scene(dialogPane, outerPadding + 5 * message.length(), outerPadding);
    dialog.setScene(dialogScene);
    dialog.setTitle(title);
    dialog.show();
  }

  public static void showError(String message) {
    makePopupWindow("Error", message);
  }
  
  public static ObservableList<TOAssignment> getAssignments() {
    List<TOAssignment> Assignments = ClimbSafeFeatureSet6Controller.getAssignments();
    // as javafx works with observable list, we need to convert the java.util.List to
    // javafx.collections.observableList
    return FXCollections.observableList(Assignments);
  }
  
  //added for member, pls don't touch!-ke
  public static ObservableList<String> getBundles() {
	  List<EquipmentBundle> bundles = ClimbSafeApplication.getClimbSafe().getBundles();
      ObservableList<String> names = FXCollections.observableArrayList();
      for (EquipmentBundle e: bundles) {
          names.add(e.getName());
      }
      return names;
  }
  
  public static ObservableList<String> getEquipment() {
	  List<Equipment> equipment = ClimbSafeApplication.getClimbSafe().getEquipment();
      ObservableList<String> names = FXCollections.observableArrayList();
      for (Equipment e: equipment) {
          names.add(e.getName());
      }
      return names;
  }
  
  public static ObservableList<Integer> getWeeks() {
	  int length = ClimbSafeApplication.getClimbSafe().getNrWeeks();
      ObservableList<Integer> weeks = FXCollections.observableArrayList();
      for (int i=1; i<length+1; i++) {
    	  weeks.add(i);
      }
      return weeks;
  }
  
  public static boolean memberInSystem(String email) {
	  List<Member> members = ClimbSafeApplication.getClimbSafe().getMembers();
      for (Member e:members) {
    	  if (e.getEmail().equals(email)) return true;
      }
      return false;
  }
  
  public static Member findMemberEmail(String email) {
	  return Utility.findMember(email);
  }
  public static String getMemberContact(String email) {
	  return Utility.findMember(email).getEmergencyContact();
  }
  public static String getMemberPassword(String email) {
	  return Utility.findMember(email).getPassword();
  }
  public static int getMemberWeek(String email) {
	  return Utility.findMember(email).getNrWeeks();
  }
  public static boolean getMemberHotel(String email) {
	  return Utility.findMember(email).getHotelRequired();
  }
  public static boolean getMemberGuide(String email) {
	  return Utility.findMember(email).getGuideRequired();
  }
  public static String getMemberItems(String email) {
	  List<BookedItem> items= Utility.findMember(email).getBookedItems();
	  String itemList="";
	  for (BookedItem e: items) {
		  String pairing="";
		  pairing+=e.getItem().getName();
		  pairing+=" x";
		  pairing+=e.getQuantity();
		  pairing+=", ";
		  itemList+=pairing;
	  }
	  return itemList;
  }
  public static String getMemberName(String email) {
	  return Utility.findMember(email).getName();
  }
  

  /*public static ObservableList<TODriver> getDrivers() {
    List<TODriver> drivers = BtmsController.getDrivers();
    // as javafx works with observable list, we need to convert the java.util.List to
    // javafx.collections.observableList
    return FXCollections.observableList(drivers);
  }

  public static ObservableList<TOBusVehicle> getBuses() {
    return FXCollections.observableList(BtmsController.getBuses());
  }

  public static ObservableList<TORoute> getRoutes() {
    return FXCollections.observableList(BtmsController.getRoutes());
  }

  public static ObservableList<TORouteAssignment> getAssignments() {
    return FXCollections.observableList(BtmsController.getAssignments());
  } 
  */
}


@FunctionalInterface
interface Executable {
  public void execute() throws Throwable;
}
