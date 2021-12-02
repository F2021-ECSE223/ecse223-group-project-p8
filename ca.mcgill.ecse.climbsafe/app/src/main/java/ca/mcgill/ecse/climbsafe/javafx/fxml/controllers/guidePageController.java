package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import javafx.fxml.FXML;
import ca.mcgill.ecse.climbsafe.controller.Utility;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import static ca.mcgill.ecse.climbsafe.javafx.fxml.controllers.ViewUtils.successful;
import java.util.List;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.TOAssignment;
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
	@FXML
	private TextField viewGuideEmail;
	@FXML
	private Label viewGuideName;
	@FXML
	private Label viewGuidePassword;
	@FXML
	private Label viewGuideContact;
	@FXML
	private Button searchGuideButton;
	@FXML
	private TableView<TOAssignment> guideAssignments;
	ObservableList<TOAssignment> assignments;


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
			
			else if(Utility.findGuideInSystem(sameEmailOfGuide) == null) {
				String inexistantGuideError="Guide does not exist in the system";
				ViewUtils.showError(inexistantGuideError);
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
	
		String emailtoDelete=deleteGuideEmail.getText();
	    if (emailtoDelete == null || emailtoDelete.trim().isEmpty()) {
	      ViewUtils.showError("Please input a valid email");
	    }
	    
	    else if (!Utility.guideInSystem(emailtoDelete)) ViewUtils.showError("Guide does not exist in system");
	    
	    else {
	    	  try {				 
	    		  if (successful(() -> ClimbSafeFeatureSet1Controller.deleteGuide(emailtoDelete))) {
	    			  deleteGuideEmail.setText("");
	    			  ClimbsafeFxmlView.getInstance().refresh();
	    			  ViewUtils.makePopupWindow("", "Successfully Deleted");
				      }
				  
			  }catch (RuntimeException e) {
				  ViewUtils.showError(e.getMessage());
			  }
	      }
		
	}
	
	// Event Listener on Button[#searchGuideButton].onAction
		@FXML
		public void searchGuideClicked(ActionEvent event) {
			String email=viewGuideEmail.getText();
			if(email == null || email.trim().isEmpty()) {
				String error="Please input a valid email to search for";
				ViewUtils.showError(error);
			}
			
			else if(Utility.findGuideInSystem(email) == null) {
				String error="Guide does not exist in the system";
				ViewUtils.showError(error);
			}
			
			else {
				if(Utility.findGuideInSystem(email) != null) {
					viewGuideName.setText(Utility.getGuideName(email));
					viewGuidePassword.setText(Utility.getGuidePassword(email));
					viewGuideContact.setText(Utility.getGuideContact(email));
					
					List<TOAssignment> guidesAss = ClimbSafeFeatureSet6Controller.getAssignments();
					assignments = FXCollections.observableArrayList();
					for (TOAssignment a : guidesAss) {
						if (a.getGuideEmail()!=null && a.getGuideEmail().equals(email) && a != null) {
								assignments.add(a);	
					   }
					}
					
					guideAssignments.setItems(assignments);
					guideAssignments.addEventHandler(ClimbsafeFxmlView.REFRESH_EVENT, e -> guideAssignments.setItems(assignments));
					ClimbsafeFxmlView.getInstance().registerRefreshEvent(guideAssignments);
					ClimbsafeFxmlView.getInstance().refresh();
				
				}
			}
			
		}
		
		@FXML
		  public void initialize() {
			TableColumn start = new TableColumn("Start Week");
			TableColumn end = new TableColumn("End Week");
			TableColumn membersAssigned = new TableColumn("Assigned Members");
				  
			guideAssignments.getColumns().addAll(start,end,membersAssigned);
			
			membersAssigned.setCellValueFactory(new PropertyValueFactory<TOAssignment,String>("memberName"));
			start.setCellValueFactory(new PropertyValueFactory<TOAssignment,String>("startWeek"));
			end.setCellValueFactory(new PropertyValueFactory<TOAssignment,String>("endWeek"));  
			

		}
		
		
		

}
