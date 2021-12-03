package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

public class viewAssignmentPageController {
	
	/**
	 * @author Mihail Calitoiu
	 * controller for the view assignment and manage trips page
	 * allows user to:
	 * a) initiate the assignment for all members / View assignments
	 * b) pay for a member’s trip
	 * c) start all trips for a specific week / Finish a member’s trip / Cancel a member’s trip
	 */
	
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
	@FXML
	private Button initiateButton1;
	ObservableList<TOAssignment> data;
	/**
	 * controller for initializing the fxml page
	 * @author Mihail Calitoiu
	 */
	@FXML
	  public void initialize() {
		TableColumn member = new TableColumn("Member Email");
		TableColumn guide = new TableColumn("Guide Email");
		TableColumn fWeek = new TableColumn("From Week");
		TableColumn tWeek = new TableColumn("To Week"); 
		TableColumn status = new TableColumn("Status"); 
		TableColumn authCode = new TableColumn("Auth Code"); 
		TableColumn refund = new TableColumn("Refund");
		TableColumn guideCost = new TableColumn("Guide Cost");
		TableColumn totalCost = new TableColumn("Total Cost");
		TableColumn prizeDiscount = new TableColumn("Discount");
		
		overviewTable.getColumns().addAll(member, guide, fWeek, tWeek, status, authCode, refund, guideCost, totalCost, prizeDiscount);
		data = getAss();
			   
		member.setCellValueFactory(new PropertyValueFactory<TOAssignment,String>("memberEmail"));
		guide.setCellValueFactory(new PropertyValueFactory<TOAssignment,String>("guideEmail"));
		fWeek.setCellValueFactory(new PropertyValueFactory<TOAssignment,String>("startWeek"));
		tWeek.setCellValueFactory(new PropertyValueFactory<TOAssignment,String>("endWeek"));
		status.setCellValueFactory(new PropertyValueFactory<TOAssignment,String>("status"));
		authCode.setCellValueFactory(new PropertyValueFactory<TOAssignment,String>("authorizationCode"));
		refund.setCellValueFactory(new PropertyValueFactory<TOAssignment,String>("refundedPercentageAmount"));
		guideCost.setCellValueFactory(new PropertyValueFactory<TOAssignment,String>("totalCostForGuide"));
		totalCost.setCellValueFactory(new PropertyValueFactory<TOAssignment,String>("totalCostForEquipment"));
		prizeDiscount.setCellValueFactory(new PropertyValueFactory<TOAssignment,String>("prizeDiscount"));  
	           
	        //Step 4: add data inside table
        overviewTable.setItems(data);
        
        overviewTable.addEventHandler(ClimbsafeFxmlView.REFRESH_EVENT, e -> overviewTable.setItems(getAss()));

        // register refreshable nodes
        ClimbsafeFxmlView.getInstance().registerRefreshEvent(overviewTable);
	}
	/**
	 * method called when start button is clicked and starts the trips
	 * @author Mihail Calitoiu
	 * @param event
	 */
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
		ClimbsafeFxmlView.getInstance().refresh();
	}
	/**
	 * method called when finish button is clicked and finishes respective trips
	 * @author Mihail Calitoiu
	 * @param event
	 */
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
	/**
	 * method called when cancel button is clicked and cancels the respective trips
	 * @author Mihail Calitoiu
	 * @param event
	 */
	// Event Listener on Button[#CancelButton].onAction
	@FXML
	public void cancelClicked(ActionEvent event) {
		var error = "";
		String memberEmail = this.emailTextField.getText();
		if (memberEmail == null || memberEmail.trim().isEmpty()) {
		    error += "Please input a valid member email" + "\n";
		}
		try {
			if(successful(() -> AssignmentController.cancelTrip(memberEmail))) {
				this.emailTextField.setText("");
			}
		}catch(RuntimeException e) {
			error += e.getMessage();
		}
	}
	/**
	 * method called when pay button is clicked and pays respective trips
	 * @author Mihail Calitoiu
	 * @param event
	 */
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
				this.codeTextField.setText("");
			}
		}catch(RuntimeException e) {
			error += e.getMessage();
		}
	}
	/**
	 * method called when refreshes button is clicked and refreshes the view assignment table
	 * @author Mihail Calitoiu
	 * @param event
	 */
	// Event Listener on Button[#initiateButton].onAction
	@FXML
	public void initiateClicked(ActionEvent event) {

		ClimbsafeFxmlView.getInstance().refresh();
		
	}
	/**
	 * method called when initiate trips button is clicked and initiates respective trips
	 * @author Mihail Calitoiu
	 * @param event
	 */
	@FXML
	public void initiateClicked1(ActionEvent event) {
		try {
			AssignmentController.initiateAssignmentProcess();
		} catch (InvalidInputException e) {
	
			e.printStackTrace();
		}
		ClimbsafeFxmlView.getInstance().refresh();
		
	}
	/**
	 * gets the assignments and returns an array of ToAssignment in ObservableList format
	 * @author Mihail Calitoiu
	 */
	ObservableList<TOAssignment> getAss(){
		
		ObservableList<TOAssignment> assignment = FXCollections.observableArrayList();
		List<TOAssignment> temp = ClimbSafeFeatureSet6Controller.getAssignments();
		
		for (TOAssignment current: temp) {
				assignment.add(current);
			
			
		}
		return assignment;
	
	}

}
