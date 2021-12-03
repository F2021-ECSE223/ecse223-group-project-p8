package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import javafx.fxml.FXML;
import static ca.mcgill.ecse.climbsafe.javafx.fxml.controllers.ViewUtils.successful;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.time.LocalDate;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.javafx.fxml.ClimbsafeFxmlView;
import javafx.event.ActionEvent;


public class setupNMCPageController {
	

	/**
	 * @author Joey Liu
	 * controller for the setup NMC page
	 * allows admin to setup NMC program information
	 */
	@FXML
	private TextField adminEmailTextField;
	@FXML
	private TextField passwordTextField;
	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField nrOfWeeksTextField;
	@FXML
	private TextField priceTextField;
	@FXML
	private Button startSeasonButton;

	/**
	 * method called when start button is clicked and starts the climbing season
	 * @author Joey Liu
	 * @param event
	 */
	@FXML
	public void startSeasonClicked(ActionEvent event) {
		var error = "";
		String adminEmail = this.adminEmailTextField.getText();
		  if (adminEmail == null || adminEmail.trim().isEmpty() || !adminEmail.equals("admin@nmc.nt")) {
		     error += "Please input the valid email address for admin" + "\n";
		  }
		  String password = this.passwordTextField.getText();
		  if (password == null || password.trim().isEmpty() || !password.equals("admin")) {
			  error +=  "Please input the valid password for admin" + "\n";
		  }
		  String pricesOfGuide = this.priceTextField.getText();
		  if (pricesOfGuide == null || pricesOfGuide.trim().isEmpty()) {
			  error += "Please input a valid price for the guides" + "\n";
		  }
		  String nrWeeks = this.nrOfWeeksTextField.getText();
		  if (nrWeeks == null || nrWeeks.trim().isEmpty()) {
			  error += "Please input a length for weeks" + "\n";
		  }
		  
		  if(error.equals("")) {
			  try {
				  
				  int priceGuide = Integer.parseInt(pricesOfGuide);
				  int nrOfWeeks = Integer.parseInt(this.nrOfWeeksTextField.getText());
				  LocalDate startDate = this.datePicker.getValue();
				 
				  if (successful(() -> ClimbSafeFeatureSet1Controller.setup(Date.valueOf(startDate), nrOfWeeks, priceGuide))) {
				        this.adminEmailTextField.setText("");
				        this.passwordTextField.setText("");
				        this.nrOfWeeksTextField.setText("");
				        this.priceTextField.setText("");
				        ClimbsafeFxmlView.getInstance().refresh();
				        ViewUtils.makePopupWindow("", "Season Started!");
				  }
				  
			  }catch (RuntimeException e) {
				  error += e.getMessage();
				  ViewUtils.showError(error);
			  }
		  }else {
			  ViewUtils.showError(error);
		  }
		  
	 }
}
