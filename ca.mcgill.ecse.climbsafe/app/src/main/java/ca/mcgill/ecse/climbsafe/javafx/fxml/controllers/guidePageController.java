package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import static ca.mcgill.ecse.climbsafe.javafx.fxml.controllers.ViewUtils.successful;


import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import ca.mcgill.ecse.climbsafe.javafx.fxml.ClimbsafeFxmlView;

public class guidePageController {
	@FXML
	private TextField guideName;
	@FXML
	private TextField guideEmail;
	@FXML
	private TextField guidePassword;
	@FXML
	private TextField guideEmergencyContact;
	@FXML
	private Button registerGuideButton;
	@FXML
	private TextField updateGuideName;
	@FXML
	private TextField updateGuideEmail;
	@FXML
	private TextField updateGuidePassword;
	@FXML
	private TextField updateGuideContact;
	@FXML
	private Button updateGuideButton;
	@FXML
	private TextField deleteGuideEmail;
	@FXML
	private Button deleteGuideButton;

	// Event Listener on Button[#registerGuideButton].onAction
	@FXML
	public void registerGuideClicked(ActionEvent event) {
		
		try {
			String nameOfGuide=guideName.getText();
			String emailOfGuide=guideEmail.getText();
			String passwordOfGuide=guidePassword.getText();
			String contactOfGuide=guideEmergencyContact.getText();
			
			if(nameOfGuide == null || nameOfGuide.trim().isEmpty()) {
				String nameError="Please input a valid name";
				ViewUtils.showError(nameError);
			}
			
			else if(emailOfGuide == null || emailOfGuide.trim().isEmpty()) {
				String emailError="Please input a valid email address";
				ViewUtils.showError(emailError);
			}
			
			else if(passwordOfGuide == null || passwordOfGuide.trim().isEmpty()) {
				String passwordError="Please input a valid password";
				ViewUtils.showError(passwordError);
			}
			
			else if(contactOfGuide == null || contactOfGuide.trim().isEmpty()) {
				String contactError="Please input a valid emergency contact";
				ViewUtils.showError(contactError);
			}
			else {
				if(successful(() -> ClimbSafeFeatureSet3Controller.registerGuide(emailOfGuide, passwordOfGuide, nameOfGuide, contactOfGuide))){
					guideName.setText("");
					guideEmail.setText("");
					guidePassword.setText("");
					guideEmergencyContact.setText("");
					ClimbsafeFxmlView.getInstance().refresh();
					ViewUtils.makePopupWindow("", "Successfully Registered");
				}
			}
			
		}
		
		catch (RuntimeException e) {
		      ViewUtils.showError(e.getMessage());
		    }
	}
	
	// Event Listener on Button[#updateGuideButton].onAction
	@FXML
	public void updateGuideClicked(ActionEvent event) {
		try {
			String newNameOfGuide=updateGuideName.getText();
			String sameEmailOfGuide=updateGuideEmail.getText();
			String newPasswordOfGuide=updateGuidePassword.getText();
			String newContactOfGuide=updateGuideContact.getText();
			
			if(newNameOfGuide == null || newNameOfGuide.trim().isEmpty()) {
				String nameError="Please input a valid name to update";
				ViewUtils.showError(nameError);
			}
			
			else if(sameEmailOfGuide == null || sameEmailOfGuide.trim().isEmpty()) {
				String emailError="Please input a valid email address to update";
				ViewUtils.showError(emailError);
			}
			
			else if(newPasswordOfGuide == null || newPasswordOfGuide.trim().isEmpty()) {
				String passwordError="Please input a valid password to update";
				ViewUtils.showError(passwordError);
			}
			
			else if(newContactOfGuide == null || newContactOfGuide.trim().isEmpty()) {
				String contactError="Please input a valid emergency contact to update";
				ViewUtils.showError(contactError);
			}
		    
		    else {
				if(successful(() -> ClimbSafeFeatureSet3Controller.updateGuide(sameEmailOfGuide, newPasswordOfGuide, newNameOfGuide, newContactOfGuide))){
					updateGuideName.setText("");
					updateGuideEmail.setText("");
					updateGuidePassword.setText("");
					updateGuideContact.setText("");
					ClimbsafeFxmlView.getInstance().refresh();
					ViewUtils.makePopupWindow("", "Successfully Updated");
					
				}
			}
			
		}
		
		catch (RuntimeException e) {
		      ViewUtils.showError(e.getMessage());
		    }
		
	}
	// Event Listener on Button[#deleteGuideButton].onAction
	@FXML
	public void deleteGuideClicked(ActionEvent event) {
		try {
			String emailtoDelete=deleteGuideEmail.getText();
			
			if(emailtoDelete == null || emailtoDelete.trim().isEmpty()) {
				String deleteGuideError="Please input a valid email to delete";
				ViewUtils.showError(deleteGuideError);
			}
			else {
				if(successful(() -> ClimbSafeFeatureSet1Controller.deleteGuide(emailtoDelete))){
					deleteGuideEmail.setText("");
					ClimbsafeFxmlView.getInstance().refresh();
					ViewUtils.makePopupWindow("", "Successfully Deleted");
					
				}
			}
			
		}
		
		catch (RuntimeException e) {
		      ViewUtils.showError(e.getMessage());
		    }
		
		
	}
}
