package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

//this file works
import javafx.fxml.FXML;

//own imports
import static ca.mcgill.ecse.climbsafe.javafx.fxml.controllers.ViewUtils.successful;
import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.climbsafe.javafx.fxml.ClimbsafeFxmlView;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import javafx.fxml.FXML;
import java.lang.*;
import java.sql.Date;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.control.ChoiceBox;

public class memberRetryController {
	//Array lists to store
		List<String> itemNames= new ArrayList<String>();
		List<Integer> itemQuantities= new ArrayList<Integer>();
		boolean wantHotel=false;
		boolean wantGuide=false;
		
	@FXML
	private TextField registerEmail;
	@FXML
	private TextField registerName;
	@FXML
	private Button RegisterSubmit;
	@FXML
	private TextField registerContact;
	@FXML
	private TextField registerPassword;
	@FXML
	private ChoiceBox<Integer> registerWeeks;
	@FXML
	private ChoiceBox<String> registerEquipmentChoice;
	@FXML
	private Button registerEquipmentSubmit;
	@FXML
	private TextField registerEquipmentQuantity;
	@FXML
	private ChoiceBox<String> registerBundleChoice;
	@FXML
	private Button registerBundleSubmit;
	@FXML
	private TextField registerBundleQuantity;
	@FXML
	private Button registerGuideToggleY;
	@FXML
	private Button registerGuideToggleN;
	@FXML
	private Button registerHotelToggleY;
	@FXML
	private Button registerHotelToggleN;
	@FXML
	private TextField updateName;
	@FXML
	private Button updateSubmit;
	@FXML
	private TextField updateContact;
	@FXML
	private TextField updatePassword;
	@FXML
	private ChoiceBox<Integer> updateWeeks;
	@FXML
	private ChoiceBox<String> updateEquipmentChoice;
	@FXML
	private Button updateEquipmentSubmit;
	@FXML
	private TextField updateEquipmentQuantity;
	@FXML
	private ChoiceBox<String> updateBundleChoice;
	@FXML
	private Button updateBundleSubmit;
	@FXML
	private TextField updateBundleQuantity;
	@FXML
	private TextField updateEmail;
	@FXML
	private Button updateGuideToggleN;
	@FXML
	private Button updateGuideToggleY;
	@FXML
	private Button updateHotelToggleY;
	@FXML
	private Button updateHotelToggleN;
	@FXML
	private Button deleteSubmit;
	@FXML
	private TextField deleteEmail;

	
	@FXML
	public void initialize() {
        registerBundleChoice.addEventHandler(ClimbsafeFxmlView.REFRESH_EVENT, e -> {
          registerBundleChoice.setItems(ViewUtils.getBundles());
          registerBundleChoice.setValue(null);
        });
        
        registerEquipmentChoice.addEventHandler(ClimbsafeFxmlView.REFRESH_EVENT, e -> {
        	registerEquipmentChoice.setItems(ViewUtils.getEquipment());
        	registerEquipmentChoice.setValue(null);
          });
        
        registerWeeks.addEventHandler(ClimbsafeFxmlView.REFRESH_EVENT, e -> {
        	registerWeeks.setItems(ViewUtils.getWeeks());
        	registerWeeks.setValue(null);
          });
        
        
        
        ClimbsafeFxmlView.getInstance().registerRefreshEvent(registerBundleChoice, registerEquipmentChoice, registerWeeks);
        //ClimbsafeFxmlView.getInstance().registerRefreshEvent(registerBundleChoice);
      }
	
	
	// Event Listener on Button[#RegisterSubmit].onAction
	@FXML
	public void RegisterMemberRegisterSubmit(ActionEvent event) {
		
		String email=registerEmail.getText();
		String password=registerPassword.getText();
		String name=registerName.getText();
		String emergencyContact=registerContact.getText();
		int nrWeeks=registerWeeks.getValue();
//		boolean guideRequired=wantGuide;
//		boolean hotelRequired=wantHotel;
//		List<String> itemNames=this.itemNames;
//		List<Integer> itemQuantities=this.itemQuantities;
		if (email == null || email.trim().isEmpty()) {
		      ViewUtils.showError("Please input a valid email");
		    }
		else if (password == null || password.trim().isEmpty()) {
		      ViewUtils.showError("Please input a valid password");
		    }
		else if (name == null || name.trim().isEmpty()) {
		      ViewUtils.showError("Please input a valid name");
		    }
		else if (emergencyContact == null || emergencyContact.trim().isEmpty()) {
		      ViewUtils.showError("Please input a valid emergencyContact");
		    }
		//do we need this
//		else if (nrWeeks == null || nrWeeks.trim().isEmpty()) {
//		      ViewUtils.showError("Please input a valid nrWeeks");
//		    }
		else {
			try {				 
				  if (successful(() -> ClimbSafeFeatureSet2Controller.registerMember(email, password, name, emergencyContact, nrWeeks, wantGuide, wantHotel, itemNames, itemQuantities))) {
						updateEmail.setText("");
						updatePassword.setText("");
						updateName.setText("");
						updateContact.setText("");
						//cleanup? not sure if really needed
						wantGuide=false;
						wantHotel=false;
						itemNames.clear();
						itemQuantities.clear();
						
				      }
				  
			  }catch (RuntimeException e) {
				  ViewUtils.showError(e.getMessage());
			  }
			
		}
	}
	// Event Listener on Button[#registerEquipmentSubmit].onAction
	@FXML
	public void RegisterMemberEquipmentSubmit(ActionEvent event) {
		String equipment= registerEquipmentChoice.getValue();
		String quantity= registerEquipmentQuantity.getText();
	    if (equipment == null || equipment.trim().isEmpty()) {
		      ViewUtils.showError("Please select a valid equipment");
		      }
	    //do we have to check if it's a number?
	    if (quantity == null || quantity.trim().isEmpty()) {
	    	ViewUtils.showError("Please enter a valid quantity");
	    }
	    else {
	    	itemNames.add(equipment);
	    	itemQuantities.add(Integer.parseInt(quantity));
	    }
	}
	// Event Listener on Button[#registerBundleSubmit].onAction
	@FXML
	public void RegisterMemberBundleSubmit(ActionEvent event) {
		String equipment= registerEquipmentChoice.getValue();
		String quantity= registerEquipmentQuantity.getText();
	    if (equipment == null || equipment.trim().isEmpty()) {
		      ViewUtils.showError("Please select a valid bundle");
		      }
	    //do we have to check if it's a number?
	    if (quantity == null || quantity.trim().isEmpty()) {
	    	ViewUtils.showError("Please enter a valid quantity");
	    }
	    else {
	    	itemNames.add(equipment);
	    	itemQuantities.add(Integer.parseInt(quantity));
	    }
	}
	// Event Listener on Button[#registerGuideToggleY].onAction
	@FXML
	public void registerGuideYes(ActionEvent event) {
		wantGuide=true;
	}
	// Event Listener on Button[#registerGuideToggleN].onAction
	@FXML
	public void registerGuideNo(ActionEvent event) {
		wantGuide=false;
	}
	// Event Listener on Button[#registerHotelToggleY].onAction
	@FXML
	public void registerHotelYes(ActionEvent event) {
		wantHotel=true;
	}
	// Event Listener on Button[#registerHotelToggleN].onAction
	@FXML
	public void registerHotelNo(ActionEvent event) {
		wantHotel=false;
	}
	// Event Listener on Button[#updateSubmit].onAction
	@FXML
	public void updateMemberSubmit(ActionEvent event) {
		String email=updateEmail.getText();
		String password=updatePassword.getText();
		String name=updateName.getText();
		String emergencyContact=updateContact.getText();
		int nrWeeks=updateWeeks.getValue();
		
		if (email == null || email.trim().isEmpty()) {
		      ViewUtils.showError("Please input a valid email");
		    }
		else if (password == null || password.trim().isEmpty()) {
		      ViewUtils.showError("Please input a valid password");
		    }
		else if (name == null || name.trim().isEmpty()) {
		      ViewUtils.showError("Please input a valid name");
		    }
		else if (emergencyContact == null || emergencyContact.trim().isEmpty()) {
		      ViewUtils.showError("Please input a valid emergencyContact");
		    }

		else {
			try {				 
				  if (successful(() -> ClimbSafeFeatureSet2Controller.registerMember(email, password, name, emergencyContact, nrWeeks, wantGuide, wantHotel, itemNames, itemQuantities))) {
						updateEmail.setText("");
						updatePassword.setText("");
						updateName.setText("");
						updateContact.setText("");
						//cleanup? not sure if really needed
						wantGuide=false;
						wantHotel=false;
						itemNames.clear();
						itemQuantities.clear();
						
				      }
				  
			  }catch (RuntimeException e) {
				  ViewUtils.showError(e.getMessage());
			  }
			
		}
	}
	// Event Listener on Button[#updateEquipmentSubmit].onAction
	@FXML
	public void UpdateMemberEquipmentSubmit(ActionEvent event) {
		String equipment= updateEquipmentChoice.getValue();
		String quantity= updateEquipmentQuantity.getText();
	    if (equipment == null || equipment.trim().isEmpty()) {
		      ViewUtils.showError("Please select a valid equipment");
		      }
	    //do we have to check if it's a number?
	    if (quantity == null || quantity.trim().isEmpty()) {
	    	ViewUtils.showError("Please enter a valid quantity");
	    }
	    else {
	    	itemNames.add(equipment);
	    	itemQuantities.add(Integer.parseInt(quantity));
	    }
	}
	// Event Listener on Button[#updateBundleSubmit].onAction
	@FXML
	public void UpdateMemberBundleSubmit(ActionEvent event) {
		String equipment= updateEquipmentChoice.getValue();
		String quantity= updateEquipmentQuantity.getText();
	    if (equipment == null || equipment.trim().isEmpty()) {
		      ViewUtils.showError("Please select a valid bundle");
		      }
	    //do we have to check if it's a number?
	    if (quantity == null || quantity.trim().isEmpty()) {
	    	ViewUtils.showError("Please enter a valid quantity");
	    }
	    else {
	    	itemNames.add(equipment);
	    	itemQuantities.add(Integer.parseInt(quantity));
	    }
	}
	// Event Listener on Button[#updateGuideToggleN].onAction
	@FXML
	public void updateGuideNo(ActionEvent event) {
		wantGuide=false;
	}
	// Event Listener on Button[#updateGuideToggleY].onAction
	@FXML
	public void updateGuideYes(ActionEvent event) {
		wantGuide=true;
	}
	// Event Listener on Button[#updateHotelToggleY].onAction
	@FXML
	public void updateHotelYes(ActionEvent event) {
		wantHotel=true;
	}
	// Event Listener on Button[#updateHotelToggleN].onAction
	@FXML
	public void updateHotelNo(ActionEvent event) {
		wantHotel=false;
	}
	// Event Listener on Button[#deleteSubmit].onAction
	@FXML
	public void deleteSubmit(ActionEvent event) {
		String email = deleteEmail.getText();
	    if (email == null || email.trim().isEmpty()) {
	      ViewUtils.showError("Please input a valid email");
	    }
	      else {
	  
	      if (successful(() -> ClimbSafeFeatureSet1Controller.deleteMember(email))) {
	    	  deleteEmail.setText("");
	        ClimbsafeFxmlView.getInstance().refresh();
	      }
	      }
	}
	
//	@FXML
////	public void selectEquipment(ActionEvent event) {
////		registerEquipmentChoice.getValue()
////	}
	
}
