package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

//k
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Hotel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.Spinner;

import javafx.scene.control.ChoiceBox;

public class memberPageController {
	@FXML
	private TextField rmEmail;
	@FXML
	private TextField rmName;
	@FXML
	private ChoiceBox<Hotel> rmHotel;
	@FXML
	private ChoiceBox<Guide> rmWantGuide;
	@FXML
	private ChoiceBox<Hotel> rmWantHotel;
	@FXML
	private Button rmSubmit;
	@FXML
	private TextField rmContact;
	@FXML
	private TextField rmPassword;
	@FXML
	private ChoiceBox<Integer> rmWeeks;
	@FXML
	private Spinner<Integer> rmEquipmentQuantity;
	@FXML
	private ChoiceBox<Equipment> rmEquipment;
	@FXML
	private Button rmEquipmentSubbmit;
	@FXML
	private Spinner<Integer> rmBundleQuantity;
	@FXML
	private ChoiceBox<EquipmentBundle> rmBudle;
	@FXML
	private Button rmBundleSubmit;
	@FXML
	private ChoiceBox<Guide> rmGuide;

	
	public void registerMemberClicked(ActionEvent event) {
		String memberName = this.rmName.getText();
		if (memberName == null || memberName.trim().isEmpty()) {
		      ViewUtils.showError("Please input a valid name");
		}
		String emergencyContact = this.rmContact.getText();
		if (emergencyContact == null || emergencyContact.trim().isEmpty()) {
		      ViewUtils.showError("Please input a valid name");
		}
		String password = this.rmPassword.getText();
		if (password == null || password.trim().isEmpty()) {
		      ViewUtils.showError("Please input a valid name");
		}
	}
	
	public void updateMemberClicked(ActionEvent event) {
		
	}
	
	public void deleteMemberClicked(ActionEvent event) {
		
	}
	
	
	
}
