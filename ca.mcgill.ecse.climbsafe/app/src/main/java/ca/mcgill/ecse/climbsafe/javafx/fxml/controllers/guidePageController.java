package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.control.ToggleButton;

public class guidePageController {
	@FXML
	private TextField guideNameTextField;
	@FXML
	private TextField guideEmailTextField;
	@FXML
	private TextField guidePasswordTextField;
	@FXML
	private TextField guideEmergencyTextField;
	@FXML
	private ToggleButton registerButton;
	@FXML
	private TextField updateNameTextField;
	@FXML
	private TextField updateEmailTextField;
	@FXML
	private TextField updatePasswordTextField;
	@FXML
	private TextField updateContactTextField;
	@FXML
	private ToggleButton updateButton;
	@FXML
	private TextField deleteGuideTextField;
	@FXML
	private ToggleButton deleteButton;

	// Event Listener on ToggleButton[#registerButton].onAction
	@FXML
	public void registerClicked(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on ToggleButton[#updateButton].onAction
	@FXML
	public void updateClicked(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on ToggleButton[#deleteButton].onAction
	@FXML
	public void deleteClicked(ActionEvent event) {
		// TODO Autogenerated
	}
}
