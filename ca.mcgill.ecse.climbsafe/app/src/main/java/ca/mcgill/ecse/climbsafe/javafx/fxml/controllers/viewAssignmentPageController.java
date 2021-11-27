package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import javafx.fxml.FXML;
import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.TOAssignment;
import ca.mcgill.ecse.climbsafe.javafx.fxml.ClimbsafeFxmlView;
import static ca.mcgill.ecse.climbsafe.javafx.fxml.controllers.ViewUtils.successful;
import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

public class viewAssignmentPageController {
	@FXML
	private TableView<TOAssignment> overviewTable;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField codeTextField;
	@FXML
	private Button startButton;
	@FXML
	private Button finishButton;
	@FXML
	private Button CancelButton;
	@FXML
	private TextField weekTextField;
	@FXML
	private Button payButton;
	@FXML
	private Button initiateButton;
	
	
	// Event Listener on Button[#startButton].onAction
	@FXML
	public void startClicked(ActionEvent event) {
		var error = "";
		try {
			int startWeek = Integer.parseInt(this.weekTextField.getText());
			if(successful(() -> AssignmentController.startTrips(startWeek))) {
				this.weekTextField.setText("");
			}
			
		}catch(RuntimeException e) {
			error += e.getMessage();
		}
	}
	// Event Listener on Button[#finishButton].onAction
	@FXML
	public void finishClicked(ActionEvent event) {
		var error = "";
		String memberEmail = this.emailTextField.getText();
		if (memberEmail == null || memberEmail.trim().isEmpty()) {
		    error += "Please input a valid member email" + "\n";
		}
		try {
			if(successful(() -> AssignmentController.finishTrip(memberEmail))) {
				this.emailTextField.setText("");
			}
		}catch(RuntimeException e) {
			error += e.getMessage();
		}
	}
	// Event Listener on Button[#CancelButton].onAction
	@FXML
	public void cancelClicked(ActionEvent event) {
		var error = "";
		String memberEmail = this.emailTextField.getText();
		if (memberEmail == null || memberEmail.trim().isEmpty()) {
		    error += "Please input a valid member email" + "\n";
		}
		try {
			if(successful(() -> AssignmentController.finishTrip(memberEmail))) {
				this.emailTextField.setText("");
			}
		}catch(RuntimeException e) {
			error += e.getMessage();
		}
	}
	// Event Listener on Button[#payButton].onAction
	@FXML
	public void payClicked(ActionEvent event) {
		var error = "";
		String memberEmail = this.emailTextField.getText();
		if (memberEmail == null || memberEmail.trim().isEmpty()) {
		    error += "Please input a valid member email" + "\n";
		}
		String authorizationCode = this.codeTextField.getText();
		if (authorizationCode == null || authorizationCode.trim().isEmpty()) {
		    error += "Please input a valid authorization code" + "\n";
		}
		try {
			if(successful(() -> AssignmentController.payTrip(memberEmail,authorizationCode))) {
				this.emailTextField.setText("");
			}
		}catch(RuntimeException e) {
			error += e.getMessage();
		}
	}
	// Event Listener on Button[#initiateButton].onAction
	@FXML
	public void initiateClicked(ActionEvent event) {
		// TODO Autogenerated
	}
}
