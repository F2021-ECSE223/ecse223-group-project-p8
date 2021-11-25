package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import java.sql.Date;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;

import static ca.mcgill.ecse.climbsafe.javafx.fxml.controllers.ViewUtils.successful;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

public class SetupNMCController {
	@FXML
	private TextField adminEmailTextField;
	@FXML
	private TextField passwordTextField;
	@FXML
	private TextField startDateTextField;
	@FXML
	private TextField nrOfWeeksTextField;
	@FXML
	private TextField priceTextField;
	@FXML
	private Button startSeasonButton;
	
	public void startSeasonClicked(ActionEvent event) {
		  String adminEmail = this.adminEmailTextField.getText();
		  if (adminEmail == null || adminEmail.trim().isEmpty()) {
		      ViewUtils.showError("Please input a valid email address");
		  }
		  String password = this.passwordTextField.getText();
		  if (password == null || password.trim().isEmpty()) {
		      ViewUtils.showError("Please input a valid password");
		  }
		  String pricesOfGuide = this.priceTextField.getText();
		  if (pricesOfGuide == null || pricesOfGuide.trim().isEmpty()) {
		      ViewUtils.showError("Please input a valid price for the guides");
		  }
		  
		  try {
			  
			  int priceGuide = Integer.parseInt(pricesOfGuide);
			  int nrOfWeeks = Integer.parseInt(this.nrOfWeeksTextField.getText());
			  Date startDate = Date.valueOf(this.startDateTextField.getText());
			 
			  if (successful(() -> ClimbSafeFeatureSet1Controller.setup(startDate, nrOfWeeks, priceGuide))) {
			        this.adminEmailTextField.setText("");
			        this.startDateTextField.setText("");
			        this.passwordTextField.setText("");
			        this.nrOfWeeksTextField.setText("");
			        this.priceTextField.setText("");
			  }
			  
		  }catch (RuntimeException e) {
		  }
	  }
	
}
